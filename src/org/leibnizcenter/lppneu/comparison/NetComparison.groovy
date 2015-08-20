package org.leibnizcenter.lppneu.comparison

import org.leibnizcenter.lppneu.components.lppetrinets.LPPlace
import org.leibnizcenter.lppneu.components.lppetrinets.LPTransition

// TO DO: refactoring

class NetComparison {

    // in order to avoid recursion, we check only the arcs which are defined in the net
    // the inputs and outputs element of the transitions and places are neglected
    // this means that two transitions with the same expression will be considered to be equal
    // possible optimization: first compare on the arcs, remove the checked places and transitions
    // and check only the remaining places and transitions after that



    static Boolean compare(LPTransition t1, LPTransition t2) {
        if (t1 == t2) return true
        if (t1.operation != t2.operation) return false
        if (t1.operator != t2.operator) return false
        if (t1.link != t2.link) return false
        return true
    }

    static Boolean compare(LPPlace p1, LPPlace p2) {
        if (p1 == p2) return true
        if (p1.expression != p2.expression) return false
        if (p1.link != p2.link) return false
        return true
    }


}
