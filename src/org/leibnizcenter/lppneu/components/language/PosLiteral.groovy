package org.leibnizcenter.lppneu.components.language

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j

@Log4j
@EqualsAndHashCode
class PosLiteral {

    // classic literal
    Atom functor
    List<Parameter> parameters = [] // TODO: there is a BUG between tokens and expression with identifiers

    PosLiteral minimalClone() {
        List<Parameter> cloneParameters = []
        for (parameter in parameters) {
            cloneParameters << parameter.minimalClone()
        }

        new PosLiteral(
                functor: functor,
                parameters: cloneParameters
        )
    }

    // special case: literal with anonymous predicate
    static PosLiteral buildNoFunctorLiteral(List<Parameter> parameters = []) {
        new PosLiteral(parameters: parameters)
    }

    static PosLiteral build(Atom functor, List<Parameter> parameters = []) {
        new PosLiteral(functor: functor, parameters: parameters)
    }

    PosLiteral reify() {
        List<Parameter> reifiedParameters = []
        for (param in parameters) {
            if (param.isVariable()) {
                // TODO: check, maybe bug
//                if (!param.variable.identifier) {
//                    throw new RuntimeException("Variable ${param} cannot be reified, there is no value.")
//                }
                reifiedParameters << Parameter.build(Atom.build(param.variable.identifier))
            } else {
                reifiedParameters << param
            }
        }
        new PosLiteral(functor: functor, parameters: reifiedParameters)
    }

    Boolean subsumes(PosLiteral specific, Map<String, Map<String, String>> mapIdentifiers = [:]) {
        log.trace("checking $this against $specific")

        if (functor.name != specific.functor.name) return false

        if (parameters.size() != specific.parameters.size()) {
            log.trace("the two literals have a different number of parameters")
            return false
        }

        for (int i = 0; i < parameters.size(); i++) {
            if (parameters[i].isLiteral()) {
                String generalId = parameters[i].literal.functor.name
                if (specific.parameters[i].isLiteral()) {
                    if (!parameters[i].literal.subsumes(specific.parameters[i].literal))
                        return false
                } else if (specific.parameters[i].isVariable()) {
                    String specificVarString = specific.parameters[i].variable.name
                    String specificId = specific.parameters[i].variable.identifier

                    log.trace("specific id: ${specificId}")

                    // TODO: check for overlap between specific and general model variables
                    if (mapIdentifiers[specificVarString] == null) {
                        mapIdentifiers[specificVarString] = [:]
                    }
                    if (mapIdentifiers[specificVarString][specificId] == null) {
                        log.trace("not yet inserted in the the mapping")
                        mapIdentifiers[specificVarString][specificId] = generalId
                    } else if (mapIdentifiers[specificVarString][specificId] != generalId) {
                        log.trace("the mapped identifier does not correspond: " + mapIdentifiers[specificVarString][specificId] + " vs " + generalId)
                        return false
                    }
                }
            } else if (parameters[i].isVariable()) {
                String generalVarString = parameters[i].variable.name
                String generalId = parameters[i].variable.identifier

                log.trace("general id: ${generalId}")

                if (generalId == null)
                    throw new RuntimeException("the literal should be reified to check concrete subsumption")

                if (specific.parameters[i].isLiteral()) {
                    if (specific.parameters[i].literal.parameters.size() > 0) {
                        throw new RuntimeException("I was expecting only an identifier, not a predicate!")
                    }

                    String specificId = specific.parameters[i].literal.functor.name

                    log.trace("specific id: ${specificId}")

                    if (mapIdentifiers[generalVarString] == null) {
                        mapIdentifiers[generalVarString] = [:]
                    }
                    if (mapIdentifiers[generalVarString][generalId] == null) {
                        log.trace("not yet inserted in the the mapping")
                        mapIdentifiers[generalVarString][generalId] = specificId
                    } else if (mapIdentifiers[generalVarString][generalId] != specificId) {
                        log.trace("the mapped identifier does not correspond: " + mapIdentifiers[generalVarString][generalId] + " vs " + specificId)
                        return false
                    }
                } else if (specific.parameters[i].isVariable()) {
                    String specificVarString = specific.parameters[i].variable.name
                    String specificId = specific.parameters[i].variable.identifier

                    if (generalVarString != specificVarString) {
                        log.trace("variables are differents")
                    }

                    log.trace("specific id: ${specificId}")

                    if (mapIdentifiers[generalVarString] == null) {
                        mapIdentifiers[generalVarString] = [:]
                    }
                    if (mapIdentifiers[generalVarString][generalId] == null) {
                        log.trace("not yet inserted in the the mapping")
                        mapIdentifiers[generalVarString][generalId] = specificId
                    } else if (mapIdentifiers[generalVarString][generalId] != specificId) {
                        log.trace("the mapped identifier does not correspond: " + mapIdentifiers[generalVarString][generalId] + " vs " + specificId)
                        return false
                    }
                } else {
                    throw new RuntimeException("Yet to be implemented")
                }
            } else {
                throw new RuntimeException("Yet to be implemented")
            }
        }

        return true
    }

    List<Variable> getVariables() {
        List<Variable> variableList = []

        for (parameter in parameters) {
            if (parameter.variable)
                variableList << parameter.variable
        }
        variableList
    }

    String toString() {
        String output = ""
        if (functor)
            output += functor.name
        else
            output += "*"
        if (parameters) {
            output += "("
            for (parameter in parameters) {
                if (parameter.literal) output += parameter.literal.toString()
                else if (parameter.variable) output += parameter.variable.toString()
                output += ", "
            }
            output = output[0..-3]
            output += ")"
        }

        output
    }

}
