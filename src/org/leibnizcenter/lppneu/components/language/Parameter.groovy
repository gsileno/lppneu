package org.leibnizcenter.lppneu.components.language

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j

@Log4j @EqualsAndHashCode
class Parameter {
    Atom atom
    Variable variable

    static Parameter build(Atom atom) {
        new Parameter(atom: atom)
    }

    static Parameter build(Variable variable) {
        new Parameter(variable: variable)
    }

    //////////////////
    // Views
    //////////////////

    String toString() {
        String output = ""

        if (atom) output += atom
        else if (variable) output += variable

        output
    }

}
