import org.leibnizcenter.lppneu.builders.LPPN2LPN
import org.leibnizcenter.lppneu.comparison.Comparison
import org.leibnizcenter.lppneu.components.language.Program
import org.leibnizcenter.lppneu.parser.LPPNLoader
import org.leibnizcenter.pneu.builders.PN2dot
import org.leibnizcenter.pneu.components.petrinet.Net

class NetSimplificationTest extends GroovyTestCase {

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

    void testTwoSimpleEqualFacts() {
        LPPN2LPN conversion = batchConvert("a. a.")

        assert conversion.net.subNets.size() == 2
        assert conversion.simplifiedNet.subNets.size() == 1
        assert conversion.unifiedNet.subNets.size() == 1
    }

    void testCompoundLogicRule() {
        LPPN2LPN conversion = batchConvert("r :- p and q. a :- p and q.")

        conversion.program.print()
        conversion.reducedProgram.print()

        batchExport(conversion.net, "source")
        batchExport(conversion.simplifiedNet, "simplified")
        batchExport(conversion.unifiedNet, "unified")

        assert conversion.net.subNets.size() == 2
        assert conversion.simplifiedNet.subNets.size() == 2
        assert conversion.unifiedNet.subNets.size() == 2
    }


}
