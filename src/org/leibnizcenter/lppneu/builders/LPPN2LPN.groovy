package org.leibnizcenter.lppneu.builders

import commons.base.Formula
import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.language.*
import org.leibnizcenter.lppneu.components.lppetrinets.LPNet
import org.leibnizcenter.lppneu.components.lppetrinets.LPPlace
import org.leibnizcenter.lppneu.components.lppetrinets.LPTransition
import org.leibnizcenter.lppneu.components.lppetrinets.NetInterface
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

    LPPNProgram program, reducedProgram

    Net net, tripleAnchoredNet, transitionAnchoredNet, simplifiedNet, unifiedNet

    static void batchExport(Net net, String filename) {
        net.exportToLog(filename)
        net.exportToDot(filename)
    }

    static private void logPreConversion(String typeConversion, Net net, Net convertedNet) {
        batchExport(net, typeConversion+".source.pre")
        batchExport(convertedNet, typeConversion+".clone.pre")
    }

    static private void logPostConversion(String typeConversion, Net net, Net convertedNet) {
        batchExport(net, typeConversion+".source.post")
        batchExport(convertedNet, typeConversion+".clone.post")
    }

    // map function
    Mapper mapper = new Mapper()

    // triple anchoring
    // for all places, it looks for other places of their propositional triple and relates them
    Net tripleAnchoringNet(Net net) {

        if (mapper.expressionPlaceMap == null) throw new RuntimeException("The net should be mapped before.")

        mapper.expressionTripleMap = [:]

        Net tripleAnchoredNet = net.minimalClone()

        logPreConversion("tripleAnchoring", net, tripleAnchoredNet)

        // for each place, construct the associated triple if necessary
        for (expression in mapper.expressionPlaceMap.keySet()) {
            Expression refExpression = expression.positive()

            log.trace("Source expression: " + expression)
            log.trace("Reference expression for triple: " + refExpression)

            if (mapper.expressionTripleMap[refExpression] == null) {
                mapper.expressionTripleMap[refExpression] = FactualTriple.build(refExpression)
            }
        }

        // for each triple reconstructed create a subNet
        for (coupling in mapper.expressionTripleMap) {
            tripleAnchoredNet.include(buildTripleNet(coupling.key))
        }

        logPostConversion("tripleAnchoring", net, tripleAnchoredNet)

        tripleAnchoredNet
    }

    // transition anchoring
    // for all places, it attaches the input transitions to a transition with the generating operation

    Net transitionAnchoringNet(Net net) {

        if (mapper.expressionPlaceMap == null) throw new RuntimeException("The net should be mapped before.")

        if (mapper.expressionTripleMap == null) { mapper.expressionTripleMap = [:] }

        Net transitionAnchoredNet = net.minimalClone()

        logPreConversion("transitionAnchoring", net, transitionAnchoredNet)

        for (expression in mapper.expressionPlaceMap.keySet()) {
            log.trace("Checking the places with expression: "+expression)
            Expression refExpression = expression.positive()

            if (mapper.expressionTripleMap[refExpression] == null) {
                mapper.expressionTripleMap[refExpression] = FactualTriple.build(refExpression)
            }

            AbstractTriple triple = mapper.expressionTripleMap[refExpression]

            // all positive places are generated by positive transitions
            if (expression.isPositive()) {
                log.trace("The expression is positive")
                log.trace("Number of places to be accounted: "+ mapper.expressionPlaceMap[expression].size())
                for (place in mapper.expressionPlaceMap[expression]) {
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
                log.trace("Number of places to be accounted: "+ mapper.expressionPlaceMap[expression].size())
                for (place in mapper.expressionPlaceMap[expression]) {
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
        for (coupling in mapper.expressionTripleMap) {

            AbstractTriple triple = mapper.expressionTripleMap[coupling.key]

            // if at least a trnasition is attached you have to create it, as it is useful for the triple
            if (triple.posTransitionList.size() > 0 || triple.negTransitionList.size() > 0) {

                Net subNet = new LPNet(function: new LPPlace(expression: Expression.build(coupling.key, Operator.ASSOCIATION)))

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

        Net simplifiedNet = net.minimalClone()

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
                        if (Net.compare(ni, nj)) {
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

        if (mapper.expressionPlaceMap == null) throw new RuntimeException("The net should be mapped before.")

        Net unifiedNet = net.minimalClone()

        logPreConversion("unification", net, unifiedNet)

        for (coupling in mapper.expressionPlaceMap) {
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

        for (coupling in mapper.operationTransitionMap) {
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

    void convert(LPPNProgram source, Boolean withTriples = false) {

        // reset net and maps
        mapper = new Mapper()

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
        mapper.mapNet(net)

        // create the subnets for the triples
        tripleAnchoredNet = tripleAnchoringNet(net)

        // anchor the transitions to the operations on triples
        if (withTriples) simplifiedNet = simplifyNet(tripleAnchoredNet) // put triples on the net
        else simplifiedNet = simplifyNet(net)                           // leave triples out of the net

        // TODO: optimization, rather then remapping, remove from the general map while simplifying
        // remap the net with the accounted reductions
        mapper.mapNet(simplifiedNet)

        // simplify the net, i.e. replicated cloned subnets with a similar one
        transitionAnchoredNet = transitionAnchoringNet(simplifiedNet)

        // TODO: optimization, rather then remapping, add to the general map while anchoring
        // remap the net now with the new transitions and places due to the anchoring
        mapper.mapNet(transitionAnchoredNet)

        // unify the net, i.e. connect places and transitions with the same labels
        unifiedNet = unifyNet(transitionAnchoredNet)

    }

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

        // TODO
        throw new RuntimeException("Not yet implemented.")

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
        net.inputs << bodyNetInterface.placeInputs[0]

        Net headNet = buildExpressionNet(head)
        NetInterface headNetInterface = net.includeWithInterface(headNet)
        Place pOut = headNetInterface.placeInputs[0]
        net.outputs << headNetInterface.placeOutputs[0]

        if (!biconditional) {
            Transition tImplication = net.createTransition(Operator.IMPLIES)
            net.createBridge(pIn, tImplication, pOut)

            // add contrapositive
            Net cBodyNet = buildExpressionNet(headExpression.negate())
            NetInterface cBodyNetInterface = net.includeWithInterface(cBodyNet)
            pIn = cBodyNetInterface.placeOutputs[0]
            net.inputs << cBodyNetInterface.placeInputs[0]

            Net cHeadNet = buildExpressionNet(bodyExpression.negate())
            NetInterface cHeadNetInterface = net.includeWithInterface(cHeadNet)
            pOut = cHeadNetInterface.placeInputs[0]
            net.outputs << cHeadNetInterface.placeOutputs[0]

            tImplication = net.createTransition(Operator.IMPLIES)
            net.createBridge(pIn, tImplication, pOut)

        } else { // biconditional
            Transition tDefinition = net.createTransition(Operator.DEFINES)
            net.createBridge(pIn, tDefinition, pOut)
            net.inputs << headNetInterface.placeOutputs[0]
            net.outputs << bodyNetInterface.placeInputs[0]
        }

        return net
    }

    static Net buildSeqExpressionNet(Formula<Situation> formula) {

        Net net = new LPNet(function: new LPPlace(expression: Expression.build(formula)))

        List<Expression> seqInputs = []

        Net subNet
        NetInterface subNetInterface

        for (input in formula.inputFormulas) {

            Expression inputExpression, subExpression, compoundExpression

            inputExpression = Expression.build(input)

            if (seqInputs.size() > 0) {
                subExpression = Expression.buildFromExpressions(seqInputs, Operator.SEQ)
                compoundExpression = Expression.build(inputExpression, subExpression, Operator.OCCURS_IN)

                subNet = buildEventConditionNet(compoundExpression.formula)
                subNetInterface = net.includeWithInterface(subNet)

            } else {
                compoundExpression = Expression.build(
                        inputExpression,
                        Operator.OCCURS
                )

                subNet = buildEventConditionNet(compoundExpression.formula)
                subNetInterface = net.includeWithInterface(subNet)

                // take the inputs from the first subnet
                net.inputs = subNetInterface.placeInputs
            }

            seqInputs << inputExpression
        }

        if (subNet == null)
            throw new RuntimeException("You should not be here.")

        // take the inputs from the last subnet
        net.outputs = subNetInterface.placeOutputs

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

            Net subNet = buildExpressionNet(Expression.build(input, Operator.NOT))
            NetInterface subNetInterface = net.includeWithInterface(subNet)

            net.createArc(subNetInterface.placeOutputs[0], tIn)
            net.inputs = subNetInterface.placeInputs
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

        if (formula.operator != Operator.POS) {
            // Logic expressions are only partially transformed into Petri Nets
            if (formula.operator.isLogicOperator()) {
                Transition t = net.createTransition(formula.operator)
                net.createArc(t, pOut)

                for (input in formula.inputPorts) {
                    Place pIn = net.createPlace(Expression.build(input))
                    net.createArc(pIn, t)
                    net.inputs << pIn
                }
            // Process expressions are transformed into actual Petri Nets
            } else if (formula.operator == Operator.OCCURS_IN || formula.operator == Operator.OCCURS) {
                return buildEventConditionNet(formula)
            } else if (formula.operator.isBinaryProcessOperator()) {
                return buildProcessExpressionNet(formula)
            } else {
                throw new RuntimeException("Error in transforming this formula: "+formula)
            }
        } else {
            net.inputs << pOut
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

        // if a catalyst context is specified about the occurrence
        if (formula.inputPorts[1] != null) {
            Place pCatalyst = net.createPlace( Expression.build(formula.inputPorts[1]))
            net.createArc(pCatalyst, tOut)
        }

        Net subNet = buildExpressionNet(Expression.build(eventExpression, Operator.NOT))
        NetInterface subNetInterface = net.includeWithInterface(subNet)

        net.createArc(subNetInterface.placeOutputs[0], tOut)

        // I/O
        net.inputs << subNetInterface.placeInputs[0]
        net.outputs << pOut

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
                net.inputs < tIn
            }

            i++
        }

        if (i == 0) throw new RuntimeException("You should not be here")

        // the output of the last subnet is a general output
        net.outputs << tOut

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
            Place pIn = net.createPlace(Expression.build((((LPTransition) subNetInterface.transitionInputs[0]).operation.toExpression()), Operator.NOT))
            Place pOut = net.createPlace(Expression.build((((LPTransition) subNetInterface.transitionInputs[0]).operation.toExpression()), Operator.POS))

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

        net.inputs << pIn
        net.outputs << pOut

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
}
