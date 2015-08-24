package org.leibnizcenter.lppneu.components.lppetrinets

import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Operation
import org.leibnizcenter.lppneu.components.language.Operator
import org.leibnizcenter.lppneu.components.language.Parameter
import org.leibnizcenter.lppneu.components.language.Variable
import org.leibnizcenter.pneu.components.basicpetrinet.BasicPlace
import org.leibnizcenter.pneu.components.petrinet.Arc
import org.leibnizcenter.pneu.components.petrinet.ArcType
import org.leibnizcenter.pneu.components.petrinet.Token
import org.leibnizcenter.pneu.components.petrinet.Transition

@Log4j
class LPTransition extends Transition {

    // for causal dependencies
    Operation operation

    // for logic dependencies
    Operator operator

    // if they are just synchronization places
    Boolean link = false

    String toString() {
        if (operation != null) {
            operation.toString() // + " LTransition@"+hashCode()
        } else if (operator != null) {
            return operator.toString() // + " LTransition@"+hashCode()
        } else if (link) {
            "*"
        } else {
            ""
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

//        if (inputs.size() == 1) {
//            for (var in ((LPPlace) inputs[0].source).expression.getVariables()) {
//                commonVarList << var.name
//            }
//            allVarList = commonVarList
//        } else {
        // for each input place
        for (elem in inputs) {
            LPPlace pl = (LPPlace) elem.source
            log.debug("input place: " + pl)

            // for each variable which is not in the filter
            for (var in (pl.expression.getVariables() - commonVarList)) {
                log.debug("variable: " + var)

                // if it is already in the list of variables, add to the filter
                if (allVarList.contains(var.name)) {
                    log.debug("it is a common variable")
                    commonVarList << var.name
                    // otherwise add it to the list of variables
                } else {
                    log.debug("add to the general list of variables")
                    allVarList << var.name
                }
            }
        }
//        }
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
            }
        }
    }

    // list to filter all the content of input tokens which satisfy the filter
    // this is the *fireable* data
    // TODO: to be optimized: now it is reset each time isEnabled is computed
    List<Map<String, String>> varWithValuesMapList = []

    Boolean isEnabled() {

        varWithValuesMapList = []

        // initialize filter to unify tokens+
        if (!commonVarList) {
            initializeUnificationFilter()
        }

        // filter tokens
        for (arc in inputs) {
            LPPlace pl = (LPPlace) arc.source

            // for optimization - there are no tokens here
            if (arc.type == ArcType.NORMAL && pl.marking.size() == 0) {
                return false
            }

            // these are the variables given by this place
            List<String> localVarList = []
            for (var in pl.expression.getVariables()) {
                localVarList << var.name
            }

            // these are the variables constrained by the other inputs
            List<String> localCommonVarList = localVarList - (localVarList - commonVarList)

            if (localCommonVarList.size() > 0) {

                // for the variables contained take the local values
                List<Map<String, String>> localVarWithValuesList = pl.getVarWithValuesList(localCommonVarList)

                log.debug("Relevant values from this place (${pl}): " + localVarWithValuesList)

                // for the first elem you take that as starting filter
                if (varWithValuesMapList.size() == 0) {
                    log.debug("This is the first element, so they start setting a filter")
                    varWithValuesMapList = localVarWithValuesList
                } else {
                    log.debug("This is not the first element, so it is used for pruning")

                    // this map is used to check which global item has been found or not
                    Map<Map<String, String>, Boolean> foundMap = [:]

                    // for each local data item
                    for (localItem in localVarWithValuesList) {
                        log.debug("Local item: " + localItem)

                        List<Map<String, String>> toBeRemoved = []

                        // for each cached data item
                        for (globalItem in varWithValuesMapList) {
                            log.debug("Global item: " + globalItem)

                            // initialize as not found
                            if (!foundMap[globalItem])
                                foundMap[globalItem] = false

                            // if they are compatible, include the new filter and record as found
                            if (contains(globalItem, localItem)) {
                                log.debug("the global item can be included to the filter")
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

                    varWithValuesMapList -= toBeRemoved

                    log.debug("Remaining global values satisfying the filter: " + varWithValuesMapList)
                }
            }

            // there are not enough data content now
            if (arc.type == ArcType.NORMAL && varWithValuesMapList.size() < arc.weight) {
                return false
            }

            // the inhibition holds
            if (arc.type == ArcType.INHIBITOR && varWithValuesMapList.size() >= arc.weight) {
                return false
            }
        }

        return true

    }

    void fire() {
        if (varWithValuesMapList.size() == 0) throw new RuntimeException("You cannot fire from " + toString())
        consumeInputTokens()
        produceOutputTokens()
    }

    void consumeInputTokens() {
        for (arc in inputs) {
            List<LPToken> tokens = ((LPPlace) arc.source).marking
            List<LPToken> toBeRemoved = []

            for (token in tokens) {
                if (token.contains(varWithValuesMapList[0])) {
                    toBeRemoved << token
                    if (toBeRemoved.size() == arc.weight)
                        break
                }
            }

            if (toBeRemoved.size() < arc.weight)
                throw new RuntimeException("Tokens satisfying the filter less than what required by the arc weight.")

            ((LPPlace) arc.source).marking -= toBeRemoved
        }
    }

    void produceOutputTokens() {
        for (arc in outputs) {
            if (arc.type == ArcType.NORMAL) {
                if (arc.weight > 1) throw new RuntimeException("Not yet implemented")
                ((LPPlace) arc.target).createToken(varWithValuesMapList[0])
            } else if (arc.type == ArcType.RESET) {
                ((LPPlace) arc.target).flush()
            }
        }
    }

}
