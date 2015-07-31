package org.leibnizcenter.lppneu.comparison

import org.leibnizcenter.lppneu.components.petrinets.LPlace
import org.leibnizcenter.lppneu.components.petrinets.LTransition
import org.leibnizcenter.pneu.components.petrinet.Arc
import org.leibnizcenter.pneu.components.petrinet.Net

class Comparison {

    // in order to avoid recursion, we check only the arcs which are defined in the net
    // the inputs and outputs element of the transitions and places are neglected
    // this means that two transitions with the same expression will be considered to be equal
    // possible optimization: first compare on the arcs, remove the checked places and transitions
    // and check only the remaining places and transitions after that

    static Boolean compare(Arc a1, Arc a2) {
        if (a1 == a2) return true
        if (a1.source.class != a2.source.class) return false
        if (a1.type != a2.type) return false
        if (a1.weight != a2.weight) return false
        if (!compare(a1.source, a2.source)) return false
        if (!compare(a1.target, a2.target)) return false
        return true
    }

    static Boolean compare(LTransition t1, LTransition t2) {
        if (t1 == t2) return true
        if (t1.operation != t2.operation) return false
        if (t1.operator != t2.operator) return false
        if (t1.link != t2.link) return false
        return true
    }

    static Boolean compare(LPlace p1, LPlace p2) {
        if (p1 == p2) return true
        if (p1.expression != p2.expression) return false
        if (p1.link != p2.link) return false
        return true
    }

    static Boolean compare(Net n1, Net n2) {

        // if they are the same object return true
        if (n1 == n2) return true

        // if they have a different number of places, transitions or arcs they are not the same
        if (n1.placeList.size() != n2.placeList.size()) { return false }
        if (n1.transitionList.size() != n2.transitionList.size()) { return false }
        if (n1.arcList.size() != n2.arcList.size()) { return false }
        if (n1.subNets.size() != n2.subNets.size()) { return false }

        // check all places
        for (p1 in n1.placeList) {
            Boolean found = false
            for (p2 in n2.placeList) {
                if (compare((LPlace) p1, (LPlace) p2)) {
                    found = true
                    break
                }
            }
            if (!found) return false
        }

        // check all transitions
        for (t1 in n1.transitionList) {
            Boolean found = false
            for (t2 in n2.transitionList) {
                if (compare((LTransition) t1, (LTransition) t2)) {
                    found = true
                    break
                }
            }
            if (!found) return false
        }

        // check all arcs
        for (a1 in n1.arcList) {
            Boolean found = false
            for (a2 in n2.arcList) {
                if (compare(a1, a2)) {
                    found = true
                    break
                }
            }
            if (!found) return false
        }

        // check all subnets
        for (subn1 in n1.subNets) {
            Boolean found = false
            for (subn2 in n2.subNets) {
                if (compare(subn1, subn2)) {
                    found = true
                    break
                }
            }
            if (!found) return false
        }

        return true
    }

}
