package org.leibnizcenter.lppneu.components.language

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j

@Log4j @EqualsAndHashCode
class Event {
    Operator operator
    PositionRef positionRef
    Literal rootLiteral
    Position position

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

    static Event build(PositionRef positionRef, Operator operator = Operator.POS) {
        new Event(
                operator: operator,
                positionRef: positionRef
        )
    }

    static Event build(Position position, Operator operator = Operator.POS) {
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
                operator: operator.NOT,
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
