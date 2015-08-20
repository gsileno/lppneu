package org.leibnizcenter.lppneu.components.language

import groovy.transform.AutoClone
import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j

@Log4j @EqualsAndHashCode @AutoClone
class Literal {

    // classic literal
    Atom functor
    List<Parameter> parameters

    Boolean grounded = false

    static Literal build(Atom functor, List<Parameter> parameters = []) {
        new Literal(functor: functor, parameters: parameters)
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
