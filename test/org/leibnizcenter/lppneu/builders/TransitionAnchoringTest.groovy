package org.leibnizcenter.lppneu.builders

import org.leibnizcenter.lppneu.builders.LPNHandler
import org.leibnizcenter.lppneu.components.language.LPPNProgram
import org.leibnizcenter.lppneu.parsers.LPPNLoader
import org.leibnizcenter.pneu.builders.PN2dot
import org.leibnizcenter.pneu.components.petrinet.Net

class TransitionAnchoringTest extends GroovyTestCase {

    static void batchExport(Net net, String filename) {

        File folder
        String outputFile

        // textual log output

        folder = new File('log/log')
        if (!folder.exists()) folder.mkdirs()

        outputFile = "log/log/" + filename + ".log"

        new File(outputFile).withWriter {
            out -> out.println(net.toLog())
        }

        // dot output

        folder = new File('log/dot')
        if (!folder.exists()) folder.mkdirs()

        outputFile = "log/dot/" + filename + ".dot"

        new File(outputFile).withWriter {
            out -> out.println(PN2dot.convert(net))
        }
        println "lpetri net exported to " + outputFile

    }

    static LPNHandler batchConvert(String code) {
        LPPNProgram program = LPPNLoader.parseString(code)
        LPNHandler handler = new LPNHandler()
//        Net net = LPNHandler.buildProgramNet(program)
//        convert.mapNet(net)
//        convert.tripleAnchoredNet = convert.tripleAnchoringNet(net)
//        convert.transitionAnchoredNet = convert.transitionAnchoringNet(convert.tripleAnchoredNet)

        handler.convert(program)
        return handler
    }

//
//    void testConversionBasicFact() {
//        LPNHandler convert = batchConvert("p.")
//        assert convert.expressionTripleMap.size() == 1 // p
//    }
//
//    void testConversionCompoundLogicFact() {
//        LPNHandler convert = batchConvert("p or q.")
//        assert convert.expressionTripleMap.size() == 3 // p, q, and p or q
//    }
//
//    void testConversionParFact() {
//        LPNHandler convert = batchConvert("p par q.")
//        assert convert.expressionTripleMap.size() == 5 // p, q, or(neg(p), null(p)), or(neg(q), null(p)), par(p, q)
//    }

    void testConversionSimpleLogicRule() {
        LPNHandler handler = batchConvert("p -> q.")
        // assert convert.expressionTripleMap.size() == 2 // p, q.
    }

//    void testConversionLogicRuleAndFact() {
//        LPNHandler convert = batchConvert("q :- p. p.")
//        assert convert.expressionTripleMap.size() == 2 // p, q.
//    }
//
//    void testConversionSimpleCausalRule() {
//        LPNHandler convert = batchConvert("a -> b.")
//        assert convert.expressionTripleMap.size() == 4 // a, b, occurs(a), or(neg(a), null(a))
//    }

}
