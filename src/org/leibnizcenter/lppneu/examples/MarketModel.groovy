package org.leibnizcenter.lppneu.examples

import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Operation
import org.leibnizcenter.lppneu.components.language.Operator
import org.leibnizcenter.lppneu.components.lppetrinets.LPNet
import org.leibnizcenter.lppneu.components.lppetrinets.LPTransition
import org.leibnizcenter.pneu.components.basicpetrinet.BasicNet
import org.leibnizcenter.pneu.components.basicpetrinet.BasicTransition
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Place
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

    // JURIX paper

    static Net groundSaleInstance() {
        Net sale = new LPNet()

        Transition tIn = sale.createEmitterTransition()
        Transition t1 = sale.createTransition("offers(sally, bob, car, amount)")
        Transition t2 = sale.createTransition("accepts(bob, sally, car, amount)")
        Transition t3 = sale.createTransition("pays(bob, sally, amount)")
        Transition t4 = sale.createTransition("delivers(sally, bob, car)")
        Transition tOut = sale.createCollectorTransition()

        sale.createBridge(tIn, t1)
        sale.createBridge(t1, t2)
        sale.createBridge(t2, t3)
        sale.createBridge(t3, t4)
        sale.createBridge(t4, tOut)

        sale.resetIds()
        sale
    }

    static Net groundSaleModel() {
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

    static Net groundSaleNormativeModel() {
        Net sale = new LPNet()

        sale.function = LPTransition.build("sells(Seller, Buyer, Good, Money)")

        Transition tIn = sale.createEmitterTransition()
        Transition tOut = sale.createCollectorTransition()

        Expression sellerPowerOffer = Expression.parse("power(Seller, Buyer, offers(Seller, Buyer, Good, Money))")
        Expression buyerPowerAccept = Expression.parse("power(Buyer, Seller, accepts(Buyer, Seller, Good, Money))")
        Expression sellerDutyDeliver = Expression.parse("duty(Seller, Buyer, delivers(Seller, Buyer, Good))")
        Expression buyerDutyPay = Expression.parse("duty(Buyer, Seller, pays(Buyer, Seller, Money))")
        Expression buyerLiabilityPay = Expression.parse("liable(Buyer, Seller, enforce(pays(Buyer, Seller, Money)))")
        Expression sellerLiabilityDeliver = Expression.parse("liable(Seller, Buyer, enforce(deliver(Seller, Buyer, Good)))")

        Expression initBuyerPowerAccept = Expression.build(buyerPowerAccept, Operator.ISTANTIATION)
        Expression initBuyerDutyPay = Expression.build(buyerDutyPay, Operator.ISTANTIATION)
        Expression failureBuyerDutyPay = Expression.build(buyerDutyPay, Operator.FAILURE)
        Expression successBuyerDutyPay = Expression.build(buyerDutyPay, Operator.SUCCESS)
        Expression initSellerDutyDeliver = Expression.build(sellerDutyDeliver, Operator.ISTANTIATION)
        Expression failureSellerDutyDeliver = Expression.build(sellerDutyDeliver, Operator.FAILURE)
        Expression successSellerDutyDeliver = Expression.build(sellerDutyDeliver, Operator.SUCCESS)

        Operation offerEvent = Operation.parse("offers(Seller, Buyer, Good, Money)")
        Operation acceptEvent = Operation.parse("accepts(Buyer, Seller, Good, Money)")
        Operation payEvent = Operation.parse("pays(Buyer, Seller, Money)")
        Operation deliverEvent = Operation.parse("delivers(Seller, Buyer, Good)")

        Operation negPayEvent = Operation.build(payEvent, Operator.NEG)
        Operation negDeliverEvent = Operation.build(deliverEvent, Operator.NEG)

        Place pSellerPowerOffer = sale.createPlace(sellerPowerOffer)
        Place pBuyerPowerAccept = sale.createPlace(buyerPowerAccept)
        Place pSellerDutyDeliver = sale.createPlace(sellerDutyDeliver)
        Place pBuyerDutyPay = sale.createPlace(buyerDutyPay)
        Place pInitBuyerPowerAccept = sale.createPlace(initBuyerPowerAccept)
        Place pInitBuyerDutyPay = sale.createPlace(initBuyerDutyPay)
        Place pSuccessBuyerDutyPay = sale.createPlace(successBuyerDutyPay)
        Place pFailureBuyerDutyPay = sale.createPlace(failureBuyerDutyPay)
        Place pLiabilityBuyerPay = sale.createPlace(buyerLiabilityPay)
        Place pInitSellerDutyDeliver = sale.createPlace(initSellerDutyDeliver)
        Place pSuccessSellerDutyDeliver = sale.createPlace(successSellerDutyDeliver)
        Place pFailureSellerDutyDeliver = sale.createPlace(failureSellerDutyDeliver)
        Place pLiabilitySellerDeliver = sale.createPlace(sellerLiabilityDeliver)

        Transition tInitBuyerDutyPay = sale.createLinkTransition()
        Transition tSuccessBuyerDutyPay = sale.createLinkTransition()
        Transition tFailureBuyerDutyPay = sale.createLinkTransition()
        Transition tInitSellerDutyDeliver = sale.createLinkTransition()
        Transition tSuccessSellerDutyDeliver = sale.createLinkTransition()
        Transition tFailureSellerDutyDeliver = sale.createLinkTransition()

        Transition tOfferEvent = sale.createTransition(offerEvent)
        Transition tAcceptEvent = sale.createTransition(acceptEvent)
        Transition tPayEvent = sale.createTransition(payEvent)
        Transition tDeliverEvent = sale.createTransition(deliverEvent)

        Transition tNegPayEvent = sale.createTransition(negPayEvent)
        Transition tNegDeliverEvent = sale.createTransition(negDeliverEvent)

        sale.createArc(tIn, pSellerPowerOffer)
        sale.createBridge(pSellerPowerOffer, tOfferEvent, pInitBuyerPowerAccept)
        sale.createBridge(pInitBuyerPowerAccept, pBuyerPowerAccept)
        sale.createArc(pBuyerPowerAccept, tAcceptEvent)

        Place constraintOnPayEvent = sale.createLinkPlace()
        Place constraintOnDeliverEvent = sale.createLinkPlace()

        sale.createArcs(tAcceptEvent, [pInitBuyerDutyPay, pInitSellerDutyDeliver, constraintOnPayEvent, constraintOnDeliverEvent])

        sale.createArcs(constraintOnPayEvent, [tPayEvent, tNegPayEvent])
        sale.createArcs(constraintOnDeliverEvent, [tDeliverEvent, tNegDeliverEvent])

        sale.createArc(pInitBuyerDutyPay, tInitBuyerDutyPay)
        sale.createPlaceNexus(pBuyerDutyPay, [tInitBuyerDutyPay], [tSuccessBuyerDutyPay], [tPayEvent, tNegPayEvent, tFailureBuyerDutyPay], [], [])
        sale.createArc(pInitSellerDutyDeliver, tInitSellerDutyDeliver)
        sale.createPlaceNexus(pSellerDutyDeliver, [tInitSellerDutyDeliver], [tSuccessSellerDutyDeliver], [tDeliverEvent, tNegDeliverEvent, tFailureSellerDutyDeliver], [], [])

        sale.createPersistentBridge(tNegPayEvent, pFailureBuyerDutyPay, tFailureBuyerDutyPay)
        sale.createDiodeBridge(tPayEvent, pSuccessBuyerDutyPay, tSuccessBuyerDutyPay)
        sale.createArc(tFailureBuyerDutyPay, pLiabilityBuyerPay)

        sale.createPersistentBridge(tNegDeliverEvent, pFailureSellerDutyDeliver, tFailureSellerDutyDeliver)
        sale.createDiodeBridge(tDeliverEvent, pSuccessSellerDutyDeliver, tSuccessSellerDutyDeliver)
        sale.createArc(tFailureSellerDutyDeliver, pLiabilitySellerDeliver)

        sale.createBridge(tSuccessBuyerDutyPay, tOut)
        sale.createBridge(tSuccessSellerDutyDeliver, tOut)

        sale.resetIds()
        sale
    }

    static Net groundSaleScriptModel() {
        Net sale = new LPNet()

        sale.resetIds()
        sale
    }
}
