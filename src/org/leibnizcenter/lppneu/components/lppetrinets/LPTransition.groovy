package org.leibnizcenter.lppneu.components.lppetrinets

import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Operation
import org.leibnizcenter.lppneu.components.language.Operator
import org.leibnizcenter.lppneu.components.language.Variable
import org.leibnizcenter.pneu.components.petrinet.ArcType
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Node
import org.leibnizcenter.pneu.components.petrinet.Token
import org.leibnizcenter.pneu.components.petrinet.Transition
import org.leibnizcenter.pneu.components.petrinet.TransitionEvent
import org.leibnizcenter.pneu.components.petrinet.TransitionType

@Log4j
class LPTransition extends Transition {

    // for causal dependencies
    Operation operation

    // for logic dependencies
    Operator operator

    // if they are just synchronization places
    Boolean link = false

    String toString() {
        if (link) {
            "* (${id})"
        } else if (operation != null) {
            operation.toString() // + " LTransition@"+hashCode()
        } else if (operator != null) {
            operator.toString() // + " LTransition@"+hashCode()
        } else if (type == TransitionType.EMITTER) {
            "E (${id})"
        } else if (type == TransitionType.COLLECTOR) {
            "C (${id})"
        } else {
            throw new RuntimeException("Empty transition?")
        }
    }

    String label() {
        toString()
    }

    Boolean isLink() {
        return link
    }

    List<Variable> varList

    // how to compute the actual variables, using the label or reifying the connected nodes
    List<Variable> getVarList() {
        if (varList == null)
            varList = reifyPlaces()
        varList
    }

    Transition minimalClone() {
        Map<String, Integer> clonedVariableAnonymousGeneratedIdCountMap = [:]
        for (item in variableAnonymousGeneratedIdCountMap) {
            clonedVariableAnonymousGeneratedIdCountMap[item.key] = item.value
        }

        Operation clonedOperation = null
        if (operation) clonedOperation = operation.minimalClone()

        return new LPTransition(
                operation: clonedOperation,
                operator: operator,
                link: link,
                variableAnonymousGeneratedIdCountMap: clonedVariableAnonymousGeneratedIdCountMap
        )
    }

    static Transition build() {
        return new LPTransition()
    }

    static Transition build(String label) {
        return new LPTransition(operation: Operation.parse(label))
    }

    static Boolean compare(Transition t1, Transition t2) {
        return compare((LPTransition) t1, (LPTransition) t2)
    }

    static Boolean compare(LPTransition t1, LPTransition t2) {
        if (t1 == t2) return true
        if (t1.operation != t2.operation) return false
        if (t1.operator != t2.operator) return false
        if (t1.link != t2.link) return false
        if (t1.id != t2.id) return false
        return true
    }

    Boolean compare(Transition other) {
        return compare(this, other)
    }

    Boolean subsumes(Transition t) {
        LPTransition lpt = (LPTransition) t

        if (isLink() && t.isLink())
            return true

        // TODO: avoid the double change
        operation.toExpression().subsumes(lpt.operation.toExpression())
    }

    //////////////////////////////
    // Operational Semantics
    //////////////////////////////

    // filter to unify tokens in input places
    List<Variable> inputCommonVarList
    // filter to unify output places
    List<Variable> outputCommonVarList
    // all variables accounted in input places
    List<Variable> allInputVarList
    // all variables accounted in output places
    List<Variable> allOutputVarList
    // all variables in input/output
    List<Variable> allVarList


    private
    static void computePlaceUnificationFilter(List<Node> nodes, List<Variable> commonVarList, List<Variable> allVarList) {

        log.trace("nodes to be unified: " + nodes)

        // for each input place
        for (node in nodes) {
            LPPlace pl = ((LPPlace) ((Place) node))
            log.trace("place to be unified: " + pl.id)

            // for each variable which is not in the filter
            for (var in (pl.getVarList() - commonVarList)) {
                log.trace("variable: " + var)

                // if it is already in the list of variables, add to the filter
                if (allVarList.contains(var)) {
                    log.trace("it is a common variable")
                    commonVarList << var
                    // otherwise add it to the list of variables
                } else {
                    log.trace("add to the general list of variables")
                    allVarList << var
                }
            }
        }
    }

    // set the filter for the input to the transition
    void initializeUnificationFilter() {

        if (allVarList != null) {
            log.trace("unification filter already computed")
        }

        inputCommonVarList = []
        outputCommonVarList = []
        allInputVarList = []
        allOutputVarList = []
        allVarList = []

        // TODO: check the semantics for inhibitor, normal and reset

        List<Node> nodeInputs = []
        for (arc in inputs) {
            if (arc.type == ArcType.NORMAL)
                nodeInputs << arc.source
        }

        log.trace(id + ": compute inputs")

        computePlaceUnificationFilter(nodeInputs, inputCommonVarList, allInputVarList)
        log.trace("computed: input common vars: " + inputCommonVarList)
        log.trace("computed: all input vars: " + allInputVarList)

        List<Node> nodeOutputs = []
        for (arc in outputs) {
            nodeOutputs << arc.target
        }

        log.trace(id + ": compute outputs")
        computePlaceUnificationFilter(nodeOutputs, outputCommonVarList, allOutputVarList)
        log.trace("computed: output common vars: " + outputCommonVarList)
        log.trace("computed: all output vars: " + allOutputVarList)

        allVarList = (allInputVarList - allOutputVarList) + allOutputVarList
        allVarList = (allVarList - getVarList()) + getVarList()

        log.trace("computed: all vars: " + allVarList)
    }

    // consider all variables: internal and external
    List<Variable> getAllVarList() {
        if (allVarList == null) {
            initializeUnificationFilter()
        }
        log.trace("All var list: " + allVarList)
        allVarList
    }

    // set the filter for the output to the transition
    List<Variable> getAllInputVarList() {
        if (allVarList == null) {
            initializeUnificationFilter()
        }
        log.trace("All input var list: " + allInputVarList)
        allInputVarList
    }

    // set the filter for the output to the transition
    List<Variable> getAllOutputVarList() {
        if (allVarList == null) {
            initializeUnificationFilter()
        }
        log.trace("All output var list: " + allOutputVarList)
        allOutputVarList
    }

    // set the filter for the output to the transition
    List<Variable> getOutputCommonVarList() {
        if (allVarList == null) {
            initializeUnificationFilter()
        }
        log.trace("Output common var list: " + outputCommonVarList)
        outputCommonVarList
    }

    // set the filter for the input to the transition
    List<Variable> getInputCommonVarList() {
        if (allVarList == null) {
            initializeUnificationFilter()
        }
        log.trace("Input common var list: " + inputCommonVarList)
        inputCommonVarList
    }

    /////////////////////////////////////////////////////////////
    // functions to filter the tokens accoring to the filter
    ////////////////////////////////////////////////////////////

    private static Boolean contains(Map<String, String> map1, Map<String, String> map2) {
        for (item in map1) {
            if (map2.keySet().contains(item.key)) {
                if (map2[item.key] != item.value)
                    return false
            }
        }
        return true
    }

    private static includes(Map<String, String> map1, Map<String, String> map2) {
        for (item in map2) {
            if (!map1.keySet().contains(item.key)) {
                map1[item.key] = item.value
            } else {
                if (map1[item.key] != item.value) {
                    throw new RuntimeException("Error: ${map1} and ${map2} are incompatible about ${item.key}")
                }
            }
        }
    }

    // to filter all the content of input tokens which satisfy the filter
    // this is used to constrain the *fireable* data

    List<Map<String, String>> getFilterList() {
        List<Map<String, String>> filterList = []

        // TODO: to add the case of label on the transition

        if (inputs.size() == 0) {
            filterList << [:]
        } else {

            // filter tokens
            for (arc in inputs) {
                LPPlace pl = (LPPlace) arc.source

                log.trace("filtering tokens from place " + pl.id + ", labeled with " + pl.label() + "")

                // these are the variables given by this place
                List<Variable> localVarList = pl.getVarList()
                log.trace("variables given by ${pl.id}: ${localVarList}")

                // these are the variables constrained by the other inputs
                List<Variable> localCommonVarList = localVarList - (localVarList - getInputCommonVarList())
                log.trace("local common variables: ${localCommonVarList}")

                if (localCommonVarList.size() > 0) {

                    // for the variables contained take the local values (avoiding duplicates)
                    List<Map<String, String>> localFilterList = pl.getFilterList(localCommonVarList)

                    log.trace("relevant filters from this place (${pl.id}): " + localFilterList)

                    // for the first elem you take that as starting filter
                    if (filterList.size() == 0) {
                        log.trace("this is the first element, so I start setting the filter")
                        filterList = localFilterList
                    } else {
                        log.trace("this is not the first element, so it is used for pruning")

                        // this map is used to check which global item has been found or not
                        Map<Map<String, String>, Boolean> foundMap = [:]

                        // for each local data item
                        for (localItem in localFilterList) {
                            log.trace("local item: " + localItem)

                            // for each cached data item
                            for (globalItem in filterList) {
                                log.trace("global item: " + globalItem)

                                // initialize as not found
                                if (!foundMap.containsKey(globalItem)) {
                                    foundMap[globalItem] = false
                                }

                                // if they are compatible, include the new filter and record as found
                                if (contains(globalItem, localItem)) {
                                    log.trace("the global item can be included to the filter")
                                    foundMap[globalItem] = true
                                    includes(globalItem, localItem)
                                }
                            }
                        }

                        // Strange BUG with Maps.
                        // repairing by using natural iteration (item.key and item.value)
                        // rather then keys and getKeys method

                        // remove global items that were not compatible with any token
                        List<Map<String, String>> toBeRemoved = []
                        for (item in foundMap) {
                            if (!item.value) {
                                toBeRemoved << item.key
                            }
                        }
                        log.trace("elements to be removed: " + toBeRemoved)

                        filterList -= toBeRemoved

                        log.trace("remaining global values satisfying the filter: " + filterList)

                        // the filter does not have any compatible value
                        if (filterList.size() == 0)
                            return null
                    }
                }
            }
        }

        log.trace("filter list: " + filterList)
        filterList
    }

    /////////////////////////////////////
    // Enabling
    /////////////////////////////////////

    Boolean isEnabledIncludingEmission() {
        if (inputs.size() == 0 && isEmitter())
            true
        else
            isEnabled()
    }

    Boolean isEnabled() {

        log.trace("checking enabled condition for transition ${id}..")

        // cut out not emitters
        if (inputs.size() == 0) return false

        // initialize filter to unify tokens
        if (inputCommonVarList == null) {
            initializeUnificationFilter()
        }

        // for optimization - there are no tokens here
        for (arc in inputs) {
            if (arc.type == ArcType.NORMAL && ((Place) arc.source).marking.size() == 0) {
                log.trace("input empty place ${arc.source.id} connected with normal arc ==> {$id} not enabled transition.")
                return false
            }
        }

        // TODO: to be optimized: now it is evaluated each time isEnabled is computed
        // here you record the constraints
        List<Map<String, String>> filterList = getFilterList()

        // there is no common filter amongst existing tokens
        if (filterList == null)
            return false

        // TODO: to add the treatment of the transition label
        for (elem in inputs) {
            LPPlace p = (LPPlace) elem.source
            List<Token> filteredMarking = p.getFilteredMarking(filterList)

            log.trace("filtered marking for ${p}: " + filteredMarking)

            // inhibitor
            if (elem.type == ArcType.INHIBITOR) {
                if (filteredMarking.size() >= elem.weight) {
                    return false
                }
            } else if (elem.type == ArcType.NORMAL) {
                if (filteredMarking.size() < elem.weight) {
                    return false
                }
            } else {
                throw new RuntimeException("Not yet implemented.")
            }
        }

        log.trace("${id} is enabled")
        return true
    }

    TransitionEvent fire() {
        log.trace("${id} fires.")
        consumeInputTokens()
        produceOutputTokens()
    }

    TransitionEvent fire(TransitionEvent event) {
        log.trace("${id} fires via ${event}.")
        consumeInputTokens(event)
        produceOutputTokens(event)
    }

    Map<String, String> coreContent

    void consumeInputTokens() {
        log.trace("${id} consumes.")
        Map<String, String> filter = [:]
        List<Map<String, String>> filterList = getFilterList()

        // consider only the first filter
        if (filterList.size() > 0) {
            filter = filterList[0]
            log.trace("Content consumed/produced depending on filter: " + filter)
        } else {
            log.trace("Content consumed/produced with no filter.")
        }

        consumeContent(filter)
    }

    void consumeInputTokens(TransitionEvent event) {
        log.trace("${id} consumes via ${event}.")

        LPToken eventToken = (LPToken) event.token
        Map<String, String> content = [:]

        if (eventToken.expression != null) {
            for (param in eventToken.expression.getParameters()) {
                if (param.isVariable()) {
                    content[param.variable.name] = param.variable.identifier
                }
            }
        }

        consumeContent(content)
    }

    private void consumeContent(Map<String, String> content) {
        log.trace("${id}: transmitted content/filter for consumption: ${content}")

        coreContent = [:]
        for (arc in inputs) {
            LPPlace p = (LPPlace) arc.source
            log.trace("input place: " + p)
            // find all tokens respecting the filter
            List<Token> filteredMarking = p.getFilteredMarking(content)
            log.trace("filtered marking: " + filteredMarking)

            List<Token> toBeRemoved = []
            if (arc.type == ArcType.NORMAL) {
                if (arc.weight > 1) throw new RuntimeException("Not yet implemented")

                log.trace("starting from content " + coreContent)
                Token token = filteredMarking[0]
                log.trace("including content from token " + token)
                includes(coreContent, ((LPToken) token).toVarWithValuesMap())
                log.trace("content so far: " + coreContent)
                toBeRemoved << token
            }
            // TODO: check for inhibitor when different weight
            log.trace("Removing from ${p.id} the tokens: " + toBeRemoved)
            p.marking -= toBeRemoved
        }
    }

    // standard production
    TransitionEvent produceOutputTokens() {
        log.trace("${id} produces.")
        if (link) log.trace("it is a link transition")

        log.trace("${id}: transmitted content for production (without anonymous variables): ${coreContent}")

        List<String> outCommonVarStringList = []
        for (var in getOutputCommonVarList()) {
            outCommonVarStringList << var.name
        }
        log.trace("${id}: output common variables: ${outCommonVarStringList}")

        List<String> transitionVarStringList = []

        if (!link) {
            for (var in operation.getVariables()) {
                transitionVarStringList << var.name
            }
            log.trace("${id}: transition variables: ${transitionVarStringList}")
        }

        for (var in outCommonVarStringList + transitionVarStringList) {
            if (!coreContent.keySet().contains(var)) {
                coreContent[var] = generateAnonymousIdentifier(var)
                log.trace("${id}: generate anonymous identifier for ${var}: "+coreContent[var])
            }
        }

        createContent(coreContent)
    }

    // when we already know which event we have to fire
    TransitionEvent produceOutputTokens(TransitionEvent event) {
        log.trace("${id} produces via ${event}.")
        if (link) log.trace("it is a link transition")

        LPToken eventToken = (LPToken) event.token
        Map<String, String> content = [:]

        if (eventToken.expression != null) {
            for (param in eventToken.expression.getParameters()) {
                if (param.isVariable()) {
                    content[param.variable.name] = param.variable.identifier
                }
            }
        }

        createContent(content)
    }

    private TransitionEvent createContent(Map<String, String> content) {
        log.trace("${id}: transmitted content for production (including anonymous variables): ${content}")

        for (arc in outputs) {
            LPPlace p = (LPPlace) arc.target
            if (arc.type == ArcType.NORMAL) {
                if (arc.weight > 1) throw new RuntimeException("Not yet implemented")
                Token token = p.createToken(content)
                log.trace("producing in ${p.id} the token " + token)

                for (var in ((LPToken) token).expression.getVariables()) {
                    if (!content.containsKey(var.name)) {
                        log.trace("adding value ${var.identifier} to var ${var.name}")
                        content[var.name] = var.identifier
                    }
                }
            } else if (arc.type == ArcType.RESET) {
                p.flush()
            } else {
                throw new RuntimeException("Not yet implemented")
            }
        }

        Token token
        if (!link) token = LPToken.createToken(operation.toExpression(), content)
        else token = LPToken.createToken(content)

        new TransitionEvent(transition: this, token: token)
    }

    // return the list of fireable events (transition, token coupling)
    // a fireable event is defined by the content of the input place
    List<TransitionEvent> fireableEvents() {

        log.trace("computing fireable events from " + id + ": " + toString())

        // propositional transition, with propositional places, basic petri nets case
        if (getAllVarList().size() == 0) {
            if (isEnabledIncludingEmission()) {
                if (!link) {
                    return [new TransitionEvent(transition: this, token: new LPToken(expression: operation.toExpression()))]
                } else {
                    return [new TransitionEvent(transition: this, token: new LPToken())]
                }
            } else return []
        }

        // there are variables involved
        List<Token> tokenList = []
        List<Map<String, String>> listContent = []
        List<Map<String, String>> filterList = getFilterList()

        // all filters were discarded, nothing can be fired
        if (filterList == null) {
            log.trace("all filters are discarded, nothing can be fired")
            return []
        }

        // if there are no filter available, then take all the elements:
        if (filterList.isEmpty()) {
            listContent = getListContentViaFilter([:])
        } else {
            // for each filter available
            for (filter in filterList) {
                log.trace("filter: " + filter)
                // take the available elements
                listContent += getListContentViaFilter(filter)
            }
        }

        // free variables to be generated at transition level
        // TODO: add transition binding

        List<Variable> freeVariables = (getAllOutputVarList() - getVarList()) + getVarList() - getAllInputVarList()

        log.trace("transition var list: " + getVarList())
        log.trace("all var list: " + getAllVarList())
        log.trace("output common var list: " + getOutputCommonVarList())
        log.trace("all input var list: " + getAllInputVarList())
        log.trace("free variables: " + freeVariables)

        // create an anonymous identifier for each free variable
        Map<String, String> anonymousTransitionContent = [:]
        for (variable in freeVariables) {
            anonymousTransitionContent[variable.name] = generateAnonymousIdentifier(variable.name)
        }

        log.trace("anonymous content: " + anonymousTransitionContent)

        if (!link) {
            log.trace("${id} is not a link")
            if (listContent.isEmpty()) {
                log.trace("there is no content.")
                tokenList = []
            } else {

                List<Variable> contextVariables = allVarList - operation.getVariables()
                log.trace("context variables: " + contextVariables)

                if (anonymousTransitionContent.size() == 0) {
                    log.trace("there is content but no anonymous content, so it simply forges from input content (if any).")
                    for (content in listContent) {
                        // create an anonymous identifier for each context variable
                        Map<String, String> contextContent = [:]
                        for (variable in contextVariables) {
                            contextContent[variable.name] = content[variable.name]
                        }
                        log.trace("context content: " + contextContent)

                        tokenList << LPToken.createToken(operation.toExpression(), content + contextContent)
                    }
                } else { // add anonymous content
                    log.trace("there is content and anonymous content, so it generates a compound input + anonymous content.")
                    for (content in listContent) {
                        Map<String, String> contextContent = [:]
                        for (variable in contextVariables) {
                            contextContent[variable.name] = content[variable.name]
                        }
                        log.trace("context content: " + contextContent)

                        tokenList << LPToken.createToken(operation.toExpression(), content + anonymousTransitionContent + contextContent)
                    }
                }
            }
        } else {
            log.trace("${id} is a link")
            if (listContent.isEmpty()) {
                if (inputs.size() == 0 && isEmitter()) {
                    log.trace("there is no content, but it is an emitter enabled to fire, so it generates anonymous content.")
                    tokenList << LPToken.createToken(Expression.buildNoFunctorExpFromVarStringList(anonymousTransitionContent.keySet()), anonymousTransitionContent)
                }
            } else {
                log.trace("there is content, so it generates a compound input + anonymous content.")
                // in the case of link you have also to consider the output
                for (content in listContent) {
                    Map<String, String> allContent = content + anonymousTransitionContent
                    tokenList << LPToken.createToken(Expression.buildNoFunctorExpFromVarStringList(allContent.keySet()), allContent)
                }
            }
        }

        List<TransitionEvent> firableEvents = []
        for (token in tokenList) {
            firableEvents << new TransitionEvent(transition: this, token: token)
        }

        log.trace("fireable events: " + firableEvents)

        firableEvents
    }

    /////////////////////////////// return event tokens given a certain filter

    // return all the event tokens from inputs which satisfy the filter
    private List<Map<String, String>> getListContentViaFilter(Map<String, String> filter) {

        List<Map<String, String>> listContentPerFilter = []
        // for each input place
        for (arc in inputs) {
            LPPlace p = (LPPlace) arc.source
            log.trace("input place: " + p)

            // find all tokens respecting the filter
            List<Token> filteredMarking = p.getFilteredMarking(filter)
            log.trace("filtered marking: " + filteredMarking)

            // execution semantics
            if (arc.type == ArcType.INHIBITOR) {
                if (filteredMarking.size() >= arc.weight) {
                    return []
                }
            } else if (arc.type == ArcType.NORMAL) {
                if (filteredMarking.size() < arc.weight) {
                    return []
                }
            }

            if (arc.type == ArcType.NORMAL) {
                if (listContentPerFilter == []) {
                    log.trace("first execution on this filter")

                    // for each token available in the token
                    for (token in filteredMarking) {
                        log.trace("creating content with token " + token)
                        listContentPerFilter << ((LPToken) token).toVarWithValuesMap()
                    }
                } else {
                    log.trace("not first execution on this filter")
                    List<Map<String, String>> newListContentPerFilter = []
                    for (contentPerFilter in listContentPerFilter) {
                        log.trace("considering content " + contentPerFilter)

                        for (token in filteredMarking) {
                            Map<String, String> newContent = [:]
                            for (item in contentPerFilter) {
                                newContent[item.key] = item.value
                            }
                            log.trace("cloning it, and including content from token " + token)
                            includes(newContent, ((LPToken) token).toVarWithValuesMap())
                            newListContentPerFilter << newContent
                        }
                    }
                    listContentPerFilter = newListContentPerFilter
                    log.trace("list of content so far: " + listContentPerFilter)
                }
            }
        }

        listContentPerFilter
    }

    // count the anonymous content generated, in order to generate unique names
    private Map<String, Integer> variableAnonymousGeneratedIdCountMap = [:]

    private String generateAnonymousIdentifier(String variable) {
        if (variableAnonymousGeneratedIdCountMap[variable] == null)
            variableAnonymousGeneratedIdCountMap[variable] = -1

        Integer n = variableAnonymousGeneratedIdCountMap[variable] + 1
        variableAnonymousGeneratedIdCountMap[variable] = n

        if (id == null) {
            "_" + variable.toLowerCase() + n
        } else {
            "_" + id + variable.toLowerCase() + n
        }
    }

    // remove redundant elements
    private static List<Variable> combineVarList(List<Variable> varList1, List<Variable> varList2) {
        return varList1 - varList2 + varList2
    }

    // TODO: I'm not sure it works for multiple links places
    // semaphor to avoid recursion
    private Boolean inReification = false

    // combine all the variables of the connected places (for link transitions)
    List<Variable> reifyPlaces(Boolean forwardDirection = true) {

        if (!link) {
            log.trace("${id} is not a link, therefore there is a label: I get variables from here " + toString())
            return operation.getVariables()
        }

        if (inReification) {
            log.trace("${id} in reification.. return to avoid recursion")
        }
        inReification = true

        log.trace("reifying ${id}, going forward? " + forwardDirection + "...")

        List<Variable> varList = []
        List<LPPlace> connectedPlaces = []

        if (forwardDirection) {
            if (outputs.size() == 0) {
                log.trace("${id}: dead end.")
                varList = []
            } else {
                for (arc in outputs) {
                    connectedPlaces << (LPPlace) arc.target
                }

                log.trace("${id}: considering ${connectedPlaces} in forward direction")

                for (place in connectedPlaces) {
                    varList = combineVarList(varList, place.reifyTransitions())
                }

                if (varList.size() == 0) {
                    log.trace("${id}: no variable collected, going backward")
                    forwardDirection = false
                    connectedPlaces = []
                }
            }
        }

        // backward direction
        if (!forwardDirection) {
            if (inputs.size() == 0) {
                log.trace("${id}: (backward) dead end.")
                varList = []
            } else {
                for (arc in inputs) {
                    connectedPlaces << (LPPlace) arc.source
                }

                log.trace("${id}: considering ${connectedPlaces} in backward direction")

                for (place in connectedPlaces) {
                    varList = combineVarList(varList, place.reifyTransitions(false))
                }

            }
        }

        log.trace("${id}: variables found: " + varList)
        varList
    }



}
