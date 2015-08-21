package org.leibnizcenter.lppneu.components.language

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j

@Log4j @EqualsAndHashCode
class Situation {
    Polarity polarity
    AbstractPositionRef positionRef
    Literal rootLiteral

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
        if (!expression.formula.operator.isUnary()) {
            return new Situation(
                    polarity: Polarity.POS,
            )
        } else {
            Expression content = expression.positive()
            Polarity polarity = expression.formula.operator.toPolarity()

            if (content.formula.inputFormulas.size() > 0) {
                return new Situation(
                        polarity: polarity,
                )
            } else {
                return new Situation(
                        polarity: polarity,
                        rootLiteral: content.formula.inputPorts[0].rootLiteral
                )
            }
        }
    }

    static Situation build(AbstractPositionRef positionRef, Polarity polarity = Polarity.POS) {
        new Situation(
                polarity: polarity,
                positionRef: positionRef
        )
    }

    Situation negate() {
            new Situation(
                    polarity: polarity.negate(),
                    positionRef: positionRef,
                    rootLiteral: rootLiteral
            )
    }

    Situation nullify() {
            new Situation(
                    polarity: polarity.NULL,
                    positionRef: positionRef,
                    rootLiteral: rootLiteral
            )
    }

    Situation positive() {
            new Situation(
                    polarity: polarity.POS,
                    positionRef: positionRef,
                    rootLiteral: rootLiteral
            )
    }

    Situation negative() {

            return new Situation(
                    polarity: polarity.NEG,
                    positionRef: positionRef,
                    rootLiteral: rootLiteral
            )
    }

    List<Parameter> getParameters() {
        if (rootLiteral) rootLiteral.getParameters()
        else throw new RuntimeException("Not yet implemented.")
    }

    List<Variable> getVariables() {
        if (rootLiteral) rootLiteral.getVariables()
        else throw new RuntimeException("Not yet implemented.")
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
