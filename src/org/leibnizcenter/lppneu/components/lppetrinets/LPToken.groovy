package org.leibnizcenter.lppneu.components.lppetrinets

import groovy.transform.Immutable
import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.language.Atom
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Operator
import org.leibnizcenter.lppneu.components.language.PosLiteral
import org.leibnizcenter.pneu.components.petrinet.Token

@Log4j
class LPToken extends Token {
    Expression expression

    String toString() {
        expression.toString()
    }

    String label() {
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

    Token minimalClone() {
        return new LPToken(
                expression: expression.minimalClone()
        )
    }

    // check if it respects the unification filter
    Boolean checkWithFilter(Map<String, String> varWithValuesMap) {
        log.trace("checking token with filter")
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

    // creates a token with a map that associates identifiers to variables
    // the missing items are filled with anonymous identifiers
    static Token createToken(Expression expression, Map<String, String> content) {

        log.trace("complete content to be forged: " + content)

        Expression tokenExpression, contextExpression
        if (expression != null) {
            tokenExpression = expression.minimalClone()

            log.trace("token expression used as a forge: " + tokenExpression)

            for (param in tokenExpression.getParameters()) {
                if (param.isVariable()) {
                    if (content.containsKey(param.variable.name)) {
                        log.trace("filling " + param.variable.name + " with " + content[param.variable.name])
                        param.variable.identifier = PosLiteral.build(Atom.build(content[param.variable.name]))
                        content.remove(param.variable.name)
                    }
                }
            }

            log.trace("forged explicit expression: " + tokenExpression)
        }

        if (content.size() > 0) {

            contextExpression = Expression.buildNoFunctorExpFromVarStringList(content.keySet())
            log.trace("context expression used as a forge: " + contextExpression)

            for (param in contextExpression.getParameters()) {
                if (param.isVariable()) {
                    if (content.containsKey(param.variable.name)) {
                        log.trace("filling " + param.variable.name + " with " + content[param.variable.name])
                        param.variable.identifier = PosLiteral.build(Atom.build(content[param.variable.name]))
                        content.remove(param.variable.name)
                    }
                }
            }

            log.trace("forged context expression: " + contextExpression)
        }

        if (tokenExpression && contextExpression) {
            new LPToken(expression: Expression.build(tokenExpression, contextExpression, Operator.AND))
        } else if (tokenExpression) {
            new LPToken(expression: tokenExpression)
        } else if (contextExpression) {
            new LPToken(expression: contextExpression)
        } else {
            new LPToken()
        }

    }

    // creates a token with a map that associates identifiers to variables
    // all the items are put into a context anonymous predicate
    static Token createToken(Map<String, String> varStringMap) {
        createToken(null, varStringMap)
    }
}

