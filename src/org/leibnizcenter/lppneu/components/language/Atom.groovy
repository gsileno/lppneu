package org.leibnizcenter.lppneu.components.language

import groovy.transform.AutoClone
import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j

@Log4j @EqualsAndHashCode
class Atom {
    String name

    String toString() { name }

    static Atom build(String name) {
        new Atom(name: name)
    }

}
