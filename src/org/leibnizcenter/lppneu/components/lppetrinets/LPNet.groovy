package org.leibnizcenter.lppneu.components.lppetrinets

import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Operation
import org.leibnizcenter.lppneu.components.language.Variable
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

    Transition createTransition(Operation operation) {
        LPTransition tr = new LPTransition(operation: operation)
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

    LPPlace createPlace(Expression expression) {
        LPPlace pl = new LPPlace(expression: expression)
        placeList << pl
        pl
    }

    ////////// Special case: link elements, which propagate data without explicit declaration

    // remove redundant elements
    private static List<String> combineVarList(List<String> varList1, List<String> varList2) {
        return varList1 - varList2 + varList2
    }

    private static List<String> combineVarList(List<List<String>> varLists) {
        List<String> combinedVarList = []
        for (varList in varLists) {
            combinedVarList = combinedVarList - varList + varList
        }
        combinedVarList
    }

    Transition createLinkTransition(Operation operation = null) {
        LPTransition tr = new LPTransition(link: true, operation: operation)
        transitionList << tr
        tr
    }

    LPPlace createLinkPlace(Expression expression = null) {
        LPPlace pl = new LPPlace(link: true, expression: expression)
        placeList << pl
        pl
    }

    // TODO: add checks for correct bindings

    // the bridge transition is meant to produce what is in the last place
    // what is already described in the first transition will be transmitted,
    // the rest will be lost
    Transition createBridge(Place p1, Place p2) {
        if (!getAllPlaces().contains(p1) || !getAllPlaces().contains(p2)) {
            throw new RuntimeException("Error: this net does not contain the place(s) to bridge")
        }
        LPPlace lpp1 = (LPPlace) p1
        LPPlace lpp2 = (LPPlace) p2

        List<String> varStringList

        if (lpp1.expression != null && lpp2.expression != null) {
            varStringList = combineVarList(Variable.toVarStringList(lpp1.expression.getVariables()),
                    Variable.toVarStringList(lpp2.expression.getVariables()))
        } else if (lpp1.expression != null) {
            varStringList = Variable.toVarStringList(lpp1.expression.getVariables())
        } else if (lpp2.expression != null) {
            varStringList = Variable.toVarStringList(lpp2.expression.getVariables())
        } else {
            throw new RuntimeException("Both places in the bridge cannot be underspecified")
        }

        Transition tBridge
        if (varStringList) {
            tBridge = createLinkTransition(Operation.buildBridgeFromVarStringList(varStringList))
        } else {
            tBridge = createLinkTransition()
        }

        createArc(p1, tBridge)
        createArc(tBridge, p2)

        tBridge
    }

    Transition createNexus(List<Place> inputs, List<Place> outputs, List<Place> biflows, List<Place> diode, List<Place> inhibitors) {

        List<String> varStringList = []

        for (p in inputs+outputs+biflows+diode+inhibitors) {
            LPPlace lpp = (LPPlace) p
            varStringList = combineVarList(varStringList, Variable.toVarStringList(lpp.expression.getVariables()))
        }

        Transition tBridge
        if (varStringList) {
            tBridge = createLinkTransition(Operation.buildBridgeFromVarStringList(varStringList))
        } else {
            tBridge = createLinkTransition()
        }

        for (p in inputs + biflows) {
            if (!getAllPlaces().contains(p)) {
                throw new RuntimeException("Error: this net does not contain the given input place (${p})")
            }
            createArc(p, tBridge)
        }

        for (p in outputs + biflows + diode) {
            if (!getAllPlaces().contains(p)) {
                throw new RuntimeException("Error: this net does not contain the given output place (${p})")
            }
            createArc(tBridge, p)
        }

        for (p in inhibitors + diode) {
            if (!getAllPlaces().contains(p)) {
                throw new RuntimeException("Error: this net does not contain the given inhibitor place (${p})")
            }
            createInhibitorArc(p, tBridge)
        }

        tBridge
    }

    // the first transition is meant to produce what is in the bridge place
    // at the same time, the second transition necessarily consumes what is in the bridge place,
    // which has therefore to be produced somewhere, this can be seen "context" information
    Place createBridge(Transition t1, Transition t2) {
        if (!getAllTransitions().contains(t1) || !getAllTransitions().contains(t2)) {
            throw new RuntimeException("Error: this net does not contain the transition(s) to bridge")
        }
        LPTransition lpt1 = (LPTransition) t1
        LPTransition lpt2 = (LPTransition) t2

        List<String> varStringList

        if (lpt1.operation != null && lpt2.operation != null) {
            varStringList = combineVarList(Variable.toVarStringList(lpt1.operation.getVariables()),
                    Variable.toVarStringList(lpt2.operation.getVariables()))
        } else if (lpt1.operation != null) {
            varStringList = Variable.toVarStringList(lpt1.operation.getVariables())
        } else if (lpt2.operation != null) {
            varStringList = Variable.toVarStringList(lpt2.operation.getVariables())
        } else {
            throw new RuntimeException("Both transitions in the bridge cannot be underspecified")
        }

        Place pBridge
        if (varStringList) {
            pBridge = createLinkPlace(Expression.buildBridgeFromVarStringList(varStringList))
        } else {
            pBridge = createLinkPlace()
        }
        createArc(t1, pBridge)
        createArc(pBridge, t2)

        pBridge
    }

    Place createDiodeBridge(Transition t1, Transition t2) {
        if (!getAllTransitions().contains(t1) || !getAllTransitions().contains(t2)) {
            throw new RuntimeException("Error: this net does not contain the transition(s) to bridge")
        }

        LPTransition lpt1 = (LPTransition) t1
        LPTransition lpt2 = (LPTransition) t2

        List<String> varStringList

        if (lpt1.operation != null && lpt2.operation != null) {
            varStringList = combineVarList(Variable.toVarStringList(lpt1.operation.getVariables()),
                    Variable.toVarStringList(lpt2.operation.getVariables()))
        } else if (lpt1.operation != null) {
            varStringList = Variable.toVarStringList(lpt1.operation.getVariables())
        } else if (lpt2.operation != null) {
            varStringList = Variable.toVarStringList(lpt2.operation.getVariables())
        } else {
            throw new RuntimeException("Both transitions in the bridge cannot be underspecified")
        }

        Place pBridge
        if (varStringList) {
            pBridge = createLinkPlace(Expression.buildBridgeFromVarStringList(varStringList))
        } else {
            pBridge = createLinkPlace()
        }
        createDiodeArc(t1, pBridge)
        createArc(pBridge, t2)
        pBridge
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
