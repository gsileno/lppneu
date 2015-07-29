package org.leibnizcenter.lppneu.builders

import commons.base.Base
import commons.base.Formula
import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.language.*
import org.leibnizcenter.lppneu.components.petrinets.LPlace
import org.leibnizcenter.lppneu.components.petrinets.LTransition
import org.leibnizcenter.pneu.components.petrinet.Arc
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Transition
import org.leibnizcenter.pneu.components.petrinet.TransitionType

@Log4j
class LPPN2LPN {

    static Net convert(Program program) {
        Program reducedProgram = program.reduce()

        Map<LPlace, List<Net>> placeNetMap = [:]
        Map<LTransition, List<Net>> transitionNetMap = [:]

        Net net = new Net()

        for (logicRule in reducedProgram.logicRules) {
            Net subNet

            if (logicRule.isRule()) {
                subNet = buildImplicationExpressionNet(logicRule.body.formula, logicRule.head.formula)
            } else if (logicRule.isConstraint()) {
                subNet = buildInhibitedExpressionNet(logicRule.body)
            } else if (logicRule.isFact()) {
                // subNet = buildFactExpressionNet()
            }

            // anchor subnet to general index of places
            for (p in subNet.placeList) {
                if (placeNetMap[(LPlace) p] == null) placeNetMap[(LPlace) p] = []
                placeNetMap[(LPlace) p] << subNet
            }

            // anchor subnet to general index of transitions
            for (t in subNet.transitionList) {
                if (transitionNetMap[(LTransition) t] == null) transitionNetMap[(LTransition) t] = []
                transitionNetMap[(LTransition) t] << subNet
            }

            net.include(subNet)
        }

        net
    }

    static Net buildInhibitedExpressionNet(Formula<Situation> constraint) {
        Net net = new Net()
        // TODO
        return net
    }

    static Net buildImplicationExpressionNet(Formula<Situation> body, Formula<Situation> head) {

        Net net = new Net()

        Net bodyNet = buildExpressionNet(body)
        net.include(bodyNet)
        LPlace pIn = (LPlace) bodyNet.outputs[0]

        Net headNet = buildExpressionNet(head)
        net.include(headNet)
        LPlace pOut = (LPlace) headNet.outputs[0]

        LTransition tImplication = new LTransition(operator: Operator.IMPLIES)
        net.transitionList += [tImplication]
        net.placeList += [pIn, pOut]
        net.arcList += Arc.buildArcs(pIn, tImplication, pOut)

        return net
    }

    static Net buildExpressionNet(Formula<Situation> formula) {
        Net net = new Net()

        LPlace pOut = new LPlace(expression: Expression.build(formula))
        net.placeList += [pOut]
        net.outputs += [pOut]

        if (formula.operator != Operator.POS) {
            LTransition t = new LTransition(operator: formula.operator)
            net.transitionList += [t]
            net.arcList += Arc.buildArc(t, pOut)

            for (input in formula.inputPorts) {
                LPlace pIn = new LPlace(expression: Expression.build(input))
                net.placeList += [pIn]
                net.arcList += Arc.buildArc(pIn, t)
                net.inputs += [pIn]
            }
        } else {
            net.inputs += [pOut]
        }

        net
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
//        if (formula.operator.unary()) {
//            if (formula.inputFormulas.size() > 0) { // there are subformulas
//                if (formula.operator == Operator.POS) {
//                    return buildExpressionNet(formula.inputFormulas[0], z - 1)
//                } else if (formula.operator == Operator.NEG || formula.operator == Operator.NULL) {
//                    return buildNegNotExpressionNet(formula, z - 1)
//                } else {
//                    log.warn("You should not be here."); return null
//                }
//            } else { // there are no subformulas, refer to literals
//                if (formula.operator == Operator.POS) {
//                    return buildSituationNet(formula.inputPorts[0], z)
//                } else if (formula.operator == Operator.NEG) {
//                    return buildSituationNet(formula.inputPorts[0].negate(), z)
//                } else if (formula.operator == Operator.NULL) {
//                    return buildSituationNet(formula.inputPorts[0].nullify(), z)
//                } else {
//                    log.warn("You should not be here."); return null
//                }
//            }
//        } else {
//            if (formula.operator == Operator.AND || formula.operator == Operator.OR || formula.operator == Operator.XOR) {
//              return buildLogicExpressionNet(formula, z)
////            } else if (formula.operator == Operator.SEQ) {
////                return buildSeqExpressionNet(formula, z)
////            } else if (formula.operator == Operator.PAR) {
////                return buildParExpressionNet(formula, z)
////            } else if (formula.operator == Operator.ALT) {
////                return buildAltExpressionNet(formula, z)
//            } else {
//                log.warn("You should not be here."); return null
//            }
//        }
//    }
//
//    static Net buildExpressionNet(Expression expression, Integer z = 0) {
//        buildExpressionNet(expression.formula, z)
//    }

// }
