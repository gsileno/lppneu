package org.leibnizcenter.lppneu.components.language

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j

@Log4j @EqualsAndHashCode
class PosLiteral {

    // classic literal
    Atom functor
    List<Parameter> parameters

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
    static PosLiteral buildAnonymous(List<Parameter> parameters = []) {
        new PosLiteral(parameters: parameters)
    }

    static PosLiteral build(Atom functor, List<Parameter> parameters = []) {
        new PosLiteral(functor: functor, parameters: parameters)
    }

    PosLiteral reify() {
        List<Parameter> reifiedParameters = []
        for (param in parameters) {
            if (param.isVariable()) {
                if (!param.variable.identifier) {
                    throw new RuntimeException("Variable ${param} cannot be reified, there is no value.")
                }
                reifiedParameters << Parameter.build(Atom.build(param.variable.identifier))
            } else {
                reifiedParameters << param
            }
        }
        new PosLiteral(functor: functor, parameters: reifiedParameters)
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
