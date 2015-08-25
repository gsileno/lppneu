package org.leibnizcenter.lppneu.examples

import org.leibnizcenter.lppneu.components.lppetrinets.LPNet
import org.leibnizcenter.lppneu.components.lppetrinets.LPTransition
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Transition

class MarketModel {
    static Net basicSaleInstance1() {
        Net sale = new LPNet()

        sale.function = LPTransition.build("sells(Seller, Good, Money, Buyer)")

        Transition tIn = sale.createEmitterTransition()
        Transition t1 = sale.createTransition("offers(Seller, Buyer, Good, Money)")
        Transition t2 = sale.createTransition("accepts(Buyer, Seller, Good, Money)")
        Transition t3 = sale.createTransition("pays(Buyer, Seller, Money)")
        Transition t4 = sale.createTransition("delivers(Seller, Buyer, Good)")
        Transition tOut = sale.createCollectorTransition()

        sale.createBridge(tIn, t1)
        sale.createBridge(t1, t2)
        sale.createBridge(t2, t3)
        sale.createBridge(t3, t4)
        sale.createBridge(t4, tOut)

        sale.resetIds()
        sale
    }

    static Net basicSaleInstance2() {
        Net sale = new LPNet()

        sale.function = LPTransition.build("sells(Seller, Good, Money, Buyer)")

        Transition tIn = sale.createEmitterTransition()
        Transition t1 = sale.createTransition("offers(Seller, Buyer, Good, Money)")
        Transition t2 = sale.createTransition("accepts(Buyer, Seller, Good, Money)")
        Transition t3 = sale.createTransition("pays(Buyer, Seller, Money)")
        Transition t4 = sale.createTransition("delivers(Seller, Buyer, Good)")
        Transition tOut = sale.createCollectorTransition()

        sale.createBridge(tIn, t1)
        sale.createBridge(t1, t2)
        sale.createBridge(t2, t4)
        sale.createBridge(t4, t3)
        sale.createBridge(t3, tOut)

        sale.resetIds()
        sale
    }

    static Net basicSaleModel() {
        Net sale = new LPNet()

        sale.function = LPTransition.build("sells(Seller, Good, Money, Buyer)")

        Transition tIn = sale.createEmitterTransition()
        Transition t1 = sale.createTransition("offers(Seller, Buyer, Good, Money)")
        Transition t2 = sale.createTransition("accepts(Buyer, Seller, Good, Money)")
        Transition t3 = sale.createTransition("pays(Buyer, Seller, Money)")
        Transition t4 = sale.createTransition("delivers(Seller, Buyer, Good)")
        Transition tOut = sale.createCollectorTransition()

        sale.createBridge(tIn, t1)
        sale.createBridge(t1, t2)
        sale.createBridge(t2, t3)
        sale.createBridge(t3, tOut)
        sale.createBridge(t2, t4)
        sale.createBridge(t4, tOut)

        sale.resetIds()
        sale
    }

    static Net basicSaleWith2Parties() {
        Net sale = new LPNet()

        sale.function = LPTransition.build("sells(Seller, Good, Money, Buyer)")

        Transition tIn = sale.createEmitterTransition()
        Transition t1s = sale.createTransition("offers(Seller, Buyer, Good, Money)")
        Transition t1b = sale.propagateTransition(t1s)
        Transition t2b = sale.createTransition("accepts(Buyer, Seller, Good, Money)")
        Transition t2s = sale.propagateTransition(t2b)
        Transition t3 = sale.createTransition("pays(Buyer, Seller, Money)")
        Transition t4 = sale.createTransition("delivers(Seller, Buyer, Good)")
        Transition tOut = sale.createCollectorTransition()

        sale.createBridge(tIn, t1s)
        sale.createBridge(t1b, t2b)
        sale.createBridge(t1s, t2s)
        sale.createBridge(t2b, t3)
        sale.createBridge(t3, tOut)
        sale.createBridge(t2s, t4)
        sale.createBridge(t4, tOut)

        sale.resetIds()
        sale
    }

    static Net basicSaleWithWorld() {
        Net sale = new LPNet()

        sale.function = LPTransition.build("sells(Seller, Good, Money, Buyer)")

        Transition tIn = sale.createEmitterTransition()
        Transition t1s = sale.createTransition("offers(Seller, Buyer, Good, Money)")
        Transition t1 = sale.propagateTransition(t1s)
        Transition t1b = sale.propagateTransition(t1)
        Transition t2b = sale.createTransition("accepts(Buyer, Seller, Good, Money)")
        Transition t2 = sale.propagateTransition(t2b)
        Transition t2s = sale.propagateTransition(t2)
        Transition t3b = sale.createTransition("pays(Buyer, Seller, Money)")
        Transition t3 = sale.propagateTransition(t3b)
        Transition t4s = sale.createTransition("delivers(Seller, Buyer, Good)")
        Transition t4 = sale.propagateTransition(t4s)
        Transition tOut = sale.createCollectorTransition()

        sale.createBridge(tIn, t1s)
        sale.createBridge(t1b, t2b)
        sale.createBridge(t1s, t2s)
        sale.createBridge(t2b, t3b)
        sale.createBridge(t2s, t4s)
        sale.createBridge(t3, tOut)
        sale.createBridge(t4, tOut)

        sale.resetIds()
        sale
    }

    static Net basicSaleWithWorldAndTimeline() {
        Net sale = new LPNet()

        sale.function = LPTransition.build("sells(Seller, Good, Money, Buyer)")

        Transition tIn = sale.createEmitterTransition()
        Transition t1s = sale.createTransition("offers(Seller, Buyer, Good, Money)")
        Transition t1 = sale.propagateTransition(t1s)
        Transition t1b = sale.propagateTransition(t1)
        Transition t2b = sale.createTransition("accepts(Buyer, Seller, Good, Money)")
        Transition t2 = sale.propagateTransition(t2b)
        Transition t2s = sale.propagateTransition(t2)
        Transition t3b = sale.createTransition("pays(Buyer, Seller, Money)")
        Transition t3 = sale.propagateTransition(t3b)
        Transition t4s = sale.createTransition("delivers(Seller, Buyer, Good)")
        Transition t4 = sale.propagateTransition(t4s)
        Transition tOut = sale.createCollectorTransition()

        sale.createBridge(tIn, t1s)
        sale.createBridge(t1b, t2b)
        sale.createBridge(t1s, t2s)
        sale.createBridge(t2b, t3b)
        sale.createBridge(t2s, t4s)

        // this is the message layer: it gives the timeline
        sale.createBridge(tIn, t1)
        sale.createBridge(t1, t2)
        sale.createBridge(t2, t3)
        sale.createBridge(t3, tOut)
        sale.createBridge(t2, t4)
        sale.createBridge(t4, tOut)

        sale.resetIds()
        sale
    }
}
