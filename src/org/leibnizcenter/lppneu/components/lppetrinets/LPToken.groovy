package org.leibnizcenter.lppneu.components.lppetrinets

import groovy.transform.Immutable
import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.pneu.components.petrinet.Token

@Log4j
class LPToken extends Token {
    Expression expression

    String toString() {
        expression.toString()
    }

    // LP net tokens are equal if they are of the same type and transports the same content
    Boolean compare(Token t) {
        if (t.class != LPToken) return false
        expression == ((LPToken) t).expression
    }

    static Boolean compare(Token t1, Token t2) {
        t1.compare(t2)
    }

    // check if it respects the unification filter
    Boolean checkWithFilter(Map<String, String> varWithValuesMap) {
        log.trace("cheking token with filter")
        for (param in expression.parameters) {
            log.trace("parameter: " + param)
            if (param.isVariable()) {
                if (varWithValuesMap.keySet().contains(param.variable.name)) {
                    log.trace("variable included in the filter, with value: " + varWithValuesMap[param.variable.name])
                    if (varWithValuesMap[param.variable.name] != param.variable.identifier)
                        return false
                }
            }
        }
        return true
    }

    // check if it respects the unification filter
    Map<String, String> toVarWithValuesMap() {
        Map<String, String> varWithValuesMap = [:]
        for (param in expression.parameters) {
            if (param.isVariable()) {
                varWithValuesMap[param.variable.name] = param.variable.identifier
                // TODO: numeric values
            }
        }
        varWithValuesMap
    }
}

