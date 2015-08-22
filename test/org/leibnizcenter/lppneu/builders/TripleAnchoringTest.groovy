package org.leibnizcenter.lppneu.builders

import org.leibnizcenter.lppneu.builders.LPPN2LPN
import org.leibnizcenter.lppneu.components.language.LPPNProgram
import org.leibnizcenter.lppneu.parsers.LPPNLoader
import org.leibnizcenter.pneu.components.petrinet.Net

class TripleAnchoringTest extends GroovyTestCase {

    static LPPN2LPN batchConvert(String code) {
        LPPNProgram program = LPPNLoader.parseString(code)
        LPPN2LPN conversion = new LPPN2LPN()
        Net net = LPPN2LPN.buildProgramNet(program)
        conversion.mapper.mapNet(net)
        conversion.tripleAnchoringNet(net)
        return conversion
    }

    void testConversionBasicFact() {
        LPPN2LPN conversion = batchConvert("p.")
        assert conversion.mapper.expressionTripleMap.size() == 1 // p
    }

    void testConversionCompoundLogicFact() {
        LPPN2LPN conversion = batchConvert("p or q.")
        assert conversion.mapper.expressionTripleMap.size() == 3 // p, q, and p or q
    }

    void testConversionParFact() {
        LPPN2LPN conversion = batchConvert("p par q.")
        assert conversion.mapper.expressionTripleMap.size() == 5 // p, q, or(neg(p), null(p)), or(neg(q), null(p)), par(p, q)
    }

    void testConversionSimpleLogicRule() {
        LPPN2LPN conversion = batchConvert("q :- p.")
        assert conversion.mapper.expressionTripleMap.size() == 2 // p, q.
    }

    void testConversionLogicRuleAndFact() {
        LPPN2LPN conversion = batchConvert("q :- p. p.")
        assert conversion.mapper.expressionTripleMap.size() == 2 // p, q.
    }

    void testConversionSimpleCausalRule() {
        LPPN2LPN conversion = batchConvert("a -> b.")
        assert conversion.mapper.expressionTripleMap.size() == 4 // a, b, occurs(a), or(neg(a), null(a))
    }

}
