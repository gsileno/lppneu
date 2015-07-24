package org.leibnizcenter.lppneu.builders

import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.language.Event
import org.leibnizcenter.lppneu.components.language.EventConditionExpression
import org.leibnizcenter.lppneu.components.language.Expression
import commons.base.Formula
import org.leibnizcenter.lppneu.components.language.Operation
import org.leibnizcenter.lppneu.components.language.Operator
import org.leibnizcenter.lppneu.components.language.Situation
import org.leibnizcenter.lppneu.components.petrinets.LPlace
import org.leibnizcenter.lppneu.components.petrinets.LTransition
import org.leibnizcenter.pneu.components.petrinet.Arc
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Transition

@Log4j
class LPPN2LPN {

    Map<Event, Transition> eventTransitionMap = [:]
    Map<Expression, Place> expressionPlaceMap = [:]
    Map<Operation, Net> operationNetMap = [:]
    Map<EventConditionExpression, Net> eventConditionExpressionNetMap = [:]

    ////////////// Situation, Expression

    static Net buildSituationNet(Situation situation, Integer z = 0) {
        Net net = new Net(zIndex: z)
        LPlace p = new LPlace(situation: situation)
        net.placeList << p

        // I/O
        net.inputs.add(p)
        net.outputs.add(p)

        net
    }

    static Net buildNegNotExpressionNet(Formula<Situation> formula, Integer z = 0) {

        Net net = new Net(zIndex: z)
        LTransition tOut = new LTransition(operation: Operation.buildFromSituationFormula(formula))
        LPlace pOut = new LPlace(expression: Expression.build(formula))
        net.transitionList << tOut
        net.placeList << pOut

        // create subformulas
        Net subNet = buildExpressionNet(formula.inputFormulas[0], z - 1)
        net.include(subNet)

        // anchoring
        net.arcList += Arc.buildArcs((Place) subNet.outputs[0], (Transition) tOut, (Place) pOut)

        // I/O
        net.inputs.add(subNet.inputs[0])
        net.outputs.add(pOut)

        return net
    }

    static Net buildAndExpressionNet(Formula<Situation> formula, Integer z = 0) {

        Net net = new Net(zIndex: z)

        LTransition tOut = new LTransition()
        LPlace pOut = new LPlace(expression: Expression.build(formula))
        net.transitionList << tOut
        net.placeList << pOut

        for (input in formula.inputFormulas) {

            // create subnet
            Net subNet = buildExpressionNet(input, z - 1)
            net.include(subNet)

            // define inputs
            net.inputs.add(subNet.inputs[0])

            // anchoring
            net.arcList += Arc.buildBiflowArcs((Place) subNet.outputs[0], (Transition) tOut)

        }

        net.arcList += Arc.buildDiodeArcs(tOut, pOut)

        // define outputs
        net.outputs.add(pOut)

        return net

    }

    static Net buildOrExpressionNet(Formula<Situation> formula, Integer z = 0) {
        Net net = new Net(zIndex: z)

        LPlace pOut = new LPlace(expression: Expression.build(formula))
        net.placeList << pOut

        for (input in formula.inputFormulas) {
            LTransition tOut = new LTransition()
            net.transitionList << tOut

            // create subnet
            Net subNet = buildExpressionNet(input, z - 1)
            net.include(subNet)

            // define inputs
            net.inputs.add(subNet.inputs[0])

            // anchoring
            net.arcList += Arc.buildBiflowArcs((Place) subNet.outputs[0], (Transition) tOut)
            net.arcList += Arc.buildDiodeArcs((Transition) tOut, (Place) pOut)

        }

        // define outputs
        net.outputs.add(pOut)

        return net
    }

    static Net buildXorExpressionNet(Formula<Situation> formula, Integer z = 0) {
        Net net = new Net(zIndex: z)

        LPlace pOut = new LPlace(expression: Expression.build(formula))
        net.placeList << pOut

        Map<LPlace, LTransition> input2output = [:]
        for (input in formula.inputFormulas) {
            LTransition tOut = new LTransition()
            net.transitionList << tOut

            // create subnet
            Net subNet = buildExpressionNet(input, z - 1)
            net.include(subNet)

            // define inputs
            net.inputs.add(subNet.inputs[0])

            // save outputs for inhibitor arcs
            input2output[(LPlace) subNet.outputs[0]] = tOut

            // anchoring
            net.arcList += Arc.buildBiflowArcs((Place) subNet.outputs[0], (Transition) tOut)
            net.arcList += Arc.buildDiodeArcs((Transition) tOut, (Place) pOut)

        }

        // define outputs
        net.outputs.add(pOut)

        // inhibitors for XOR
        for (input in input2output.keySet()) {
            for (otherInput in input2output.keySet() - input) {
                net.arcList << Arc.buildInhibitorArc(otherInput, input2output[input])
            }
        }

        return net
    }

    static Net buildSeqExpressionNet(Formula<Situation> formula, Integer z = 0) {

        Net net = new Net(zIndex: z)

        LTransition tOut = null
        LPlace pBridge

        for (int i = 0; i < formula.inputFormulas.size(); i++) {
            Formula<Situation> input = formula.inputFormulas[i]

            tOut = new LTransition()

            if (pBridge) {
                net.arcList << Arc.buildArc(pBridge, tOut)
            }

            if (i != formula.inputFormulas.size() - 1) {
                pBridge = new LPlace()
                net.placeList << pBridge
                net.arcList += Arc.buildDiodeArcs(tOut, pBridge)
            }

            net.transitionList << tOut

            // create subnet
            Net subNet = buildExpressionNet(input, z - 1)
            net.include(subNet)

            for (subNetInput in subNet.inputs) {

                Operation operation = null
                if (subNetInput.situation) {
                    operation = Operation.buildFromSituation(subNetInput.situation)
                } else if (subNetInput.expression) {
                    operation = Operation.buildFromSituationFormula(subNetInput.expression.formula)
                }

                if (!operation) {
                    log.warn("You should not be here."); return null
                }

                LTransition tIn = new LTransition(operation: operation)
                net.transitionList << tIn
                net.arcList << Arc.buildArc((Transition) tIn, (Place) subNetInput)

                // define inputs
                net.inputs.add(subNetInput)
            }

            // anchoring with subnet
            net.arcList += Arc.buildBiflowArcs((Place) subNet.outputs[0], (Transition) tOut)

        }

        if (!tOut) {
            log.warn("You should not be here."); return null
        }

        // define outputs
        LPlace pOut = new LPlace(expression: Expression.build(formula))
        net.placeList << pOut
        net.outputs.add(pOut)

        // final anchoring
        net.arcList += Arc.buildDiodeArcs((Transition) tOut, (Place) pOut)

        return net

    }

    static Net buildParExpressionNet(Formula<Situation> formula, Integer z = 0) {
        Net net = new Net(zIndex: z)

        return net

    }

    static Net buildAltExpressionNet(Formula<Situation> formula, Integer z = 0) {
        Net net = new Net(zIndex: z)

        return net
    }

    static Net buildExpressionNet(Formula<Situation> formula, Integer z = 0) {
        if (formula.operator.unary()) {
            if (formula.inputFormulas.size() > 0) { // there are subformulas
                if (formula.operator == Operator.POS) {
                    return buildExpressionNet(formula.inputFormulas[0], z - 1)
                } else if (formula.operator == Operator.NEG || formula.operator == Operator.NULL) {
                    return buildNegNotExpressionNet(formula, z - 1)
                } else {
                    log.warn("You should not be here."); return null
                }
            } else { // there are no subformulas, refer to literals
                if (formula.operator == Operator.POS) {
                    return buildSituationNet(formula.inputPorts[0], z)
                } else if (formula.operator == Operator.NEG) {
                    return buildSituationNet(formula.inputPorts[0].negate(), z)
                } else if (formula.operator == Operator.NULL) {
                    return buildSituationNet(formula.inputPorts[0].nullify(), z)
                } else {
                    log.warn("You should not be here."); return null
                }
            }
        } else {
            if (formula.operator == Operator.AND) {
                return buildAndExpressionNet(formula, z)
            } else if (formula.operator == Operator.OR) {
                return buildOrExpressionNet(formula, z)
            } else if (formula.operator == Operator.XOR) {
                return buildXorExpressionNet(formula, z)
            } else if (formula.operator == Operator.SEQ) {
                return buildSeqExpressionNet(formula, z)
            } else if (formula.operator == Operator.PAR) {
                return buildParExpressionNet(formula, z)
            } else if (formula.operator == Operator.ALT) {
                return buildAltExpressionNet(formula, z)
            } else {
                log.warn("You should not be here."); return null
            }
        }
    }

    static Net buildExpressionNet(Expression expression, Integer z = 0) {
        buildExpressionNet(expression.formula, z)
    }

    ////////////// Event, Operation

    static Net buildEventNet(Event event, Integer z = 0) {
        Net net = new Net(zIndex: z)
        LTransition t = new LTransition(event: event)
        net.transitionList += [t]

        // I/O
        net.inputs.add(t)
        net.outputs.add(t)

        net.setupGrid()
        net
    }

    static Net buildSeqOperationNet(Formula<Event> formula, Integer z = 0) {
        Net net = new Net(zIndex: z)
        LTransition t; LPlace p

        // preparatory nodes
        t = new LTransition()
        p = new LPlace()
        net.transitionList << t
        net.placeList << p

        for (input in formula.inputFormulas) {

            // create subnet
            Net subNet = buildOperationNet(input, z - 1)
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

    static Net buildParOperationNet(Formula<Event> formula, Integer z = 0) {
        Net net = new Net(zIndex: z)
        LTransition tIn = new LTransition()
        LTransition tOut = new LTransition()
        net.transitionList += [tIn, tOut]

        for (input in formula.inputFormulas) {
            // preparatory place
            LPlace pIn = new LPlace()
            net.placeList << pIn

            // create subnet
            Net subNet = buildOperationNet(input, z - 1)
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

    static Net buildAltOperationNet(Formula<Event> formula, Integer z = 0) {
        Net net = new Net(zIndex: z)

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
            Net subNet = buildOperationNet(input, z - 1)
            net.include(subNet)

            // anchoring
            net.arcList << Arc.buildArc((Place) pIn, (Transition) subNet.inputs[0])
            net.arcList << Arc.buildArc((Transition) subNet.outputs[0], (Place) pOut)
        }
        return net
    }

    static Net buildOperationNet(Formula<Event> formula, Integer z = 0) {

        if (formula.operator.unary()) {
            if (formula.inputFormulas.size() > 0) {
                return buildOperationNet(formula.inputFormulas[0], z - 1)
            } else {
                if (formula.operator == Operator.POS) {
                    return buildEventNet(formula.inputPorts[0], z)
                } else if (formula.operator == Operator.NEG) {
                    return buildEventNet(formula.inputPorts[0].negate(), z)
                } else if (formula.operator == Operator.NULL) {
                    return buildEventNet(formula.inputPorts[0].nullify(), z)
                } else {
                    log.warn("You should not be here."); return null
                }
            }
        } else {
            if (formula.inputFormulas.size() == 0) {
                return buildEventNet(formula.inputPorts[0], z)
            } else {
                if (formula.operator == Operator.SEQ) {
                    return buildSeqOperationNet(formula, z)
                } else if (formula.operator == Operator.PAR) {
                    return buildParOperationNet(formula, z)
                } else if (formula.operator == Operator.ALT) {
                    return buildAltOperationNet(formula, z)
                } else {
                    log.warn("You should not be here."); return null
                }
            }
        }
    }

    static Net buildOperationNet(Operation operation, Integer z = 0) {
        buildOperationNet(operation.formula, z)
    }

    // propositional triple with strong and default negations
    // notation: operation => outcome
    // neg(p) => neg p
    // neg(neg p) => p
    // not(p) => not p
    // not(neg p) => not p
    // pos(p) => p
    // pos(neg p) => neg p

//    static Net buildPropositionalTripleNet(Situation situation, Integer z = 0) {
//        Net net = new Net(zIndex: z)
//
//        SituationTriple pTriple = SituationTriple.build(situation)
//        LPlace pPlace = new LPlace(situation: pTriple.positive)
//        LPlace negpPlace = new LPlace(situation: pTriple.negative)
//        LPlace notpPlace = new LPlace(situation: pTriple.nullified)
//        net.placeList += [pPlace, negpPlace, notpPlace]
//
//        LTransition negnegpOperator = new LTransition(event: pTriple.pos)
//        // double negation = assertion
//        LTransition pospOperator = new LTransition(event: pTriple.pos)
//        LTransition negpOperator = new LTransition(event: pTriple.neg)
//        LTransition posnegpOperator = new LTransition(event: pTriple.neg)
//        LTransition notpOperator = new LTransition(event: pTriple.not)
//        LTransition notnegpOperator = new LTransition(event: pTriple.not)
//
//        for (t in [pospOperator, posnegpOperator, negpOperator, negnegpOperator, notpOperator, notnegpOperator]) {
//            net.transitionList << t
//        }
//
//        net.arcList = Arc.buildArcs(pPlace, negpOperator, negpPlace) +
//                Arc.buildArcs(negpPlace, negnegpOperator, pPlace) +
//                Arc.buildArcs(notpPlace, pospOperator, pPlace) +
//                Arc.buildArcs(notpPlace, posnegpOperator, negpPlace) +
//                Arc.buildArcs(pPlace, notpOperator, notpPlace) +
//                Arc.buildArcs(negpPlace, notnegpOperator, notpPlace)
//
//        net.setupGrid()
//        net
//    }
}
