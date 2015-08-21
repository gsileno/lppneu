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
//        LPPN2LPN convert = batchConvert("a.")
//        batchExport(convert, "simpleFact")
//    }
//
//    void testTwoSimpleFacts() {
//        LPPN2LPN convert = batchConvert("a. b.")
//        batchExport(convert, "twoSimpleFacts")
//    }
//
//    void testTwoSimpleEqualFacts() {
//        LPPN2LPN convert = batchConvert("a. a.")
//        batchExport(convert, "twoSimpleEqualFacts")
//    }
//
//    void testSimpleLogicRule() {
//        LPPN2LPN convert = batchConvert("b :- a.")
//        batchExport(convert, "simpleLogicRule")
//    }
//
//    void testSimpleCasualRule() {
//        LPPN2LPN convert = batchConvert("a -> b.")
//        batchExport(convert, "simpleCausalRule")
//    }
//
//    void testCompoundLogicRule() {
//        LPPN2LPN convert = batchConvert("r :- p and q.")
//        batchExport(convert, "compoundLogicRule")
//    }
//
//    void testChainingLogicRules() {
//        LPPN2LPN convert = batchConvert("q :- p. r :- q.")
//        batchExport(convert, "chainingLogicRule")
//    }
//
//    void testChainingLogicRules2() {
//        LPPN2LPN convert = batchConvert("p :- a and b. q :- a and b.")
//        batchExport(convert, "chainingLogicRule2")
//    }
//
//    void testInversedRules() {
//        LPPN2LPN convert = batchConvert("a and b :- r. p :- a and b.")
//        batchExport(convert, "inversedRules")
//    }
//
//    void testMultipleLogicRules() {
//        LPPN2LPN convert = batchConvert("p :- a and a. -p :- b. p :- c.")
//        batchExport(convert, "multipleLogicRules")
//    }
//
//
//    void testCompoundCausalRule() {
//        LPPN2LPN convert = batchConvert("p and q -> r.")
//        batchExport(convert, "compoundCausalRule")
//    }
//
//    void testChainingCausalRules() {
//        LPPN2LPN convert = batchConvert("p -> q. q -> r.")
//        batchExport(convert, "chainingCausalRule")
//    }
//
//    void testSeqFact() {
//        LPPN2LPN convert = batchConvert("p seq q.")
//        batchExport(convert, "seqFact")
//    }
//
//    void testAltFact() {
//        LPPN2LPN convert = batchConvert("p alt q.")
//        batchExport(convert, "altFact")
//    }
//
//    void testParFact() {
//        LPPN2LPN convert = batchConvert("p par q.")
//        batchExport(convert, "parFact")
//    }
//
//// PROBLEM
////    void testOptFact() {
////        LPPN2LPN convert = batchConvert("p opt q.")
////        batchExport(convert, "optFact")
////    }
//
//    void testEvent() {
//        LPPN2LPN convert = batchConvert("-> a.")
//        batchExport(convert, "event")
//    }
//
//    // PROBLEM
////    void testConditionEvent() {
////        LPPN2LPN convert = batchConvert("-> a and b.")
////        batchExport(convert, "conditionEvent")
////    }
//
//    void testSeqProcess() {
//        LPPN2LPN convert = batchConvert("-> p seq q.")
//        batchExport(convert, "seqProcess")
//    }
//
//    void testAltProcess() {
//        LPPN2LPN convert = batchConvert("-> p alt q.")
//        batchExport(convert, "altProcess")
//    }
//
//    void testParProcess() {
//        LPPN2LPN convert = batchConvert("-> p par q.")
//        batchExport(convert, "parProcess")
//    }
//
//    // PROBLEM
////    void testOptProcess() {
////        LPPN2LPN convert = batchConvert("-> p opt q.")
////        batchExport(convert, "optProcess")
////    }
//
//    void testCompoundProcess() {
//        LPPN2LPN convert = batchConvert("-> a seq (b par c).")
//        batchExport(convert, "compoundProcess")
//    }

    void testSale() {
        LPPN2LPN conversion = batchConvert("offers(Seller, Good, Money) seq accepts(Buyer, Good, Money).")
        batchExport(conversion, "sale")
    }
}
