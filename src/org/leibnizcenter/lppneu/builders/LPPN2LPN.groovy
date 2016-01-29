package org.leibnizcenter.lppneu.builders

import commons.base.Formula
import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.language.*
import org.leibnizcenter.lppneu.components.lppetrinets.LPNet
import org.leibnizcenter.lppneu.components.lppetrinets.LPPlace
import org.leibnizcenter.lppneu.components.lppetrinets.LPTransition
import org.leibnizcenter.pneu.components.petrinet.NetInterface
import org.leibnizcenter.lppneu.components.position.AbstractPositionRef
import org.leibnizcenter.lppneu.components.position.AbstractTriple
import org.leibnizcenter.lppneu.components.position.FactualTriple
import org.leibnizcenter.pneu.components.petrinet.Arc
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Transition

@Log4j
class LPPN2LPN {

    static Net buildProgramNet(LPPNProgram source) {

        // reset net and maps
        Net net = new LPNet()

        // reduce it, decomposing compound formulas
        LPPNProgram reducedProgram = source.reduce()

        // create all logic rule nets
        for (logicRule in reducedProgram.logicRules) {
            Net subNet = buildLogicRuleNet(logicRule)
            net.include(subNet)
        }

        // create all causal rule nets
        for (causalRule in reducedProgram.causalRules) {
            Net subNet = buildCausalRuleNet(causalRule)
            net.include(subNet)
        }

        net
    }

    static Net buildLogicRuleNet(LogicRule logicRule) {
        if (logicRule.isRule()) {
            return buildImplicationNet(logicRule.body.formula, logicRule.head.formula, logicRule.biconditional)
        } else if (logicRule.isConstraint()) {
            return buildConstraintNet(logicRule.body.formula)
        } else if (logicRule.isFact()) {
            return buildFactNet(logicRule.head.formula)
        } else {
            throw new RuntimeException("Logic rule not recognized")
        }
    }

    static Net buildCausalRuleNet(CausalRule causalRule) {
        if (causalRule.isMechanism()) {
            return buildMechanismNet(causalRule.condition, causalRule.action)
        } else if (causalRule.isEventSeries()) {
            return buildEventSeriesNet(causalRule.action)
        } else {
            throw new RuntimeException("Causal rule not recognized")
        }
    }


    static Net buildConstraintNet(Formula<Situation> constraint) {
        Net net = new LPNet()

        Expression bodyExpression = Expression.build(constraint)

        net = new LPNet(function: new LPPlace(expression:
                Expression.build(bodyExpression, Operator.FORBIDS)))

        Net bodyNet = buildExpressionNet(bodyExpression)
        NetInterface bodyNetInterface = net.includeWithInterface(bodyNet)
        net.inputs += bodyNetInterface.placeInputs

        return net
    }

    static Net buildFactNet(Formula<Situation> fact) {
        Net net = buildExpressionNet(fact)
        return net
    }

    static Net buildImplicationNet(Formula<Situation> body, Formula<Situation> head, Boolean biconditional) {
        Net net

        Expression bodyExpression = Expression.build(body)
        Expression headExpression = Expression.build(head)

        if (!biconditional) {
            net = new LPNet(function: new LPPlace(expression:
                    Expression.build(bodyExpression, headExpression, Operator.IMPLIES)))
        } else {
            net = new LPNet(function: new LPPlace(expression:
                    Expression.build(bodyExpression, headExpression, Operator.DEFINES)))
        }

        log.trace("create implication net " + net)

        Net bodyNet = buildExpressionNet(body)
        NetInterface bodyNetInterface = net.includeWithInterface(bodyNet)
        Place pIn = bodyNetInterface.placeOutputs[0]
        net.inputs += bodyNetInterface.placeInputs

        Net headNet = buildExpressionNet(head)
        NetInterface headNetInterface = net.includeWithInterface(headNet)
        Place pOut = headNetInterface.placeOutputs[0]
        net.outputs += headNetInterface.placeInputs

        if (!biconditional) {
            Transition tImplication = net.createTransition(Operator.IMPLIES)
            net.createBridge(pIn, tImplication, pOut)

            // add contrapositive
            Net cBodyNet = buildExpressionNet(headExpression.negate())
            NetInterface cBodyNetInterface = net.includeWithInterface(cBodyNet)
            pIn = cBodyNetInterface.placeOutputs[0]
            net.inputs += cBodyNetInterface.placeInputs

            Net cHeadNet = buildExpressionNet(bodyExpression.negate())
            NetInterface cHeadNetInterface = net.includeWithInterface(cHeadNet)
            pOut = cHeadNetInterface.placeOutputs[0]
            net.outputs += cHeadNetInterface.placeInputs

            tImplication = net.createTransition(Operator.IMPLIES)
            net.createBridge(pIn, tImplication, pOut)

        } else { // biconditional
            Transition tDefinition = net.createTransition(Operator.DEFINES)
            net.createLinkBridge(pIn, tDefinition, pOut)
            net.inputs += headNetInterface.placeOutputs
            net.outputs += bodyNetInterface.placeInputs
        }

        return net
    }

    static Net buildSeqExpressionNet(Formula<Situation> formula) {

        Net net = new LPNet(function: new LPPlace(expression: Expression.build(formula)))

        List<Expression> seqInputs = []

        Net subNet
        NetInterface subNetInterface

        Place pIn, pOut

        for (input in formula.inputFormulas) {

            Expression inputExpression, subExpression, compoundExpression

            inputExpression = Expression.build(input)

            if (seqInputs.size() > 0) {
                subExpression = Expression.buildFromExpressions(seqInputs, Operator.SEQ)
                compoundExpression = Expression.build(inputExpression, subExpression, Operator.OCCURS_IN)

                subNet = buildEventConditionNet(compoundExpression.formula)
                subNetInterface = net.includeWithInterface(subNet)

                println subNetInterface
                pIn = subNetInterface.placeInputs[1]
                net.createBridge(pOut, pIn)

            } else {
                compoundExpression = Expression.build(
                        inputExpression,
                        Operator.OCCURS
                )

                subNet = buildEventConditionNet(compoundExpression.formula)
                subNetInterface = net.includeWithInterface(subNet)
                pOut = subNetInterface.placeOutputs[0]

                // take the inputs from the first subnet
                net.inputs += subNetInterface.placeInputs
            }

            seqInputs << inputExpression
        }

        if (subNet == null)
            throw new RuntimeException("You should not be here.")

        // take the inputs from the last subnet
        net.outputs += subNetInterface.placeOutputs

        return net

    }

    static Net buildProcessExpressionNet(Formula<Situation> formula) {

        if (formula.operator == Operator.SEQ) {
            return buildSeqExpressionNet(formula)
        }

        Net net = new LPNet(function: new LPPlace(expression: Expression.build(formula)))

        Place pOut = net.createPlace(Expression.build(formula))

        Transition tOut
        if (formula.operator == Operator.PAR) {
            tOut = net.createTransition(Operator.AND)
        } else if (formula.operator == Operator.ALT) {
            tOut = net.createTransition(Operator.XOR)
        } else if (formula.operator == Operator.OPT) {
            tOut = net.createTransition(Operator.OR)
        } else {
            throw new RuntimeException("Not yet implemented.")
        }

        net.createArc(tOut, pOut)
        net.outputs << pOut

        for (input in formula.inputFormulas) {

            Place pIn = net.createPlace(Expression.build(input))
            Transition tIn = net.createTransition(Expression.build(input).toOperation())
            net.createArc(pIn, tOut)
            net.createArc(tIn, pIn)

            Net subNet = buildExpressionNet(Expression.build(input, Operator.DUAL))
            NetInterface subNetInterface = net.includeWithInterface(subNet)

            net.createArc(subNetInterface.placeOutputs[0], tIn)
            net.inputs += subNetInterface.placeInputs
        }

        return net
    }

    static Net buildExpressionNet(Expression expression) {
        buildExpressionNet(expression.formula)
    }

    static Net buildExpressionNet(Formula<Situation> formula) {
        Net net = new LPNet(function: new LPPlace(expression: Expression.build(formula)))

        Place pOut = net.createPlace(Expression.build(formula))
        net.outputs << pOut

        if (formula.operator == Operator.POS) {
            net.inputs << pOut
        } else {
            // Logic expressions are only partially transformed into Petri Nets
            if (formula.operator.isLogicOperator()) {
                Transition t = net.createTransition(formula.operator)
                net.createArc(t, pOut)

//                if (formula.isAtomic()) {
                    for (input in formula.inputPorts) {
                        Place pIn = net.createPlace(Expression.build(input))
                        net.createArc(pIn, t)
                        net.inputs << pIn
                    }
//                } else {
//                    for (input in formula.inputFormulas) {
//                        Net subNet = buildExpressionNet(input)
//                        NetInterface subNetInterface = net.includeWithInterface(subNet)
//                        net.inputs += subNetInterface.placeInputs
//                    }
//                }

                // Process expressions are transformed into actual Petri Nets
            } else if (formula.operator == Operator.OCCURS_IN || formula.operator == Operator.OCCURS) {
                return buildEventConditionNet(formula)
            } else if (formula.operator.isBinaryProcessOperator()) {
                return buildProcessExpressionNet(formula)
            } else {
                throw new RuntimeException("Error in transforming this formula: "+formula)
            }
        }

        net
    }

    static Net buildEventConditionNet(Formula<Situation> formula) {
        Net net = new LPNet(function: new LPPlace(expression: Expression.build(formula)))

        if (formula.operator != Operator.OCCURS && formula.operator != Operator.OCCURS_IN) {
            throw new RuntimeException("Wrong operator: expecting OCCURS, or OCCURS_IN, found ${formula.operator}.")
        }

        Expression eventExpression = Expression.build(formula.inputPorts[0])

        Place pOut = net.createPlace(Expression.build(formula))
        Transition tOut = net.createTransition(eventExpression.toOperation())
        net.createArc(tOut, pOut)

        if (formula.inputPorts.size() > 2) {
            throw new RuntimeException("Fatal error: OCCURS_IN requires just two parameters.")
        }

        Net subNet = buildExpressionNet(Expression.build(eventExpression, Operator.DUAL))
        NetInterface subNetInterface = net.includeWithInterface(subNet)

        net.createArc(subNetInterface.placeOutputs[0], tOut)

        // I/O
        net.inputs << subNetInterface.placeInputs[0]
        net.outputs << pOut

        // if a catalyst context is specified about the occurrence
        // it is recoded a second output
        if (formula.inputPorts[1] != null) {
            Place pCatalyst = net.createPlace( Expression.build(formula.inputPorts[1]))
            net.createArc(pCatalyst, tOut)
            net.inputs << pCatalyst
        }

        net
    }

    static Net buildEventNet(Event event) {
        Net net = new LPNet(function: new LPTransition(operation: Operation.build(event)))
        Transition t = net.createTransition(Operation.build(event))

        net.inputs << t
        net.outputs << t

        net
    }

    static Net buildSeqOperationNet(Formula<Event> formula, AbstractPositionRef thisRef = null, AbstractPositionRef instanceRef = null) {
        Net net = new LPNet(function: new LPTransition(operation: Operation.build(formula)))

        Transition tIn, tOut

        int i = 0
        for (input in formula.inputFormulas) {

            // create subnet
            Net subNet = buildOperationNet(input, thisRef, instanceRef)
            NetInterface subNetInterface = net.includeWithInterface(subNet)

            // take the new input
            tIn = subNetInterface.transitionInputs[0]
            // anchor the previous output to the new input
            if (i > 0) { net.createBridge(tOut, tIn) }
            // take the new output
            tOut = subNetInterface.transitionOutputs[0]

            // the input of the first subnet is a general input
            if (i == 0) {
                net.inputs << tIn
                Transition t = net.createLinkTransition()
                net.createBridge(t, tIn)
            }

            i++
        }

        if (i == 0) throw new RuntimeException("You should not be here")

        // the output of the last subnet is a general output
        net.outputs << tOut
        Transition t = net.createLinkTransition()
        net.createBridge(tOut, t)

        return net
    }

    static Net buildParOperationNet(Formula<Event> formula, AbstractPositionRef thisRef = null, AbstractPositionRef instanceRef = null) {
        Net net = new LPNet(function: new LPTransition(operation: Operation.build(formula)))

        List<Place> pStartings = []
        List<Place> pEndings = []

        int i = 0
        for (input in formula.inputFormulas) {
            // create subnet
            Net subNet = buildOperationNet(input, thisRef, instanceRef)
            NetInterface subNetInterface = net.includeWithInterface(subNet)
            Place pIn = net.createLinkPlace()
            Place pOut = net.createLinkPlace()
            net.createArc(pIn, subNetInterface.transitionInputs[0])
            net.createArc(subNetInterface.transitionOutputs[0], pOut)
            pStartings << pIn
            pEndings << pOut
            i++
        }

        if (i == 0) throw new RuntimeException("You should not be here")

        Transition tIn = net.createTransitionNexus([], pStartings, [], [], [])
        Transition tOut = net.createTransitionNexus(pEndings, [], [], [], [])

        net.inputs << tIn
        net.outputs << tOut

        return net
    }

    static Net buildAltOperationNet(Formula<Event> formula, AbstractPositionRef thisRef = null, AbstractPositionRef instanceRef = null) {
        Net net = new LPNet(function: new LPTransition(operation: Operation.build(formula)))

        List<Transition> tStartings = []
        List<Transition> tEndings = []

        for (input in formula.inputFormulas) {
            // create subnet
            Net subNet = buildOperationNet(input, thisRef, instanceRef)
            NetInterface subNetInterface = net.includeWithInterface(subNet)
            tStartings << subNetInterface.transitionInputs[0]
            tEndings << subNetInterface.transitionOutputs[0]
        }

        Place pIn = net.createPlaceNexus([], tStartings, [], [], [])
        Place pOut = net.createPlaceNexus(tEndings, [], [], [], [])

        Transition tIn = net.createLinkTransition()
        net.createArc(tIn, pIn)
        Transition tOut = net.createLinkTransition()
        net.createArc(pOut, tOut)

        net.inputs << tIn
        net.outputs << tOut

        return net
    }

    static Net buildOperationNet(Operation operation, AbstractPositionRef thisRef = null, AbstractPositionRef instanceRef = null) {
        buildOperationNet(operation.formula, thisRef, instanceRef)
    }

    static Net buildOperationNet(Formula<Event> formula, AbstractPositionRef thisRef = null, AbstractPositionRef instanceRef = null) {

        if (formula.operator.isUnary()) {
            if (!formula.isAtomic()) {
                return buildOperationNet(formula.inputFormulas[0], thisRef, instanceRef)
            } else {
                if (formula.operator == Operator.POS) {
                    return buildEventNet(formula.inputPorts[0])
                } else if (formula.operator == Operator.NEG) {
                    return buildEventNet(formula.inputPorts[0].negate())
                } else if (formula.operator == Operator.NULL) {
                    return buildEventNet(formula.inputPorts[0].nullify())
                } else if (formula.operator == Operator.POS_THIS) {
                    return buildEventNet(Event.build(thisRef, Operator.POS))
                } else if (formula.operator == Operator.NEG_THIS) {
                    return buildEventNet(Event.build(thisRef, Operator.NEG))
                } else if (formula.operator == Operator.NULL_THIS) {
                    return buildEventNet(Event.build(thisRef, Operator.NULL))
                } else if (formula.operator == Operator.NULL_INSTANCE) {
                    return buildEventNet(Event.build(instanceRef, Operator.POS))
                } else if (formula.operator == Operator.NEG_INSTANCE) {
                    return buildEventNet(Event.build(instanceRef, Operator.NEG))
                } else if (formula.operator == Operator.NULL_INSTANCE) {
                    return buildEventNet(Event.build(instanceRef, Operator.NULL))
                } else {
                    throw new RuntimeException("Not yet implemented")
                }
            }
        } else {
            if (formula.isAtomic()) {
                // TODO: check properly, I think it may be wrong
                return buildEventNet(formula.inputPorts[0])
            } else {
                if (formula.operator == Operator.SEQ) {
                    return buildSeqOperationNet(formula, thisRef, instanceRef)
                } else if (formula.operator == Operator.PAR) {
                    return buildParOperationNet(formula, thisRef, instanceRef)
                } else if (formula.operator == Operator.ALT) {
                    return buildAltOperationNet(formula, thisRef, instanceRef)
                } else {
                    throw new RuntimeException("Not yet implemented")
                }
            }
        }
    }

    static Net buildEventSeriesNet(Operation action, AbstractPositionRef thisRef = null, AbstractPositionRef instanceRef = null) {

        return buildOperationNet(action.formula, thisRef, instanceRef)

    }

    static Net buildMechanismNet(Expression condition, Operation action) {

        Expression antecedent

        // transform condition action rule in event condition action
        // i.e. when the given condition is not described by an event IN a context
        // consider the "creation" of such condition as the event.
        if (condition.formula.operator != Operator.OCCURS_IN) {
            antecedent = Expression.build(condition, Operator.OCCURS)
        } else {
            antecedent = condition
        }

        Net net = new LPNet()

        // create antecedent
        Net triggerNet = buildExpressionNet(antecedent)
        NetInterface triggerNetInterface = net.includeWithInterface(triggerNet)

        Place pIn = triggerNetInterface.placeOutputs[0]

        // create consequent
        Expression consequent = action.toExpression()
        Place pOut = net.createPlace(consequent)

        // define net function as causal dependency between antecedent and consequent
        net.function = new LPPlace(expression: Expression.build(antecedent, consequent, Operator.CAUSES))

        Net actionNet = buildOperationNet(action.formula)
        NetInterface actionNetInterface = net.includeWithInterface(actionNet)

        net.createArc(pIn, actionNetInterface.transitionInputs[0])
        net.createArc(actionNetInterface.transitionOutputs[0], pOut)

        // TODO I/O for causal dependency
        // the input is pluasibly the pIn
        // is the output pOut?

        net.inputs << pIn
        net.outputs << pOut

        return net
    }

    // propositional triple with strong and default negations
    // notation: operation => outcome
    // neg(p) => neg p
    // neg(neg p) => p
    // null(p) => null p
    // null(neg p) => null p
    // pos(p) => p
    // pos(neg p) => neg p

    static Net buildTripleNet(Expression expression) {
        Net net = new LPNet(function: new LPPlace(expression: Expression.build(expression, Operator.TRIPLE)))

        AbstractTriple pTriple = FactualTriple.build(expression)
        LPPlace pPlace = new LPPlace(expression: pTriple.positive.ref.label)
        LPPlace negpPlace = new LPPlace(expression: pTriple.negative.ref.label)
        LPPlace notpPlace = new LPPlace(expression: pTriple.nullified.ref.label)
        net.placeList += [pPlace, negpPlace, notpPlace]

        LPTransition pospOperator = new LPTransition(operation: pTriple.positive.toOperation())
        LPTransition negnegpOperator = new LPTransition(operation: pTriple.positive.toOperation())
        // double negation = assertion
        LPTransition negpOperator = new LPTransition(operation: pTriple.negative.toOperation())
        LPTransition posnegpOperator = new LPTransition(operation: pTriple.negative.toOperation())
        LPTransition notpOperator = new LPTransition(operation: pTriple.nullified.toOperation())
        LPTransition notnegpOperator = new LPTransition(operation: pTriple.nullified.toOperation())

        for (t in [pospOperator, posnegpOperator, negpOperator, negnegpOperator, notpOperator, notnegpOperator]) {
            net.transitionList << t
        }

        pTriple.positivePlace = pPlace
        pTriple.negativePlace = negpPlace
        pTriple.nullPlace = notpPlace

        pTriple.posTransitionList += [pospOperator, negnegpOperator]
        pTriple.negTransitionList += [negpOperator, posnegpOperator]
        pTriple.nullTransitionList += [notpOperator, notnegpOperator]

        net.arcList = Arc.buildArcs(pPlace, negpOperator, negpPlace) +
                Arc.buildArcs(negpPlace, negnegpOperator, pPlace) +
                Arc.buildArcs(notpPlace, pospOperator, pPlace) +
                Arc.buildArcs(notpPlace, posnegpOperator, negpPlace) +
                Arc.buildArcs(pPlace, notpOperator, notpPlace) +
                Arc.buildArcs(negpPlace, notnegpOperator, notpPlace)

        net
    }

//    static Net buildFirstSquare(Expression initiationReference,
//                                Expression expirationReference,
//                                Expression successReference,
//                                Expression failureReference,
//                                Operation successAction,
//                                Operation failureReference)


}
