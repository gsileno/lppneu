package org.leibnizcenter.lppneu.components.language

import groovy.util.logging.Log4j

@Log4j
class PositionTriple {

    Position positive
    Position negative
    Position nullified

    List<Operation> posList
    List<Operation> negList
    List<Operation> notList

    static PositionTriple build(Position position) {
        PositionTriple triple = new PositionTriple()

        Expression expression = position.ref.label

        if (expression.polarity() == Polarity.POS) {
            triple.positive = position
            triple.negative = position.negate()
            triple.nullified = position.nullify()
        } else if (expression.polarity() == Polarity.NEG) {
            triple.positive = position.negate()
            triple.negative = position
            triple.nullified = position.nullify()
        } else if (expression.polarity() == Polarity.NOT) {
            triple.positive = position.negative()
            triple.negative = position.positive()
            triple.nullified = position
        }
        triple
    }

}
