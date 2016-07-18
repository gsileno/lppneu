package lppneu.components.language

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

    Boolean biconditional

    Boolean isFact() {
        (body == null)
    }

    Boolean isBiconditional() {
        (biconditional)
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
        if (body != null) {
            if (isBiconditional()) output += " ::- "
            else output += " :- "
            output += body.toString()
        }

        output
    }

    Expression toExpression() {
        if (isRule())
            if (isBiconditional()) {
                Expression.build(body, head, Operator.DEFINES)
            } else {
                Expression.build(body, head, Operator.IMPLIES)
            }
        else if (isFact())
            head
        else if (isConstraint())
            Expression.build(body, Operator.FORBIDS)
    }

}