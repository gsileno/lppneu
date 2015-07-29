package org.leibnizcenter.lppneu.components.language

import groovy.transform.AutoClone

/**
 * Represents a relation of the type
 * premises -> conclusions  
 *
 * to be interpreted as:   
 * reasons -> conclusion
 **/

@AutoClone
class LogicRule {
    Expression head
    Expression body

    Boolean isFact() {
        (body == null)
    }

    Boolean isConstraint() {
        (head == null)
    }

    Boolean isRule() {
        !isFact() && !isConstraint()
    }

    String toString() {
        String output = ""

        if (head != null)
            output += head.toString()
        if (body != null)
            output += " :- " + body.toString()

        output
    }

    Expression toExpression() {
        if (isRule())
            Expression.build(body, head, Operator.IMPLIES)
        else if (isFact())
            head
        else if (isConstraint())
            Expression.build(body, Operator.INHIBITS)
    }

}