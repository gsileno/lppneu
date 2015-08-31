package org.leibnizcenter.lppneu.builders

import commons.base.Formula
import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.language.*
import org.leibnizcenter.lppneu.components.lppetrinets.LPNet
import org.leibnizcenter.lppneu.components.lppetrinets.LPPlace
import org.leibnizcenter.lppneu.components.lppetrinets.LPTransition
import org.leibnizcenter.lppneu.components.mapper.Mapper
import org.leibnizcenter.lppneu.components.position.AbstractPositionRef
import org.leibnizcenter.lppneu.components.position.AbstractTriple
import org.leibnizcenter.lppneu.components.position.FactualTriple
import org.leibnizcenter.pneu.components.petrinet.Arc
import org.leibnizcenter.pneu.components.petrinet.ArcType
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Node
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

        Net bodyNet = buildExpressionNet(body)
        net.include(bodyNet)
        log.trace("attaching body net " + bodyNet)

        LPPlace pIn = (LPPlace) bodyNet.outputs[0]

        return net
    }

    static Net buildFactNet(Formula<Situation> fact) {
        Net net = buildExpressionNet(fact)
        return net
    }

    static Net buildImplicationNet(Formula<Situation> body, Formula<Situation> head, Boolean biconditional) {
        Net net

        Expression headExpression = Expression.build(head)
        Expression bodyExpression = Expression.build(body)

        if (!biconditional) {
            net = new LPNet(function: new LPPlace(expression:
                    Expression.build(bodyExpression, headExpression, Operator.IMPLIES)))
        } else {
            net = new LPNet(function: new LPPlace(expression:
                    Expression.build(bodyExpression, headExpression, Operator.DEFINES)))
        }

        log.trace("create implication net " + net)

        Net bodyNet = buildExpressionNet(body)
        net.include(bodyNet)
        log.trace("attaching body net " + bodyNet)

        LPPlace pIn = (LPPlace) bodyNet.outputs[0]
        log.trace("input place " + pIn)

        Net headNet = buildExpressionNet(head)
        net.include(headNet)
        log.trace("attaching head net " + headNet)

        LPPlace pOut = (LPPlace) headNet.outputs[0]
        log.trace("contrapositive: output place " + pOut)

        if (!biconditional) {
            LPTransition tImplication = new LPTransition(operator: Operator.IMPLIES)
            net.transitionList += [tImplication]
            net.arcList += Arc.buildArcs(pIn, tImplication, pOut)

            // add contrapositive

            bodyNet = buildExpressionNet(headExpression.negate())
            net.include(bodyNet)
            log.trace("contrapositive: attaching body net " + bodyNet)
            pIn = (LPPlace) bodyNet.outputs[0]
            log.trace("contrapositive: input place " + pIn)

            headNet = buildExpressionNet(bodyExpression.negate())
            net.include(headNet)
            log.trace("contrapositive: attaching head net " + headNet)
            pOut = (LPPlace) headNet.outputs[0]
            log.trace("contrapositive: output place " + pOut)

            tImplication = new LPTransition(operator: Operator.IMPLIES)
            net.transitionList += [tImplication]
            net.arcList += Arc.buildArcs(pIn, tImplication, pOut)

        } else { // biconditional
            LPTransition tNexus = new LPTransition(operator: Operator.IMPLIES, link: true)
            LPTransition tNexus2 = new LPTransition(operator: Operator.IMPLIES, link: true)
            net.transitionList += [tNexus, tNexus2]
            net.arcList << Arc.buildArc((Place) pIn, (Transition) tNexus, ArcType.LINK)
            net.arcList << Arc.buildArc((Transition) tNexus, (Place) pOut, ArcType.LINK)
            net.arcList << Arc.buildArc((Place) pOut, (Transition) tNexus2, ArcType.LINK)
            net.arcList << Arc.buildArc((Transition) tNexus2, (Place) pIn, ArcType.LINK)
        }

        return net
    }

    static Net buildSeqExpressionNet(Formula<Situation> formula) {

        Net net = new LPNet(function: new LPPlace(expression: Expression.build(formula)))

        List<Expression> seqInputs = []

        Net subNet

        for (input in formula.inputFormulas) {

            Expression inputExpression, subExpression, compoundExpression

            inputExpression = Expression.build(input)

            if (seqInputs.size() > 0) {
                subExpression = Expression.buildFromExpressions(seqInputs, Operator.SEQ)
                compoundExpression = Expression.build(
                        inputExpression,
                        subExpression,
                        Operator.OCCURS_IN
                )

                subNet = buildEventConditionNet(compoundExpression.formula)
                net.include(subNet)

            } else {
                compoundExpression = Expression.build(
                        inputExpression,
                        Operator.OCCURS
                )

                subNet = buildEventConditionNet(compoundExpression.formula)
                net.include(subNet)

                // take the inputs from the first subnet
                net.inputs += subNet.inputs
            }

            seqInputs << inputExpression
        }

        // take the inputs from the last subnet
        net.outputs += subNet.outputs

        return net

    }

//    static Net buildSeqExpressionNet(Formula<Situation> formula) {
//
//        Net net = new LPNet(function: new LPlace(expression: Expression.build(formula)))
//
//        LPlace pOut
//        LTransition tIn
//        List<Expression> seqInputs = []
//        Expression seqExpression
//
//        for (input in formula.inputFormulas) {
//            tIn = new LTransition(operation: Expression.build(input).toOperation())
//
//            if (pOut) net.arcList += Arc.buildArc((Place) pOut, (Transition) tIn)
//
//            net.transitionList << tIn
//            Net subNetIn = buildExpressionNet(Expression.buildFromExpressions(
//                    [Expression.build(input, Operator.NEG), Expression.build(input, Operator.NULL)], Operator.OR
//            ).formula)
//            net.include(subNetIn)
//
//            net.arcList += Arc.buildArc((Place) subNetIn.outputs[0], tIn)
//            net.inputs += subNetIn.inputs
//
//            Expression expression = Expression.build(input)
//            seqInputs << expression
//            seqExpression = Expression.buildFromExpressions(seqInputs, Operator.SEQ)
//
//            if (seqInputs.size() > 1) {
//                LPlace pIn = new LPlace(expression: expression)
//                LPlace pBridge = new LPlace(expression: Expression.build(seqExpression.formula))
//                pOut = new LPlace(expression: Expression.buildFromExpressions([seqExpression, expression], Operator.AND))
//                net.placeList += [pIn, pBridge, pOut]
//
//                LTransition tBridge = new LTransition(operator: Operator.AND)
//                net.transitionList += [tBridge]
//
//                net.arcList += Arc.buildArc((Transition) tIn, (Place) pBridge)
//                net.arcList += Arc.buildArc((Place) pBridge, (Transition) tBridge)
//                net.arcList += Arc.buildArc((Place) pIn, (Transition) tBridge)
//                net.arcList += Arc.buildArc((Transition) tBridge, (Place) pOut)
//            } else {
//                pOut = new LPlace(expression: expression)
//                net.placeList += [pOut]
//                net.arcList += Arc.buildArc((Transition) tIn, (Place) pOut)
//            }
//
//        }
//
//        if (pOut == null) {
//            throw new RuntimeException()
//        }
//
//        net.outputs += [pOut]
//        return net
//
//    }

    static Net buildProcessExpressionNet(Formula<Situation> formula) {

        if (formula.operator == Operator.SEQ) {
            return buildSeqExpressionNet(formula)
        }

        Net net = new LPNet(function: new LPPlace(expression: Expression.build(formula)))

        LPPlace pOut = new LPPlace(expression: Expression.build(formula))

        LPTransition tOut
        if (formula.operator == Operator.PAR) {
            tOut = new LPTransition(operator: Operator.AND)
        } else if (formula.operator == Operator.ALT) {
            tOut = new LPTransition(operator: Operator.XOR)
        } else if (formula.operator == Operator.OPT) {
            tOut = new LPTransition(operator: Operator.OR)
        } else {
            throw new RuntimeException()
        }

        net.placeList << pOut
        net.transitionList << tOut
        net.arcList += Arc.buildArc((Transition) tOut, (Place) pOut)

        net.outputs += [pOut]

        for (input in formula.inputFormulas) {

            LPPlace pIn = new LPPlace(expression: Expression.build(input))
            LPTransition tIn = new LPTransition(operation: Expression.build(input).toOperation())
            net.placeList << pIn
            net.transitionList << tIn
            net.arcList += Arc.buildArc((Place) pIn, (Transition) tOut)
            net.arcList += Arc.buildArc((Transition) tIn, (Place) pIn)

            Net subNet = buildExpressionNet(Expression.buildFromExpressions(
                    [Expression.build(input, Operator.NEG), Expression.build(input, Operator.NULL)], Operator.OR
            ).formula)
            net.include(subNet)

            net.arcList += Arc.buildArc((Place) subNet.outputs[0], tIn)
            net.inputs += subNet.inputs
        }

        return net
    }

    static Net buildExpressionNet(Expression expression) {
        buildExpressionNet(expression.formula)
    }

    static Net buildExpressionNet(Formula<Situation> formula) {
        Net net = new LPNet(function: new LPPlace(expression: Expression.build(formula)))

        LPPlace pOut = new LPPlace(expression: Expression.build(formula))
        net.placeList += [pOut]
        net.outputs += [pOut]

        if (formula.operator != Operator.POS) {
            // Logic expressions are only partially transformed into Petri Nets
            if (formula.operator.isLogicOperator()) {
                LPTransition t = new LPTransition(operator: formula.operator)
                net.transitionList += [t]
                net.arcList += Arc.buildArc(t, pOut)

                for (input in formula.inputPorts) {
                    LPPlace pIn = new LPPlace(expression: Expression.build(input))
                    net.placeList += [pIn]
                    net.arcList += Arc.buildArc(pIn, t)
                    net.inputs += [pIn]
                }
                // Process expressions are transformed into actual Petri Nets
            } else if (formula.operator == Operator.OCCURS_IN || formula.operator == Operator.OCCURS) {
                return buildEventConditionNet(formula)
            } else if (formula.operator.isBinaryProcessOperator()) {
                return buildProcessExpressionNet(formula)
            } else {
                throw new RuntimeException()
            }
        } else {
            net.inputs += [pOut]
        }

        net
    }

    static Net buildEventConditionNet(Formula<Situation> formula) {
        Net net = new LPNet(function: new LPPlace(expression: Expression.build(formula)))

        if (formula.operator != Operator.OCCURS && formula.operator != Operator.OCCURS_IN) {
            throw new RuntimeException("Wrong operator: expecting OCCURS, or OCCURS_IN, found ${formula.operator}.")
        }

        Expression eventExpression = Expression.build(formula.inputPorts[0])

        LPPlace pOut
        LPTransition tOut
        pOut = new LPPlace(expression: Expression.build(formula))
        tOut = new LPTransition(operation: eventExpression.toOperation())

        net.placeList += [pOut]
        net.transitionList += [tOut]
        net.arcList += Arc.buildArc((Transition) tOut, (Place) pOut)

        // if a catalyst context is specified about the occurrence
        if (formula.inputPorts[1] != null) {
            Expression conditionExpression = Expression.build(formula.inputPorts[1])
            LPPlace pCatalyst = new LPPlace(expression: conditionExpression)
            net.arcList += Arc.buildArc((Place) pCatalyst, (Transition) tOut)
            net.placeList += [pCatalyst]
        }

        Net subNet = buildExpressionNet(Expression.buildFromExpressions(
                [Expression.build(eventExpression.formula, Operator.NEG),
                 Expression.build(eventExpression.formula, Operator.NULL)],
                Operator.OR
        ).formula)

        net.include(subNet)
        net.arcList += Arc.buildArc((Place) subNet.outputs[0], (Transition) tOut)

        // I/O
        net.inputs += subNet.inputs
        net.outputs += [pOut]

        net
    }

    static Net buildEventNet(Event event) {
        Net net = new LPNet(function: new LPTransition(operation: Operation.build(event)))
        LPTransition t = new LPTransition(operation: Operation.build(event))
        net.transitionList += [t]

        // I/O
        net.inputs << t
        net.outputs << t

        net
    }

    static Net buildSeqOperationNet(Formula<Event> formula, AbstractPositionRef thisRef = null, AbstractPositionRef instanceRef = null) {
        Net net = new LPNet(function: new LPTransition(operation: Operation.build(formula)))
        LPTransition t
        LPPlace p

        // preparatory nodes
        t = new LPTransition(link: true)
        p = new LPPlace(link: true)

        net.inputs << t
        net.transitionList << t
        net.placeList << p

        int i = 0
        for (input in formula.inputFormulas) {

            // create subnet
            Net subNet = buildOperationNet(input, thisRef, instanceRef)
            net.include(subNet)

            // anchoring
            net.arcList += Arc.buildArcs((Transition) t, (Place) p, (Transition) subNet.inputs[0])
            t = (LPTransition) subNet.outputs[0]

            // synchronization place
            p = new LPPlace(link: true)
            net.placeList << p
        }

        // output transition
        LPTransition tOut = new LPTransition(link: true)
        net.transitionList << tOut

        // final anchoring
        net.arcList += Arc.buildArcs((Transition) t, (Place) p, (Transition) tOut)

        net.outputs << tOut

        return net
    }

    static Net buildParOperationNet(Formula<Event> formula, AbstractPositionRef thisRef = null, AbstractPositionRef instanceRef = null) {
        Net net = new LPNet(function: new LPTransition(operation: Operation.build(formula)))
        LPTransition tIn = new LPTransition(link: true)
        LPTransition tOut = new LPTransition(link: true)
        net.transitionList += [tIn, tOut]

        int i = 0
        for (input in formula.inputFormulas) {
            // preparatory place
            LPPlace pIn = new LPPlace(link: true)
            net.placeList << pIn

            // create subnet
            Net subNet = buildOperationNet(input, thisRef, instanceRef)
            net.include(subNet)

            // synchronization place
            LPPlace pOut = new LPPlace(link: true)
            net.placeList << pOut

            // anchoring
            net.arcList += Arc.buildArcs((Transition) tIn, (Place) pIn, (Transition) subNet.inputs[0])
            net.arcList += Arc.buildArcs((Transition) subNet.outputs[0], (Place) pOut, (Transition) tOut)
            i++
        }

        net.inputs << tIn
        net.outputs << tOut

        return net
    }

    static Net buildAltOperationNet(Formula<Event> formula, AbstractPositionRef thisRef = null, AbstractPositionRef instanceRef = null) {
        Net net = new LPNet(function: new LPTransition(operation: Operation.build(formula)))

        LPTransition tIn = new LPTransition(link: true)
        LPTransition tOut = new LPTransition(link: true)
        LPPlace pIn = new LPPlace(link: true)
        LPPlace pOut = new LPPlace(link: true)

        net.transitionList += [tIn, tOut]
        net.placeList += [pIn, pOut]
        net.arcList << Arc.buildArc(tIn, pIn)

        for (input in formula.inputFormulas) {
            // create subnet
            Net subNet = buildOperationNet(input, thisRef, instanceRef)
            net.include(subNet)

            // anchoring
            net.arcList << Arc.buildArc((Place) pIn, (Transition) subNet.inputs[0])
            net.arcList << Arc.buildArc((Transition) subNet.outputs[0], (Place) pOut)
        }

        net.arcList << Arc.buildArc(pOut, tOut)

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
                // TODO: check properly, it may be wrong I think
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
        net.include(triggerNet)

        LPPlace pIn = (LPPlace) triggerNet.outputs[0]

        // create consequent
        Expression consequent = action.toExpression()
        LPPlace pOut = new LPPlace(expression: consequent)
        net.placeList += [pOut]

        // define net function as causal dependency between antecedent and consequent
        net.function = new LPPlace(expression: Expression.build(antecedent, consequent, Operator.CAUSES))

        Net actionNet = buildOperationNet(action.formula)
        net.include(actionNet)

        // anchoring
        net.arcList += Arc.buildArc((Place) pIn, (Transition) actionNet.inputs[0])
        net.arcList += Arc.buildArc((Transition) actionNet.outputs[0], (Place) pOut)

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
}
