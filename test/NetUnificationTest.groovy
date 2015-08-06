import org.leibnizcenter.lppneu.builders.LPPN2LPN
import org.leibnizcenter.lppneu.components.language.*
import org.leibnizcenter.lppneu.parser.LPPNLoader
import org.leibnizcenter.pneu.builders.PN2dot
import org.leibnizcenter.pneu.components.petrinet.Net

class NetUnificationTest extends GroovyTestCase {

    void printResult(LPPN2LPN conversion) {

        println conversion.net.getAllPlaces().size()
        println conversion.net.getAllTransitions().size()
        println conversion.net.getAllArcs().size()
        println conversion.net.getAllNets().size()
        println conversion.unifiedNet.getAllPlaces().size()
        println conversion.unifiedNet.getAllTransitions().size()
        println conversion.unifiedNet.getAllArcs().size()
        println conversion.unifiedNet.getAllNets().size()

    }

    static LPPN2LPN batchConvert(String code) {
        Program program = LPPNLoader.parseString(code)
        LPPN2LPN conversion = new LPPN2LPN()
        conversion.convert(program)
        return conversion
    }

//    void testUnifyFact() {
//        LPPN2LPN conversion = batchConvert("a. a.")
//        printResult(conversion)
//
//        assert conversion.net.getAllPlaces().size() == 2
//        assert conversion.net.getAllTransitions().size() == 0
//        assert conversion.net.getAllArcs().size() == 0
//        assert conversion.net.getAllNets().size() == 3
//
//        assert conversion.unifiedNet.getAllPlaces().size() == 1
//        assert conversion.unifiedNet.getAllTransitions().size() == 0
//        assert conversion.unifiedNet.getAllArcs().size() == 0
//        assert conversion.unifiedNet.getAllNets().size() == 2
//    }
//
//    void testUnifyFact2() {
//        LPPN2LPN conversion = batchConvert("a. a. a. a. a.")
//        printResult(conversion)
//
//        assert conversion.net.getAllPlaces().size() == 5
//        assert conversion.net.getAllTransitions().size() == 0
//        assert conversion.net.getAllArcs().size() == 0
//        assert conversion.net.getAllNets().size() == 6
//
//        assert conversion.unifiedNet.getAllPlaces().size() == 1
//        assert conversion.unifiedNet.getAllTransitions().size() == 0
//        assert conversion.unifiedNet.getAllArcs().size() == 0
//        assert conversion.unifiedNet.getAllNets().size() == 2
//
//    }
//
//    void testUnifyFact3() {
//        LPPN2LPN conversion = batchConvert("a and b. a and b. a and b.")
//        printResult(conversion)
//
//        assert conversion.net.getAllPlaces().size() == 9
//        assert conversion.net.getAllTransitions().size() == 3
//        assert conversion.net.getAllArcs().size() == 9
//        assert conversion.net.getAllNets().size() == 4
//
//        assert conversion.unifiedNet.getAllPlaces().size() == 4
//        assert conversion.unifiedNet.getAllTransitions().size() == 2
//        assert conversion.unifiedNet.getAllArcs().size() == 5
//        assert conversion.unifiedNet.getAllNets().size() == 3
//
//    }
//
//    void testUnifyLogicRule() {
//        LPPN2LPN conversion = batchConvert("p :- q.")
//        printResult(conversion)
//
//        assert conversion.net.getAllPlaces().size() == 4
//        assert conversion.net.getAllTransitions().size() == 2
//        assert conversion.net.getAllArcs().size() == 4
//        assert conversion.net.getAllNets().size() == 6
//
//        assert conversion.unifiedNet.getAllPlaces().size() == 6
//        assert conversion.unifiedNet.getAllTransitions().size() == 4
//        assert conversion.unifiedNet.getAllArcs().size() == 8
//        assert conversion.unifiedNet.getAllNets().size() == 8
//    }
//
//
//    void testUnifyLogicRules() {
//        LPPN2LPN conversion = batchConvert("b :- a. c :- b.")
//        printResult(conversion)
//
//        assert conversion.net.getAllPlaces().size() == 8
//        assert conversion.net.getAllTransitions().size() == 4
//        assert conversion.net.getAllArcs().size() == 8
//        assert conversion.net.getAllNets().size() == 11
//
//        assert conversion.unifiedNet.getAllPlaces().size() == 12
//        assert conversion.unifiedNet.getAllTransitions().size() == 8
//        assert conversion.unifiedNet.getAllArcs().size() == 16
//        assert conversion.unifiedNet.getAllNets().size() == 14
//    }
//
//    void testUnifyLogicRules2() {
//        LPPN2LPN conversion = batchConvert("p :- a. q :- a.")
//        printResult(conversion)
//
//        assert conversion.net.getAllPlaces().size() == 8
//        assert conversion.net.getAllTransitions().size() == 4
//        assert conversion.net.getAllArcs().size() == 8
//        assert conversion.net.getAllNets().size() == 11
//
//        assert conversion.unifiedNet.getAllPlaces().size() == 11
//        assert conversion.unifiedNet.getAllTransitions().size() == 7
//        assert conversion.unifiedNet.getAllArcs().size() == 15
//        assert conversion.unifiedNet.getAllNets().size() == 14
//    }
//
//    void testUnifyLogicRules3() {
//        LPPN2LPN conversion = batchConvert("p :- a and b. q :- a and b. a.")
//        printResult(conversion)
//
//        assert conversion.net.getAllPlaces().size() == 17
//        assert conversion.net.getAllTransitions().size() == 8
//        assert conversion.net.getAllArcs().size() == 20
//        assert conversion.net.getAllNets().size() == 12
//
//        assert conversion.unifiedNet.getAllPlaces().size() == 23
//        assert conversion.unifiedNet.getAllTransitions().size() == 14
//        assert conversion.unifiedNet.getAllArcs().size() == 36
//        assert conversion.unifiedNet.getAllNets().size() == 15
//    }
//
//    void testUnifyCausalRule() {
//        LPPN2LPN conversion = batchConvert("a -> b.")
//        printResult(conversion)
//
//        assert conversion.net.getAllPlaces().size() == 5
//        assert conversion.net.getAllTransitions().size() == 3
//        assert conversion.net.getAllArcs().size() == 7
//        assert conversion.net.getAllNets().size() == 5
//
//        assert conversion.unifiedNet.getAllPlaces().size() == 6
//        assert conversion.unifiedNet.getAllTransitions().size() == 4
//        assert conversion.unifiedNet.getAllArcs().size() == 9
//        assert conversion.unifiedNet.getAllNets().size() == 6
//    }
//
//    void testUnifyCausalRules() {
//        LPPN2LPN conversion = batchConvert("a -> b. b -> a.")
//        printResult(conversion)
//
//        assert conversion.net.getAllPlaces().size() == 10
//        assert conversion.net.getAllTransitions().size() == 6
//        assert conversion.net.getAllArcs().size() == 14
//        assert conversion.net.getAllNets().size() == 9
//
//        assert conversion.unifiedNet.getAllPlaces().size() == 14
//        assert conversion.unifiedNet.getAllTransitions().size() == 10
//        assert conversion.unifiedNet.getAllArcs().size() == 24
//        assert conversion.unifiedNet.getAllNets().size() == 11
//    }

//    void testUnifyEventFact() {
//        LPPN2LPN conversion = batchConvert("-> a.")
//        printResult(conversion)
//
//        assert conversion.net.getAllPlaces().size() == 0
//        assert conversion.net.getAllTransitions().size() == 1
//        assert conversion.net.getAllArcs().size() == 0
//        assert conversion.net.getAllNets().size() == 2
//
//        assert conversion.unifiedNet.getAllPlaces().size() == 0
//        assert conversion.unifiedNet.getAllTransitions().size() == 1
//        assert conversion.unifiedNet.getAllArcs().size() == 0
//        assert conversion.unifiedNet.getAllNets().size() == 2
//    }

//    void testUnifyEventFact2() {
//        LPPN2LPN conversion = batchConvert("-> a seq b.")
//        printResult(conversion)
//
//        assert conversion.net.getAllPlaces().size() == 3
//        assert conversion.net.getAllTransitions().size() == 4
//        assert conversion.net.getAllArcs().size() == 6
//        assert conversion.net.getAllNets().size() == 4
//
//        assert conversion.unifiedNet.getAllPlaces().size() == 3
//        assert conversion.unifiedNet.getAllTransitions().size() == 4
//        assert conversion.unifiedNet.getAllArcs().size() == 6
//        assert conversion.unifiedNet.getAllNets().size() == 4
//    }

//    void testUnifyProcessFact() {
//        LPPN2LPN conversion = batchConvert("a seq b.")
//        printResult(conversion)
//
//        assert conversion.net.getAllPlaces().size() == 10
//        assert conversion.net.getAllTransitions().size() == 5
//        assert conversion.net.getAllArcs().size() == 14
//        assert conversion.net.getAllNets().size() == 4
//
//        assert conversion.unifiedNet.getAllPlaces().size() == 13
//        assert conversion.unifiedNet.getAllTransitions().size() == 8
//        assert conversion.unifiedNet.getAllArcs().size() == 20
//        assert conversion.unifiedNet.getAllNets().size() == 7
//    }

//        void testUnifyECARule() {
//        LPPN2LPN conversion = batchConvert("a in b -> p.")
//        printResult(conversion)
//
//        assert conversion.net.getAllPlaces().size() == 6
//        assert conversion.net.getAllTransitions().size() == 3
//        assert conversion.net.getAllArcs().size() == 8
//        assert conversion.net.getAllNets().size() == 5
//
//        assert conversion.unifiedNet.getAllPlaces().size() == 7
//        assert conversion.unifiedNet.getAllTransitions().size() == 4
//        assert conversion.unifiedNet.getAllArcs().size() == 10
//        assert conversion.unifiedNet.getAllNets().size() == 6
//    }
//
//    void testUnifyDefinition() {
//        LPPN2LPN conversion = batchConvert("a := b.")
//        printResult(conversion)
//
//        assert conversion.net.getAllPlaces().size() == 2
//        assert conversion.net.getAllTransitions().size() == 2
//        assert conversion.net.getAllArcs().size() == 4
//        assert conversion.net.getAllNets().size() == 4
//
//        assert conversion.unifiedNet.getAllPlaces().size() == 4
//        assert conversion.unifiedNet.getAllTransitions().size() == 4
//        assert conversion.unifiedNet.getAllArcs().size() == 8
//        assert conversion.unifiedNet.getAllNets().size() == 6
//    }

    void testUnifyCompoundDefinition() {
        LPPN2LPN conversion = batchConvert("p := a and b.")
        printResult(conversion)

        assert conversion.net.getAllPlaces().size() == 10
        assert conversion.net.getAllTransitions().size() == 5
        assert conversion.net.getAllArcs().size() == 14
        assert conversion.net.getAllNets().size() == 4

        assert conversion.unifiedNet.getAllPlaces().size() == 13
        assert conversion.unifiedNet.getAllTransitions().size() == 8
        assert conversion.unifiedNet.getAllArcs().size() == 20
        assert conversion.unifiedNet.getAllNets().size() == 7
    }


//    void testUnifyCausalCompoundRule() {
//        LPPN2LPN conversion = batchConvert("a seq b seq c -> p.")
//        printResult(conversion)
//
//        assert conversion.net.getAllPlaces().size() == 10
//        assert conversion.net.getAllTransitions().size() == 5
//        assert conversion.net.getAllArcs().size() == 14
//        assert conversion.net.getAllNets().size() == 4
//
//        assert conversion.unifiedNet.getAllPlaces().size() == 13
//        assert conversion.unifiedNet.getAllTransitions().size() == 8
//        assert conversion.unifiedNet.getAllArcs().size() == 20
//        assert conversion.unifiedNet.getAllNets().size() == 7
//    }

//    void testUnifyCausalCompoundRule() {
//        LPPN2LPN conversion = batchConvert("a seq b -> p. q and r :- p.")
//        printResult(conversion)
//
//        assert conversion.net.getAllPlaces().size() == 10
//        assert conversion.net.getAllTransitions().size() == 5
//        assert conversion.net.getAllArcs().size() == 14
//        assert conversion.net.getAllNets().size() == 4
//
//        assert conversion.unifiedNet.getAllPlaces().size() == 13
//        assert conversion.unifiedNet.getAllTransitions().size() == 8
//        assert conversion.unifiedNet.getAllArcs().size() == 20
//        assert conversion.unifiedNet.getAllNets().size() == 7
//    }

}
