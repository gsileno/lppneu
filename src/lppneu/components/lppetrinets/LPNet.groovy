package lppneu.components.lppetrinets

import lppneu.components.language.Expression
import lppneu.components.language.Operation
import lppneu.components.language.Operator
import pneu.components.petrinet.Net
import pneu.components.petrinet.Place
import pneu.components.petrinet.Transition
import pneu.components.petrinet.TransitionType

class LPNet extends Net {

    // an emitter transition is a natural input
    Transition createEmitterTransition() {
        Transition tr = createLinkTransition()
        tr.type = TransitionType.EMITTER
        inputs << tr
        tr
    }

    // a collector transition is a natural output
    Transition createCollectorTransition() {
        Transition tr = createLinkTransition()
        tr.type = TransitionType.COLLECTOR
        outputs << tr
        tr
    }

    Transition createTransition(String label) {
        Transition tr = new LPTransition()
        if (label) {
            tr.operation = Operation.parse(label)
        }
        transitionList << tr
        tr
    }

    Transition createTransition(Operation operation) {
        Transition tr = new LPTransition(operation: operation)
        transitionList << tr
        tr
    }

    Transition createTransition(Operator operator) {
        Transition tr = new LPTransition(operator: operator)
        transitionList << tr
        tr
    }

    LPPlace createPlace(String label) {
        Place pl = new LPPlace()
        pl.expression = Expression.parse(label)
        placeList << pl
        pl
    }

    LPPlace createPlace(Expression expression) {
        LPPlace pl = new LPPlace(expression: expression)
        placeList << pl
        pl
    }

    Transition createLinkTransition() {
        Transition tr = new LPTransition(link: true)
        transitionList << tr
        tr
    }

    LPPlace createLinkPlace() {
        Place pl = new LPPlace(link: true)
        placeList << pl
        pl
    }

    Net minimalCloneNoRecursive() {
        new LPNet(transitionList: transitionList.collect(),
                placeList: placeList.collect(),
                arcList: arcList.collect(),
                inputs: inputs.collect(),
                outputs: outputs.collect(),
                function: function)
    }

    ////////// Special case: link elements, which propagate data without explicit declaration

}
