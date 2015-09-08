package org.leibnizcenter.lppneu.components.lppetrinets

import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.builders.LPPN2LPN
import org.leibnizcenter.lppneu.components.language.Atom
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Operation
import org.leibnizcenter.lppneu.components.language.Parameter
import org.leibnizcenter.lppneu.components.language.PosLiteral
import org.leibnizcenter.lppneu.components.language.Operator
import org.leibnizcenter.lppneu.components.language.Variable
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Token

@Log4j
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
        if (varList == null)
            varList = reifyTransitions()
        varList
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

    private void fillParametersWithContent(List<Parameter> parameters, Map<String, String> content) {
        for (param in parameters) {
            if (param.isVariable()) {
                if (content[param.variable.name] == null)
                    param.variable.identifier = generateAnonymousIdentifier(param.variable.name)
                param.variable.identifier = PosLiteral.build(Atom.build(content[param.variable.name]))
            } else if (param.isLiteral()) {
                if (param.literal.parameters.size() > 0) {
                    fillParametersWithContent(param.literal.parameters, content)
                }
            }
        }
    }

    // creates a token with a map that associates identifiers to variables
    // the missing items are filled with anonymous identifiers
    Token createToken(Map<String, String> content) {

        // TODO: refactor with Token.createToken

        Expression tokenExpression
        if (!link) { // normal places
            tokenExpression = expression.minimalClone()
        } else {
            tokenExpression = Expression.buildNoFunctorExpFromVarList(getVarList())
        }

        fillParametersWithContent(tokenExpression.getParameters(), content)

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

    Boolean compare(Place that) {
        return compare((LPPlace) this, (LPPlace) that)
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

    // TODO
    Boolean subsumes(Place that) {
        compare(that)
    }

    // remove redundant elements
    private static List<Variable> combineVarList(List<Variable> varList1, List<Variable> varList2) {
        return varList1 - varList2 + varList2
    }

    // TODO: I'm not sure it works for multiple links places
    // semaphor to avoid recursion
    private Boolean inReification = false

    // combine all the variables of the connected transitions (for link places)
    // it searches for the first node reified/specified in the given direction
    // the direction is by default forward (specification tells what information we require from the current content)
    // in case of collectors/sink places it is backward
    List<Variable> reifyTransitions(Boolean forwardDirection = true) {

        if (!link) {
            log.trace("${id} is specified.. "+toString())
            return expression.getVariables()
        }

        if (inReification) {
            log.trace("${id} in reification.. return to avoid recursion")
        }
        inReification = true

        log.trace("reifying ${id}, going forward? "+forwardDirection+"...")

        List<Variable> varList = []
        List<LPTransition> connectedTransitions = []

        if (forwardDirection) {
            if (outputs.size() == 0) {
                log.trace("${id}: dead end.")
                varList = []
            } else {
                for (arc in outputs) {
                    connectedTransitions << (LPTransition) arc.target
                }

                log.trace("${id}: considering ${connectedTransitions} in forward direction")

                for (transition in connectedTransitions) {
                    varList = combineVarList(varList, transition.reifyPlaces())
                }

                if (varList.size() == 0) {
                    log.trace("${id}: no variable collected, going backward")
                    forwardDirection = false
                    connectedTransitions = []
                }
            }
        }

        // backward direction
        if (!forwardDirection) {
            if (inputs.size() == 0) {
                log.trace("${id}: (backward) dead end.")
                varList = []
            } else {
                for (arc in inputs) {
                    connectedTransitions << (LPTransition) arc.source
                }

                log.trace("${id}: considering ${connectedTransitions} in backward direction")

                for (transition in connectedTransitions) {
                    varList = combineVarList(varList, transition.reifyPlaces(false))
                }

            }
        }

        log.trace("${id}: variables found: "+varList)
        varList
    }


}

