package org.leibnizcenter.lppneu.components.petrinets

import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Operator
import org.leibnizcenter.pneu.components.petrinet.Place

class LPlace extends Place {

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

    @Override
    Boolean isLink() {
        link
    }

    @Override
    Boolean isCluster() {
        if (expression.formula.operator == Operator.ASSOCIATION) return false
        return true
    }

}

