package org.leibnizcenter.lppneu.components.language

import groovy.transform.AutoClone

/**
 * Represents a relation of the type
 * antecedent -> consequent
 *
 * to be interpreted as:   
 * cause -> effect
 **/

@AutoClone
class CausalRule {
    Expression condition
    Operation action

    Boolean isMechanism() {
        (condition != null && action != null)
    }

    Boolean isEventSeries() {
        (condition == null && action != null)
    }

    String toString() {
        String output = ""

        if (condition != null)
           output += condition.toString() + " "
        output += "=> " + action.toString()

        output
    }
}