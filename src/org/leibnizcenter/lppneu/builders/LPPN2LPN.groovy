package org.leibnizcenter.lppneu.builders

import commons.base.Formula
import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.language.*
import org.leibnizcenter.lppneu.components.petrinets.LPlace
import org.leibnizcenter.lppneu.components.petrinets.LTransition
import org.leibnizcenter.pneu.components.petrinet.Arc
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Transition

@Log4j
class LPPN2LPN {

    static Net convert(Program program) {
        Program reducedProgram = program.reduce()

        Net net = new Net()

        for (logicRule in reducedProgram.logicRules) {
            Net subNet

            if (logicRule.isRule()) {
                subNet = buildImplicationNet(logicRule.body.formula, logicRule.head.formula)
            } else if (logicRule.isConstraint()) {
                subNet = buildConstraintNet(logicRule.body.formula)
            } else if (logicRule.isFact()) {
                subNet = buildFactNet(logicRule.head.formula)
            } else {
                throw new RuntimeException()
            }

//            // anchor subnet to general index of places
//            for (p in subNet.placeList) {
//                if (placeNetMap[(LPlace) p] == null) placeNetMap[(LPlace) p] = []
//                placeNetMap[(LPlace) p] << subNet
//            }
//
//            // anchor subnet to general index of transitions
//            for (t in subNet.transitionList) {
//                if (transitionNetMap[(LTransition) t] == null) transitionNetMap[(LTransition) t] = []
//                transitionNetMap[(LTransition) t] << subNet
//            }

            net.include(subNet)
        }

        for (causalRule in reducedProgram.causalRules) {

            Net subNet

            if (causalRule.isMechanism()) {
                subNet = buildMechanismNet(causalRule.condition, causalRule.action)
            } else {
                throw new RuntimeException() 
            }

            net.include(subNet)
        }

        net
    }

    static Net buildConstraintNet(Formula<Situation> constraint) {
        Net net = new Net()
        // TODO
        return net
    }

    static Net buildFactNet(Formula<Situation> fact) {
        Net net = buildExpressionNet(fact)
        return net
    }

    static Net buildImplicationNet(Formula<Situation> body, Formula<Situation> head) {

        Net net = new Net()

        Net bodyNet = buildExpressionNet(body)
        net.include(bodyNet)
        LPlace pIn = (LPlace) bodyNet.outputs[0]

        Net headNet = buildExpressionNet(head)
        net.include(headNet)
        LPlace pOut = (LPlace) headNet.outputs[0]

        LTransition tImplication = new LTransition(operator: Operator.IMPLIES)
        net.transitionList += [tImplication]
        net.arcList += Arc.buildArcs(pIn, tImplication, pOut)

        return net
    }

    static Net buildSeqNet(Formula<Situation> formula) {

        Net net = new Net()

        LPlace pOut
        LTransition tIn
        List<Expression> seqInputs = []
        Expression seqExpression

        for (input in formula.inputFormulas) {
            tIn = new LTransition(operation: Expression.build(input).toOperation())

            if (pOut) net.arcList += Arc.buildArc((Place) pOut, (Transition) tIn)

            net.transitionList << tIn
            Net subNetIn = buildExpressionNet(Expression.buildFromExpressions(
                    [Expression.build(input, Operator.NEG), Expression.build(input, Operator.NULL)], Operator.OR
            ).formula)
            net.include(subNetIn)

            net.arcList += Arc.buildArc((Place) subNetIn.outputs[0], tIn)
            net.inputs += subNetIn.inputs

            Expression expression = Expression.build(input)
            seqInputs << expression
            seqExpression = Expression.buildFromExpressions(seqInputs, Operator.SEQ)

            if (seqInputs.size() > 1) {
                LPlace pIn = new LPlace(expression: expression)
                LPlace pBridge = new LPlace(expression: Expression.build(seqExpression.formula))
                pOut = new LPlace(expression: Expression.buildFromExpressions([seqExpression, expression], Operator.AND))
                net.placeList += [pIn, pBridge, pOut]

                LTransition tBridge = new LTransition(operator: Operator.AND)
                net.transitionList += [tBridge]

                net.arcList += Arc.buildArc((Transition) tIn, (Place) pBridge)
                net.arcList += Arc.buildArc((Place) pBridge,(Transition) tBridge)
                net.arcList += Arc.buildArc((Place) pIn,(Transition) tBridge)
                net.arcList += Arc.buildArc((Transition) tBridge, (Place) pOut)
            } else {
                pOut = new LPlace(expression: expression)
                net.placeList += [pOut]
                net.arcList += Arc.buildArc((Transition) tIn, (Place) pOut)
            }

        }

        if (pOut == null) { throw new RuntimeException()  }

        net.outputs += [pOut]
        return net

    }

    static Net buildProcessNet(Formula<Situation> formula) {

        if (formula.operator == Operator.SEQ) {
            return buildSeqNet(formula)
        }

        Net net = new Net()

        LPlace pOut = new LPlace(expression: Expression.build(formula))

        LTransition tOut
        if (formula.operator == Operator.PAR) {
            tOut = new LTransition(operator: Operator.AND)
        } else if (formula.operator == Operator.ALT) {
            tOut = new LTransition(operator: Operator.XOR)
        } else if (formula.operator == Operator.OPT) {
            tOut = new LTransition(operator: Operator.OR)
        } else {
            throw new RuntimeException() 
        }

        net.placeList << pOut
        net.transitionList << tOut
        net.arcList += Arc.buildArc((Transition) tOut, (Place) pOut)

        net.outputs += [pOut]

        for (input in formula.inputFormulas) {

            LPlace pIn = new LPlace(expression: Expression.build(input))
            LTransition tIn = new LTransition(operation: Expression.build(input).toOperation())
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
        Net net = new Net()

        LPlace pOut = new LPlace(expression: Expression.build(formula))
        net.placeList += [pOut]
        net.outputs += [pOut]

        if (formula.operator != Operator.POS) {
            // Logic expressions are only partially transformed into Petri Nets
            if (formula.operator.isLogicOperator()) {
                LTransition t = new LTransition(operator: formula.operator)
                net.transitionList += [t]
                net.arcList += Arc.buildArc(t, pOut)

                for (input in formula.inputPorts) {
                    LPlace pIn = new LPlace(expression: Expression.build(input))
                    net.placeList += [pIn]
                    net.arcList += Arc.buildArc(pIn, t)
                    net.inputs += [pIn]
                }
            // Process expressions are transformed into actual Petri Nets
            } else if (formula.operator == Operator.IN) {
                return buildEventConditionNet(formula)
            } else if (formula.operator.isBinaryProcessOperator()) {
                return buildProcessNet(formula)
            } else {
                throw new RuntimeException() 
            }
        } else {
            net.inputs += [pOut]
        }

        net
    }

    static Net buildEventConditionNet(Formula<Situation> formula) {
        Net net = new Net()

        Expression eventExpression = Expression.build(formula.inputPorts[0])

        LPlace pOut
        LTransition tOut
        pOut = new LPlace(expression: Expression.build(formula))
        tOut = new LTransition(operation: eventExpression.toOperation())

        net.placeList += [pOut]
        net.transitionList += [tOut]
        net.arcList += Arc.buildArc((Transition) tOut, (Place) pOut)

        // if a catalyst context is specified about the occurrence
        if (formula.inputPorts[1] != null) {
            Expression conditionExpression = Expression.build(formula.inputPorts[1])
            LPlace pCatalyst = new LPlace(expression: conditionExpression)
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

//        if (formula.isMereCondition()) {
//            Expression targetExpression = formula.condition
//            pOut = new LPlace(expression: targetExpression)
//            tOut = new LTransition(operation: targetExpression.toOperation())
//            net.placeList += [pOut]
//            net.transitionList += [tOut]
//            net.arcList += Arc.buildArc((Transition) tOut, (Place) pOut)
//
//            Net subNet = buildExpressionNet(Expression.buildFromExpressions(
//                    [Expression.build(targetExpression.formula, Operator.NEG),
//                     Expression.build(targetExpression.formula, Operator.NULL)],
//                    Operator.OR
//            ).formula)
//            net.include(subNet)
//            net.arcList += Arc.buildArc((Place) subNet.outputs[0], (Transition) tOut)
//        } else if (eventCondition.isMereEvent()) {
//            Expression targetExpression = Expression.build(eventCondition.event.toSituation())
//            pOut = new LPlace(expression: targetExpression)
//            tOut = new LTransition(operation: Operation.build(eventCondition.event))
//            net.placeList += [pOut]
//            net.transitionList += [tOut]
//            net.arcList += Arc.buildArc((Transition) tOut, (Place) pOut)
//
//            Net subNet = buildExpressionNet(Expression.buildFromExpressions(
//                    [Expression.build(targetExpression.formula, Operator.NEG),
//                     Expression.build(targetExpression.formula, Operator.NULL)],
//                    Operator.OR
//            ).formula)
//            net.include(subNet)
//            net.arcList += Arc.buildArc((Place) subNet.outputs[0], (Transition) tOut)
//        } else if (formula.isEventCondition()) {

    }

    static Net buildEventNet(Event event) {
        Net net = new Net()
        LTransition t = new LTransition(event: event)
        net.transitionList += [t]

        // I/O
        net.inputs.add(t)
        net.outputs.add(t)

        net
    }

    static Net buildSeqOperationNet(Formula<Event> formula) {
        Net net = new Net()
        LTransition t; LPlace p

        // preparatory nodes
        t = new LTransition()
        p = new LPlace()

        net.transitionList << t
        net.placeList << p

        for (input in formula.inputFormulas) {

            // create subnet
            Net subNet = buildOperationNet(input)
            net.include(subNet)

            // anchoring
            net.arcList += Arc.buildArcs((Transition) t, (Place) p, (Transition) subNet.inputs[0])
            t = (LTransition) subNet.outputs[0]

            // synchronization place
            p = new LPlace()
            net.placeList << p
        }

        // output transition
        LTransition tOut = new LTransition()
        net.transitionList << tOut

        // final anchoring
        net.arcList += Arc.buildArcs((Transition) t, (Place) p, (Transition) tOut)

        return net
    }

    static Net buildParOperationNet(Formula<Event> formula) {
        Net net = new Net()
        LTransition tIn = new LTransition()
        LTransition tOut = new LTransition()
        net.transitionList += [tIn, tOut]

        for (input in formula.inputFormulas) {
            // preparatory place
            LPlace pIn = new LPlace()
            net.placeList << pIn

            // create subnet
            Net subNet = buildOperationNet(input)
            net.include(subNet)

            // synchronization place
            LPlace pOut = new LPlace()
            net.placeList << pOut

            // anchoring
            net.arcList += Arc.buildArcs((Transition) tIn, (Place) pIn, (Transition) subNet.inputs[0])
            net.arcList += Arc.buildArcs((Transition) subNet.outputs[0], (Place) pOut, (Transition) tOut)
        }
        return net
    }

    static Net buildAltOperationNet(Formula<Event> formula) {
        Net net = new Net()

        LTransition tIn = new LTransition()
        LTransition tOut = new LTransition()
        LPlace pIn = new LPlace()
        LPlace pOut = new LPlace()

        net.transitionList += [tIn, tOut]
        net.placeList += [pIn, pOut]
        net.arcList << Arc.buildArc(pOut, tOut)
        net.arcList << Arc.buildArc(tIn, pIn)

        for (input in formula.inputFormulas) {
            // create subnet
            Net subNet = buildOperationNet(input)
            net.include(subNet)

            // anchoring
            net.arcList << Arc.buildArc((Place) pIn, (Transition) subNet.inputs[0])
            net.arcList << Arc.buildArc((Transition) subNet.outputs[0], (Place) pOut)
        }
        return net
    }

    static Net buildOperationNet(Formula<Event> formula) {

        if (formula.operator.isUnary()) {
            if (formula.inputFormulas.size() > 0) {
                return buildOperationNet(formula.inputFormulas[0])
            } else {
                if (formula.operator == Operator.POS) {
                    return buildEventNet(formula.inputPorts[0])
                } else if (formula.operator == Operator.NEG) {
                    return buildEventNet(formula.inputPorts[0].negate())
                } else if (formula.operator == Operator.NULL) {
                    return buildEventNet(formula.inputPorts[0].nullify())
                } else {
                    throw new RuntimeException() 
                }
            }
        } else {
            if (formula.inputFormulas.size() == 0) {
                return buildEventNet(formula.inputPorts[0])
            } else {
                if (formula.operator == Operator.SEQ) {
                    return buildSeqOperationNet(formula)
                } else if (formula.operator == Operator.PAR) {
                    return buildParOperationNet(formula)
                } else if (formula.operator == Operator.ALT) {
                    return buildAltOperationNet(formula)
                } else {
                    throw new RuntimeException() 
                }
            }
        }
    }

    static Net buildMechanismNet(Expression condition, Operation action) {

        Expression actualCondition

        Net net = new Net()

        // transform condition action rule in event condition action
        // i.e. when the given condition is not described by an event IN a context
        // consider the "creation" of such condition as the event.
        if (condition.formula.operator != Operator.IN) {
            actualCondition = Expression.build(condition, Operator.IN)
        } else {
            actualCondition = condition
        }

        // create antecedent
        Net triggerNet = buildExpressionNet(actualCondition)
        net.include(triggerNet)

        LPlace pIn = (LPlace) triggerNet.outputs[0]
        net.placeList += [pIn]

        // create consequent
        Expression consequent = action.toExpression()
        LPlace pOut = new LPlace(expression: consequent)
        net.placeList += [pOut]

        Net actionNet = buildOperationNet(action.formula)
        net.include(actionNet)

        // anchoring
        net.arcList += Arc.buildArc((Place) pIn, (Transition) actionNet.inputs[0])
        net.arcList += Arc.buildArc((Transition) actionNet.outputs[0], (Place) pOut)

        return net
    }

}
















//    Map<Event, LTransition> eventTransitionMap = [:]
//    Map<Expression, LPlace> expressionPlaceMap = [:]
//    Map<Operation, Net> operationNetMap = [:]
//    Map<EventConditionExpression, Net> eventConditionExpressionNetMap = [:]
//
//    Base<Situation> situationBase = new Base<Situation>()
//
//    ////////////// Situation, Expression
//
//    Net buildSituationNet(Situation situation, Integer z = 0) {
//
//        Net net = new Net(zIndex: z)
//        Expression expression = Expression.build(situation)
//        LPlace p
//
//        if (expressionPlaceMap[expression]) {
//            p = expressionPlaceMap[expression]
//        } else {
//            p = new LPlace(situation: situation)
//            expressionPlaceMap[expression] = p
//        }
//
//        net.placeList << p
//
//        // I/O
//        net.inputs.add(p)
//        net.outputs.add(p)
//
//        net
//    }
//
//    static Net buildLogicExpressionNet(Formula<Situation> formula, Integer z = 0) {
//
//        Net net = new Net(zIndex: z)
//
//        LTransition tOut = new LTransition(operator: formula.operator)
//        LPlace pOut = new LPlace(expression: Expression.build(formula))
//        net.transitionList << tOut
//        net.placeList << pOut
//
//        if (formula.inputPorts) {
//
//            if (formula.inputPorts.size() == 1 &&
//                    formula.operator != Operator.NEG &&
//                    formula.operator != Operator.NULL) {
//
//            }
//
//        } else {
//            for (input in formula.inputFormulas) {
//
//                // create subnet
//                Net subNet = buildExpressionNet(input, z - 1)
//                net.include(subNet)
//
//                // I/.. (Input/..)
//                net.inputs.add(subNet.inputs[0])
//
//                // anchoring
//                net.arcList += Arc.buildBiflowArcs((Place) subNet.outputs[0], (Transition) tOut)
//
//            }
//
//            net.arcList += Arc.buildDiodeArcs(tOut, pOut)
//
//            // ..O (..Output)
//            net.outputs.add(pOut)
//        }
//
//        return net
//    }
//
//    static Net buildExpressionNet(Formula<Situation> formula, Integer z = 0) {
//        if (formula.operator.isUnary()) {
//            if (formula.inputFormulas.size() > 0) { // there are subformulas
//                if (formula.operator == Operator.POS) {
//                    return buildExpressionNet(formula.inputFormulas[0], z - 1)
//                } else if (formula.operator == Operator.NEG || formula.operator == Operator.NULL) {
//                    return buildNegNotExpressionNet(formula, z - 1)
//                } else {
//                    throw new RuntimeException() 
//                }
//            } else { // there are no subformulas, refer to literals
//                if (formula.operator == Operator.POS) {
//                    return buildSituationNet(formula.inputPorts[0], z)
//                } else if (formula.operator == Operator.NEG) {
//                    return buildSituationNet(formula.inputPorts[0].negate(), z)
//                } else if (formula.operator == Operator.NULL) {
//                    return buildSituationNet(formula.inputPorts[0].nullify(), z)
//                } else {
//                    throw new RuntimeException() 
//                }
//            }
//        } else {
//            if (formula.operator == Operator.AND || formula.operator == Operator.OR || formula.operator == Operator.XOR) {
//              return buildLogicExpressionNet(formula, z)
////            } else if (formula.operator == Operator.SEQ) {
////                return buildSeqNet(formula, z)
////            } else if (formula.operator == Operator.PAR) {
////                return buildParExpressionNet(formula, z)
////            } else if (formula.operator == Operator.ALT) {
////                return buildAltExpressionNet(formula, z)
//            } else {
//                throw new RuntimeException() 
//            }
//        }
//    }
//
//    static Net buildExpressionNet(Expression expression, Integer z = 0) {
//        buildExpressionNet(expression.formula, z)
//    }

// }
