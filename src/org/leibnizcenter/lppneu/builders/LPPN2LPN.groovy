package org.leibnizcenter.lppneu.builders

import commons.base.Formula
import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.comparison.Comparison
import org.leibnizcenter.lppneu.components.language.*
import org.leibnizcenter.lppneu.components.petrinets.LPlace
import org.leibnizcenter.lppneu.components.petrinets.LTransition
import org.leibnizcenter.pneu.components.petrinet.Arc
import org.leibnizcenter.pneu.components.petrinet.ArcType
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Node
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Transition

@Log4j
class LPPN2LPN {

    Map<Expression, List<LPlace>> expressionPlaceMap
    Map<Operation, List<LTransition>> operationTransitionMap

    Program program, reducedProgram

    Net net, simplifiedNet, unifiedNet

    void recordPlace(LPlace place) {

        if (place.expression) {
            if (expressionPlaceMap[place.expression] == null) expressionPlaceMap[place.expression] = []
            expressionPlaceMap[place.expression] << place
        } else {
            throw new RuntimeException()
        }
    }

    void recordTransition(LTransition transition) {
        if (transition.operation) {
            if (operationTransitionMap[transition.operation] == null) operationTransitionMap[transition.operation] = []
            operationTransitionMap[transition.operation] << transition
        } else if (transition.operator) {
            return
        } else {
            throw new RuntimeException()
        }
    }

    // map all places and transitions indexed by their logic content
    void recordNet(Net net) {

        // map places
        for (p in net.placeList) {
            recordPlace((LPlace) p)
        }
        // map transitions
        for (t in net.transitionList) {
            recordTransition((LTransition) t)
        }

        // nested nets
        for (subNet in net.subNets) {
            recordNet(subNet)
        }
    }

    // the simplification look for cloned subnets
    // once they are identified, it replaces them, i.e
    // it replaces the cloned subnets with a root subent
    // overwriting the associated inputs/outputs

    static Net simplifyNet(Net net) {

        Net simplifiedNet = net.clone()

        List<Net> netList = simplifiedNet.getAllNets()

        Map<Net, List<Net>> clonesNetMap = [:]

        for (int i = netList.size() - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                Net ni = netList[i]
                Net nj = netList[j]

                if (Comparison.compare(ni, nj)) {
                    if (!clonesNetMap[ni]) clonesNetMap[ni] = []
                    clonesNetMap[ni] << nj
                }
            }
        }

        if (clonesNetMap.size() == 0) {
            return net
        } else {
            for (coupling in clonesNetMap) {
                // take the root
                Net rootNet = coupling.key

                // for each cloned net
                for (cloneNet in coupling.value) {

                    // change the link from the parent
                    Net parent = cloneNet.parent
                    if (parent) {
                        parent.subNets -= [cloneNet]
                        parent.subNets += rootNet
                    }

                    // for each input node
                    // (the indexing correspond to that of the root net)
                    for (int i = 0; i < cloneNet.inputs.size(); i++) {
                        Node clonedNode = cloneNet.inputs[i]
                        // for each of its input arcs
                        for (arc in clonedNode.inputs) {
                            // if the arc goes externally
                            if (!cloneNet.arcList.contains(arc.source)) {
                                // attach the arc to the root node
                                arc.target = rootNet.inputs[i]
                                // bind the arc to the rootNet
                                rootNet.arcList << arc
                            }
                        }
                    }

                    // the same, for the output nodes
                    for (int i = 0; i < cloneNet.outputs.size(); i++) {
                        Node clonedNode = cloneNet.outputs[i]
                        for (arc in clonedNode.outputs) {
                            if (!cloneNet.arcList.contains(arc.target)) {
                                arc.target = rootNet.outputs[i]
                                rootNet.arcList << arc
                            }
                        }
                    }
                }
            }

        }

        simplifiedNet
    }

    Net unifyNet(Net net) {
        recordNet(net)

        Net unifiedNet = net.clone()

        for (coupling in expressionPlaceMap) {
            if (coupling.value.size() > 1) {
                LPlace pNexus = new LPlace(expression: coupling.key)
                unifiedNet.placeList << pNexus
                LTransition tNexus = new LTransition(link: true)
                unifiedNet.transitionList << tNexus
                unifiedNet.arcList << Arc.buildArc((Place) pNexus, (Transition) tNexus, ArcType.LINK)

                for (p in coupling.value) {
                    unifiedNet.arcList << Arc.buildArc((Transition) tNexus, (Place) p, ArcType.LINK)
                }
            }
        }

        for (coupling in operationTransitionMap) {
            if (coupling.value.size() > 1) {
                LTransition tNexus = new LTransition(operation: coupling.key)
                unifiedNet.transitionList << tNexus
                LPlace pNexus = new LPlace(link: true)  // this is a synchornization place
                unifiedNet.placeList << pNexus
                unifiedNet.arcList << Arc.buildArc((Transition) tNexus, (Place) pNexus, ArcType.LINK)

                for (t in coupling.value) {
                    unifiedNet.arcList << Arc.buildArc((Place) pNexus, (Transition) t, ArcType.LINK)
                }
            }
        }

        unifiedNet
    }

    void convert(Program source) {

        // reset net and maps

        net = new Net()
        expressionPlaceMap = [:]
        operationTransitionMap = [:]

        program = source

        reducedProgram = program.reduce()

        for (logicRule in reducedProgram.logicRules) {
            Net subNet = buildLogicRuleNet(logicRule)
            net.include(subNet)
        }

        for (causalRule in reducedProgram.causalRules) {
            Net subNet = buildCausalRuleNet(causalRule)
            net.include(subNet)
        }

        simplifiedNet = simplifyNet(net)
        unifiedNet = unifyNet(net)
    }

    static Net buildLogicRuleNet(LogicRule logicRule) {
        if (logicRule.isRule()) {
            return buildImplicationNet(logicRule.body.formula, logicRule.head.formula)
        } else if (logicRule.isConstraint()) {
            return buildConstraintNet(logicRule.body.formula)
        } else if (logicRule.isFact()) {
            return buildFactNet(logicRule.head.formula)
        } else {
            throw new RuntimeException()
        }
    }

    static Net buildCausalRuleNet(CausalRule causalRule) {
        if (causalRule.isMechanism()) {
            return buildMechanismNet(causalRule.condition, causalRule.action)
        } else {
            throw new RuntimeException()
        }
    }


    static Net buildConstraintNet(Formula<Situation> constraint) {
        Net net = new Net()

        // TODO
        throw new RuntimeException()

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
                net.arcList += Arc.buildArc((Place) pBridge, (Transition) tBridge)
                net.arcList += Arc.buildArc((Place) pIn, (Transition) tBridge)
                net.arcList += Arc.buildArc((Transition) tBridge, (Place) pOut)
            } else {
                pOut = new LPlace(expression: expression)
                net.placeList += [pOut]
                net.arcList += Arc.buildArc((Transition) tIn, (Place) pOut)
            }

        }

        if (pOut == null) {
            throw new RuntimeException()
        }

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
            } else if (formula.operator == Operator.OCCURS_IN || formula.operator == Operator.OCCURS) {
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
    }

    static Net buildEventNet(Event event) {
        Net net = new Net()
        LTransition t = new LTransition(operation: Operation.build(event))
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
        if (condition.formula.operator != Operator.OCCURS_IN) {
            actualCondition = Expression.build(condition, Operator.OCCURS)
        } else {
            actualCondition = condition
        }

        // create antecedent
        Net triggerNet = buildExpressionNet(actualCondition)
        net.include(triggerNet)

        LPlace pIn = (LPlace) triggerNet.outputs[0]

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

