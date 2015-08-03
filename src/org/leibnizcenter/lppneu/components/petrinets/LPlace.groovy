package org.leibnizcenter.lppneu.components.petrinets

import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.pneu.components.petrinet.Place

class LPlace extends Place {

    // usually logic places contains a logic content
    Expression expression

    // unless they are just synchronization places
    Boolean link = false

    String toString() {
        if (expression) expression.toString() // + " LPlace@"+hashCode()
        else if (link) "*"
        else name // + "LPlace@"+hashCode()
    }

    String toMinString() {
        if (expression) expression.toString() // + " LPlace@"+hashCode()
        else if (link) "*"
        else name // + " LPlace@"+hashCode()
    }

    @Override
    Boolean isLink() {
        link
    }

}

