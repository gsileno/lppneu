package org.leibnizcenter.lppneu.components.language

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Variable extends Term {

    static Variable build(String name) {
        new Variable(name: name)
    }

}
