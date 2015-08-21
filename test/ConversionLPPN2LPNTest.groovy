import org.leibnizcenter.lppneu.builders.LPPN2LPN
import org.leibnizcenter.lppneu.components.language.*
import org.leibnizcenter.lppneu.parser.LPPNLoader
import org.leibnizcenter.pneu.builders.PN2dot
import org.leibnizcenter.pneu.components.petrinet.Net

class ConversionLPPN2LPNTest extends GroovyTestCase {

    static void batchExport(Net net, String filename) {

        def folder = new File('examples/out/dot/')
        if (!folder.exists()) folder.mkdirs()

        String outputFile = "examples/out/dot/" + filename + ".dot"

        new File(outputFile).withWriter {
            out -> out.println(PN2dot.convert(net))
        }
        println "lpetri net exported to " + outputFile
    }

    static Net batchConvert(String code) {
        LPPNProgram program = LPPNLoader.parseString(code)
        LPPN2LPN conversion = new LPPN2LPN()
        conversion.convert(program)
        return conversion.net
    }

    void testConversionBasicFact() {
        Net net = batchConvert("p.")
        assert net.subNets.size() == 1
        assert net.subNets[0].placeList.size() == 1
        assert net.subNets[0].transitionList.size() == 0
        assert net.subNets[0].arcList.size() == 0
    }

    void testConversionCompoundLogicFact() {
        Net net = batchConvert("p or q.")

        assert net.subNets.size() == 3
        assert net.subNets[0].placeList.size() == 1
        assert net.subNets[0].transitionList.size() == 0
        assert net.subNets[0].arcList.size() == 0
        assert net.subNets[1].placeList.size() == 0
        assert net.subNets[1].transitionList.size() == 1
        assert net.subNets[1].arcList.size() == 2

    }

    void testConversionParFact() {
        Net net = batchConvert("p par q.")
        assert net.subNets.size() == 3
        assert net.subNets[0].inputs.size() == 1
        assert net.subNets[0].outputs.size() == 1
    }

    void testConversionAltFact() {
        Net net = batchConvert("p alt q.")
        assert net.subNets.size() == 3
        assert net.subNets[0].inputs.size() == 1
        assert net.subNets[0].outputs.size() == 1
    }

    void testConversionSeqFact() {
        Net net = batchConvert("p seq q.")
        assert net.subNets.size() == 3
        assert net.subNets[0].inputs.size() == 1
        assert net.subNets[0].outputs.size() == 1
    }

    void testConversionSimpleLogicRule() {
        Net net = batchConvert("q :- p.")
        assert net.subNets.size() == 1
        assert net.subNets[0].placeList.size() == 0
        assert net.subNets[0].subNets[0].placeList.size() == 1
        assert net.subNets[0].subNets[1].placeList.size() == 1
        assert net.subNets[0].transitionList.size() == 2
        assert net.subNets[0].arcList.size() == 4

    }

    void testConversionCompoundLogicRule() {
        Net net = batchConvert("r or s :- p and q.")
        assert net.subNets.size() == 5
        assert net.subNets[0].placeList.size() == 0
        assert net.subNets[0].subNets[0].placeList.size() == 1
        assert net.subNets[0].subNets[1].placeList.size() == 1
        assert net.subNets[0].transitionList.size() == 1
        assert net.subNets[0].arcList.size() == 2
        assert net.subNets[1].placeList.size() == 0
        assert net.subNets[1].transitionList.size() == 1
        assert net.subNets[1].arcList.size() == 2
        assert net.subNets[1].subNets.size() == 2
    }

    void testConversionSimpleCausalRule() {
        Net net = batchConvert("a -> b.")
        assert net.subNets.size() == 1
        assert net.placeList.size() == 0
        assert net.transitionList.size() == 0
        assert net.arcList.size() == 0
        assert net.subNets[0].placeList.size() == 2
        assert net.subNets[0].subNets[0].placeList.size() == 1
        assert net.subNets[0].subNets[0].transitionList.size() == 1
        assert net.subNets[0].subNets[1].placeList.size() == 0
        assert net.subNets[0].subNets[1].transitionList.size() == 1
        assert net.subNets[0].transitionList.size() == 0
        assert net.subNets[0].arcList.size() == 2
    }

}
