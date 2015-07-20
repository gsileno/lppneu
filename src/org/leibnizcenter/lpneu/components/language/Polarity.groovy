package org.leibnizcenter.lpneu.components.language

import groovy.util.logging.Log4j

// POS: positive polarity (+)
// NEG: negative polarity (-), correspondent to strong negation
// NOT: null polarity (0), equivalent to default negation (NAF), unknown, undecidable, etc.

@Log4j
enum Polarity {
    POS, NEG, NOT

    Operator toOperator() {
        if (this == POS) return Operator.POS
        else if (this == NEG) return Operator.NEG
        else if (this == NOT) return Operator.NOT
        else { log.warn("You should not be here."); return null }
    }

    Polarity negate() {
        if (this == POS) return NEG
        else if (this == NEG) return POS
        else if (this == NOT) return NOT
        else { log.warn("You should not be here."); return null }
    }

}
