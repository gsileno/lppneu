package org.leibnizcenter.lppneu.components.lppetrinets

import org.leibnizcenter.lppneu.components.language.Atom
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Operation
import org.leibnizcenter.lppneu.components.language.PosLiteral
import org.leibnizcenter.lppneu.components.language.Operator
import org.leibnizcenter.lppneu.components.language.Variable
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Token

class LPPlace extends Place {

    List<LPToken> marking = []

    // usually logic places contains a logic content
    Expression expression

    // unless they are just synchronization places
    // in this case they transport whatever it passes
    Boolean link = false

    String toString() {
        if (link) "*" + " ${id} (" + marking.size() + ")"
        else if (expression != null) expression.toString() + " (" + marking.size() + ")" // + " LPlace@"+hashCode()
        else throw new RuntimeException("Empty place?")
    }

    String label() {
        if (link) "*"
        else if (expression != null) expression.toString() // + " LPlace@"+hashCode()
        else throw new RuntimeException("Empty place?")
    }

    Boolean isLink() {
        link
    }

    Boolean isCluster() {
        if (expression.formula.operator == Operator.ASSOCIATION) return false
        return true
    }

    // here you store the actual variables of the place
    List<Variable> varList

    // how to compute the actual variables, using the label or reifying the connected nodes
    List<Variable> getVarList() {
        if (varList == null) {
            if (!link)
                varList = expression.getVariables()
            else {
                varList = reifyTransitions()
            }
            varList
        } else {
            varList
        }
    }

    // get the elements recorded in the tokens
    // with the relevance given by the variables included in the input list,
    List<Map<String, String>> getFilterList(List<Variable> localCommonVarList) {
        List<Map<String, String>> varWithValuesMapList = []

        for (token in marking) {
            Map<String, String> varWithValuesMap = [:]
            for (param in token.expression.getParameters()) {
                if (param.isVariable()) {
                    for (var in localCommonVarList) {
                        if (var.name == param.variable.name) {
                            // TODO: numeric constants and variables
                            if (param.variable.identifier == null)
                                throw new RuntimeException("The constant identifier in the variable ${param.variable} cannot be null.")

                            varWithValuesMap[param.variable.name] = param.variable.identifier
                            break;
                        }
                    }
                }
            }

            // avoid to put duplicate tokens for filter
            if (!varWithValuesMapList.contains(varWithValuesMap))
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

    private PosLiteral generateAnonymousIdentifier(String variable) {

        if (variableAnonymousGeneratedIdCountMap[variable] == null)
            variableAnonymousGeneratedIdCountMap[variable] = -1

        Integer n = variableAnonymousGeneratedIdCountMap[variable] + 1
        variableAnonymousGeneratedIdCountMap[variable] = n

        if (id == null) {
            PosLiteral.build(Atom.build("_" + variable.toLowerCase() + n))
        } else {
            PosLiteral.build(Atom.build("_" + id + variable.toLowerCase() + n))
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

        LPToken newToken = new LPToken(expression: tokenExpression)

        // only when the place is labeled with a predicate, check that you are not creating the same content
        if (tokenExpression.getVariables().size() > 0) {
            for (token in marking) {
                if (LPToken.compare(token, newToken)) {
                    throw new RuntimeException("You cannot produce a predicate token with the same content.")
                }
            }
        }

        marking << newToken
        newToken
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

        // TODO: refactor with Token.createToken

        Expression tokenExpression
        if (!link) { // normal places
            tokenExpression = expression.minimalClone()
        } else {
            tokenExpression = Expression.buildNoFunctorExpFromVarList(getVarList())
        }

        for (param in tokenExpression.getParameters()) {
            if (param.isVariable()) {
                if (variableLiteralMap[param.variable.name])
                    param.variable.identifier = PosLiteral.build(Atom.build(variableLiteralMap[param.variable.name]))
                else
                    param.variable.identifier = generateAnonymousIdentifier(param.variable.name)
            }
        }

        LPToken newToken = new LPToken(expression: tokenExpression)

        // only when the place is labeled with a predicate, check that you are not creating the same content
        if (tokenExpression.getVariables().size() > 0) {
            for (token in marking) {
                if (LPToken.compare(token, newToken)) {
                    throw new RuntimeException("You cannot produce a predicate token with the same content.")
                }
            }
        }

        marking << newToken

        newToken
    }

    LPPlace minimalClone() {
        Map<String, Integer> newVariableAnonymousGeneratedIdCountMap = [:]
        for (item in variableAnonymousGeneratedIdCountMap) {
            newVariableAnonymousGeneratedIdCountMap[item.key] = item.value
        }

        Expression clonedExpression = null
        if (expression) clonedExpression = expression.minimalClone()

        return new LPPlace(
                marking: marking.collect(),
                expression: clonedExpression,
                link: link,
                variableAnonymousGeneratedIdCountMap: newVariableAnonymousGeneratedIdCountMap
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

    // remove redundant elements
    private static List<Variable> combineVarList(List<Variable> varList1, List<Variable> varList2) {
        return varList1 - varList2 + varList2
    }

    // semaphor to avoid recursion
    private Boolean inReification = false

    // combine all the variables of the connected transitions (for link places)
    List<Variable> reifyTransitions() {
        if (inReification) return []
        else inReification = true

        List<Variable> varList = []
        List<LPTransition> connectedTransitions = []
        for (arc in inputs) {
            connectedTransitions << (LPTransition) arc.source
        }
        for (arc in outputs) {
            connectedTransitions << (LPTransition) arc.target
        }

        for (transition in connectedTransitions) {
            varList = combineVarList(varList, transition.getVarList())
        }
        varList
    }


}

