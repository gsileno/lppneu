package lppneu.components.position

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j
import lppneu.components.language.Expression
import lppneu.components.language.Polarity

@Log4j @EqualsAndHashCode
class FactualTriple extends AbstractTriple {

    FactualPosition positive
    FactualPosition negative
    FactualPosition nullified

    static FactualTriple build(Expression expression) {
        FactualTriple triple = new FactualTriple()
        FactualPosition position = FactualPosition.build(expression)

        if (expression.polarity() == Polarity.POS) {
            triple.positive = position
            triple.negative = position.negate()
            triple.nullified = position.nullify()
        } else if (expression.polarity() == Polarity.NEG) {
            triple.positive = position.negate()
            triple.negative = position
            triple.nullified = position.nullify()
        } else if (expression.polarity() == Polarity.NULL) {
            triple.positive = position.negative()
            triple.negative = position.positive()
            triple.nullified = position
        }
        triple
    }

}
