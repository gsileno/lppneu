package org.leibnizcenter.lpneu.components.language

import groovy.util.logging.Log4j

@Log4j
class Situation {
    Polarity polarity
    PositionRef positionRef
    Literal rootLiteral
    Expression rootExpression

    static Situation build(Literal literal, Polarity polarity = Polarity.POS) {
        new Situation(
                polarity: polarity,
                rootLiteral: literal
        )
    }

    static Situation build(ExtLiteral extLiteral) {
        new Situation(
                polarity: extLiteral.polarity,
                rootLiteral: extLiteral.literal
        )
    }

    static Situation build(Expression expression) {
        if (!expression.formula.operator.unary()) {
            return new Situation(
                    polarity: Polarity.POS,
                    rootExpression: expression
            )
        } else {
            Expression content = expression.positive()
            Polarity polarity = expression.formula.operator.toPolarity()

            if (content.formula.inputFormulas.size() > 0) {
                return new Situation(
                        polarity: polarity,
                        rootExpression: content
                )
            } else {
                return new Situation(
                        polarity: polarity,
                        rootLiteral: content.formula.inputPorts[0]
                )
            }
        }
    }

    static Situation build(PositionRef positionRef, Polarity polarity = Polarity.POS) {
        new Situation(
                polarity: polarity,
                positionRef: positionRef
        )
    }

    Situation negate() {
        if (rootExpression) {
            return Situation.build(rootExpression.negate())
        } else
            return new Situation(
                    polarity: polarity.negate(),
                    positionRef: positionRef,
                    rootLiteral: rootLiteral
            )
    }

    Situation nullify() {
        if (rootExpression) {
            return Situation.build(rootExpression.nullify())
        } else {
            new Situation(
                    polarity: polarity.NOT,
                    positionRef: positionRef,
                    rootLiteral: rootLiteral
            )
        }
    }

    Situation positive() {
        if (rootExpression) {
            return Situation.build(rootExpression.positive())
        } else {
            return new Situation(
                    polarity: polarity.POS,
                    positionRef: positionRef,
                    rootLiteral: rootLiteral
            )
        }
    }

    Situation negative() {
        if (rootExpression) {
            return Situation.build(rootExpression.negative())
        } else {
            return new Situation(
                    polarity: polarity.NEG,
                    positionRef: positionRef,
                    rootLiteral: rootLiteral
            )
        }
    }

    String toString() {
        String output = ""

        Boolean printOp = (polarity != Polarity.POS)

        if (printOp) output += polarity.toString() + "("
        if (rootLiteral) output += rootLiteral.toString()
        else output += positionRef.toString()
        if (printOp) output += ")"

        output
    }

    Event toEvent() {
        return new Event(
                operator: polarity.toOperator(),
                positionRef: positionRef,
                rootLiteral: rootLiteral
        )
    }

}
