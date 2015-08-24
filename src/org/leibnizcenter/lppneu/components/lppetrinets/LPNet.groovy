package org.leibnizcenter.lppneu.components.lppetrinets

import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Operation
import org.leibnizcenter.pneu.components.petrinet.Arc
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Transition
import org.leibnizcenter.pneu.components.petrinet.TransitionType

class LPNet extends Net {

    // an emitter transition is a natural input
    Transition createEmitterTransition() {
        LPTransition tr = new LPTransition(type: TransitionType.EMITTER)
        transitionList << tr
        inputs << tr
        tr
    }

    // a collector transition is a natural output
    Transition createCollectorTransition() {
        LPTransition tr = new LPTransition(type: TransitionType.COLLECTOR)
        transitionList << tr
        outputs << tr
        tr
    }

    Transition createTransition(String label = null) {
        LPTransition tr = new LPTransition()
        if (label) {
            tr.operation = Operation.parse(label)
        }
        transitionList << tr
        tr
    }

    Transition createTransition(Expression expression) {
        LPTransition tr = new LPTransition(operation: expression.toOperation())
        transitionList << tr
        tr
    }

    LPPlace createPlace(String label = null) {
        LPPlace pl = new LPPlace()
        if (label) {
            pl.expression = Expression.parse(label)
        }
        placeList << pl
        pl
    }

    LPPlace createPlace(Operation operation) {
        LPPlace pl = new LPPlace(expression: operation.toExpression())
        placeList << pl
        pl
    }

    // TODO: add checks for correct bindings

    // the bridge transition is meant to produce what is in the last place
    void createBridge(Place p1, Place p2) {
        if (!placeList.contains(p1) || !placeList.contains(p2)) {
            throw new RuntimeException("Error: this net does not contain the place(s) to bridge")
        }
        LPPlace lpp = (LPPlace) p2
        Transition tBridge = createTransition(lpp.expression)
        arcList += Arc.buildArcs(p1, tBridge, p2)
    }

    // the first transition is meant to produce what is in the bridge place
    // at the same time, the second transition necessarily consumes what is in the bridge place,
    // which has therefore to be produced somewhere, this is a context
    void createBridge(Transition t1, Transition t2) {
        if (!transitionList.contains(t1) || !transitionList.contains(t2)) {
            throw new RuntimeException("Error: this net does not contain the transition(s) to bridge")
        }
        LPTransition lpt = (LPTransition) t1
        Place pBridge = createPlace(lpt.operation)
        arcList += Arc.buildArcs(t1, pBridge, t2)
    }

    // deep cloning done for nets
    // only the net structure is cloned, all the elements remains the same (e.g. places, transitions, etc.)
    Net minimalClone(Map<Net, Net> sourceCloneMap = [:]) {

        if (!sourceCloneMap[this]) {
            sourceCloneMap[this] = new LPNet(transitionList: transitionList.collect(),
                    placeList: placeList.collect(),
                    arcList: arcList.collect(),
                    inputs: inputs.collect(),
                    outputs: outputs.collect(),
                    function: function)
        }

        Net clone = sourceCloneMap[this]

        for (subNet in subNets) {
            if (!sourceCloneMap[subNet]) {
                sourceCloneMap[subNet] = subNet.minimalClone(sourceCloneMap)
            }
            clone.subNets << sourceCloneMap[subNet]
        }

        for (parent in parents) {
            if (!sourceCloneMap[parent]) {
                sourceCloneMap[parent] = parent.minimalClone(sourceCloneMap)
            }
            clone.parents << sourceCloneMap[parent]
        }

        clone
    }

}
