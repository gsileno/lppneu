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

    static void batchExport(LPPN2LPN conversion, String filename) {
        println ("creating original")
        batchExport(conversion.net, filename+".original")
        println ("creating simplified")
        batchExport(conversion.simplifiedNet, filename+".simplified")
        println ("creating unified")
        batchExport(conversion.unifiedNet, filename+".unified")
    }

    static LPPN2LPN batchConvert(String code) {
        LPPNProgram program = LPPNLoader.parseString(code)
        LPPN2LPN conversion = new LPPN2LPN()
        conversion.convert(program)
        return conversion
    }

//
//    void testSimpleFact() {
//        LPPN2LPN convert = batchConvert("a.")
//        batchExport(convert, "simpleFact")
//    }
//
//    void testTwoSimpleFacts() {
//        LPPN2LPN convert = batchConvert("a. b.")
//        batchExport(convert, "twoSimpleFacts")
//    }
//
//    void testTwoSimpleEqualFacts() {
//        LPPN2LPN convert = batchConvert("a. a.")
//        batchExport(convert, "twoSimpleEqualFacts")
//    }
//
//    void testSimpleLogicRule() {
//        LPPN2LPN convert = batchConvert("b :- a.")
//        batchExport(convert, "simpleLogicRule")
//    }
//
//    void testSimpleCasualRule() {
//        LPPN2LPN convert = batchConvert("a -> b.")
//        batchExport(convert, "simpleCausalRule")
//    }
//
//    void testCompoundLogicRule() {
//        LPPN2LPN convert = batchConvert("r :- p and q.")
//        batchExport(convert, "compoundLogicRule")
//    }
//
//    void testChainingLogicRules() {
//        LPPN2LPN convert = batchConvert("q :- p. r :- q.")
//        batchExport(convert, "chainingLogicRule")
//    }
//
//    void testChainingLogicRules2() {
//        LPPN2LPN convert = batchConvert("p :- a and b. q :- a and b.")
//        batchExport(convert, "chainingLogicRule2")
//    }
//
//    void testInversedRules() {
//        LPPN2LPN convert = batchConvert("a and b :- r. p :- a and b.")
//        batchExport(convert, "inversedRules")
//    }
//
//    void testMultipleLogicRules() {
//        LPPN2LPN convert = batchConvert("p :- a and a. -p :- b. p :- c.")
//        batchExport(convert, "multipleLogicRules")
//    }
//
//
//    void testCompoundCausalRule() {
//        LPPN2LPN convert = batchConvert("p and q -> r.")
//        batchExport(convert, "compoundCausalRule")
//    }
//
//    void testChainingCausalRules() {
//        LPPN2LPN convert = batchConvert("p -> q. q -> r.")
//        batchExport(convert, "chainingCausalRule")
//    }
//
//    void testSeqFact() {
//        LPPN2LPN convert = batchConvert("p seq q.")
//        batchExport(convert, "seqFact")
//    }
//
//    void testAltFact() {
//        LPPN2LPN convert = batchConvert("p alt q.")
//        batchExport(convert, "altFact")
//    }
//
//    void testParFact() {
//        LPPN2LPN convert = batchConvert("p par q.")
//        batchExport(convert, "parFact")
//    }
//
//    void testOptFact() {
//        LPPN2LPN convert = batchConvert("p opt q.")
//        batchExport(convert, "optFact")
//    }
//
//    void testEvent() {
//        LPPN2LPN convert = batchConvert("-> a.")
//        batchExport(convert, "event")
//    }
//
//    void testConditionEvent() {
//        LPPN2LPN convert = batchConvert("-> a and b.")
//        batchExport(convert, "conditionEvent")
//    }
//
//    void testSeqProcess() {
//        LPPN2LPN convert = batchConvert("-> p seq q.")
//        batchExport(convert, "seqProcess")
//    }
//
//    void testAltProcess() {
//        LPPN2LPN convert = batchConvert("-> p alt q.")
//        batchExport(convert, "altProcess")
//    }
//
//    void testParProcess() {
//        LPPN2LPN convert = batchConvert("-> p par q.")
//        batchExport(convert, "parProcess")
//    }
//
//    void testOptProcess() {
//        LPPN2LPN convert = batchConvert("-> p opt q.")
//        batchExport(convert, "optProcess")
//    }
//
//    void testCompoundProcess() {
//        LPPN2LPN convert = batchConvert("-> a seq (b par c)")
//        batchExport(convert, "compoundProcess")
//    }

//    void testTriple() {
//        LPPN2LPN convert = batchConvert("a. -a.")
//        Net net = convert.tripleAnchoringNet(convert.net)
//
//        assert net.subNets.size() == 3
//        assert net.subNets[2].placeList.size() == 3
//        assert net.subNets[2].transitionList.size() == 6
//        assert net.subNets[2].arcList.size() == 12
//    }

    void testSale() {
        LPPN2LPN conversion = batchConvert("offers(Seller, Good, Money) seq accepts(Buyer, Good, Money).")
        batchExport(conversion, "sale")
    }

//    void testTransitionAssociation() {
//        LPPN2LPN convert = batchConvert("a -> b.")

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
