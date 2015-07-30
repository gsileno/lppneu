

import groovy.xml.XmlUtil
import org.leibnizcenter.lppneu.builders.LPPN2LPN
import org.leibnizcenter.lppneu.components.language.*
import org.leibnizcenter.lppneu.parser.LPPNLoader
import org.leibnizcenter.pneu.builders.PN2PNML
import org.leibnizcenter.pneu.builders.PN2dot
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Transition
import org.leibnizcenter.pneu.parsers.PNML2PN

class LPNBuilderTest extends GroovyTestCase {

    void batchExport(Net net, String filename) {

        def folder = new File('examples/out/dot/')
        if (!folder.exists()) folder.mkdirs()

        String outputFile = "examples/out/dot/" + filename + ".dot"

        new File(outputFile).withWriter {
            out -> out.println(PN2dot.simpleConversion(net))
        }
        println "lpetri net exported to " + outputFile
    }

//    void testConversionYaleShooting() {
//        Program program = LPPNLoader.parseFile("examples/basic/yaleshooting.lppn")
//        // program.print()
//
//        LPPN2LPN conversion = new LPPN2LPN()
//
//        Net net = conversion.convert(program)
//
//        program.reduce().print()
//
//        assert net.subNets.size() == 4
//        assert net.allArcs.size() == 14
//        assert net.allPlaces.size() == 20
//        assert net.allTransitions.size() == 6
//
//        batchExport(net, "test")
//    }

    Net batchConvert(String code) {
        Program program = LPPNLoader.parseString(code)
        LPPN2LPN conversion = new LPPN2LPN()
        return conversion.convert(program)
    }
//
//    void testConversionBasicFact() {
//        Net net = batchConvert("p.")
//        assert net.subNets.size() == 1
//        assert net.subNets[0].placeList.size() == 1
//        assert net.subNets[0].transitionList.size() == 0
//        assert net.subNets[0].arcList.size() == 0
//    }
//
//    void testConversionCompoundLogicFact() {
//        Net net = batchConvert("p or q.")
//        assert net.subNets.size() == 1
//        assert net.subNets[0].placeList.size() == 3
//        assert net.subNets[0].transitionList.size() == 1
//        assert net.subNets[0].arcList.size() == 3
//
//    }
//
//    void testConversionParFact() {
//        Net net = batchConvert("p par q.")
//        assert net.subNets.size() == 1
//        assert net.subNets[0].inputs.size() == 4
//        assert net.subNets[0].outputs.size() == 1
//    }
//
//    void testConversionAltFact() {
//        Net net = batchConvert("p alt q.")
//        assert net.subNets.size() == 1
//        assert net.subNets[0].inputs.size() == 4
//        assert net.subNets[0].outputs.size() == 1
//    }

//    void testConversionSeqFact() {
//        Net net = batchConvert("p seq q.")
//        assert net.subNets.size() == 1
//        assert net.subNets[0].inputs.size() == 4
//        assert net.subNets[0].outputs.size() == 1
//        batchExport(net, "test")
//    }

//    void testConversionSimpleLogicRule() {
//        Net net = batchConvert("q :- p.")
//        assert net.subNets.size() == 1
//        assert net.subNets[0].placeList.size() == 0
//        assert net.subNets[0].subNets[0].placeList.size() == 1
//        assert net.subNets[0].subNets[1].placeList.size() == 1
//        assert net.subNets[0].transitionList.size() == 1
//        assert net.subNets[0].arcList.size() == 2
//
//    }
//
//    void testConversionCompoundLogicRule() {
//        Net net = batchConvert("r or s :- p and q.")
//        assert net.subNets.size() == 5
//        assert net.subNets[0].placeList.size() == 0
//        assert net.subNets[0].subNets[0].placeList.size() == 1
//        assert net.subNets[0].subNets[1].placeList.size() == 1
//        assert net.subNets[0].transitionList.size() == 1
//        assert net.subNets[0].arcList.size() == 2
//        assert net.subNets[1].placeList.size() == 0
//        assert net.subNets[1].transitionList.size() == 1
//        assert net.subNets[1].arcList.size() == 2
//        assert net.subNets[1].subNets.size() == 2
//    }

    void testConversionSimpleCausalRule() {
        Net net = batchConvert("p -> q.")

        net.print()
    }

}
