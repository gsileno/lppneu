package lppneu.components.position

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j
import lppneu.components.language.Expression
import lppneu.components.language.Operation

@Log4j @EqualsAndHashCode
class FactualPosition extends AbstractPosition {

    FactualPosition parent
    FactualPositionRef ref

    FactualPosition minimalClone() {
        new FactualPosition(
                parent: parent,
                ref: ref.minimalClone()
        )
    }

    static FactualPosition build(Expression expression, FactualPosition parent = null) {
        new FactualPosition(
            parent: parent,
            ref: FactualPositionRef.build(expression)
        )
    }

    FactualPosition negate() {
        FactualPosition negated = minimalClone()
        negated.ref.label = negated.ref.label.negate()
        negated
    }

    FactualPosition nullify() {
        FactualPosition nullified = minimalClone()
        nullified.ref.label = nullified.ref.label.nullify()
        nullified
    }

    FactualPosition positive() {
        FactualPosition positive = minimalClone()
        positive.ref.label = positive.ref.label.positive()
        positive
    }

    FactualPosition negative() {
        FactualPosition negative = minimalClone()
        negative.ref.label = negative.ref.label.negative()
        negative
    }

    Operation toOperation() {
        ref.label.toOperation()
    }

    Expression toExpression() {
        ref.label
    }
}
