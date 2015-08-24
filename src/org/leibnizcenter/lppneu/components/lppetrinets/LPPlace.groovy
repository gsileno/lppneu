package org.leibnizcenter.lppneu.components.lppetrinets

import org.leibnizcenter.lppneu.components.language.Atom
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Literal
import org.leibnizcenter.lppneu.components.language.Operator
import org.leibnizcenter.pneu.components.petrinet.Arc
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Token
import org.leibnizcenter.pneu.components.petrinet.Transition

class LPPlace extends Place {

    List<LPToken> marking = []

    // usually logic places contains a logic content
    Expression expression

    // unless they are just synchronization places
    // in this case they transport whatever it passes
    Boolean link = false

    String toString() {
        if (link) "*"
        else if (expression != null) expression.toString() // + " LPlace@"+hashCode()
        else throw new RuntimeException("Empty place?")
    }

    String label() {
       toString()
    }

    Boolean isLink() {
        link
    }

    Boolean isCluster() {
        if (expression.formula.operator == Operator.ASSOCIATION) return false
        return true
    }

    // Name of the variables given by this place
    List<String> getVarList() {
        List<String> localVarList = []
        for (var in expression.getVariables()) {
            localVarList << var.name
        }
        localVarList
    }

    // get the elements recorded in the tokens
    // with the relevance given by the variables included in the input list,
    List<Map<String, String>> getFilterList(List<String> localCommonVarList) {
        List<Map<String, String>> varWithValuesMapList = []

        for (token in marking) {
            Map<String, String> varWithValuesMap = [:]
            for (param in token.expression.getParameters()) {
                if (param.isVariable()) {
                    if (localCommonVarList.contains(param.variable.name)) {
                        // TODO: numeric constants
                        if (param.variable.identifier == null)
                            throw new RuntimeException("The constant identifier in the variable ${param.variable} cannot be null.")

                        varWithValuesMap[param.variable.name] = param.variable.identifier
                    }
                }
            }
            varWithValuesMapList << varWithValuesMap
        }
        varWithValuesMapList
    }

    // get the markings filtered by the filters given in the list
    List<Token> getFilteredMarking(List<Map<String, String>> filterList) {

        // no filter return all the marking
        if (filterList.size() == 0)
            return marking

        List<Token> filteredMarking = []
        for (filter in filterList) {
            filteredMarking += getFilteredMarking(filter)
        }
        filteredMarking
    }

    // get the markings filtered by the filter given in the list
    List<Token> getFilteredMarking(Map<String, String> filter) {

        // no filter return all the marking
        if (filter.size() == 0)
            return marking

        List<Token> filteredMarking = []

        for (token in marking) {
            if (token.checkWithFilter(filter)) {
                filteredMarking << token
            }
        }

        filteredMarking
    }

    // count the anonymous content generated, in order to generate unique names
    private Map<String, Integer> variableAnonymousGeneratedIdCountMap = [:]
    private Literal generateAnonymousIdentifier(String variable) {

        if (variableAnonymousGeneratedIdCountMap[variable] == null)
            variableAnonymousGeneratedIdCountMap[variable] = -1

        Integer n = variableAnonymousGeneratedIdCountMap[variable] + 1
        variableAnonymousGeneratedIdCountMap[variable] = n

        if (id == null) {
            Literal.build(Atom.build("_"+variable.toLowerCase()+n))
        } else {
            Literal.build(Atom.build("_"+id+variable.toLowerCase()+n))
        }

    }

    // creates a token with a list of constants, filling in order the variables
    // the missing items are filled with anonymous identifiers
    Token createToken(List<String> constants) {
        Expression tokenExpression = (Expression) expression.minimalClone()

        Integer i = 0
        for (param in tokenExpression.getParameters()) {
            if (param.isVariable()) {
                if (constants.size() > i) {
                    param.variable.identifier = constants[i]
                    i++
                } else {
                    param.variable.identifier = generateAnonymousIdentifier(param.variable.name)
                }
            }
        }

        if (constants.size() > i) {
            throw new RuntimeException("Given more constants than necessary: ${constants} for ${expression}.")
        }

        LPToken token = new LPToken(expression: tokenExpression)

        if (marking.contains(token))
            throw new RuntimeException("You cannot produce a token with the same content")

        marking << token
        token
    }

    Token createToken(String constant) {
        createToken([constant])
    }

    Token createToken() {
        createToken([])
    }

    // creates a token with a map that associates identifiers to variables
    // the missing items are filled with anonymous identifiers
    Token createToken(Map<String, String> variableLiteralMap) {
        Expression tokenExpression = expression.minimalClone()

        for (param in tokenExpression.getParameters()) {
            if (param.isVariable()) {
                if (variableLiteralMap[param.variable.name])
                    param.variable.identifier = Literal.build(Atom.build(variableLiteralMap[param.variable.name]))
                else
                    param.variable.identifier = generateAnonymousIdentifier(param.variable.name)
            }
        }

        LPToken newToken = new LPToken(expression: tokenExpression)
        for (token in marking) {
            if (LPToken.compare(token, newToken)) {
                throw new RuntimeException("You cannot produce a token with the same content")
            }
        }
        marking << newToken

        newToken
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

