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

    // Name of the variables given by this place
    List<Variable> getVarList() {
        if (varList == null) {
            if (!link)
                varList = operation.getVariables()
            else {
                varList = reifyPlaces()
            }
            varList
        } else {
            varList
        }
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
        inputCommonVarList = []
        outputCommonVarList = []
        allInputVarList = []
        allOutputVarList = []
        allVarList = []

        List<Node> nodeInputs = []
        for (arc in inputs) {
            nodeInputs << arc.source
        }

        computePlaceUnificationFilter(nodeInputs, inputCommonVarList, allInputVarList)

        List<Node> nodeOutputs = []
        for (arc in outputs) {
            nodeOutputs << arc.target
        }

        computePlaceUnificationFilter(nodeOutputs, outputCommonVarList, allOutputVarList)

        allVarList = allInputVarList + allOutputVarList
    }

    // set the filter for the output to the transition
    List<Variable> getOutputCommonVarList() {
        if (allVarList == null)
            initializeUnificationFilter()
        outputCommonVarList
    }

    // set the filter for the input to the transition
    List<Variable> getInputCommonVarList() {
        if (allVarList == null)
            initializeUnificationFilter()
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
    // this is the *fireable* data

    List<Map<String, String>> getFilterList() {
        List<Map<String, String>> filterList = []

        // TODO: to add the case of label on the transition

        // filter tokens
        for (arc in inputs) {
            LPPlace pl = (LPPlace) arc.source

            // these are the variables given by this place
            List<Variable> localVarList = pl.getVarList()

            // these are the variables constrained by the other inputs
            List<Variable> localCommonVarList = localVarList - (localVarList - getInputCommonVarList())

            if (localCommonVarList.size() > 0) {

                // for the variables contained take the local values (avoiding duplicates)
                List<Map<String, String>> localFilterList = pl.getFilterList(localCommonVarList)

                log.trace("relevant item from this place (${pl.id}): " + localFilterList)

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
                            if (!foundMap[globalItem])
                                foundMap[globalItem] = false

                            // if they are compatible, include the new filter and record as found
                            if (contains(globalItem, localItem)) {
                                log.trace("the global item can be included to the filter")
                                foundMap[globalItem] = true
                                includes(globalItem, localItem)
                            }
                        }
                    }

                    // remove global items that were not compatible with any token
                    List<Map<String, String>> toBeRemoved = []
                    for (globalItem in foundMap.keySet()) {
                        if (!foundMap.get(globalItem)) {
                            toBeRemoved << globalItem
                        }
                    }

                    filterList -= toBeRemoved

                    log.trace("remaining global values satisfying the filter: " + filterList)

                    // the filter does not have any compatible value
                    if (filterList.size() == 0)
                        return null
                }
            }
        }

        log.trace("Filter list: " + filterList)
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

        log.trace("Checking enabled condition for transition ${id}")

        // cut out not emitters
        if (inputs.size() == 0) return false

        // initialize filter to unify tokens+
        if (inputCommonVarList == null) {
            initializeUnificationFilter()
        }

        // for optimization - there are no tokens here
        for (arc in inputs) {
            if (arc.type == ArcType.NORMAL && ((Place) arc.source).marking.size() == 0) {
                log.trace("empty place with normal arc: {$id} not enabled transition.")
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

        for (var in getInputCommonVarList()) {
            for (param in eventToken.expression.getParameters()) {
                if (param.isVariable()) {
                    if (param.variable.name == var.name) {
                        content[var.name] = param.variable.identifier
                    }
                }
            }
            if (!content.keySet().contains(var.name)) {
                throw new RuntimeException("You should not be here.")
            }
        }

        consumeContent(content)
    }

    private void consumeContent(Map<String, String> content) {
        log.trace("transmitted content/filter for consumption: ${content}")

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

        log.trace("transmitted content from consumption (without anonymous variables): ${coreContent}")
        List<String> outCommonVarStringList = []

        for (var in getOutputCommonVarList()) {
            outCommonVarStringList << var.name
        }

        for (var in outCommonVarStringList) {
            if (!coreContent.keySet().contains(var))
                coreContent[var] = generateAnonymousIdentifier(var)
        }

        createContent(coreContent)
    }

    // when we already know which event we have to choose
    TransitionEvent produceOutputTokens(TransitionEvent event) {
        log.trace("${id} produces via ${event}.")
        if (link) log.trace("it is a link transition")

        LPToken eventToken = (LPToken) event.token
        Map<String, String> content = [:]

        for (var in getOutputCommonVarList()) {
            for (param in eventToken.expression.getParameters()) {
                if (param.isVariable()) {
                    if (param.variable.name == var.name) {
                        content[var.name] = param.variable.identifier
                    }
                }
            }
            if (!content.keySet().contains(var.name))
                content[var.name] = generateAnonymousIdentifier(var.name)
        }

        createContent(content)
    }

    private TransitionEvent createContent(Map<String, String> content) {
        log.trace("transmitted content (with anonymous variables): ${content}")

        for (arc in outputs) {
            LPPlace p = (LPPlace) arc.target
            if (arc.type == ArcType.NORMAL) {
                if (arc.weight > 1) throw new RuntimeException("Not yet implemented")
                Token token = p.createToken(content)
                log.trace("Producing in ${p.id} the token " + token)
            } else if (arc.type == ArcType.RESET) {
                p.flush()
            } else {
                throw new RuntimeException("Not yet implemented")
            }
        }

        Token token

        if (!link) token = LPToken.createToken(operation.toExpression(), coreContent)
        else token = LPToken.createToken(Expression.buildNoFunctorExpFromVarStringList(coreContent.keySet()), coreContent)

        new TransitionEvent(transition: this, token: token)
    }

    // return the list of fireable events (transition, token coupling)
    // a fireable event is defined by the content of the input place
    List<TransitionEvent> fireableEvents() {
        List<Token> tokenList = []
        List<Map<String, String>> listContent = []
        List<Map<String, String>> filterList = getFilterList()

        // for each filter available
        for (filter in filterList) {
            log.trace("filter: " + filter)
            listContent += getListContentPerFilter(filter)
        }

        if (!link) {
            for (content in listContent) {
                tokenList << LPToken.createToken(operation.toExpression(), content)
            }
        } else {
            for (content in listContent) {
                tokenList << LPToken.createToken(Expression.buildNoFunctorExpFromVarStringList(content.keySet()), content)
            }
        }

        List<TransitionEvent> firableEvents = []
        for (token in tokenList) {
            firableEvents << new TransitionEvent(transition: this, token: token)
        }

        firableEvents
    }

    /////////////////////////////// return event tokens given a certain filter

    // return all the event tokens from inputs which satisfy the filter
    private List<Map<String, String>> getListContentPerFilter(Map<String, String> filter) {
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
    List<Variable> reifyPlaces() {
        if (inReification) return []
        else inReification = true

        List<Variable> varList = []
        List<LPPlace> connectedPlaces = []
        for (arc in inputs) {
            connectedPlaces << (LPPlace) arc.source
        }
        for (arc in outputs) {
            connectedPlaces << (LPPlace) arc.target
        }

        for (transition in connectedPlaces) {
            varList = combineVarList(varList, transition.getVarList())
        }
        varList
    }

}
