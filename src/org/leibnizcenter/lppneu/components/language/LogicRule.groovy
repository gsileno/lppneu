package org.leibnizcenter.lppneu.components.language

/**
 * Represents a relation of the type
 * premises -> conclusions  
 *
 * to be interpreted as:   
 * reasons -> conclusion
 **/

class LogicRule {
    Expression head
    Expression body

    String toString() {
        String output = ""

        if (head != null)
            output += head.toString()
        if (body != null)
            output += " :- " + body.toString()

        output
    }
}