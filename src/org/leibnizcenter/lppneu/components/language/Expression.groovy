package org.leibnizcenter.lppneu.components.language

import commons.base.Formula
import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j

/**
 * Represents an expression (of situations)
 */
@Log4j @EqualsAndHashCode
class Expression {

    Formula<Situation> formula

    // a not elegant way to solve problem with non-static types of Formula
    private static Formula<Situation> PROTOTYPE = new Formula<>()

    static Expression build(Literal literal) {
        new Expression(
                formula: PROTOTYPE.build(Situation.build(literal))
        )
    }

    static Expression build(ExtLiteral extLiteral) {
        new Expression(
                formula: PROTOTYPE.build(Situation.build(extLiteral))
        )
    }

    static Expression build(Situation situation) {
        new Expression(
                formula: PROTOTYPE.build(situation)
        )
    }

    static Expression build(Formula<Situation> formula) {
        new Expression(
                formula: formula
        )
    }

    static Expression build(Formula<Situation> formula, Operator op) {
        new Expression(
                formula: PROTOTYPE.build(formula, op)
        )
    }

    static Expression build(Expression expression, Operator op) {
        new Expression(
                formula: PROTOTYPE.build(expression.formula, op)
        )
    }

    static Expression build(Expression left, Expression right, Operator op) {
        new Expression(
                formula: PROTOTYPE.build(left.formula, right.formula, op)
        )
    }

    static Expression buildFromExpressions(List<Expression> expressions, Operator op) {
        List<Formula> formulas = []
        for (expression in expressions) {
            formulas << expression.formula
        }
        new Expression(
                formula: PROTOTYPE.buildFromFormulas(formulas, op)
        )
    }

    static Expression buildFromSituations(List<Situation> situations, Operator op) {
        List<Formula> formulas = []
        for (situation in situations) {
            formulas << PROTOTYPE.build(situation)
        }
        new Expression(
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

    Expression negate() {
        return build(this, Operator.NEG)
    }

    Expression nullify() {
        return build(this, Operator.NULL)
    }

    // to obtain the positive content we can take the internal part of the proposition
    Expression positive() {
        if (!this.formula.operator.unary()) {
            log.warn("You should not be here."); return null
        }

        return build(this.formula.inputFormulas[0])
    }

    // to obtain the negated content we can negate the internal part
    Expression negative() {
        return this.positive().negate()
    }

    String toString() {
        formula.toString()
    }

}
