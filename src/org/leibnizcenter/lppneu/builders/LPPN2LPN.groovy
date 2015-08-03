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
        } else if (place.link) {
            return
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
        } else if (transition.link) {
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

    // deep cloning done for nets
    // by construction, you have only one parent
    // things may change after simplification/unification
    static Net minimalNetClone(Net source, Map<Net, Net> sourceCloneMap = [:], List<Net> ongoingCloning = []) {

        ongoingCloning << source

        Net clone = new Net(transitionList: source.transitionList.collect(),
                placeList: source.placeList.collect(),
                arcList: source.arcList.collect(),
                inputs: source.inputs.collect(),
                outputs: source.inputs.collect(),
                function: source.function)

        for (subNet in source.subNets) {
            if (!ongoingCloning.contains(subNet)) {
                if (!sourceCloneMap[subNet]) {
                    sourceCloneMap[subNet] = minimalNetClone(subNet, sourceCloneMap, ongoingCloning)
                }
                clone.subNets << sourceCloneMap[subNet]
            }
        }

        for (parent in source.parents) {
            if (!ongoingCloning.contains(parent)) {
                if (!sourceCloneMap[parent]) {
                    sourceCloneMap[parent] = minimalNetClone(parent, sourceCloneMap, ongoingCloning)
                }
                clone.parents << sourceCloneMap[parent]
            }
        }

        clone
    }

    // the simplification look for cloned subnets
    // once they are identified, it replaces them, i.e
    // it replaces the cloned subnets with a root subent
    // overwriting the associated inputs/outputs

    static Net simplifyNet(Net net) {

        Net simplifiedNet = minimalNetClone(net)

//        new File("examples/out/log/pre-simplification-source.log").withWriter {
//            out -> out.println(net.toLog())
//        }
//        new File("examples/out/log/pre-simplification-clone.log").withWriter {
//            out -> out.println(simplifiedNet.toLog())
//        }

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

                log.trace("I have to aggregate " + coupling.value.size() + " cloned nets on the following root net")

                log.trace("root net: " + coupling.key)
                log.trace("root net places: " + coupling.key.placeList)
                log.trace("root net transitions: " + coupling.key.transitionList)
                log.trace("root net arcs: " + coupling.key.arcList)
                log.trace("root net subnets: " + coupling.key.subNets)

                // take the root
                Net rootNet = coupling.key

                // for each cloned net
                for (cloneNet in coupling.value) {
                    log.trace("clone net: " + cloneNet)
                    log.trace("clone net places: " + cloneNet.placeList)
                    log.trace("clone net transitions: " + cloneNet.transitionList)
                    log.trace("clone net arcs: " + cloneNet.arcList)
                    log.trace("clone net subnets: " + cloneNet.subNets)

                    // change the link from the parents
                    for (parent in cloneNet.parents) {
                        log.trace("change the link from the parent to the clone to the root instead...")
                        log.trace("parent net: " + parent)
                        log.trace("parent subnet: " + parent.subNets)
                        parent.subNets -= [cloneNet]
                        log.trace("parent subnet (remove clone net): " + parent.subNets)
                        // TOCHECK: possible problems of recursions
                        // when they have different levels
                        parent.include(rootNet)
                        log.trace("parent subnet (add root net): " + parent.subNets)
                    }

                    // for each input node
                    // (the indexing correspond to that of the root net)
                    for (int i = 0; i < cloneNet.inputs.size(); i++) {
                        Node rootNode = rootNet.inputs[i]
                        Node clonedNode = cloneNet.inputs[i]

                        log.trace("clone node: " + clonedNode)

                        // for each of its input arcs
                        for (arc in clonedNode.inputs) {
                            log.trace("arc input to cloned node: " + arc)

                            // if the arc goes externally
                            if (!cloneNet.arcList.contains(arc.source)) {
                                log.trace("the arc comes from externally")

                                // attach the arc to the root node
                                Arc newArc = new Arc(source: arc.source, target: rootNode, weight: arc.weight)
                                log.trace("change arc output " + arc)

                                // bind the arc to the rootNet
                                rootNode.inputs << newArc
                                log.trace("add arc to rootNode" + rootNode.inputs)
                            }
                        }
                    }

                    // the same, for the output nodes
                    for (int i = 0; i < cloneNet.outputs.size(); i++) {
                        Node rootNode = rootNet.inputs[i]
                        Node clonedNode = cloneNet.outputs[i]
                        for (arc in clonedNode.outputs) {
                            if (!cloneNet.arcList.contains(arc.target)) {
                                Arc newArc = new Arc(source: rootNode, target: arc.target, weight: arc.weight)
                                rootNode.outputs << newArc
                            }
                        }
                    }
                }
            }

        }

//        new File("examples/out/log/post-simplification.log").withWriter {
//            out -> out.println(simplifiedNet.toLog())
//        }
//
//        new File("examples/out/log/post-simplification-source.log").withWriter {
//            out -> out.println(net.toLog())
//        }

        simplifiedNet
    }

    Net unifyNet(Net net) {
        recordNet(net)

        Net unifiedNet = minimalNetClone(net)

//        new File("examples/out/log/pre-unification-source.log").withWriter {
//            out -> out.println(net.toLog())
//        }
//
//        new File("examples/out/log/pre-unification-clone.log").withWriter {
//            out -> out.println(unifiedNet.toLog())
//        }

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

//        new File("examples/out/log/post-unification.log").withWriter {
//            out -> out.println(unifiedNet.toLog())
//        }
//
//        new File("examples/out/log/post-unification-source.log").withWriter {
//            out -> out.println(net.toLog())
//        }

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
        unifiedNet = unifyNet(simplifiedNet)
    }

    static Net buildLogicRuleNet(LogicRule logicRule) {
        if (logicRule.isRule()) {
            return buildImplicationNet(logicRule.body.formula, logicRule.head.formula, logicRule.biconditional)
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
        } else if (causalRule.isEventSeries()) {
            return buildEventSeriesNet(causalRule.action)
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

    static Net buildImplicationNet(Formula<Situation> body, Formula<Situation> head, Boolean biconditional) {
        Net net

        if (!biconditional) {
            net = new Net(function: new LPlace(expression:
                    Expression.build(Expression.build(body), Expression.build(head), Operator.IMPLIES)))
        } else {
            net = new Net(function: new LPlace(expression:
                    Expression.build(Expression.build(body), Expression.build(head), Operator.DEFINES)))
        }

        log.trace("create implication net " + net)

        Net bodyNet = buildExpressionNet(body)
        net.include(bodyNet)
        log.trace("attaching body net " + bodyNet)

        LPlace pIn = (LPlace) bodyNet.outputs[0]
        log.trace("input place " + pIn)

        Net headNet = buildExpressionNet(head)
        net.include(headNet)
        log.trace("attaching head net " + headNet)

        LPlace pOut = (LPlace) headNet.outputs[0]
        log.trace("output place " + pOut)

        if (!biconditional) {
            LTransition tImplication = new LTransition(operator: Operator.IMPLIES)
            net.transitionList += [tImplication]
            net.arcList += Arc.buildArcs(pIn, tImplication, pOut)
            log.trace("linking out arc " + net.arcList[-1])
        } else {
            LTransition tNexus = new LTransition(link: true)
            net.transitionList += [tNexus]
            net.arcList << Arc.buildArc((Place) pIn, (Transition) tNexus, ArcType.LINK)
            net.arcList << Arc.buildArc((Transition) tNexus, (Place) pOut, ArcType.LINK)
        }

        return net
    }

    static Net buildSeqExpressionNet(Formula<Situation> formula) {

        Net net = new Net(function: new LPlace(expression: Expression.build(formula)))

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

    static Net buildProcessExpressionNet(Formula<Situation> formula) {

        if (formula.operator == Operator.SEQ) {
            return buildSeqExpressionNet(formula)
        }

        Net net = new Net(function: new LPlace(expression: Expression.build(formula)))

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
        Net net = new Net(function: new LPlace(expression: Expression.build(formula)))

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
        Net net = new Net(function: new LPlace(expression: Expression.build(formula)))

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
        Net net = new Net(function: new LTransition(operation: Operation.build(event)))
        LTransition t = new LTransition(operation: Operation.build(event))
        net.transitionList += [t]

        // I/O
        net.inputs << t
        net.outputs << t

        net
    }

    static Net buildSeqOperationNet(Formula<Event> formula) {
        Net net = new Net(function: new LTransition(operation: Operation.build(formula)))
        LTransition t
        LPlace p

        // preparatory nodes
        t = new LTransition(link: true, name: "start")
        p = new LPlace(link: true, name: "after start")

        net.inputs << t
        net.transitionList << t
        net.placeList << p

        int i = 0
        for (input in formula.inputFormulas) {

            // create subnet
            Net subNet = buildOperationNet(input)
            net.include(subNet)

            // anchoring
            net.arcList += Arc.buildArcs((Transition) t, (Place) p, (Transition) subNet.inputs[0])
            t = (LTransition) subNet.outputs[0]

            // synchronization place
            p = new LPlace(link: true, name: "bridge " + i)
            net.placeList << p
        }

        p.name = "before end"

        // output transition
        LTransition tOut = new LTransition(link: true, name: "end")
        net.transitionList << tOut

        // final anchoring
        net.arcList += Arc.buildArcs((Transition) t, (Place) p, (Transition) tOut)

        net.outputs << tOut

        return net
    }

    static Net buildParOperationNet(Formula<Event> formula) {
        Net net = new Net(function: new LTransition(operation: Operation.build(formula)))
        LTransition tIn = new LTransition(link: true, name: "start")
        LTransition tOut = new LTransition(link: true, name: "end")
        net.transitionList += [tIn, tOut]

        int i = 0
        for (input in formula.inputFormulas) {
            // preparatory place
            LPlace pIn = new LPlace(link: true, name: "start bridge" + i)
            net.placeList << pIn

            // create subnet
            Net subNet = buildOperationNet(input)
            net.include(subNet)

            // synchronization place
            LPlace pOut = new LPlace(link: true, name: "end bridge " + i)
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

    static Net buildAltOperationNet(Formula<Event> formula) {
        Net net = new Net(function: new LTransition(operation: Operation.build(formula)))

        LTransition tIn = new LTransition(link: true, name: "start")
        LTransition tOut = new LTransition(link: true, name: "end")
        LPlace pIn = new LPlace(link: true, name: "after start")
        LPlace pOut = new LPlace(link: true, name: "before end")

        net.transitionList += [tIn, tOut]
        net.placeList += [pIn, pOut]
        net.arcList << Arc.buildArc(tIn, pIn)

        for (input in formula.inputFormulas) {
            // create subnet
            Net subNet = buildOperationNet(input)
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

    static Net buildOperationNet(Formula<Event> formula) {

        if (formula.operator.isUnary()) {
            if (!formula.isAtomic()) {
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
            if (formula.isAtomic()) {
                // TOCHECK: it is wrong I think
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

    static Net buildEventSeriesNet(Operation action) {

        return buildOperationNet(action.formula)

//        Expression consequent = action.toExpression()
//
//        Net net = new Net(function: new LPlace(expression: consequent))
//
//        LPlace pIn = new LPlace(expression: consequent.negate())
//        net.placeList += [pIn]
//
//        // create consequent
//        LPlace pOut = new LPlace(expression: consequent)
//        net.placeList += [pOut]
//
//        // define net function as causal dependency between antecedent and consequent
//
//        Net actionNet = buildOperationNet(action.formula)
//        net.include(actionNet)
//
//        // anchoring
//        net.arcList += Arc.buildArc((Place) pIn, (Transition) actionNet.inputs[0])
//        net.arcList += Arc.buildArc((Transition) actionNet.outputs[0], (Place) pOut)
//
//        net.inputs << pIn
//        net.outputs << pOut
//
//        return net
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

        Net net = new Net()

        // create antecedent
        Net triggerNet = buildExpressionNet(antecedent)
        net.include(triggerNet)

        LPlace pIn = (LPlace) triggerNet.outputs[0]

        // create consequent
        Expression consequent = action.toExpression()
        LPlace pOut = new LPlace(expression: consequent)
        net.placeList += [pOut]

        // define net function as causal dependency between antecedent and consequent
        net.function = new LPlace(expression: Expression.build(antecedent, consequent, Operator.CAUSES))

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

}

