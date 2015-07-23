package org.leibnizcenter.lppneu.components.language

/**
 * Represents a relation of the type
 * antecedent -> consequent
 *
 * to be interpreted as:   
 * cause -> effect
 **/

class CausalRule {
    EventConditionExpression trigger
    Operation action

    String toString() {
        String output = ""

        if (trigger != null)
           output += trigger.toString() + " "
        output += "=> " + action.toString()

        output
    }
}