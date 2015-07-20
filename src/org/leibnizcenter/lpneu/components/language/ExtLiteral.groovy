package org.leibnizcenter.lpneu.components.language

import groovy.transform.AutoClone
import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j

@Log4j @EqualsAndHashCode @AutoClone
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
        buildNegation(this)
    }

    ExtLiteral nullify() {
        buildNull(this)
    }

    static ExtLiteral buildNegation(ExtLiteral input) {
        ExtLiteral output = input.clone()
        if (output.polarity == Polarity.POS)
            output.polarity = Polarity.NEG
        else if (output.polarity == Polarity.NEG)
            output.polarity = Polarity.POS
        else
            log.warn("Negation of a null polarity is a null polarity ($input)")
        output
    }

    static ExtLiteral buildNull(ExtLiteral input) {
        ExtLiteral output = input.clone()
        if (output.polarity == Polarity.POS | output.polarity == Polarity.NEG)
            output.polarity = Polarity.NOT
        else
            log.warn("Nullification of a null polarity is a null polarity ($input)")
        output
    }
}
