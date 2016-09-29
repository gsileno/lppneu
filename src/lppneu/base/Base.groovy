package lppneu.base

import groovy.util.logging.Log4j

@Log4j
class Base<T> {
    List<T> base = []

    Integer add(T elem) {

        log.trace "attempting to add element"

        Integer pos = findIndexOf(elem)
        if (pos != null) {
            log.warn("This element [${elem}] already exists at @ "+pos)
        }
        pos = base.size()
        base << elem
        log.trace "elem "+elem.toString()+" added @ "+pos

        pos
    }

    T build(T elem) {
        Integer pos = add(elem)
        if (pos == null) return null
        base[pos]
    }

    T find(T elem) {
        Integer pos = findIndexOf(elem)
        if (pos == null) return null
        base[pos]
    }

    Integer findIndexOf(T elem) {
        Integer pos = base.findIndexOf { it == elem }
        if (pos == -1) return null
        pos
    }

    T read(Integer pos) {
        log.trace "reading elem @ "+pos
        base[pos]
    }

    Integer size() { base.size() }

    void print() {
        for (Integer i = 0; i < base.size(); i++) {
            println i + ": " + base[i].toString()
        }
    }
}
