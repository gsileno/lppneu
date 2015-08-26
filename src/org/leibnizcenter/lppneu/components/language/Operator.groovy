package org.leibnizcenter.lppneu.components.language

import groovy.util.logging.Log4j

// isUnary operators for situations

// POS: positive polarity (+)
// NEG: negative polarity (-), correspondent to strong negation
// NULL: null polarity (0), equivalent to default negation (NAF), unknown, undecidable, etc.

// isUnary operators for events

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
    POS, NEG, NULL,                  // unary operators  (situations and events)
    AND, OR, XOR,                    // binary operators (situations)
    SEQ, PAR, OPT, ALT,              // binary operators (events and situations)
    IMPLIES, INHIBITS, DEFINES,      // logical dependencies on situations
    OCCURS_IN, OCCURS, CAUSES,       // event definition and causal dependency

    TRIPLE, ASSOCIATION,                         // just for labeling purposes (TODO: refactoring)
    IS_IMPLIED_BY, IS_CAUSED_BY, IS_DEFINED_BY,  // for operation reconstruction when associating the transitions to the triples

    // specific operators for positional programming, to be reconverted
    POS_INSTANCE, NEG_INSTANCE, NOT_INSTANCE,
    POS_THIS, NEG_THIS, NOT_THIS,

    // specific operators for positional programming
    SUCCESS, FAILURE, ISTANTIATION, EXPIRATION, INHIBITION

    // TODO: to consider SUSPENSION for commitments?
    // could it be considered as a kind of inhibition?

    Operator negate() {
        if (this == POS) return NEG
        else if (this == NEG) return POS
        else if (this == NULL) return NULL
        else  { log.warn("You should not be here."); return null }
    }

    Boolean isUnary() {
        this == POS || this == NEG || this == NULL
    }

    Boolean isBinaryProcessOperator() {
        this == SEQ || this == PAR || this == OPT || this == ALT
    }

    Boolean isBinaryLogicOperator() {
        this == AND || this == OR || this == XOR
    }

    Boolean isLogicOperator() {
        isUnary() || isBinaryLogicOperator()
    }

    Polarity toPolarity() {
        if (this == POS) return Polarity.POS
        else if (this == NEG) return Polarity.NEG
        else if (this == NULL) return Polarity.NULL
        else  { log.warn("You should not be here."); return null }
    }

}
