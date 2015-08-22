package org.leibnizcenter.lppneu.components.lppetrinets

import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Operation
import org.leibnizcenter.lppneu.components.language.Operator
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Transition

class LPPlace extends Place {

    List<LPToken> marking = []

    // usually logic places contains a logic content
    Expression expression

    // unless they are just synchronization places
    Boolean link = false

    String toString() {
        if (expression != null) expression.toString() // + " LPlace@"+hashCode()
        else if (link) "*"
        else ""
    }

    String label() {
        if (expression != null) expression.toString() // + " LPlace@"+hashCode()
        else if (link) "*"
        else ""
    }

    Boolean isLink() {
        link
    }

    Boolean isCluster() {
        if (expression.formula.operator == Operator.ASSOCIATION) return false
        return true
    }

    LPPlace clone() {
        return new LPPlace(
                marking: marking.collect(),
                expression: expression,
                link: link
        )
    }

    static LPPlace build() {
        return new LPPlace()
    }

    static LPPlace build(String label) {
        return new LPPlace(expression: Expression.parse(label))
    }

    static Boolean compare(Place p1, Place p2) {
        return compare((LPPlace) p1, (LPPlace) p2)
    }

    static Boolean compare(LPPlace p1, LPPlace p2) {
        if (p1 == p2) return true
        if (p1.expression != p2.expression) return false
        if (p1.link != p2.link) return false
        return true
    }

}

