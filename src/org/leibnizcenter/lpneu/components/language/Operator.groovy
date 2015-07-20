package org.leibnizcenter.lpneu.components.language

import groovy.util.logging.Log4j

// unary operators for situations

// POS: positive polarity (+)
// NEG: negative polarity (-), correspondent to strong negation
// NOT: null polarity (0), equivalent to default negation (NAF), unknown, undecidable, etc.

// unary operators for events

// POS: charge positively (+)
// NEG: charge negatively (-)
// NOT: remove polarity (0)

// binary operators for events

// SEQ: sequential  (order constrained)
// PAR: in parallel (order not constrained)
// ALT: only one of them

@Log4j
enum Operator {
    POS, NEG, NOT,          // unary operators  (situations and events)
    AND, OR, XOR,           // binary operators (situations)
    SEQ, PAR, ALT,          // binary operators (events)

    // TOCHECK not formally correct:
    POS_INSTANCE, NEG_INSTANCE, NOT_INSTANCE,  // specific operators
    POS_THIS, NEG_THIS, NOT_THIS

    Operator negate() {
        if (this == POS) return NEG
        else if (this == NEG) return POS
        else if (this == NOT) return NOT
        else  { log.warn("You should not be here."); return null }
    }

    Boolean unary() {
        (this == POS || this == NEG || this == NOT)
    }

    Polarity toPolarity() {
        if (this == POS) return Polarity.POS
        else if (this == NEG) return Polarity.NEG
        else if (this == NOT) return Polarity.NOT
        else  { log.warn("You should not be here."); return null }
    }

}
