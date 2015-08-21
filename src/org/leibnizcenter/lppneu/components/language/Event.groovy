package org.leibnizcenter.lppneu.components.language

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j

@Log4j @EqualsAndHashCode
class Event {
    Operator operator
    AbstractPositionRef positionRef
    Literal rootLiteral
    AbstractPosition position

    static Event build(ExtLiteral extLiteral) {
        new Event(
                operator: extLiteral.polarity.toOperator(),
                rootLiteral: extLiteral.literal
        )
    }

    static Event build(Literal literal, Operator operator = Operator.POS) {
        new Event(
                operator: operator,
                rootLiteral: literal
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
                position: position
        )
    }

    Event nullify() {
        return new Event(
                operator: operator.NULL,
                positionRef: positionRef,
                rootLiteral: rootLiteral,
                position: position
        )
    }

    Event positive() {
        return new Event(
                operator: operator.POS,
                positionRef: positionRef,
                rootLiteral: rootLiteral,
                position: position
        )
    }

    Event negative() {
        return new Event(
                operator: operator.NEG,
                positionRef: positionRef,
                rootLiteral: rootLiteral,
                position: position
        )
    }

    Situation toSituation() {
        return new Situation(
                polarity: operator.toPolarity(),
                positionRef: positionRef,
                rootLiteral: rootLiteral
        )
    }

    List<Variable> getVariables() {
        if (rootLiteral) rootLiteral.getVariables()
        else throw new RuntimeException("Not yet implemented.")
    }

    String toString() {
        String output = ""

        Boolean printOp = (operator != Operator.POS)

        if (printOp) output += operator.toString() + "("
        if (rootLiteral) output += rootLiteral.toString()
        else output += positionRef.toString()
        if (printOp) output += ")"

        output
    }

}
