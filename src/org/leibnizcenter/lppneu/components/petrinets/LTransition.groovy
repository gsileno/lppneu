package org.leibnizcenter.lppneu.components.petrinets

import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.language.Event
import org.leibnizcenter.lppneu.components.language.Operation
import org.leibnizcenter.lppneu.components.language.Operator
import org.leibnizcenter.pneu.components.petrinet.Transition

@Log4j
class LTransition extends Transition {

    // for causal dependencies
    Event event
    Operation operation

    // for logic dependencies
    Operator operator

    String toString() {
        if (event != null) event.toString()
        else if (operation != null) operation.toString()
        else if (operator != null) {
            String output = operator.toString()
//            output += "("
//            for (input in inputs) {
//                output += input.source.name + ", "
//            }
//            output = output[0..-3] + ")"
            return output
        }
    }
}
