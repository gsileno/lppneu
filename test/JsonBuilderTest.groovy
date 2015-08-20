import org.leibnizcenter.lppneu.builders.LPPN2LPN
import org.leibnizcenter.lppneu.components.language.Program
import org.leibnizcenter.lppneu.parser.LPPNLoader
import org.leibnizcenter.pneu.builders.PN2json
import org.leibnizcenter.pneu.components.petrinet.Net

class JsonBuilderTest extends GroovyTestCase {

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

        // json output

        folder = new File('examples/out/json/')
        if (!folder.exists()) folder.mkdirs()

        outputFile = "examples/out/json/" + filename + ".json"

        new File(outputFile).withWriter {
            out -> out.println(PN2json.simpleConversion(net))
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
//
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
//
//    void testChainingLogicRules() {
//        LPPN2LPN conversion = batchConvert("q :- p. r :- q.")
//        batchExport(conversion, "chainingLogicRule")
//    }
//
//    void testChainingLogicRules2() {
//        LPPN2LPN conversion = batchConvert("p :- a and b. q :- a and b.")
//        batchExport(conversion, "chainingLogicRule2")
//    }
//
//    void testInversedRules() {
//        LPPN2LPN conversion = batchConvert("a and b :- r. p :- a and b.")
//        batchExport(conversion, "inversedRules")
//    }
//
//    void testMultipleLogicRules() {
//        LPPN2LPN conversion = batchConvert("p :- a and a. -p :- b. p :- c.")
//        batchExport(conversion, "multipleLogicRules")
//    }
//
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
//// PROBLEM
////    void testOptFact() {
////        LPPN2LPN conversion = batchConvert("p opt q.")
////        batchExport(conversion, "optFact")
////    }
//
//    void testEvent() {
//        LPPN2LPN conversion = batchConvert("-> a.")
//        batchExport(conversion, "event")
//    }
//
//    // PROBLEM
////    void testConditionEvent() {
////        LPPN2LPN conversion = batchConvert("-> a and b.")
////        batchExport(conversion, "conditionEvent")
////    }
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
//    // PROBLEM
////    void testOptProcess() {
////        LPPN2LPN conversion = batchConvert("-> p opt q.")
////        batchExport(conversion, "optProcess")
////    }
//
//    void testCompoundProcess() {
//        LPPN2LPN conversion = batchConvert("-> a seq (b par c).")
//        batchExport(conversion, "compoundProcess")
//    }

    void testSale() {
        LPPN2LPN conversion = batchConvert("offers(Seller, Good, Money) seq accepts(Buyer, Good, Money).")
        batchExport(conversion, "sale")
    }
}