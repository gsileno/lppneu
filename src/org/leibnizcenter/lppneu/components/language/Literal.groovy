package org.leibnizcenter.lppneu.components.language

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j

@Log4j @EqualsAndHashCode
class Literal {
    Polarity polarity
    PosLiteral literal

    static Literal build(PosLiteral classicLiteral, Polarity polarity = Polarity.POS) {
        Literal extLiteral = new Literal()
        extLiteral.literal = classicLiteral
        extLiteral.polarity = polarity
        extLiteral
    }

    Literal negate() {
        if (polarity == Polarity.POS)
            polarity = Polarity.NEG
        else if (polarity == Polarity.NEG)
            polarity = Polarity.POS
        else
            log.warn("Negation of a null polarity is a null polarity ($this)")
        this
    }

    Literal nullify() {
        if (polarity == Polarity.POS | polarity == Polarity.NEG)
            polarity = Polarity.NULL
        else
            log.warn("Nullification of a null polarity is a null polarity ($this)")
        this
    }

    Literal minimalClone() {
        new Literal(
                polarity: polarity,
                literal: literal.minimalClone()
        )
    }

    static Literal buildNegation(Literal input) {
        input.minimalClone().negate()
    }

    static Literal buildNull(Literal input) {
        input.minimalClone().nullify()
    }

    Literal reify() {
        new Literal(
                polarity: polarity,
                literal: literal.reify()
        )
    }
}
