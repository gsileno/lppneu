package org.leibnizcenter.lppneu.examples

import org.leibnizcenter.lppneu.components.lppetrinets.LPNet
import org.leibnizcenter.lppneu.components.lppetrinets.LPTransition
import org.leibnizcenter.pneu.components.basicpetrinet.BasicNet
import org.leibnizcenter.pneu.components.basicpetrinet.BasicTransition
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Transition

class MarketModel {
    static Net basicSaleInstance1() {
        Net sale = new LPNet()

        sale.function = LPTransition.build("sells(Seller, Buyer, Good, Money)")

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

        sale.function = LPTransition.build("sells(Seller, Buyer, Good, Money)")

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

        sale.function = LPTransition.build("sells(Seller, Buyer, Good, Money)")

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

    static Net basicSale() {
        Net sale = new LPNet()

        sale.function = LPTransition.build("sells(Seller, Buyer, Good, Money)")

        Transition tIn = sale.createEmitterTransition()
        Transition t1 = sale.createTransition("sells(Seller, Buyer, Good, Money)")
        Transition tOut = sale.createCollectorTransition()

        sale.createBridge(tIn, t1)
        sale.createBridge(t1, tOut)

        sale.resetIds()
        sale
    }

    static Net basicSaleWithTaxes() {
        Net sale = new LPNet()

        sale.function = LPTransition.build("sellsAndPaysTaxes(Seller, Buyer, Good, Money)")

        Transition tIn = sale.createEmitterTransition()
        Transition t1 = sale.createTransition("sells(Seller, Buyer, Good, Money)")
        Transition t2 = sale.createTransition("paysTax(Seller, administration, Money)")
        Transition t3 = sale.createTransition("paysTax(Buyer, administration, Money)")
        Transition tOut = sale.createCollectorTransition()

        sale.createBridge(tIn, t1)
        sale.createBridge(t1, t2)
        sale.createBridge(t1, t3)
        sale.createBridge(t2, tOut)
        sale.createBridge(t3, tOut)

        sale.resetIds()
        sale
    }

    static Net basicSaleWith2Parties() {
        Net sale = new LPNet()

        sale.function = LPTransition.build("sells(Seller, Buyer, Good, Money)")

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

        sale.function = LPTransition.build("sells(Seller, Buyer, Good, Money)")

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

        sale.function = LPTransition.build("sells(Seller, Buyer, Good, Money)")

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

    // a swap sale is a sale in which the buyer is the seller of the other
    static Net basicSwapSale() {
        Net sale = new BasicNet()

        sale.function = BasicTransition.build("sells(Seller, Buyer, Good, Money)")

        Transition tSellerIn = sale.createEmitterTransition()
        Transition tBuyerIn = sale.createEmitterTransition()
        Transition tSellerOut = sale.createCollectorTransition()
        Transition tBuyerOut = sale.createCollectorTransition()

        Transition t1s = sale.createTransition("offers(Seller, Buyer, Good, Money)")
        Transition t1b = sale.propagateTransition(t1s)
        Transition t2b = sale.createTransition("accepts(Buyer, Seller, Good, Money)")
        Transition t2s = sale.propagateTransition(t2b)
        Transition t3 = sale.createTransition("pays(Buyer, Seller, Money)")
        Transition t4 = sale.createTransition("delivers(Seller, Buyer, Good)")

        sale.createBridge(tSellerIn, t1s)
        sale.createBridge(tBuyerIn, t1b)
        sale.createBridge(t1b, t2b)
        sale.createBridge(t1s, t2s)
        sale.createBridge(t2b, t3)
        sale.createBridge(t3, tBuyerOut)
        sale.createBridge(t2s, t4)
        sale.createBridge(t4, tSellerOut)

        Net swap = new BasicNet()

        swap.include(sale)

        swap.resetIds()
        swap

    }

}
