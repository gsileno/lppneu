package org.leibnizcenter.lpneu.components.petrinets

import org.leibnizcenter.lpneu.components.language.Event
import org.leibnizcenter.lpneu.components.language.Operation
import org.leibnizcenter.pneu.components.petrinet.Transition

class LTransition extends Transition {

    Event event
    Operation operation

    void setEvent(Event e) {
        event = e
        name = e.toString()
    }

    void setOperation(Operation o) {
        operation = o
        name = o.toString()
    }

}
