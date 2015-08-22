package org.leibnizcenter.lppneu.components.lppetrinets

import org.leibnizcenter.lppneu.components.language.Atom
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Literal
import org.leibnizcenter.lppneu.components.language.Operator
import org.leibnizcenter.lppneu.components.language.Variable
import org.leibnizcenter.pneu.components.petrinet.Place

class LPPlace extends Place {

    List<LPToken> marking = []

    // usually logic places contains a logic content
    Expression expression

    // unless they are just synchronization places
    // in this case they transport whatever it passes
    Boolean link = false

    String toString() {
        if (expression != null) expression.toString() // + " LPlace@"+hashCode()
        else if (link) "*"
        else ""
    }

    String label() {
        if (expression != null) expression.toString() // + " LPlace@"+hashCode()
        else if (link) "*"
        else ""
    }

    Boolean isLink() {
        link
    }

    Boolean isCluster() {
        if (expression.formula.operator == Operator.ASSOCIATION) return false
        return true
    }

    // get all the variables included in the input list, and map their values recorded in the tokens
    List<Map<Variable, String>> getVarWithValuesList(List<Variable> localCommonVarList) {
        List<Map<Variable, String>> varWithValuesMapList = []

        for (token in marking) {
            Map<Variable, String> varWithValuesMap = [:]
            for (param in token.expression.getParameters()) {
                if (param.isVariable()) {
                    if (!localCommonVarList.contains(param.variable))
                        throw new RuntimeException("The variable ${param.variable} should have been accounted.")

                    // TODO: numeric constants
                    if (param.variable.identifier == null)
                        throw new RuntimeException("The constant identifier in the variable ${param.variable} cannot be null.")

                    varWithValuesMap[param.variable] = param.variable.identifier
                }
            }
            varWithValuesMapList << varWithValuesMap
        }
        varWithValuesMapList
    }

    // count the anonymous content generated, in order to generate unique names
    private Map<Variable, Integer> variableAnonymousGeneratedIdCountMap = [:]
    private Literal generateAnonymousIdentifier(Variable variable) {

        if (!variableAnonymousGeneratedIdCountMap[variable])
            variableAnonymousGeneratedIdCountMap[variable] = -1

        Integer n = variableAnonymousGeneratedIdCountMap[variable] + 1
        variableAnonymousGeneratedIdCountMap[variable] = n

        if (id == null) {
            Literal.build(Atom.build("_"+variable.name.toLowerCase()+n))
        } else {
            Literal.build(Atom.build("_"+variable.name.toLowerCase()+n+id))
        }

    }

    // creates a token with a list of constants, filling in order the variables
    // the missing items are filled with anonymous identifiers
    void createToken(List<String> constants) {
        Expression tokenExpression = (Expression) expression.minimalClone()

        Integer i = 0
        for (param in tokenExpression.getParameters()) {
            if (param.isVariable()) {
                if (constants.size() > i) {
                    param.variable.identifier = constants[i]
                    i++
                } else {
                    param.variable.identifier = generateAnonymousIdentifier(param.variable)
                }
            }
        }

        if (constants.size() > i) {
            throw new RuntimeException("Given more constants than necessary: ${constants} for ${expression}.")
        }

        marking << new LPToken(expression: tokenExpression)
    }

    void createToken(String constant) {
        createToken([constant])
    }

    void createToken() {
        createToken([])
    }

    // creates a token with a map that associates identifiers to variables
    // the missing items are filled with anonymous identifiers
    void createToken(Map<Variable, String> variableLiteralMap) {
        Expression tokenExpression = expression.minimalClone()

        for (param in tokenExpression.getParameters()) {
            if (param.isVariable()) {
                if (variableLiteralMap[param.variable])
                    param.variable.identifier = Literal.build(Atom.build(variableLiteralMap[param.variable]))
                else
                    param.variable.identifier = generateAnonymousIdentifier(param.variable)
            }
        }

        marking << new LPToken(expression: tokenExpression)
    }

    LPPlace minimalClone() {
        return new LPPlace(
                marking: marking.collect(),
                expression: expression,
                link: link
        )
    }

    static LPPlace build() {
        return new LPPlace()
    }

    static LPPlace build(String label) {
        return new LPPlace(expression: Expression.parse(label))
    }

    static Boolean compare(Place p1, Place p2) {
        return compare((LPPlace) p1, (LPPlace) p2)
    }

    static Boolean compare(LPPlace p1, LPPlace p2) {
        if (p1 == p2) return true
        if (p1.expression != p2.expression) return false
        if (p1.link != p2.link) return false
        return true
    }

}

