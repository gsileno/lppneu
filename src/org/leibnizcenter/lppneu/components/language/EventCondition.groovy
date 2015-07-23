package org.leibnizcenter.lppneu.components.language

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j

/**
 * Represents a trigger: an event occurring in a certain condition
 */
@Log4j @EqualsAndHashCode
class EventCondition {

    Event event
    Expression condition

    static EventCondition build(Event event) {
        new EventCondition(
                event: event
        )
    }

    static EventCondition build(Expression condition) {
        new EventCondition(
                condition: condition
        )
    }

    static EventCondition build(Event event, Expression condition) {
        new EventCondition(
                event: event,
                condition: condition
        )
    }

    String toString() {
        String output = ""

        if (event) {
            output = event.toString()
            if (condition) {
                output += " in "+condition.toString()
            }
        } else {
            output += condition.toString()
        }

        output
    }
}
