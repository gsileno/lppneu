package org.leibnizcenter.lppneu.components.language

import commons.base.Formula
import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j

/**
 * Represents an expression (of situations)
 */
@Log4j @EqualsAndHashCode
class EventConditionExpression {

    Formula<EventCondition> formula

    // a not elegant way to solve problem with non-static types of Formula
    private static Formula<EventCondition> PROTOTYPE = new Formula<>()

    static EventConditionExpression build(Event event) {
        new EventConditionExpression(
                formula: PROTOTYPE.build(EventCondition.build(event))
        )
    }

    static EventConditionExpression build(Expression condition) {
        new EventConditionExpression(
                formula: PROTOTYPE.build(EventCondition.build(condition))
        )
    }

    static EventConditionExpression build(Event event, Expression condition) {
        new EventConditionExpression(
                formula: PROTOTYPE.build(EventCondition.build(event, condition))
        )
    }

    static EventConditionExpression build(Formula<EventCondition> formula, Operator op) {
        new EventConditionExpression(
                formula: PROTOTYPE.build(formula, op)
        )
    }

    static EventConditionExpression build(EventConditionExpression expression, Operator op) {
        new EventConditionExpression(
                formula: PROTOTYPE.build(expression.formula, op)
        )
    }

    static EventConditionExpression build(EventConditionExpression left, EventConditionExpression right, Operator op) {
        new EventConditionExpression(
                formula: PROTOTYPE.build(left.formula, right.formula, op)
        )
    }

    static EventConditionExpression buildFromExpressions(List<EventConditionExpression> expressions, Operator op) {
        List<Formula> formulas = []
        for (expression in expressions) {
            formulas << expression.formula
        }
        new EventConditionExpression(
                formula: PROTOTYPE.buildFromFormulas(formulas, op)
        )
    }

    Polarity polarity() {
        if (formula.operator == Operator.NEG)
            return Polarity.NEG
        else if (formula.operator == Operator.NULL)
            return Polarity.NOT
        else
            return Polarity.POS
    }

    EventConditionExpression negate() {
        return build(this, Operator.NEG)
    }

    EventConditionExpression nullify() {
        return build(this, Operator.NULL)
    }

    // to obtain the positive content we can take the internal part of the proposition
    EventConditionExpression positive() {
        if (!this.formula.operator.unary()) {
            log.warn("You should not be here."); return null
        }

        return build(this.formula.inputFormulas[0], Operator.POS)
    }

    // to obtain the negated content we can negate the internal part
    EventConditionExpression negative() {
        return this.positive().negate()
    }

    String toString() {
        formula.toString()
    }

}
