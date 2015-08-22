package org.leibnizcenter.lppneu.components.language

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Variable {

    String name

    String toString() {
        String output = name
        if (identifier) output += ":"+identifier
        else if (value) output += ":"+value.toString()
        output
    }

    static Variable build(String name) {
        new Variable(name: name)
    }

    Variable minimalClone() {
        new Variable(
                name: name,
                identifier: identifier,
                value: value
        )
    }

    // decoration with value
    String identifier
    Integer value

}
