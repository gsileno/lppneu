package org.leibnizcenter.lppneu.components.language

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.position.AbstractPositionRef

@Log4j
@EqualsAndHashCode
class Situation {

    Polarity polarity
    AbstractPositionRef positionRef
    PosLiteral rootLiteral // root literal are for primitive perceptions
    PosLiteral factLiteral

    Situation minimalClone() {
        if (rootLiteral) {
            new Situation(
                    polarity: polarity,
                    rootLiteral: rootLiteral.minimalClone(),
            )
        } else if (factLiteral) {
            new Situation(
                    polarity: polarity,
                    factLiteral: factLiteral.minimalClone()
            )
        } else {
            new Situation(
                    polarity: polarity,
                    positionRef: positionRef
            )
        }
    }

    static Situation build(PosLiteral literal, Polarity polarity = Polarity.POS) {
        new Situation(
                polarity: polarity,
                factLiteral: literal
        )
    }

    static Situation build(Literal extLiteral) {
        new Situation(
                polarity: extLiteral.polarity,
                factLiteral: extLiteral.literal
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
                        factLiteral: content.formula.inputPorts[0].factLiteral
                )
            }
        }
    }

    static Situation buildFromRootLiteral(Literal extLiteral) {
        new Situation(
                polarity: extLiteral.polarity,
                rootLiteral: extLiteral.literal
        )
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
                rootLiteral: rootLiteral,
                factLiteral: factLiteral
        )
    }

    Situation nullify() {
        new Situation(
                polarity: polarity.NULL,
                positionRef: positionRef,
                rootLiteral: rootLiteral,
                factLiteral: factLiteral
        )
    }

    Situation positive() {
        new Situation(
                polarity: polarity.POS,
                positionRef: positionRef,
                rootLiteral: rootLiteral,
                factLiteral: factLiteral
        )
    }

    Situation negative() {
        return new Situation(
                polarity: polarity.NEG,
                positionRef: positionRef,
                rootLiteral: rootLiteral,
                factLiteral: factLiteral
        )
    }

    List<Parameter> getParameters() {
        if (rootLiteral) rootLiteral.getParameters()
        else if (factLiteral) factLiteral.getParameters()
        else if (positionRef) positionRef.getParameters()
        else throw new RuntimeException("Not yet implemented.")
    }

    List<Variable> getVariables() {
        if (rootLiteral) rootLiteral.getVariables()
        else if (factLiteral) factLiteral.getVariables()
        else if (positionRef) positionRef.getVariables()
        else throw new RuntimeException("Not yet implemented.")
    }

    String toString() {
        String output = ""

        Boolean printOp = (polarity != Polarity.POS)

        if (printOp) output += polarity.toString() + "("
        if (factLiteral) output += factLiteral.toString()
        else if (rootLiteral) output += "." + rootLiteral.toString()
        else output += positionRef.toString()
        if (printOp) output += ")"

        output
    }

    Situation reify() {
        if (positionRef) {
            new Situation(
                    polarity: polarity,
                    positionRef: positionRef.reify()
            )
        } else if (rootLiteral) {
            new Situation(
                    polarity: polarity,
                    rootLiteral: rootLiteral.reify()
            )
        } else if (factLiteral) {
            new Situation(
                    polarity: polarity,
                    factLiteral: factLiteral.reify()
            )
        } else {
            throw new RuntimeException("You shouldn't be here")
        }

    }

    Event toEvent() {
        return new Event(
                operator: polarity.toOperator(),
                positionRef: positionRef,
                rootLiteral: rootLiteral,
                factLiteral: factLiteral
        )
    }

}
