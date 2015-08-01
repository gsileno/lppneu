import org.leibnizcenter.lppneu.builders.LPPN2LPN
import org.leibnizcenter.lppneu.components.language.Program
import org.leibnizcenter.lppneu.parser.LPPNLoader
import org.leibnizcenter.pneu.builders.PN2dot
import org.leibnizcenter.pneu.components.petrinet.Net

class DotBuilderTest extends GroovyTestCase {

    static void batchExport(Net net, String filename) {

        File folder
        String outputFile

        // textual log output

        folder = new File('examples/out/log/')
        if (!folder.exists()) folder.mkdirs()

        outputFile = "examples/out/log/" + filename + ".log"

        new File(outputFile).withWriter {
            out -> out.println(net.toLog())
        }

        // dot output

        folder = new File('examples/out/dot/')
        if (!folder.exists()) folder.mkdirs()

        outputFile = "examples/out/dot/" + filename + ".dot"

        new File(outputFile).withWriter {
            out -> out.println(PN2dot.simpleConversion(net))
        }
        println "lpetri net exported to " + outputFile

    }

    static void batchExport(LPPN2LPN conversion, String filename) {
        println ("creating original")
        batchExport(conversion.net, filename+".original")
        println ("creating simplified")
        batchExport(conversion.simplifiedNet, filename+".simplified")
        println ("creating unified")
        batchExport(conversion.unifiedNet, filename+".unified")
    }

    static LPPN2LPN batchConvert(String code) {
        Program program = LPPNLoader.parseString(code)
        LPPN2LPN conversion = new LPPN2LPN()
        conversion.convert(program)
        return conversion
    }

//    void testSimpleFact() {
//        LPPN2LPN conversion = batchConvert("a.")
//        batchExport(conversion, "simpleFact")
//    }
//
//    void testTwoSimpleFacts() {
//        LPPN2LPN conversion = batchConvert("a. b.")
//        batchExport(conversion, "twoSimpleFacts")
//    }
//
//    void testTwoSimpleEqualFacts() {
//        LPPN2LPN conversion = batchConvert("a. a.")
//        batchExport(conversion, "twoSimpleEqualFacts")
//    }
//
//    void testSimpleLogicRule() {
//        LPPN2LPN conversion = batchConvert("b :- a.")
//        batchExport(conversion, "simpleLogicRule")
//    }
//
//    void testSimpleCasualRule() {
//        LPPN2LPN conversion = batchConvert("a -> b.")
//        batchExport(conversion, "simpleCausalRule")
//    }
//
//    void testCompoundLogicRule() {
//        LPPN2LPN conversion = batchConvert("r :- p and q.")
//        batchExport(conversion, "compoundLogicRule")
//    }

    void testChainingLogicRules() {
        LPPN2LPN conversion = batchConvert("q :- p. r :- q.")
        batchExport(conversion, "chainingLogicRule")
    }

//
//    void testCompoundCausalRule() {
//        LPPN2LPN conversion = batchConvert("p and q -> r.")
//        batchExport(conversion, "compoundCausalRule")
//    }
//
//    void testChainingCausalRules() {
//        LPPN2LPN conversion = batchConvert("p -> q. q -> r.")
//        batchExport(conversion, "chainingCausalRule")
//    }
//
//    void testSeqFact() {
//        LPPN2LPN conversion = batchConvert("p seq q.")
//        batchExport(conversion, "seqFact")
//    }
//
//    void testAltFact() {
//        LPPN2LPN conversion = batchConvert("p alt q.")
//        batchExport(conversion, "altFact")
//    }
//
//    void testParFact() {
//        LPPN2LPN conversion = batchConvert("p par q.")
//        batchExport(conversion, "parFact")
//    }
//
//    void testOptFact() {
//        LPPN2LPN conversion = batchConvert("p opt q.")
//        batchExport(conversion, "optFact")
//    }
//
//    void testEvent() {
//        LPPN2LPN conversion = batchConvert("-> a.")
//        batchExport(conversion, "event")
//    }
//
//    void testConditionEvent() {
//        LPPN2LPN conversion = batchConvert("-> a and b.")
//        batchExport(conversion, "conditionEvent")
//    }
//
//    void testSeqProcess() {
//        LPPN2LPN conversion = batchConvert("-> p seq q.")
//        batchExport(conversion, "seqProcess")
//    }
//
//    void testAltProcess() {
//        LPPN2LPN conversion = batchConvert("-> p alt q.")
//        batchExport(conversion, "altProcess")
//    }
//
//    void testParProcess() {
//        LPPN2LPN conversion = batchConvert("-> p par q.")
//        batchExport(conversion, "parProcess")
//    }
//
//    void testOptProcess() {
//        LPPN2LPN conversion = batchConvert("-> p opt q.")
//        batchExport(conversion, "optProcess")
//    }

//        conversion.program.print()
//        conversion.reducedProgram.print()
//        conversion.net.print()
//        println conversion.situationPlaceMap
//        println conversion.expressionPlaceMap
//        println conversion.eventTransitionMap
//        println conversion.operationTransitionMap

}
