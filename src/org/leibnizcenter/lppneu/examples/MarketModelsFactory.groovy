package org.leibnizcenter.lppneu.examples

import org.leibnizcenter.lppneu.components.language.LPPNProgram
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.lppetrinets.LPNet
import org.leibnizcenter.lppneu.components.lppetrinets.LPPlace
import org.leibnizcenter.lppneu.components.lppetrinets.LPTransition
import org.leibnizcenter.lppneu.parser.LPPNLoader
import org.leibnizcenter.pneu.components.petrinet.Arc
import org.leibnizcenter.pneu.components.petrinet.Net

class MarketModelsFactory {

    static void batchExport(Net net, String filename) {
        net.exportToDot(filename)
        net.exportToLog(filename)
    }

    static Expression parse(String code) {
        LPPNProgram program = LPPNLoader.parseString(code + ".")

        if (program.parsingErrors.size() > 0)
            throw new RuntimeException("Parsing errors: " + program.parsingErrors)

        if (program.logicRules.size() > 1 || program.logicRules.size() == 0 || program.causalRules.size() != 0 || program.logicRules[0].body != null)
            throw new RuntimeException("Invalid input: only simple facts expected.")

        return program.logicRules[0].head
    }

    static LPTransition createTransition(String label = null) {
        LPTransition tr
        if (label != null)
            tr = new LPTransition(operation: parse(label).toOperation())
        else
            tr = new LPTransition()
        tr
    }

    static LPTransition createTransition(Net net, String label = null) {
        LPTransition tr = createTransition(label)
        net.transitionList << tr
        tr
    }

    static LPTransition propagateTransition(Net net, LPTransition source) {
        LPTransition target = new LPTransition(operation: source.operation,
                operator: source.operator,
                link: source.link)
        net.transitionList << target
        bridgeTransitions(net, source, target)
        target
    }

    static LPPlace createPlace(String label = null) {
        LPPlace pl
        if (label != null)
            pl = new LPPlace(expression: parse(label))
        else
            pl = new LPPlace()
        pl
    }

    static LPPlace createPlace(Net net, String label = null) {
        LPPlace pl = createPlace(label)
        net.placeList << pl
        pl
    }

    static void bridgeTransitions(Net net, LPTransition t1, LPTransition t2) {

        if (!net.transitionList.contains(t1) || !net.transitionList.contains(t2)) {
            throw new RuntimeException("Error: this net does not contain the transition(s) to bridge")
        }

        LPPlace pBridge = createPlace(net)
        List<Arc> arcs = Arc.buildArcs(t1, pBridge, t2)

        net.arcList += arcs
    }

    static Net basicSaleInstance1() {
        Net sale = new LPNet()

        sale.function = createTransition("sells(Seller, Good, Money, Buyer)")

        LPTransition tIn = createTransition(sale)
        LPTransition tOut = createTransition(sale)

        LPTransition t1 = createTransition(sale, "offers(Seller, Buyer, Good, Money)")
        LPTransition t2 = createTransition(sale, "accepts(Buyer, Seller, Good, Money)")
        LPTransition t3 = createTransition(sale, "pays(Buyer, Seller, Money)")
        LPTransition t4 = createTransition(sale, "delivers(Seller, Buyer, Good)")

        bridgeTransitions(sale, tIn, t1)
        bridgeTransitions(sale, t1, t2)
        bridgeTransitions(sale, t2, t3)
        bridgeTransitions(sale, t3, t4)
        bridgeTransitions(sale, t4, tOut)

        batchExport(sale, "basicSaleInstance1")
    }

    static Net basicSaleInstance2() {
        Net sale = new LPNet()

        sale.function = createTransition("sells(Seller, Good, Money, Buyer)")

        LPTransition tIn = createTransition(sale)
        LPTransition tOut = createTransition(sale)

        LPTransition t1 = createTransition(sale, "offers(Seller, Buyer, Good, Money)")
        LPTransition t2 = createTransition(sale, "accepts(Buyer, Seller, Good, Money)")
        LPTransition t3 = createTransition(sale, "pays(Buyer, Seller, Money)")
        LPTransition t4 = createTransition(sale, "delivers(Seller, Buyer, Good)")

        bridgeTransitions(sale, tIn, t1)
        bridgeTransitions(sale, t1, t2)
        bridgeTransitions(sale, t2, t4)
        bridgeTransitions(sale, t4, t3)
        bridgeTransitions(sale, t3, tOut)

        batchExport(sale, "basicSaleInstance2")
    }

    static Net basicSaleModel() {
        Net sale = new LPNet()

        sale.function = createTransition("sells(Seller, Good, Money, Buyer)")

        LPTransition tIn = createTransition(sale)
        LPTransition tOut = createTransition(sale)

        LPTransition t1 = createTransition(sale, "offers(Seller, Buyer, Good, Money)")
        LPTransition t2 = createTransition(sale, "accepts(Buyer, Seller, Good, Money)")
        LPTransition t3 = createTransition(sale, "pays(Buyer, Seller, Money)")
        LPTransition t4 = createTransition(sale, "delivers(Seller, Buyer, Good)")

        bridgeTransitions(sale, tIn, t1)
        bridgeTransitions(sale, t1, t2)
        bridgeTransitions(sale, t2, t3)
        bridgeTransitions(sale, t3, tOut)
        bridgeTransitions(sale, t2, t4)
        bridgeTransitions(sale, t4, tOut)

        batchExport(sale, "basicSale")
    }

    static Net basicSaleWith2Parties() {
        Net sale = new LPNet()

        sale.function = createTransition("sells(Seller, Good, Money, Buyer)")

        LPTransition tIn = createTransition(sale)
        LPTransition tOut = createTransition(sale)

        LPTransition t1s = createTransition(sale, "offers(Seller, Buyer, Good, Money)")
        LPTransition t1b = propagateTransition(sale, t1s)
        LPTransition t2b = createTransition(sale, "accepts(Buyer, Seller, Good, Money)")
        LPTransition t2s = propagateTransition(sale, t2b)
        LPTransition t3 = createTransition(sale, "pays(Buyer, Seller, Money)")
        LPTransition t4 = createTransition(sale, "delivers(Seller, Buyer, Good)")

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
        Net sale = new LPNet()

        sale.function = createTransition("sells(Seller, Good, Money, Buyer)")

        LPTransition tIn = createTransition(sale)
        LPTransition tOut = createTransition(sale)

        LPTransition t1s = createTransition(sale, "offers(Seller, Buyer, Good, Money)")
        LPTransition t1 = propagateTransition(sale, t1s)
        LPTransition t1b = propagateTransition(sale, t1)
        LPTransition t2b = createTransition(sale, "accepts(Buyer, Seller, Good, Money)")
        LPTransition t2 = propagateTransition(sale, t2b)
        LPTransition t2s = propagateTransition(sale, t2)
        LPTransition t3b = createTransition(sale, "pays(Buyer, Seller, Money)")
        LPTransition t3 = propagateTransition(sale, t3b)
        LPTransition t4s = createTransition(sale, "delivers(Seller, Buyer, Good)")
        LPTransition t4 = propagateTransition(sale, t4s)

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
        Net sale = new LPNet()

        sale.function = createTransition("sells(Seller, Good, Money, Buyer)")

        LPTransition tIn = createTransition(sale)
        LPTransition tOut = createTransition(sale)

        LPTransition t1s = createTransition(sale, "offers(Seller, Buyer, Good, Money)")
        LPTransition t1 = propagateTransition(sale, t1s)
        LPTransition t1b = propagateTransition(sale, t1)
        LPTransition t2b = createTransition(sale, "accepts(Buyer, Seller, Good, Money)")
        LPTransition t2 = propagateTransition(sale, t2b)
        LPTransition t2s = propagateTransition(sale, t2)
        LPTransition t3b = createTransition(sale, "pays(Buyer, Seller, Money)")
        LPTransition t3 = propagateTransition(sale, t3b)
        LPTransition t4s = createTransition(sale, "delivers(Seller, Buyer, Good)")
        LPTransition t4 = propagateTransition(sale, t4s)

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