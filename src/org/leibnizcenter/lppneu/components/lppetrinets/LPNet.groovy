package org.leibnizcenter.lppneu.components.lppetrinets

import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Operation
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

    LPPlace createPlace(String label = null) {
        LPPlace pl = new LPPlace()
        if (label) {
            pl.expression = Expression.parse(label)
        }
        placeList << pl
        pl
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
