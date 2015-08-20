package org.leibnizcenter.lppneu.examples

import org.leibnizcenter.lppneu.components.language.Program
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.petrinets.LPlace
import org.leibnizcenter.lppneu.components.petrinets.LTransition
import org.leibnizcenter.lppneu.parser.LPPNLoader
import org.leibnizcenter.pneu.builders.PN2dot
import org.leibnizcenter.pneu.components.petrinet.Arc
import org.leibnizcenter.pneu.components.petrinet.Net

class MarketModelsFactory {

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

    static Expression parse(String code) {
        Program program = LPPNLoader.parseString(code + ".")

        if (program.parsingErrors.size() > 0)
            throw new RuntimeException("Parsing errors: " + program.parsingErrors)

        if (program.logicRules.size() > 1 || program.logicRules.size() == 0 || program.causalRules.size() != 0 || program.logicRules[0].body != null)
            throw new RuntimeException("Invalid input: only simple facts expected.")

        return program.logicRules[0].head
    }

    static LTransition createTransition(String label = null) {
        LTransition tr
        if (label != null)
            tr = new LTransition(operation: parse(label).toOperation())
        else
            tr = new LTransition()
        tr
    }

    static LTransition createTransition(Net net, String label = null) {
        LTransition tr = createTransition(label)
        net.transitionList << tr
        tr
    }

    static LTransition propagateTransition(Net net, LTransition source) {
        LTransition target = new LTransition(operation: source.operation,
                operator: source.operator,
                link: source.link)
        net.transitionList << target
        bridgeTransitions(net, source, target)
        target
    }

    static LPlace createPlace(String label = null) {
        LPlace pl
        if (label != null)
            pl = new LPlace(expression: parse(label))
        else
            pl = new LPlace()
        pl
    }

    static LPlace createPlace(Net net, String label = null) {
        LPlace pl = createPlace(label)
        net.placeList << pl
        pl
    }

    static void bridgeTransitions(Net net, LTransition t1, LTransition t2) {

        if (!net.transitionList.contains(t1) || !net.transitionList.contains(t2)) {
            throw new RuntimeException("Error: this net does not contain the transition(s) to bridge")
        }

        LPlace pBridge = createPlace(net)
        List<Arc> arcs = Arc.buildArcs(t1, pBridge, t2)

        net.arcList += arcs
    }

    static Net basicSaleInstance1() {
        Net sale = new Net()

        sale.function = createTransition("sells(Seller, Good, Money, Buyer)")

        LTransition tIn = createTransition(sale)
        LTransition tOut = createTransition(sale)

        LTransition t1 = createTransition(sale, "offers(Seller, Buyer, Good, Money)")
        LTransition t2 = createTransition(sale, "accepts(Buyer, Seller, Good, Money)")
        LTransition t3 = createTransition(sale, "pays(Buyer, Seller, Money)")
        LTransition t4 = createTransition(sale, "delivers(Seller, Buyer, Good)")

        bridgeTransitions(sale, tIn, t1)
        bridgeTransitions(sale, t1, t2)
        bridgeTransitions(sale, t2, t3)
        bridgeTransitions(sale, t3, t4)
        bridgeTransitions(sale, t4, tOut)

        batchExport(sale, "basicSaleInstance1")
    }

    static Net basicSaleInstance2() {
        Net sale = new Net()

        sale.function = createTransition("sells(Seller, Good, Money, Buyer)")

        LTransition tIn = createTransition(sale)
        LTransition tOut = createTransition(sale)

        LTransition t1 = createTransition(sale, "offers(Seller, Buyer, Good, Money)")
        LTransition t2 = createTransition(sale, "accepts(Buyer, Seller, Good, Money)")
        LTransition t3 = createTransition(sale, "pays(Buyer, Seller, Money)")
        LTransition t4 = createTransition(sale, "delivers(Seller, Buyer, Good)")

        bridgeTransitions(sale, tIn, t1)
        bridgeTransitions(sale, t1, t2)
        bridgeTransitions(sale, t2, t4)
        bridgeTransitions(sale, t4, t3)
        bridgeTransitions(sale, t3, tOut)

        batchExport(sale, "basicSaleInstance2")
    }

    static Net basicSaleModel() {
        Net sale = new Net()

        sale.function = createTransition("sells(Seller, Good, Money, Buyer)")

        LTransition tIn = createTransition(sale)
        LTransition tOut = createTransition(sale)

        LTransition t1 = createTransition(sale, "offers(Seller, Buyer, Good, Money)")
        LTransition t2 = createTransition(sale, "accepts(Buyer, Seller, Good, Money)")
        LTransition t3 = createTransition(sale, "pays(Buyer, Seller, Money)")
        LTransition t4 = createTransition(sale, "delivers(Seller, Buyer, Good)")

        bridgeTransitions(sale, tIn, t1)
        bridgeTransitions(sale, t1, t2)
        bridgeTransitions(sale, t2, t3)
        bridgeTransitions(sale, t3, tOut)
        bridgeTransitions(sale, t2, t4)
        bridgeTransitions(sale, t4, tOut)

        batchExport(sale, "basicSale")
    }

    static Net basicSaleWith2Parties() {
        Net sale = new Net()

        sale.function = createTransition("sells(Seller, Good, Money, Buyer)")

        LTransition tIn = createTransition(sale)
        LTransition tOut = createTransition(sale)

        LTransition t1s = createTransition(sale, "offers(Seller, Buyer, Good, Money)")
        LTransition t1b = propagateTransition(sale, t1s)
        LTransition t2b = createTransition(sale, "accepts(Buyer, Seller, Good, Money)")
        LTransition t2s = propagateTransition(sale, t2b)
        LTransition t3 = createTransition(sale, "pays(Buyer, Seller, Money)")
        LTransition t4 = createTransition(sale, "delivers(Seller, Buyer, Good)")

        bridgeTransitions(sale, tIn, t1s)
        bridgeTransitions(sale, t1b, t2b)
        bridgeTransitions(sale, t1s, t2s)
        bridgeTransitions(sale, t2b, t3)
        bridgeTransitions(sale, t3, tOut)
        bridgeTransitions(sale, t2s, t4)
        bridgeTransitions(sale, t4, tOut)

        batchExport(sale, "basicSale2Parties")
    }

    static Net basicSaleWithWorld() {
        Net sale = new Net()

        sale.function = createTransition("sells(Seller, Good, Money, Buyer)")

        LTransition tIn = createTransition(sale)
        LTransition tOut = createTransition(sale)

        LTransition t1s = createTransition(sale, "offers(Seller, Buyer, Good, Money)")
        LTransition t1 = propagateTransition(sale, t1s)
        LTransition t1b = propagateTransition(sale, t1)
        LTransition t2b = createTransition(sale, "accepts(Buyer, Seller, Good, Money)")
        LTransition t2 = propagateTransition(sale, t2b)
        LTransition t2s = propagateTransition(sale, t2)
        LTransition t3b = createTransition(sale, "pays(Buyer, Seller, Money)")
        LTransition t3 = propagateTransition(sale, t3b)
        LTransition t4s = createTransition(sale, "delivers(Seller, Buyer, Good)")
        LTransition t4 = propagateTransition(sale, t4s)

        bridgeTransitions(sale, tIn, t1s)
        bridgeTransitions(sale, t1b, t2b)
        bridgeTransitions(sale, t1s, t2s)
        bridgeTransitions(sale, t2b, t3b)
        bridgeTransitions(sale, t2s, t4s)
        bridgeTransitions(sale, t3, tOut)
        bridgeTransitions(sale, t4, tOut)

        batchExport(sale, "basicSaleWithWorld")
    }

    static Net basicSaleWithWorldAndTimeline() {
        Net sale = new Net()

        sale.function = createTransition("sells(Seller, Good, Money, Buyer)")

        LTransition tIn = createTransition(sale)
        LTransition tOut = createTransition(sale)

        LTransition t1s = createTransition(sale, "offers(Seller, Buyer, Good, Money)")
        LTransition t1 = propagateTransition(sale, t1s)
        LTransition t1b = propagateTransition(sale, t1)
        LTransition t2b = createTransition(sale, "accepts(Buyer, Seller, Good, Money)")
        LTransition t2 = propagateTransition(sale, t2b)
        LTransition t2s = propagateTransition(sale, t2)
        LTransition t3b = createTransition(sale, "pays(Buyer, Seller, Money)")
        LTransition t3 = propagateTransition(sale, t3b)
        LTransition t4s = createTransition(sale, "delivers(Seller, Buyer, Good)")
        LTransition t4 = propagateTransition(sale, t4s)

        bridgeTransitions(sale, tIn, t1s)
        bridgeTransitions(sale, t1b, t2b)
        bridgeTransitions(sale, t1s, t2s)
        bridgeTransitions(sale, t2b, t3b)
        bridgeTransitions(sale, t2s, t4s)

        // this is the message layer: it gives the timeline
        bridgeTransitions(sale, tIn, t1)
        bridgeTransitions(sale, t1, t2)
        bridgeTransitions(sale, t2, t3)
        bridgeTransitions(sale, t3, tOut)
        bridgeTransitions(sale, t2, t4)
        bridgeTransitions(sale, t4, tOut)

        batchExport(sale, "basicSaleWithWorldAndTimeline")
    }

}