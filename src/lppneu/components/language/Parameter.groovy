package lppneu.components.language

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j

@Log4j
@EqualsAndHashCode
class Parameter {
    PosLiteral literal
    Variable variable

    static Parameter build(Atom atom) {
        new Parameter(literal: PosLiteral.build(atom))
    }

    static Parameter build(PosLiteral literal) {
        new Parameter(literal: literal)
    }

    static Parameter build(Variable variable) {
        new Parameter(variable: variable)
    }

    Parameter minimalClone() {
        if (literal != null) {
            return new Parameter(
                    literal: literal.minimalClone()
            )
        } else if (variable != null) {
            new Parameter(
                    variable: variable.minimalClone()
            )
        }
    }

    Boolean isLiteral() {
        (literal != null)
    }

    Boolean isVariable() {
        (variable != null)
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
