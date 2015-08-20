package org.leibnizcenter.lppneu.components.language

import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.lppetrinets.LPPlace
import org.leibnizcenter.lppneu.components.lppetrinets.LPTransition

@Log4j
class Triple {

    Expression positive
    Expression negative
    Expression nullified

    List<Operation> posOperationList = []
    List<Operation> negOperationList = []
    List<Operation> nullOperationList = []

    // for the net

    LPPlace positivePlace
    LPPlace negativePlace
    LPPlace nullPlace

    List<LPTransition> posTransitionList = []
    List<LPTransition> negTransitionList = []
    List<LPTransition> nullTransitionList = []

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
