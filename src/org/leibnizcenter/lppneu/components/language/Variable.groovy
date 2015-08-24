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

    String toVarString() {
        name
    }

    static List<String> toVarStringList(List<Variable> varList) {
        List<String> varStringList = []

        for (var in varList) {
            varStringList << var.name
        }

        varStringList
    }

    // decoration with value
    String identifier
    Integer value

}
