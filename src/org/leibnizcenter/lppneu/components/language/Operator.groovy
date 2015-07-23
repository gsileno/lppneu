package org.leibnizcenter.lppneu.components.language

import groovy.util.logging.Log4j

// unary operators for situations

// POS: positive polarity (+)
// NEG: negative polarity (-), correspondent to strong negation
// NULL: null polarity (0), equivalent to default negation (NAF), unknown, undecidable, etc.

// unary operators for events

// POS: charge positively (+)
// NEG: charge negatively (-)
// NULL: remove polarity (0)

// NOT: it is a

// binary operators for events

// SEQ: sequential  (order constrained)
// PAR: in parallel (order not constrained)
// ALT: only one of them

@Log4j
enum Operator {
    POS, NEG, NULL, NOT,    // unary operators  (situations and events)
    AND, OR, XOR,           // binary operators (situations)
    SEQ, PAR, ALT,          // binary operators (events)

    // TOCHECK not formally correct:
    POS_INSTANCE, NEG_INSTANCE, NOT_INSTANCE,  // specific operators
    POS_THIS, NEG_THIS, NOT_THIS

    Operator negate() {
        if (this == POS) return NEG
        else if (this == NEG) return POS
        else if (this == NULL) return NULL
        else  { log.warn("You should not be here."); return null }
    }

    Boolean unary() {
        this == POS || this == NEG || this == NULL
    }

    Boolean binaryProcessOperator() {
        this == SEQ || this == PAR || this == ALT
    }

    Boolean binaryLogicOperator() {
        this == AND || this == OR || this == XOR
    }

    Boolean binary() {
        binaryLogicOperator() || binaryProcessOperator()
    }

    Polarity toPolarity() {
        if (this == POS) return Polarity.POS
        else if (this == NEG) return Polarity.NEG
        else if (this == NULL) return Polarity.NOT
        else  { log.warn("You should not be here."); return null }
    }

}
