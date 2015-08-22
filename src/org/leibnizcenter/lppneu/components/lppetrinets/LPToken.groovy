package org.leibnizcenter.lppneu.components.lppetrinets

import groovy.transform.Immutable
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Variable
import org.leibnizcenter.pneu.components.petrinet.Token

class LPToken extends Token {
    Expression expression

    String toString() {
        expression.toString()
    }

    Boolean contains(Map<Variable, String> varWithValuesMap) {
        for (param in expression.parameters) {
            if (param.isVariable()) {
                if (varWithValuesMap[param.variable] != param.variable.identifier)
                    return false
            }
        }
        return true
    }
}

