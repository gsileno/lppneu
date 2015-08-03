package org.leibnizcenter.lppneu.components.petrinets

import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.language.Event
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Operation
import org.leibnizcenter.lppneu.components.language.Operator
import org.leibnizcenter.pneu.components.petrinet.Transition

@Log4j
class LTransition extends Transition {

    // for causal dependencies
    Operation operation

    // for logic dependencies
    Operator operator

    // if they are just synchronization places
    Boolean link = false

    String toString() {
        if (operation != null) operation.toString() // + " LTransition@"+hashCode()
        else if (operator != null) {
            return operator.toString() // + " LTransition@"+hashCode()
        } else if (link) "*"
        else {
            name // + " LTransition@"+hashCode()
        }
    }

    Operation reconstructOperation() {
        if (operation) return operation
        else if (operator) {

            log.trace("Reconstructing the original logic dependency...")

            List<Expression> inputExpressions = []
            for (input in inputs) {
                if (!((LPlace) (input.source)).expression)
                    throw new RuntimeException("Expression cannot be null")
                inputExpressions << ((LPlace) (input.source)).expression
            }

            log.trace("...involving ${inputExpressions} under ${operator}")

            return Expression.buildFromExpressions(inputExpressions, operator).toOperation()
        }
    }

    @Override
    Boolean isLink() {
        return link
    }

}
