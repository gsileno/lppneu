package org.leibnizcenter.lppneu.components.language

import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.petrinets.LPlace
import org.leibnizcenter.lppneu.components.petrinets.LTransition

@Log4j
class Triple {

    Expression positive
    Expression negative
    Expression nullified

    List<Operation> posOperationList = []
    List<Operation> negOperationList = []
    List<Operation> nullOperationList = []

    // for the net

    LPlace positivePlace
    LPlace negativePlace
    LPlace nullPlace

    List<LTransition> posTransitionList = []
    List<LTransition> negTransitionList = []
    List<LTransition> nullTransitionList = []

    static Triple build(Expression position) {
        Triple triple = new Triple()

        if (position.polarity() == Polarity.POS) {
            triple.positive = position
            triple.negative = position.negate()
            triple.nullified = position.nullify()
        } else if (position.polarity() == Polarity.NEG) {
            triple.positive = position.negate()
            triple.negative = position
            triple.nullified = position.nullify()
        } else if (position.polarity() == Polarity.NULL) {
            triple.positive = position.negative()
            triple.negative = position.positive()
            triple.nullified = position
        }
        triple
    }

}
