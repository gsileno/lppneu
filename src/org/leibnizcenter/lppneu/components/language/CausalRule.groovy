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
    EventConditionExpression trigger
    Operation action

    Boolean isMechanism() {
        (trigger != null && action != null)
    }

    String toString() {
        String output = ""

        if (trigger != null)
           output += trigger.toString() + " "
        output += "=> " + action.toString()

        output
    }
}