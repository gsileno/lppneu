import org.leibnizcenter.lppneu.builders.LPPN2LPN
import org.leibnizcenter.lppneu.components.language.Program
import org.leibnizcenter.lppneu.parser.LPPNLoader
import org.leibnizcenter.pneu.builders.PN2dot
import org.leibnizcenter.pneu.components.petrinet.Net

class DotBuilderTest extends GroovyTestCase {

    static void batchExport(Net net, String filename) {

        def folder = new File('examples/out/dot/')
        if (!folder.exists()) folder.mkdirs()

        String outputFile = "examples/out/dot/" + filename + ".dot"

        new File(outputFile).withWriter {
            out -> out.println(PN2dot.simpleConversion(net))
        }
        println "lpetri net exported to " + outputFile
    }

    static LPPN2LPN batchConvert(String code) {
        Program program = LPPNLoader.parseString(code)
        LPPN2LPN conversion = new LPPN2LPN()
        conversion.convert(program)
        return conversion
    }

    void testSimpleFact() {
        LPPN2LPN conversion = batchConvert("a.")
        batchExport(conversion.unifiedNet, "simpleFact")
    }

    void testTwoSimpleFacts() {
        LPPN2LPN conversion = batchConvert("a. b.")
        batchExport(conversion.unifiedNet, "twoSimpleFacts")
    }

    void testTwoSimpleEqualFacts() {
        LPPN2LPN conversion = batchConvert("a. a.")
        batchExport(conversion.unifiedNet, "twoSimpleEqualFacts")
    }

    void testSimpleLogicRule() {
        LPPN2LPN conversion = batchConvert("b :- a.")
        batchExport(conversion.unifiedNet, "simpleLogicRule")
    }

    void testSimpleCasualRule() {
        LPPN2LPN conversion = batchConvert("a -> b.")
        batchExport(conversion.unifiedNet, "simpleCausalRule")
    }

    void testCompoundLogicRule() {
        LPPN2LPN conversion = batchConvert("r :- p and q.")
        batchExport(conversion.unifiedNet, "compoundLogicRule")
    }

    void testChainingLogicRules() {
        LPPN2LPN conversion = batchConvert("q :- p. r :- q.")
        batchExport(conversion.unifiedNet, "chainingLogicRule")
    }


    void testCompoundCausalRule() {
        LPPN2LPN conversion = batchConvert("p and q -> r.")
        batchExport(conversion.unifiedNet, "compoundCausalRule")
    }

    void testChainingCausalRules() {
        LPPN2LPN conversion = batchConvert("p -> q. q -> r.")
        batchExport(conversion.unifiedNet, "chainingCausalRule")
    }

//        conversion.program.print()
//        conversion.reducedProgram.print()
//        conversion.net.print()
//        println conversion.situationPlaceMap
//        println conversion.expressionPlaceMap
//        println conversion.eventTransitionMap
//        println conversion.operationTransitionMap

}
