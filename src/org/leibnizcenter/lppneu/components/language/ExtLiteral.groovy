package org.leibnizcenter.lppneu.components.language

import groovy.transform.AutoClone
import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j

@Log4j @EqualsAndHashCode
class ExtLiteral {
    Polarity polarity
    Literal literal

    static ExtLiteral build(Literal classicLiteral, Polarity polarity = Polarity.POS) {
        ExtLiteral extLiteral = new ExtLiteral()
        extLiteral.literal = classicLiteral
        extLiteral.polarity = polarity
        extLiteral
    }

    ExtLiteral negate() {
        if (polarity == Polarity.POS)
            polarity = Polarity.NEG
        else if (polarity == Polarity.NEG)
            polarity = Polarity.POS
        else
            log.warn("Negation of a null polarity is a null polarity ($this)")
        this
    }

    ExtLiteral nullify() {
        if (polarity == Polarity.POS | polarity == Polarity.NEG)
            polarity = Polarity.NULL
        else
            log.warn("Nullification of a null polarity is a null polarity ($input)")
        this
    }

    ExtLiteral minimalClone() {
        new ExtLiteral(
                polarity: polarity,
                literal: literal.minimalClone()
        )
    }

    static ExtLiteral buildNegation(ExtLiteral input) {
        input.minimalClone().negate()
    }

    static ExtLiteral buildNull(ExtLiteral input) {
        input.minimalClone().nullify()
    }
}
