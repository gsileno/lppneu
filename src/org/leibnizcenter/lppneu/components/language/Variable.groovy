package org.leibnizcenter.lppneu.components.language

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Variable {

    String name

    String toString() { name }

    static Variable build(String name) {
        new Variable(name: name)
    }

}
