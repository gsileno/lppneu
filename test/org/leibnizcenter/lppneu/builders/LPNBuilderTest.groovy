package org.leibnizcenter.lppneu.builders

import groovy.xml.XmlUtil
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

    void testReduceYaleShooting() {
        Program program = LPPNLoader.parseFile("examples/basic/yaleshooting.lppn")
        // program.print()

        LPPN2LPN conversion = new LPPN2LPN()

        Net net = conversion.convert(program)

        program.reduce().print()

        assert net.subNets.size() == 4
        assert net.allArcs.size() == 14
        assert net.allPlaces.size() == 20
        assert net.allTransitions.size() == 6

        batchExport(net, "test")
    }

}
