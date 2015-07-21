package org.leibnizcenter.lppneu.components.petrinets

import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Situation
import org.leibnizcenter.pneu.components.petrinet.Place

class LPlace extends Place {

    Expression expression
    Situation situation

    void setSituation(Situation s) {
        situation = s
        name = s.toString()
    }

    void setExpression(Expression e) {
        expression = e
        name = e.toString()
    }


}
