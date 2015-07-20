package org.leibnizcenter.lpneu.components.language

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Variable extends Term {

    static Variable build(String name) {
        new Variable(name: name)
    }

}
