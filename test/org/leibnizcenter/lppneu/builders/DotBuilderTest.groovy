package org.leibnizcenter.lppneu.builders

import org.leibnizcenter.lppneu.components.language.LPPNProgram
import org.leibnizcenter.lppneu.components.lppetrinets.LPNet
import org.leibnizcenter.lppneu.parsers.LPPNLoader
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Place

class DotBuilderTest extends GroovyTestCase {

    static void batchExport(Net net, String filename) {
        net.exportToDot(filename)
        net.exportToLog(filename)
    }

    static void batchExport(LPNHandler handler, String filename) {
        println ("creating original")
        batchExport(handler.net, filename+".original")
        println ("creating simplified")
        batchExport(handler.simplifiedNet, filename+".simplified")
        println ("creating unified")
        batchExport(handler.unifiedNet, filename+".unified")
    }

    static LPNHandler batchConvert(String code) {
        LPPNProgram program = LPPNLoader.parseString(code)
        LPNHandler handler = new LPNHandler()
        handler.convert(program)
        return handler
    }

//
//    void testSimpleFact() {
//        LPNHandler convert = batchConvert("a.")
//        batchExport(convert, "simpleFact")
//    }
//
//    void testTwoSimpleFacts() {
//        LPNHandler convert = batchConvert("a. b.")
//        batchExport(convert, "twoSimpleFacts")
//    }
//
//    void testTwoSimpleEqualFacts() {
//        LPNHandler convert = batchConvert("a. a.")
//        batchExport(convert, "twoSimpleEqualFacts")
//    }
//
//    void testSimpleLogicRule() {
//        LPNHandler convert = batchConvert("b :- a.")
//        batchExport(convert, "simpleLogicRule")
//    }
//
//    void testSimpleCasualRule() {
//        LPNHandler convert = batchConvert("a -> b.")
//        batchExport(convert, "simpleCausalRule")
//    }
//
//    void testCompoundLogicRule() {
//        LPNHandler convert = batchConvert("r :- p and q.")
//        batchExport(convert, "compoundLogicRule")
//    }
//
//    void testChainingLogicRules() {
//        LPNHandler convert = batchConvert("q :- p. r :- q.")
//        batchExport(convert, "chainingLogicRule")
//    }
//
//    void testChainingLogicRules2() {
//        LPNHandler convert = batchConvert("p :- a and b. q :- a and b.")
//        batchExport(convert, "chainingLogicRule2")
//    }
//
//    void testInversedRules() {
//        LPNHandler convert = batchConvert("a and b :- r. p :- a and b.")
//        batchExport(convert, "inversedRules")
//    }
//
//    void testMultipleLogicRules() {
//        LPNHandler convert = batchConvert("p :- a and a. -p :- b. p :- c.")
//        batchExport(convert, "multipleLogicRules")
//    }
//
//
//    void testCompoundCausalRule() {
//        LPNHandler convert = batchConvert("p and q -> r.")
//        batchExport(convert, "compoundCausalRule")
//    }
//
//    void testChainingCausalRules() {
//        LPNHandler convert = batchConvert("p -> q. q -> r.")
//        batchExport(convert, "chainingCausalRule")
//    }
//
//    void testSeqFact() {
//        LPNHandler convert = batchConvert("p seq q.")
//        batchExport(convert, "seqFact")
//    }
//
//    void testAltFact() {
//        LPNHandler convert = batchConvert("p alt q.")
//        batchExport(convert, "altFact")
//    }
//
//    void testParFact() {
//        LPNHandler convert = batchConvert("p par q.")
//        batchExport(convert, "parFact")
//    }
//
//    void testOptFact() {
//        LPNHandler convert = batchConvert("p opt q.")
//        batchExport(convert, "optFact")
//    }
//
//    void testEvent() {
//        LPNHandler convert = batchConvert("-> a.")
//        batchExport(convert, "event")
//    }
//
//    void testConditionEvent() {
//        LPNHandler convert = batchConvert("-> a and b.")
//        batchExport(convert, "conditionEvent")
//    }
//
//    void testSeqProcess() {
//        LPNHandler convert = batchConvert("-> p seq q.")
//        batchExport(convert, "seqProcess")
//    }
//
//    void testAltProcess() {
//        LPNHandler convert = batchConvert("-> p alt q.")
//        batchExport(convert, "altProcess")
//    }
//
//    void testParProcess() {
//        LPNHandler convert = batchConvert("-> p par q.")
//        batchExport(convert, "parProcess")
//    }
//
//    void testOptProcess() {
//        LPNHandler convert = batchConvert("-> p opt q.")
//        batchExport(convert, "optProcess")
//    }
//
//    void testCompoundProcess() {
//        LPNHandler convert = batchConvert("-> a seq (b par c)")
//        batchExport(convert, "compoundProcess")
//    }

//    void testTriple() {
//        LPNHandler convert = batchConvert("a. -a.")
//        Net net = convert.tripleAnchoringNet(convert.net)
//
//        assert net.subNets.size() == 3
//        assert net.subNets[2].placeList.size() == 3
//        assert net.subNets[2].transitionList.size() == 6
//        assert net.subNets[2].arcList.size() == 12
//    }

    void testSale() {
        LPNHandler handler = batchConvert("offers(Seller, Good, Money) seq accepts(Buyer, Good, Money).")
        batchExport(handler, "sale")
    }

//    void testTransitionAssociation() {
//        LPNHandler convert = batchConvert("a -> b.")

//        assert net.subNets.size() == 6
//        assert net.subNets[4].placeList.size() == 2
//        assert net.subNets[4].transitionList.size() == 2
//        assert net.subNets[4].arcList.size() == 2
//
//        assert net.subNets[5].placeList.size() == 2
//        assert net.subNets[5].transitionList.size() == 3
//        assert net.subNets[5].arcList.size() == 3
//    }

}
