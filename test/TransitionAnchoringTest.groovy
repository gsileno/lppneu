import org.leibnizcenter.lppneu.builders.LPPN2LPN
import org.leibnizcenter.lppneu.components.language.Program
import org.leibnizcenter.lppneu.parser.LPPNLoader
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
            out -> out.println(PN2dot.simpleConversion(net))
        }
        println "lpetri net exported to " + outputFile

    }

    static LPPN2LPN batchConvert(String code) {
        Program program = LPPNLoader.parseString(code)
        LPPN2LPN conversion = new LPPN2LPN()
//        Net net = LPPN2LPN.buildProgramNet(program)
//        conversion.mapNet(net)
//        conversion.tripleAnchoredNet = conversion.tripleAnchoringNet(net)
//        conversion.transitionAnchoredNet = conversion.transitionAnchoringNet(conversion.tripleAnchoredNet)

        conversion.convert(program)
        return conversion
    }

//
//    void testConversionBasicFact() {
//        LPPN2LPN conversion = batchConvert("p.")
//        assert conversion.expressionTripleMap.size() == 1 // p
//    }
//
//    void testConversionCompoundLogicFact() {
//        LPPN2LPN conversion = batchConvert("p or q.")
//        assert conversion.expressionTripleMap.size() == 3 // p, q, and p or q
//    }
//
//    void testConversionParFact() {
//        LPPN2LPN conversion = batchConvert("p par q.")
//        assert conversion.expressionTripleMap.size() == 5 // p, q, or(neg(p), null(p)), or(neg(q), null(p)), par(p, q)
//    }

    void testConversionSimpleLogicRule() {
        LPPN2LPN conversion = batchConvert("p -> q.")
        // assert conversion.expressionTripleMap.size() == 2 // p, q.
    }

//    void testConversionLogicRuleAndFact() {
//        LPPN2LPN conversion = batchConvert("q :- p. p.")
//        assert conversion.expressionTripleMap.size() == 2 // p, q.
//    }
//
//    void testConversionSimpleCausalRule() {
//        LPPN2LPN conversion = batchConvert("a -> b.")
//        assert conversion.expressionTripleMap.size() == 4 // a, b, occurs(a), or(neg(a), null(a))
//    }

}
