package lppneu.components.language

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j
import lppneu.components.position.AbstractPosition
import lppneu.components.position.AbstractPositionRef

@Log4j
@EqualsAndHashCode
class Event {
    Operator operator
    AbstractPositionRef positionRef
    PosLiteral factLiteral
    PosLiteral rootLiteral // root literal are for primitive actions
    AbstractPosition position

    Event minimalClone() {
        if (rootLiteral) {
            new Event(
                    operator: operator,
                    rootLiteral: rootLiteral.minimalClone(),
            )
        } else if (factLiteral) {
            new Event(
                    operator: operator,
                    factLiteral: factLiteral.minimalClone()
            )
        } else {
            new Event(
                    operator: operator,
                    positionRef: positionRef,
                    position: position,
                    factLiteral: factLiteral.minimalClone(),
                    rootLiteral: rootLiteral.minimalClone()
            )
        }
    }

    static Event build(Literal extLiteral) {
        new Event(
                operator: extLiteral.polarity.toOperator(),
                factLiteral: extLiteral.literal
        )
    }

    static Event buildFromRootLiteral(Literal extLiteral) {
        new Event(
                operator: extLiteral.polarity.toOperator(),
                rootLiteral: extLiteral.literal
        )
    }

    static Event build(PosLiteral literal, Operator operator = Operator.POS) {
        new Event(
                operator: operator,
                factLiteral: literal
        )
    }

    static Event build(AbstractPositionRef positionRef, Operator operator = Operator.POS) {
        new Event(
                operator: operator,
                positionRef: positionRef
        )
    }

    static Event build(AbstractPosition position, Operator operator = Operator.POS) {
        new Event(
                operator: operator,
                position: position
        )
    }

    Event negate() {
        return new Event(
                operator: operator.negate(),
                positionRef: positionRef,
                rootLiteral: rootLiteral,
                factLiteral: factLiteral,
                position: position
        )
    }

    Event nullify() {
        return new Event(
                operator: operator.NULL,
                positionRef: positionRef,
                rootLiteral: rootLiteral,
                factLiteral: factLiteral,
                position: position
        )
    }

    Event positive() {
        return new Event(
                operator: operator.POS,
                positionRef: positionRef,
                rootLiteral: rootLiteral,
                factLiteral: factLiteral,
                position: position
        )
    }

    Event negative() {
        return new Event(
                operator: operator.NEG,
                positionRef: positionRef,
                rootLiteral: rootLiteral,
                factLiteral: factLiteral,
                position: position
        )
    }

    Situation toSituation() {

        if (position)
            throw new RuntimeException("This event cannot be converted to a situation.")

        return new Situation(
                polarity: operator.toPolarity(),
                positionRef: positionRef,
                rootLiteral: rootLiteral,
                factLiteral: factLiteral,
        )
    }

    List<Variable> getVariables() {
        if (rootLiteral) rootLiteral.getVariables()
        else if (factLiteral) factLiteral.getVariables()
        else if (positionRef) positionRef.getVariables()
        else throw new RuntimeException("Not yet implemented.")
    }

    String toString() {
        String output = ""

        Boolean printOp = (operator != Operator.POS)

        if (printOp) output += operator.toString() + "("
        if (factLiteral != null) output += factLiteral.toString()
        else if (rootLiteral != null) output += "."+rootLiteral.toString()
        else output += positionRef.toString()
        if (printOp) output += ")"

        output
    }

}
