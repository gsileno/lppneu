package org.leibnizcenter.lppneu.builders

import commons.base.Formula
import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.comparison.NetComparison
import org.leibnizcenter.lppneu.components.language.*
import org.leibnizcenter.lppneu.components.lppetrinets.LPPlace
import org.leibnizcenter.lppneu.components.lppetrinets.LPTransition
import org.leibnizcenter.pneu.builders.PN2dot
import org.leibnizcenter.pneu.components.petrinet.Arc
import org.leibnizcenter.pneu.components.petrinet.ArcType
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Node
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Transition

@Log4j
class LPPN2LPN {

    Map<Expression, List<LPPlace>> expressionPlaceMap
    Map<Operation, List<LPTransition>> operationTransitionMap
    Map<Expression, Triple> expressionTripleMap

    Program program, reducedProgram

    Net net, tripleAnchoredNet, transitionAnchoredNet, simplifiedNet, unifiedNet

    void mapPlace(LPPlace place) {

        if (place.expression) {
            if (expressionPlaceMap[place.expression] == null) expressionPlaceMap[place.expression] = []
            expressionPlaceMap[place.expression] << place
        } else if (place.link) {
            return
        } else {
            throw new RuntimeException()
        }
    }

    void mapTransition(LPTransition transition) {
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
    // inner function for traversal
    void innerMapNet(Net net, List<Net> alreadyRecordedNets = []) {

        // map places
        for (p in net.placeList) {
            mapPlace((LPPlace) p)
        }
        // map transitions
        for (t in net.transitionList) {
            mapTransition((LPTransition) t)
        }

        // nested nets
        for (subNet in net.subNets) {
            if (!alreadyRecordedNets.contains(subNet)) {
                innerMapNet(subNet, alreadyRecordedNets)
                alreadyRecordedNets << subNet
            }
        }
    }

    // map all places and transitions indexed by their logic content
    // outer function for reset of maps
    void mapNet(Net net) {

        // set the maps as empty
        expressionPlaceMap = [:]
        operationTransitionMap = [:]

        innerMapNet(net)
    }

    // TODO: refactor with Net class

    // deep cloning done for nets
    // by construction, you have only one parent
    // things may change after simplification/unification
    static Net minimalNetClone(Net source, Map<Net, Net> sourceCloneMap = [:]) {

        if (!sourceCloneMap[source]) {
            sourceCloneMap[source] = new Net(transitionList: source.transitionList.collect(),
                    placeList: source.placeList.collect(),
                    arcList: source.arcList.collect(),
                    inputs: source.inputs.collect(),
                    outputs: source.outputs.collect(),
                    function: source.function)
        }

        Net clone = sourceCloneMap[source]

        for (subNet in source.subNets) {
            if (!sourceCloneMap[subNet]) {
                sourceCloneMap[subNet] = minimalNetClone(subNet, sourceCloneMap)
            }
            clone.subNets << sourceCloneMap[subNet]
        }

        for (parent in source.parents) {
            if (!sourceCloneMap[parent]) {
                sourceCloneMap[parent] = minimalNetClone(parent, sourceCloneMap)
            }
            clone.parents << sourceCloneMap[parent]
        }

        // just to check the cloning
        assert source.placeList.size() == clone.placeList.size()
        assert source.transitionList.size() == clone.transitionList.size()
        assert source.arcList.size() == clone.arcList.size()
        assert source.parents.size() == clone.parents.size()
        assert source.subNets.size() == clone.subNets.size()

        clone
    }

    static void batchExport(Net net, String filename) {

        File folder
        String outputFile

        // textual log output

        folder = new File('logs/log')
        if (!folder.exists()) folder.mkdirs()

        outputFile = "logs/log/" + filename + ".log"

        new File(outputFile).withWriter {
            out -> out.println(net.toLog())
        }

        // dot output

        folder = new File('logs/dot')
        if (!folder.exists()) folder.mkdirs()

        outputFile = "logs/dot/" + filename + ".dot"

        new File(outputFile).withWriter {
            out -> out.println(PN2dot.simpleConversion(net))
        }

    }

    static private void logPreConversion(String typeConversion, Net net, Net convertedNet) {
        batchExport(net, typeConversion+".source.pre")
        batchExport(convertedNet, typeConversion+".clone.pre")
    }

    static private void logPostConversion(String typeConversion, Net net, Net convertedNet) {
        batchExport(net, typeConversion+".source.post")
        batchExport(convertedNet, typeConversion+".clone.post")
    }

    // triple anchoring
    // for all places, it looks for other places of their propositional triple and relates them
    Net tripleAnchoringNet(Net net) {

        if (expressionPlaceMap == null) throw new RuntimeException("The net should be mapped before.")

        expressionTripleMap = [:]

        Net tripleAnchoredNet = minimalNetClone(net)

        logPreConversion("tripleAnchoring", net, tripleAnchoredNet)

        // for each place, construct the associated triple if necessary
        for (expression in expressionPlaceMap.keySet()) {
            Expression refExpression = expression.positive()

            log.trace("Source expression: " + expression)
            log.trace("Reference expression for triple: " + refExpression)

            if (expressionTripleMap[refExpression] == null) {
                expressionTripleMap[refExpression] = Triple.build(refExpression)
            }
        }

        // for each triple reconstructed create a subNet
        for (coupling in expressionTripleMap) {
            tripleAnchoredNet.include(buildTripleNet(coupling.key))
        }

        logPostConversion("tripleAnchoring", net, tripleAnchoredNet)

        tripleAnchoredNet
    }

    // transition anchoring
    // for all places, it attaches the input transitions to a transition with the generating operation

    Net transitionAnchoringNet(Net net) {

        if (expressionPlaceMap == null) throw new RuntimeException("The net should be mapped before.")

        if (expressionTripleMap == null) { expressionTripleMap = [:] }

        Net transitionAnchoredNet = minimalNetClone(net)

        logPreConversion("transitionAnchoring", net, transitionAnchoredNet)

        for (expression in expressionPlaceMap.keySet()) {
            log.trace("Checking the places with expression: "+expression)
            Expression refExpression = expression.positive()

            if (expressionTripleMap[refExpression] == null) {
                expressionTripleMap[refExpression] = Triple.build(refExpression)
            }

            Triple triple = expressionTripleMap[refExpression]

            // all positive places are generated by positive transitions
            if (expression.isPositive()) {
                log.trace("The expression is positive")
                log.trace("Number of places to be accounted: "+ expressionPlaceMap[expression].size())
                for (place in expressionPlaceMap[expression]) {
                    log.trace("Number of inputs to the place ${place}: "+ place.inputs.size())
                    for (input in place.inputs) {
                        LPTransition tInput = (LPTransition) (input.source)
                        if (tInput.operation) {
                            triple.posOperationList << tInput.operation
                        } else {
                            triple.posTransitionList << tInput
                        }
                    }
                }
            } else if (expression.isNegative()) {
                log.trace("The expression is negative")
                log.trace("Number of places to be accounted: "+ expressionPlaceMap[expression].size())
                for (place in expressionPlaceMap[expression]) {
                    log.trace("Number of inputs to the place ${place}: "+ place.inputs.size())
                    for (input in place.inputs) {
                        LPTransition tInput = (LPTransition) (input.source)
                        if (tInput.operation) {
                            triple.negOperationList << tInput.operation
                        } else {
                            triple.negTransitionList << tInput
                        }
                    }
                }
            }

        }

        // for each operator of the triple, create a link from the associated transition
        for (coupling in expressionTripleMap) {

            Triple triple = expressionTripleMap[coupling.key]

            // if at least a trnasition is attached you have to create it, as it is useful for the triple
            if (triple.posTransitionList.size() > 0 || triple.negTransitionList.size() > 0) {

                Net subNet = new Net(function: new LPPlace(expression: Expression.build(coupling.key, Operator.ASSOCIATION)))

                LPTransition tNexus
                LPPlace pNexus

                if (triple.posTransitionList.size() > 0) {

                    tNexus = new LPTransition(operation: triple.positive.toOperation())
                    pNexus = new LPPlace(link: true)

                    subNet.transitionList << tNexus
                    subNet.placeList << pNexus
                    subNet.arcList << Arc.buildArc((Place) pNexus, (Transition) tNexus, ArcType.LINK)

                    for (int i = 0; i < triple.posTransitionList.size(); i++) {
                        LPTransition tOperator = (LPTransition) triple.posTransitionList[i]
                        subNet.arcList << Arc.buildArc((Transition) tOperator, (Place) pNexus, ArcType.LINK)
                    }

                }

                if (triple.negTransitionList.size() > 0) {

                    tNexus = new LPTransition(operation: triple.negative.toOperation())
                    pNexus = new LPPlace(link: true)

                    subNet.transitionList << tNexus
                    subNet.placeList << pNexus
                    subNet.arcList << Arc.buildArc((Place) pNexus, (Transition) tNexus, ArcType.LINK)

                    for (int i = 0; i < triple.negTransitionList.size(); i++) {
                        LPTransition tOperator = (LPTransition) triple.negTransitionList[i]
                        subNet.arcList << Arc.buildArc((Transition) tOperator, (Place) pNexus, ArcType.LINK)
                    }
                }

                transitionAnchoredNet.include(subNet)
            }
        }

        logPostConversion("transitionAnchoring", net, transitionAnchoredNet)

        transitionAnchoredNet
    }

    // simplification looks for cloned subnets
    // once they are identified, it replaces them, i.e
    // it replaces the cloned subnets with a root subnet
    // overwriting the associated inputs/outputs
    // it also remap all internal arcs

    static Net simplifyNet(Net net) {

        Net simplifiedNet = minimalNetClone(net)

        logPreConversion("simplification", net, simplifiedNet)

        List<Net> netList = simplifiedNet.getAllNets()

        Map<Net, List<Net>> clonesNetMap = [:]
        List<Net> alreadyAccounted = []

        for (int i = netList.size() - 1; i > 0; i--) {
            Net ni = netList[i]
            if (!alreadyAccounted.contains(ni)) {
                for (int j = i - 1; j >= 0; j--) {
                    Net nj = netList[j]
                    if (!alreadyAccounted.contains(nj)) {
                        if (NetComparison.compare(ni, nj)) {
                            if (!clonesNetMap[ni]) clonesNetMap[ni] = []
                            clonesNetMap[ni] << nj
                            alreadyAccounted << nj // take out a net which has already been associated
                        }
                    }
                }
                alreadyAccounted << ni // take out a net which has already been associated
            }
        }

        log.trace("Clones Map: "+clonesNetMap)

        if (clonesNetMap.size() == 0) {
            return net
        } else {
            for (coupling in clonesNetMap) {
                log.trace("OUTER cycle: root net: " + coupling.key)
                log.trace("I have to aggregate " + coupling.value.size() + " cloned nets on the previous net")
                // log.trace("root net detail: " + coupling.key.toLog())

                // take the root
                Net rootNet = coupling.key

                // for each cloned net
                for (cloneNet in coupling.value) {
                    log.trace("INNER cycle: clone net: " + cloneNet)
                    // log.trace("clone net detail: " + cloneNet.toLog())

                    // change the link from the parents
                    for (parent in cloneNet.parents) {
                        log.trace("change the link from the parent to the clone to the root instead...")
                        log.trace("parent net: " + parent)
                        // log.trace("parent net detail: " + parent.toLog())

                        log.trace("parent subnet before modification: " + parent.subNets)

                        parent.subNets -= [cloneNet]
                        log.trace("parent subnet (after removal clone net (${cloneNet}): " + parent.subNets)
                        // TOCHECK: possible problems of recursions
                        // when they have different levels
                        parent.include(rootNet)
                        log.trace("parent subnet (after addition root net  (${rootNet})): " + parent.subNets)

                        // for each of its arcs
                        for (arc in parent.arcList) {
                            log.trace("arc of the parent: " + arc)
                            Integer pos

                            pos = cloneNet.placeList.findIndexOf { it == arc.source }
                            if (pos >= 0) {
                                Arc newArc = new Arc(source: rootNet.placeList[pos], target: arc.target, weight: arc.weight)
                                parent.arcList -= [arc]
                                parent.arcList += [newArc]
                                log.trace("modifying arc to: " + newArc)
                            }

                            pos = cloneNet.placeList.findIndexOf { it == arc.target }
                            if (pos >= 0) {
                                Arc newArc = new Arc(source: arc.source, target: rootNet.placeList[pos], weight: arc.weight)
                                parent.arcList -= [arc]
                                parent.arcList += [newArc]
                                log.trace("modifying arc to: " + newArc)
                            }

                            pos = cloneNet.transitionList.findIndexOf { it == arc.source }
                            if (pos >= 0) {
                                Arc newArc = new Arc(source: rootNet.transitionList[pos], target: arc.target, weight: arc.weight)
                                parent.arcList -= [arc]
                                parent.arcList += [newArc]
                                log.trace("modifying arc to: " + newArc)
                            }

                            pos = cloneNet.transitionList.findIndexOf { it == arc.target }
                            if (pos >= 0) {
                                Arc newArc = new Arc(source: arc.source, target: rootNet.transitionList[pos], weight: arc.weight)
                                parent.arcList.remove(arc)
                                parent.arcList.add(newArc)
                                log.trace("modifying arc to: " + newArc)
                            }
                        }

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

                            if (arc.source == null || arc.target == null)
                                throw new RuntimeException("Arc cannot be null")

                            // if the arc goes externally
                            if (!cloneNet.arcList.contains(arc.source)) {
                                log.trace("the arc comes from externally")

                                // attach the arc to the root node
                                Arc newArc = new Arc(source: arc.source, target: rootNode, weight: arc.weight)
                                log.trace("change arc output " + arc)
                                if (arc.source.class == rootNode.class) throw new RuntimeException("You cannot link two nodes of the same type.")

                                // bind the arc to the rootNet
                                rootNode.inputs << newArc
                                log.trace("add arc to rootNode" + rootNode.inputs)
                            }
                        }
                    }

                    // the same, for the output nodes
                    for (int i = 0; i < cloneNet.outputs.size(); i++) {
                        Node rootNode = rootNet.outputs[i]
                        Node clonedNode = cloneNet.outputs[i]
                        for (arc in clonedNode.outputs) {
                            if (arc.source == null || arc.target == null)
                                throw new RuntimeException("Arc cannot be null")

                            if (!cloneNet.arcList.contains(arc.target)) {
                                Arc newArc = new Arc(source: rootNode, target: arc.target, weight: arc.weight)
                                if (arc.target.class == rootNode.class)
                                    throw new RuntimeException("You cannot link two nodes of the same type.")
                                rootNode.outputs << newArc
                            }
                        }

                    }
                }
            }

        }

        logPostConversion("simplification", net, simplifiedNet)

        simplifiedNet
    }

    // unification looks for all places with the same expression
    // and all transitions with the same operation and attach them using nexus nodes

    Net unifyNet(Net net) {

        if (expressionPlaceMap == null) throw new RuntimeException("The net should be mapped before.")

        Net unifiedNet = minimalNetClone(net)

        logPreConversion("unification", net, unifiedNet)

        for (coupling in expressionPlaceMap) {
            if (coupling.value.size() > 1) {
                LPPlace pNexus = new LPPlace(expression: coupling.key)
                unifiedNet.placeList << pNexus
                LPTransition tNexus = new LPTransition(link: true)
                unifiedNet.transitionList << tNexus
                unifiedNet.arcList << Arc.buildArc((Place) pNexus, (Transition) tNexus, ArcType.LINK)

                for (p in coupling.value) {
                    unifiedNet.arcList << Arc.buildArc((Transition) tNexus, (Place) p, ArcType.LINK)
                }
            }
        }

        for (coupling in operationTransitionMap) {
            if (coupling.value.size() > 1) {
                LPTransition tNexus = new LPTransition(operation: coupling.key)
                unifiedNet.transitionList << tNexus
                LPPlace pNexus = new LPPlace(link: true)  // this is a synchornization place
                unifiedNet.placeList << pNexus
                unifiedNet.arcList << Arc.buildArc((Transition) tNexus, (Place) pNexus, ArcType.LINK)

                for (t in coupling.value) {
                    unifiedNet.arcList << Arc.buildArc((Place) pNexus, (Transition) t, ArcType.LINK)
                }
            }
        }

        logPostConversion("unification", net, unifiedNet)

        unifiedNet
    }

    void convert(Program source, Boolean withTriples = false) {

        // reset net and maps
        expressionPlaceMap = null
        operationTransitionMap = null
        expressionTripleMap = null

        // save the original program
        program = source

        if (program.parsingErrors.size() > 0) {
            throw new RuntimeException("parsing errors:\n"+program.parsingErrors.join("\n"))
        }

        // reduce it, decomposing compound formulas
        reducedProgram = program.reduce()

        // transform the program to a net
        net = buildProgramNet(reducedProgram)

        // map the net, i.e. map places and transitions to expressions and operations
        mapNet(net)

        // create the subnets for the triples
        tripleAnchoredNet = tripleAnchoringNet(net)

        // anchor the transitions to the operations on triples
        if (withTriples) simplifiedNet = simplifyNet(tripleAnchoredNet) // put triples on the net
        else simplifiedNet = simplifyNet(net)                           // leave triples out of the net

        // TODO: optimization, rather then remapping, remove from the general map while simplifying
        // remap the net with the accounted reductions
        mapNet(simplifiedNet)

        // simplify the net, i.e. replicated cloned subnets with a similar one
        transitionAnchoredNet = transitionAnchoringNet(simplifiedNet)

        // TODO: optimization, rather then remapping, add to the general map while anchoring
        // remap the net now with the new transitions and places due to the anchoring
        mapNet(transitionAnchoredNet)

        // unify the net, i.e. connect places and transitions with the same labels
        unifiedNet = unifyNet(transitionAnchoredNet)

    }

    static Net buildProgramNet(Program source) {

        // reset net and maps
        Net net = new Net()

        // reduce it, decomposing compound formulas
        Program reducedProgram = source.reduce()

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

        Expression headExpression = Expression.build(head)
        Expression bodyExpression = Expression.build(body)

        if (!biconditional) {
            net = new Net(function: new LPPlace(expression:
                    Expression.build(bodyExpression, headExpression, Operator.IMPLIES)))
        } else {
            net = new Net(function: new LPPlace(expression:
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

        Net net = new Net(function: new LPPlace(expression: Expression.build(formula)))

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
//        Net net = new Net(function: new LPlace(expression: Expression.build(formula)))
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

        Net net = new Net(function: new LPPlace(expression: Expression.build(formula)))

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
        Net net = new Net(function: new LPPlace(expression: Expression.build(formula)))

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
        Net net = new Net(function: new LPPlace(expression: Expression.build(formula)))

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
        Net net = new Net(function: new LPTransition(operation: Operation.build(event)))
        LPTransition t = new LPTransition(operation: Operation.build(event))
        net.transitionList += [t]

        // I/O
        net.inputs << t
        net.outputs << t

        net
    }

    static Net buildSeqOperationNet(Formula<Event> formula) {
        Net net = new Net(function: new LPTransition(operation: Operation.build(formula)))
        LPTransition t
        LPPlace p

        // preparatory nodes
        t = new LPTransition(link: true, name: "start")
        p = new LPPlace(link: true, name: "after start")

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
            t = (LPTransition) subNet.outputs[0]

            // synchronization place
            p = new LPPlace(link: true, name: "bridge " + i)
            net.placeList << p
        }

        p.name = "before end"

        // output transition
        LPTransition tOut = new LPTransition(link: true, name: "end")
        net.transitionList << tOut

        // final anchoring
        net.arcList += Arc.buildArcs((Transition) t, (Place) p, (Transition) tOut)

        net.outputs << tOut

        return net
    }

    static Net buildParOperationNet(Formula<Event> formula) {
        Net net = new Net(function: new LPTransition(operation: Operation.build(formula)))
        LPTransition tIn = new LPTransition(link: true, name: "start")
        LPTransition tOut = new LPTransition(link: true, name: "end")
        net.transitionList += [tIn, tOut]

        int i = 0
        for (input in formula.inputFormulas) {
            // preparatory place
            LPPlace pIn = new LPPlace(link: true, name: "start bridge" + i)
            net.placeList << pIn

            // create subnet
            Net subNet = buildOperationNet(input)
            net.include(subNet)

            // synchronization place
            LPPlace pOut = new LPPlace(link: true, name: "end bridge " + i)
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
        Net net = new Net(function: new LPTransition(operation: Operation.build(formula)))

        LPTransition tIn = new LPTransition(link: true, name: "start")
        LPTransition tOut = new LPTransition(link: true, name: "end")
        LPPlace pIn = new LPPlace(link: true, name: "after start")
        LPPlace pOut = new LPPlace(link: true, name: "before end")

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
        Net net = new Net(function: new LPPlace(expression: Expression.build(expression, Operator.TRIPLE)))

        Triple pTriple = Triple.build(expression)
        LPPlace pPlace = new LPPlace(expression: pTriple.positive)
        LPPlace negpPlace = new LPPlace(expression: pTriple.negative)
        LPPlace notpPlace = new LPPlace(expression: pTriple.nullified)
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
