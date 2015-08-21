package org.leibnizcenter.lppneu.components.position

import groovy.transform.AutoClone
import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.language.Expression

@Log4j @EqualsAndHashCode
class FactualPositionRef extends AbstractPositionRef {

    Expression label

    String toString() {
       return label
    }

    static FactualPositionRef build(Expression label) {
        new FactualPositionRef(
                label: label
        )
    }

    FactualPositionRef minimalClone() {
        new FactualPositionRef(
                label: label
        )
    }
}
