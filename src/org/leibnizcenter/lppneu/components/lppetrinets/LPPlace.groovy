package org.leibnizcenter.lppneu.components.lppetrinets

import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Operator
import org.leibnizcenter.pneu.components.petrinet.Place

class LPPlace extends Place {

    List<LPToken> marking = []

    // usually logic places contains a logic content
    Expression expression

    // unless they are just synchronization places
    Boolean link = false

    String toString() {
        if (expression != null) expression.toString() // + " LPlace@"+hashCode()
        else if (link) "*"
        else if (name) name // + "LPlace@"+hashCode()
        else ""
    }

    String toMinString() {
        if (expression != null) expression.toString() // + " LPlace@"+hashCode()
        else if (link) "*"
        else if (name) name // + " LPlace@"+hashCode()
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
                name: name,
                marking: marking.collect(),
                expression: expression,
                link: link
        )
    }

    static Boolean compare(LPPlace p1, LPPlace p2) {
        if (p1 == p2) return true
        if (p1.expression != p2.expression) return false
        if (p1.link != p2.link) return false
        return true
    }

}

