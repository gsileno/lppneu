package org.leibnizcenter.lppneu.components.lppetrinets

import groovy.transform.Immutable
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.pneu.components.petrinet.Token

@Immutable
class LPToken extends Token {
    Expression expression
}

