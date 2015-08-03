package org.leibnizcenter.lppneu.components.language

import groovy.util.logging.Log4j

@Log4j
class Triple {

    Expression positive
    Expression negative
    Expression nullified

    List<Operation> posList = []
    List<Operation> negList = []
    List<Operation> notList = []

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
