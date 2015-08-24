package org.leibnizcenter.lppneu.components.lppetrinets

import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.language.Operation
import org.leibnizcenter.lppneu.components.language.Operator
import org.leibnizcenter.pneu.components.petrinet.ArcType
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Token
import org.leibnizcenter.pneu.components.petrinet.Transition
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
            "*"
        } else if (operation != null) {
            operation.toString() // + " LTransition@"+hashCode()
        } else if (operator != null) {
            operator.toString() // + " LTransition@"+hashCode()
        } else if (type == TransitionType.EMITTER) {
            "E"
        } else if (type == TransitionType.COLLECTOR) {
            "C"
        } else {
            throw new RuntimeException("Empty place?")
        }
    }

    String label() {
        toString()
    }

    Boolean isLink() {
        return link
    }

    Transition clone() {
        return new LPTransition(
                operation: operation,
                operator: operator,
                link: link
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
        return true
    }

    //////////////////////////////
    // Operational Semantics
    //////////////////////////////

    // filter to unify tokens
    List<String> commonVarList
    // all variables accounted in places
    List<String> allVarList

    // set the filter
    void initializeUnificationFilter() {
        commonVarList = []
        allVarList = []

        // for each input place
        for (elem in inputs) {
            LPPlace pl = (LPPlace) elem.source
            log.trace("input place: " + pl.id)

            // for each variable which is not in the filter
            for (var in (pl.expression.getVariables() - commonVarList)) {
                log.trace("variable: " + var)

                // if it is already in the list of variables, add to the filter
                if (allVarList.contains(var.name)) {
                    log.trace("it is a common variable")
                    commonVarList << var.name
                    // otherwise add it to the list of variables
                } else {
                    log.trace("add to the general list of variables")
                    allVarList << var.name
                }
            }
        }
    }

    Boolean isEnabledIncludingEmission() {
        if (inputs.size() == 0 && isEmitter())
            true
        else
            isEnabled()
    }

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

    // list to filter all the content of input tokens which satisfy the filter
    // this is the *fireable* data

    List<Map<String, String>> getFilterList() {
        List<Map<String, String>> filterList = []

        // filter tokens
        for (arc in inputs) {
            LPPlace pl = (LPPlace) arc.source

            // these are the variables given by this place
            List<String> localVarList = pl.getVarList()

            // these are the variables constrained by the other inputs
            List<String> localCommonVarList = localVarList - (localVarList - commonVarList)

            if (localCommonVarList.size() > 0) {

                // for the variables contained take the local values
                List<Map<String, String>> localFilterList = pl.getFilterList(localCommonVarList)

                log.trace("Relevant values from this place (${pl.id}): " + localFilterList)

                // for the first elem you take that as starting filter
                if (filterList.size() == 0) {
                    log.trace("This is the first element, so I start setting the filter")
                    filterList = localFilterList
                } else {
                    log.trace("This is not the first element, so it is used for pruning")

                    // this map is used to check which global item has been found or not
                    Map<Map<String, String>, Boolean> foundMap = [:]

                    // for each local data item
                    for (localItem in localFilterList) {
                        log.trace("Local item: " + localItem)

                        // for each cached data item
                        for (globalItem in filterList) {
                            log.trace("Global item: " + globalItem)

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

                    log.trace("Remaining global values satisfying the filter: " + filterList)

                    // the filter does not have any compatible value
                    if (filterList.size() == 0)
                        return null
                }
            }
        }

        log.trace("Filter list: " + filterList)
        filterList
    }

    Boolean isEnabled() {

        log.trace("Checking transition of ${id}")

        // cut out not emitters
        if (inputs.size() == 0) return false

        // initialize filter to unify tokens+
        if (commonVarList == null) {
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

    void fire() {
        log.trace("${id} fires.")
        consumeInputTokens()
        produceOutputTokens()
    }

    Map<String, String> coreContent = [:]

    void consumeInputTokens() {
        log.trace("${id} consumes.")

        Map<String, String> filter = [:]
        List<Map<String, String>> filterList = getFilterList()

        if (filterList.size() > 0) {
            filter = filterList[0]
            log.trace("Content consumed/produced depending on filter: " + filter)
        } else {
            log.trace("Content consumed/produced with no filter.")
        }

        for (arc in inputs) {
            LPPlace p = (LPPlace) arc.source
            List<Token> tokens = p.getFilteredMarking(filter)
            List<Token> toBeRemoved = []

            if (arc.type == ArcType.NORMAL) {
                for (int i = 0; i < arc.weight; i++) {
                    toBeRemoved << tokens[i]
                    includes(coreContent, ((LPToken) tokens[i]).toVarWithValuesMap())
                }
            } else {
                throw new RuntimeException("Not yet implemented")
                // TODO: check for inhibitor when different weight
            }

            log.trace("Removing from ${p.id} the tokens: " + toBeRemoved)

            p.marking -= toBeRemoved
        }
    }

    void produceOutputTokens() {
        log.trace("${id} produces.")

        log.trace("Transmitted content in consumption: ${coreContent}")

        for (arc in outputs) {
            LPPlace p = (LPPlace) arc.target
            if (arc.type == ArcType.NORMAL) {
                if (arc.weight > 1) throw new RuntimeException("Not yet implemented")
                Token token = p.createToken(coreContent)
                log.trace("Producing in ${p.id} the token " + token)
            } else if (arc.type == ArcType.RESET) {
                p.flush()
            } else {
                throw new RuntimeException("Not yet implemented")
            }
        }

        coreContent = [:]
    }

}
