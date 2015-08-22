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
    List<Variable> commonVarList
    // all variables accounted in places
    List<Variable> allVarList

    // set the filter
    void initializeUnificationFilter() {

        // for each input place
        for (elem in inputs) {
            LPPlace pl = (LPPlace) elem.source

            // for each variable which is not in the filter
            for (var in (pl.expression.getVariables() - commonVarList)) {

                // if it is already in the list of variables, add to the filter
                if (allVarList.contains(var)) {
                    commonVarList << var
                    // otherwise add it to the list of variables
                } else {
                    allVarList << var
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

    private static Boolean contains(Map<Variable, String> map1, Map<Variable, String> map2) {
        for (item in map1) {
            if (map2.keySet().contains(item.key)) {
                if (map2[item.key] != item.value)
                    return false
            }
        }
        return true
    }

    private static includes(Map<Variable, String> map1, Map<Variable, String> map2) {
        for (item in map2) {
            if (!map1.keySet().contains(item.key)) {
                map1[item.key] = item.value
            }
        }
    }

    // list to filter all the content of input tokens which satisfy the filter
    // this is the *fireable* data
    // TODO: to be optimized: now it is reset each time isEnabled is computed
    List<Map<Variable, String>> varWithValuesMapList = []

    Boolean isEnabled() {

        varWithValuesMapList = []

        // initialize filter to unify tokens
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

            List<Variable> localVarList = pl.expression.getVariables()
            List<Variable> localCommonVarList = localVarList - (localVarList - commonVarList)

            List<Map<Variable, String>> localVarWithValuesList = pl.getVarWithValuesList(localCommonVarList)

            // for the first elem you take that as starting filter
            if (varWithValuesMapList.size() == 0) {
                varWithValuesMapList = localVarWithValuesList
            } else {

                // for each local data item
                for (localItem in localVarWithValuesList) {
                    List<Map<Variable, String>> toBeRemoved = []

                    // for each cached data item
                    for (globalItem in varWithValuesMapList) {

                        // if they are not compatible, prepare to remove the cache
                        if (!contains(globalItem, localItem)) {
                            toBeRemoved << globalItem

                        // for optimization, stop the search if you already have to remove all the cache
                        if (toBeRemoved.size() == varWithValuesMapList.size())
                            break

                        // otherwise include the parts which were not counted yet
                        } else {
                            includes(globalItem, localItem)
                        }
                    }

                    // remove the not compatible cache
                    varWithValuesMapList -= toBeRemoved

                    // there are not enough data content now
                    if (arc.type == ArcType.NORMAL && varWithValuesMapList.size() < arc.weight) {
                        return false
                    }
                }
            }

            // the inhibition holds
            if (arc.type == ArcType.INHIBITOR && varWithValuesMapList.size() >= arc.weight) {
                return false
            }
        }
        return true
    }

    void fire() {

        if (varWithValuesMapList.size() == 0)
            throw new RuntimeException("You cannot fire from "+toString())

        consumeInputTokens()
        produceOutputTokens()
        varWithValuesMapList.pop()
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
