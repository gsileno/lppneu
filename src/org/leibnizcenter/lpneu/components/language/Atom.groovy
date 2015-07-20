package org.leibnizcenter.lpneu.components.language

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j

@Log4j @EqualsAndHashCode
class Atom extends Term {

    static Atom build(String name) {
        new Atom(name: name)
    }

}
