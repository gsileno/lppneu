package org.leibnizcenter.lppneu.components.language

import groovy.transform.AutoClone
import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j
import org.apache.tools.ant.taskdefs.XSLTProcess

@Log4j @EqualsAndHashCode
class Literal {

    // classic literal
    Atom functor
    List<Parameter> parameters

    Literal minimalClone() {
        List<Parameter> cloneParameters = []
        for (parameter in parameters) {
            cloneParameters << parameter.minimalClone()
        }

        new Literal(
                functor: functor,
                parameters: cloneParameters
        )
    }

    static Literal build(Atom functor, List<Parameter> parameters = []) {
        new Literal(functor: functor, parameters: parameters)
    }

    Literal reify() {
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
        new Literal(functor: functor, parameters: reifiedParameters)
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
        String output = functor.name
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
