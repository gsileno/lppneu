package lppneu.examples

import lppneu.components.language.Expression
import lppneu.components.language.Operation
import lppneu.components.language.Operator
import lppneu.components.lppetrinets.LPNet
import lppneu.components.lppetrinets.LPTransition
import pneu.components.basicpetrinet.BasicNet
import pneu.components.basicpetrinet.BasicTransition
import pneu.components.petrinet.Net
import pneu.components.petrinet.Place
import pneu.components.petrinet.Transition

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

        Expression commitmentBuyGood = Expression.parse("commits(Buyer, buys(Buyer, Good, Money))")
        Expression failureCommitmentBuyGood = Expression.build(commitmentBuyGood, Operator.FAILURE)
        Expression successCommitmentBuyGood = Expression.build(commitmentBuyGood, Operator.SUCCESS)

        Expression affordanceBuyGood = Expression.parse("affords(Buyer, buys(Buyer, Good, Money))")
        Expression initAffordanceBuyGood = Expression.build(affordanceBuyGood, Operator.ISTANTIATION)

        Expression commitmentWaitForOffer = Expression.parse("waitsFor(offers(Seller, Buyer, Good, Money))")
        Expression failureCommitmentWaitForOffer = Expression.build(commitmentWaitForOffer, Operator.FAILURE)
        Expression successCommitmentWaitForOffer = Expression.build(commitmentWaitForOffer, Operator.SUCCESS)

        Expression commitmentWaitForDelivery = Expression.parse("waitsFor(delivers(Seller, Buyer, Good))")
        Expression failureCommitmentWaitForDelivery = Expression.build(commitmentWaitForDelivery, Operator.FAILURE)
        Expression successCommitmentWaitForDelivery = Expression.build(commitmentWaitForDelivery, Operator.SUCCESS)

        Expression ownsMoney = Expression.parse("owns(Buyer, Money)")

        Operation buyEvent = Operation.parse("buys(Buyer, Good, Money)")
        Operation negBuyEvent = Operation.build(buyEvent, Operator.NEG)

        Operation offerEvent = Operation.parse("offers(Seller, Buyer, Good, Money)")
        Operation acceptEvent = Operation.parse("accepts(Buyer, Seller, Good, Money)")
        Operation payEvent = Operation.parse("pays(Buyer, Seller, Money)")
        Operation deliverEvent = Operation.parse("delivers(Seller, Buyer, Good)")
        Operation enforceEvent = Operation.parse("enforces(Buyer, delivers(Seller, Buyer, Good))")

        Operation timeOutOffer = Operation.parse("timeout(offers(Seller, Buyer, Good, Money))")
        Operation timeOutDelivery = Operation.parse("timeout(delivers(Seller, Buyer, Good))")

        Place pCommitmentBuyGood = sale.createPlace(commitmentBuyGood)
        Place pFailureCommitmentBuyGood = sale.createPlace(failureCommitmentBuyGood)
        Place pSuccessCommitmentBuyGood = sale.createPlace(successCommitmentBuyGood)

        Place pAffordanceBuyGood = sale.createPlace(affordanceBuyGood)
        Place pInitAffordanceBuyGood = sale.createPlace(initAffordanceBuyGood)

        Place pCommitmentWaitForOffer = sale.createPlace(commitmentWaitForOffer)
        Place pFailureCommitmentWaitForOffer = sale.createPlace(failureCommitmentWaitForOffer)
        Place pSuccessCommitmentWaitForOffer = sale.createPlace(successCommitmentWaitForOffer)

        Place pCommitmentWaitForDelivery = sale.createPlace(commitmentWaitForDelivery)
        Place pFailureCommitmentWaitForDelivery = sale.createPlace(failureCommitmentWaitForDelivery)
        Place pSuccessCommitmentWaitForDelivery = sale.createPlace(successCommitmentWaitForDelivery)

        Place pOwnsMoney = sale.createPlace(ownsMoney)

        Place pConstraintTimeoutOffer = sale.createLinkPlace()
        Place pConstraintTimeoutDelivery = sale.createLinkPlace()

        Transition tSuccessCommitmentBuyGood = sale.createLinkTransition()
        Transition tFailureCommitmentBuyGood = sale.createLinkTransition()
        Transition tInitCommitmentWaitForOffer = sale.createLinkTransition()
        Transition tSuccessCommitmentWaitForOffer = sale.createLinkTransition()
        Transition tFailureCommitmentWaitForOffer = sale.createLinkTransition()
        Transition tInitCommitmentWaitForDelivery = sale.createLinkTransition()
        Transition tSuccessCommitmentWaitForDelivery = sale.createLinkTransition()
        Transition tFailureCommitmentWaitForDelivery = sale.createLinkTransition()
        Transition tInitAffordanceBuyGood = sale.createLinkTransition()

        Transition tBuyEvent = sale.createTransition(buyEvent)
        Transition tNegBuyEvent = sale.createTransition(negBuyEvent)

        Transition tOfferEvent = sale.createTransition(offerEvent)
        Transition tAcceptEvent = sale.createTransition(acceptEvent)
        Transition tPayEvent = sale.createTransition(payEvent)
        Transition tDeliverEvent = sale.createTransition(deliverEvent)
        Transition tEnforceEvent = sale.createTransition(enforceEvent)

        Transition tTimeOutOffer = sale.createTransition(timeOutOffer)
        Transition tTimeOutDelivery = sale.createTransition(timeOutDelivery)

        Transition tMainIn = sale.createEmitterTransition()
        Transition tCatalystIn = sale.createEmitterTransition()

        sale.createPlaceNexus(pCommitmentBuyGood, [tMainIn], [tFailureCommitmentBuyGood, tSuccessCommitmentBuyGood], [tBuyEvent, tNegBuyEvent], [], [])
        sale.createBridge(tNegBuyEvent, pFailureCommitmentBuyGood, tFailureCommitmentBuyGood)
        sale.createBridge(tBuyEvent, pSuccessCommitmentBuyGood, tSuccessCommitmentBuyGood)

        sale.createPlaceNexus(pCommitmentWaitForOffer, [tInitCommitmentWaitForOffer], [tFailureCommitmentWaitForOffer, tSuccessCommitmentWaitForOffer], [tBuyEvent, tNegBuyEvent], [], [])
        sale.createBridge(tTimeOutOffer, pFailureCommitmentWaitForOffer, tFailureCommitmentWaitForOffer)
        sale.createBridge(tOfferEvent, pSuccessCommitmentWaitForOffer, tSuccessCommitmentWaitForOffer)
        sale.createArc(pConstraintTimeoutOffer, tTimeOutOffer)
        sale.createArc(pConstraintTimeoutOffer, tOfferEvent)

        sale.createPlaceNexus(pCommitmentWaitForDelivery, [tInitCommitmentWaitForDelivery], [tFailureCommitmentWaitForDelivery, tSuccessCommitmentWaitForDelivery], [tBuyEvent, tNegBuyEvent], [], [])
        sale.createBridge(tNegBuyEvent, pFailureCommitmentWaitForDelivery, tFailureCommitmentWaitForDelivery)
        sale.createBridge(tBuyEvent, pSuccessCommitmentWaitForDelivery, tSuccessCommitmentWaitForDelivery)
        sale.createArc(pConstraintTimeoutDelivery, tTimeOutDelivery)
        sale.createArc(pConstraintTimeoutDelivery, tDeliverEvent)

        sale.createArc(tCatalystIn, pOwnsMoney)
        Transition tCatalyst = sale.createLinkTransition()
        sale.createBiflowArc(pOwnsMoney, tCatalyst)
        sale.createPersistentBridge(tCatalyst, pInitAffordanceBuyGood, tInitAffordanceBuyGood)
        sale.createDiodeArc(tInitAffordanceBuyGood, pAffordanceBuyGood)

        Transition tStartAction = sale.createLinkTransition()
        Place pStartAction = sale.createLinkPlace()

        sale.createDiodeArc(tStartAction, pStartAction)
        sale.createBiflowArc(pStartAction, tInitCommitmentWaitForOffer)

        sale.createBiflowArc(tStartAction, pCommitmentBuyGood)
        sale.createBiflowArc(tStartAction, pAffordanceBuyGood)
        sale.createBridge(tStartAction, tInitCommitmentWaitForOffer)
        sale.createBridge(tSuccessCommitmentWaitForOffer, tAcceptEvent)
        sale.createBridge(tFailureCommitmentWaitForDelivery, tEnforceEvent)

        Place pFailureSink = sale.createLinkPlace()
        sale.createArc(tFailureCommitmentWaitForOffer, pFailureSink)
        sale.createArc(tFailureCommitmentWaitForDelivery, pFailureSink)
        sale.createArc(pFailureSink, tNegBuyEvent)

        Place pBridge1 = sale.createBridge(tAcceptEvent, tPayEvent)
        Place pBridge2 = sale.createBridge(tPayEvent, tBuyEvent)

        sale.createResetArc(pStartAction, tFailureCommitmentBuyGood)
        sale.createResetArc(pBridge1, tFailureCommitmentBuyGood)
        sale.createResetArc(pBridge2, tFailureCommitmentBuyGood)

        sale.createBridge(tAcceptEvent, tInitCommitmentWaitForDelivery)
        sale.createBridge(tSuccessCommitmentWaitForDelivery, tBuyEvent)

        sale.createArc(tInitCommitmentWaitForOffer, pConstraintTimeoutOffer)
        sale.createArc(tInitCommitmentWaitForDelivery, pConstraintTimeoutDelivery)

        sale.resetIds()
        sale
    }

}
