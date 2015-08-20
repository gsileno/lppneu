package org.leibnizcenter.lppneu.components.language

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j

@Log4j @EqualsAndHashCode
class Parameter {
    Literal literal
    Variable variable

    static Parameter build(Literal literal) {
        new Parameter(literal: literal)
    }

    static Parameter build(Variable variable) {
        new Parameter(variable: variable)
    }

    //////////////////
    // Views
    //////////////////

    String toString() {
        String output = ""

        if (literal) output += literal
        else if (variable) output += variable

        output
    }

}
