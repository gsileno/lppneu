package lppneu.builders

import lppneu.components.language.LPPNProgram
import lppneu.parsers.LPPNLoader
import pneu.components.petrinet.Net


class LPNBuilderTest extends GroovyTestCase {

    static Net batchConvert(String code) {
        LPPNProgram program = LPPNLoader.parseString(code)
        LPNHandler handler = new LPNHandler()
        handler.convert(program)
        return handler.net
    }

    void testConversionBasicFact() {
        Net net = batchConvert("p.")
        net.exportToDot("LPNBuilderTest.p")
    }

    void testConversionBasicFact2() {
        Net net = batchConvert("neg p.")
        net.exportToDot("LPNBuilderTest.negp")
    }

    void testConversionBasicOccursIn() {
        Net net = batchConvert("p in q.")
        net.exportToDot("LPNBuilderTest.occurspinq")
    }

    void testConversionOrFact() {
        Net net = batchConvert("p or q.")
        net.exportToDot("LPNBuilderTest.porq")
    }

    void testConversionAndFact() {
        Net net = batchConvert("p and q.")
        net.exportToDot("LPNBuilderTest.pandq")
    }

    void testConversionXorFact() {
        Net net = batchConvert("p xor q.")
        net.exportToDot("LPNBuilderTest.pxorq")
    }

    void testConversionNegOrFact() {
        Net net = batchConvert("neg (p or q).")
        net.exportToDot("LPNBuilderTest.negporq")
    }

    void testConversionParFact() {
        Net net = batchConvert("p par q.")
        net.exportToDot("LPNBuilderTest.pparq")
    }

    void testConversionAltFact() {
        Net net = batchConvert("p alt q.")
        net.exportToDot("LPNBuilderTest.paltq")
    }

    void testConversionSeqFact() {
        Net net = batchConvert("p seq q.")
        net.exportToDot("LPNBuilderTest.pseqq")
    }

    void testConversionSimpleLogicRule() {
        Net net = batchConvert("q :- p.")
        net.exportToDot("LPNBuilderTest.pimpliesq")
    }

    void testConversionCompoundLogicRule() {
        Net net = batchConvert("r or s :- p and q.")
        net.exportToDot("LPNBuilderTest.pandqimpliesrors")
    }

    void testConversionSimpleCausalRule() {
        Net net = batchConvert("a -> b.")
        net.exportToDot("LPNBuilderTest.acausesb")
    }

    void testConversionCompoundCausalRule() {
        Net net = batchConvert("a and b -> p seq q.")
        net.exportToDot("LPNBuilderTest.aandbcausespseqq")
    }

    void testConversionBasicEvent() {
        Net net = batchConvert("-> p.")
        net.exportToDot("LPNBuilderTest.eventoccursp")
    }

    void testConversionSeqOperation() {
        Net net = batchConvert("-> p seq q.")
        net.exportToDot("LPNBuilderTest.operationpseqq")
    }

    void testConversionParOperation() {
        Net net = batchConvert("-> p par q.")
        net.exportToDot("LPNBuilderTest.operationpparq")
    }

    void testConversionAltOperation() {
        Net net = batchConvert("-> p alt q.")
        net.exportToDot("LPNBuilderTest.operationaltq")
    }

//    void testConversionCompoundOperation() {
//        Net net = batchConvert("-> (a and b) alt q.")
//        net.exportToDot("LPNBuilderTest.operationaandbaltq")
//    }
}
