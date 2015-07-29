package org.leibnizcenter.lppneu.components.language

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j

@Log4j @EqualsAndHashCode
class ExpressionTriple {

    Expression positive
    Expression negative
    Expression nullified

    List<Operation> posList
    List<Operation> negList
    List<Operation> notList

    static ExpressionTriple build(Expression expression) {
        ExpressionTriple triple = new ExpressionTriple()

        if (expression.polarity() == Polarity.POS) {
            triple.positive = expression
            triple.negative = expression.negate()
            triple.nullified = expression.nullify()
        } else if (expression.polarity() == Polarity.NEG) {
            triple.positive = expression.negate()
            triple.negative = expression
            triple.nullified = expression.nullify()
        } else if (expression.polarity() == Polarity.NULL) {
            triple.positive = expression.negative()
            triple.negative = expression.positive()
            triple.nullified = expression
        }

        triple

    }

}
