package org.leibnizcenter.lppneu.components.position

import groovy.transform.AutoClone
import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Polarity

@Log4j @EqualsAndHashCode
class FactualPositionRef extends AbstractPositionRef {

    Expression label

    String toString() {
       return label
    }

    static FactualPositionRef build(Expression label) {
        if (label.polarity() != Polarity.POS) {
            throw new RuntimeException("Expression ${label} must have positive polarity to become target reference.")
        }
        new FactualPositionRef(
                label: label
        )
    }

    FactualPositionRef minimalClone() {
        new FactualPositionRef(
                label: label.minimalClone()
        )
    }

    FactualPositionRef reify() {
        new FactualPositionRef(
                label: label.reify()
        )
    }
}
