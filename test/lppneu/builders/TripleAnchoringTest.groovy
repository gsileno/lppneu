package lppneu.builders

import lppneu.components.language.LPPNProgram
import lppneu.parsers.LPPNLoader
import pneu.components.petrinet.Net

class TripleAnchoringTest extends GroovyTestCase {

    static LPNHandler batchConvert(String code) {
        LPPNProgram program = LPPNLoader.parseString(code)
        LPNHandler handler = new LPNHandler()
        Net net = LPPN2LPN.buildProgramNet(program)
        handler.mapper.mapNet(net)
        handler.tripleAnchoringNet(net)
        return handler
    }

    void testConversionBasicFact() {
        LPNHandler handler = batchConvert("p.")
        assert handler.mapper.expressionTripleMap.size() == 1 // p
    }

    void testConversionCompoundLogicFact() {
        LPNHandler handler = batchConvert("p or q.")
        assert handler.mapper.expressionTripleMap.size() == 3 // p, q, and p or q
    }

    void testConversionParFact() {
        LPNHandler handler = batchConvert("p par q.")
        assert handler.mapper.expressionTripleMap.size() == 5 // p, q, or(neg(p), null(p)), or(neg(q), null(p)), par(p, q)
    }

    void testConversionSimpleLogicRule() {
        LPNHandler handler = batchConvert("q :- p.")
        assert handler.mapper.expressionTripleMap.size() == 2 // p, q.
    }

    void testConversionLogicRuleAndFact() {
        LPNHandler handler = batchConvert("q :- p. p.")
        assert handler.mapper.expressionTripleMap.size() == 2 // p, q.
    }

    void testConversionSimpleCausalRule() {
        LPNHandler handler = batchConvert("a -> b.")
        assert handler.mapper.expressionTripleMap.size() == 4 // a, b, occurs(a), or(neg(a), null(a))
    }

}
