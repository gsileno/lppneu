package org.leibnizcenter.lppneu.components.language

import org.leibnizcenter.lppneu.builders.LPPN2LPN
import org.leibnizcenter.lppneu.parser.LPPNLoader
import org.leibnizcenter.lppneu.parser.LPPNParser
import org.leibnizcenter.pneu.builders.PN2dot
import org.leibnizcenter.pneu.components.petrinet.Net

class TripleAnchoringTest extends GroovyTestCase {

    static LPPN2LPN batchConvert(String code) {
        Program program = LPPNLoader.parseString(code)
        LPPN2LPN conversion = new LPPN2LPN()
        Net net = LPPN2LPN.buildProgramNet(program)
        conversion.mapNet(net)
        conversion.tripleAnchoringNet(net)
        return conversion
    }

    void testConversionBasicFact() {
        LPPN2LPN conversion = batchConvert("p.")
        assert conversion.expressionTripleMap.size() == 1 // p
    }

    void testConversionCompoundLogicFact() {
        LPPN2LPN conversion = batchConvert("p or q.")
        assert conversion.expressionTripleMap.size() == 3 // p, q, and p or q
    }

    void testConversionParFact() {
        LPPN2LPN conversion = batchConvert("p par q.")
        assert conversion.expressionTripleMap.size() == 5 // p, q, or(neg(p), null(p)), or(neg(q), null(p)), par(p, q)
    }

    void testConversionSimpleLogicRule() {
        LPPN2LPN conversion = batchConvert("q :- p.")
        assert conversion.expressionTripleMap.size() == 2 // p, q.
    }

    void testConversionLogicRuleAndFact() {
        LPPN2LPN conversion = batchConvert("q :- p. p.")
        assert conversion.expressionTripleMap.size() == 2 // p, q.
    }

    void testConversionSimpleCausalRule() {
        LPPN2LPN conversion = batchConvert("a -> b.")
        assert conversion.expressionTripleMap.size() == 4 // a, b, occurs(a), or(neg(a), null(a))
    }

}
