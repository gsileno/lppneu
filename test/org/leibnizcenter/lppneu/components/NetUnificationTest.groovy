package org.leibnizcenter.lppneu.components

import org.leibnizcenter.lppneu.builders.LPNHandler
import org.leibnizcenter.lppneu.components.language.*
import org.leibnizcenter.lppneu.parsers.LPPNLoader

class NetUnificationTest extends GroovyTestCase {

    static void printResult(LPNHandler conversion) {

        println conversion.net.getAllPlaces().size()
        println conversion.net.getAllTransitions().size()
        println conversion.net.getAllArcs().size()
        println conversion.net.getAllNets().size()
        println conversion.unifiedNet.getAllPlaces().size()
        println conversion.unifiedNet.getAllTransitions().size()
        println conversion.unifiedNet.getAllArcs().size()
        println conversion.unifiedNet.getAllNets().size()

    }

    static LPNHandler batchConvert(String code) {
        LPPNProgram program = LPPNLoader.parseString(code)
        LPNHandler conversion = new LPNHandler()
        conversion.convert(program)
        return conversion
    }

    void testUnifyFact() {
        LPNHandler convert = batchConvert("a. a.")
        printResult(convert)

        assert convert.net.getAllPlaces().size() == 2
        assert convert.net.getAllTransitions().size() == 0
        assert convert.net.getAllArcs().size() == 0
        assert convert.net.getAllNets().size() == 3

        assert convert.unifiedNet.getAllPlaces().size() == 1
        assert convert.unifiedNet.getAllTransitions().size() == 0
        assert convert.unifiedNet.getAllArcs().size() == 0
        assert convert.unifiedNet.getAllNets().size() == 2
    }

    void testUnifyFact2() {
        LPNHandler convert = batchConvert("a. a. a. a. a.")
        printResult(convert)

        assert convert.net.getAllPlaces().size() == 5
        assert convert.net.getAllTransitions().size() == 0
        assert convert.net.getAllArcs().size() == 0
        assert convert.net.getAllNets().size() == 6

        assert convert.unifiedNet.getAllPlaces().size() == 1
        assert convert.unifiedNet.getAllTransitions().size() == 0
        assert convert.unifiedNet.getAllArcs().size() == 0
        assert convert.unifiedNet.getAllNets().size() == 2

    }

    void testUnifyFact3() {
        LPNHandler convert = batchConvert("a and b. a and b. a and b.")
        printResult(convert)

        assert convert.net.getAllPlaces().size() == 9
        assert convert.net.getAllTransitions().size() == 3
        assert convert.net.getAllArcs().size() == 9
        assert convert.net.getAllNets().size() == 4

        assert convert.unifiedNet.getAllPlaces().size() == 4
        assert convert.unifiedNet.getAllTransitions().size() == 2
        assert convert.unifiedNet.getAllArcs().size() == 5
        assert convert.unifiedNet.getAllNets().size() == 3

    }

    void testUnifyLogicRule() {
        LPNHandler convert = batchConvert("p :- q.")
        printResult(convert)

        assert convert.net.getAllPlaces().size() == 4
        assert convert.net.getAllTransitions().size() == 2
        assert convert.net.getAllArcs().size() == 4
        assert convert.net.getAllNets().size() == 6

        assert convert.unifiedNet.getAllPlaces().size() == 6
        assert convert.unifiedNet.getAllTransitions().size() == 4
        assert convert.unifiedNet.getAllArcs().size() == 8
        assert convert.unifiedNet.getAllNets().size() == 8
    }


    void testUnifyLogicRules() {
        LPNHandler convert = batchConvert("b :- a. c :- b.")
        printResult(convert)

        assert convert.net.getAllPlaces().size() == 8
        assert convert.net.getAllTransitions().size() == 4
        assert convert.net.getAllArcs().size() == 8
        assert convert.net.getAllNets().size() == 11

        assert convert.unifiedNet.getAllPlaces().size() == 12
        assert convert.unifiedNet.getAllTransitions().size() == 8
        assert convert.unifiedNet.getAllArcs().size() == 16
        assert convert.unifiedNet.getAllNets().size() == 14
    }

    void testUnifyLogicRules2() {
        LPNHandler convert = batchConvert("p :- a. q :- a.")
        printResult(convert)

        assert convert.net.getAllPlaces().size() == 8
        assert convert.net.getAllTransitions().size() == 4
        assert convert.net.getAllArcs().size() == 8
        assert convert.net.getAllNets().size() == 11

        assert convert.unifiedNet.getAllPlaces().size() == 11
        assert convert.unifiedNet.getAllTransitions().size() == 7
        assert convert.unifiedNet.getAllArcs().size() == 15
        assert convert.unifiedNet.getAllNets().size() == 14
    }

    void testUnifyLogicRules3() {
        LPNHandler convert = batchConvert("p :- a and b. q :- a and b. a.")
        printResult(convert)

        assert convert.net.getAllPlaces().size() == 17
        assert convert.net.getAllTransitions().size() == 8
        assert convert.net.getAllArcs().size() == 20
        assert convert.net.getAllNets().size() == 12

        assert convert.unifiedNet.getAllPlaces().size() == 23
        assert convert.unifiedNet.getAllTransitions().size() == 14
        assert convert.unifiedNet.getAllArcs().size() == 36
        assert convert.unifiedNet.getAllNets().size() == 15
    }

    void testUnifyCausalRule() {
        LPNHandler convert = batchConvert("a -> b.")
        printResult(convert)

        assert convert.net.getAllPlaces().size() == 5
        assert convert.net.getAllTransitions().size() == 3
        assert convert.net.getAllArcs().size() == 7
        assert convert.net.getAllNets().size() == 5

        assert convert.unifiedNet.getAllPlaces().size() == 6
        assert convert.unifiedNet.getAllTransitions().size() == 4
        assert convert.unifiedNet.getAllArcs().size() == 9
        assert convert.unifiedNet.getAllNets().size() == 6
    }

    void testUnifyCausalRules() {
        LPNHandler convert = batchConvert("a -> b. b -> a.")
        printResult(convert)

        assert convert.net.getAllPlaces().size() == 10
        assert convert.net.getAllTransitions().size() == 6
        assert convert.net.getAllArcs().size() == 14
        assert convert.net.getAllNets().size() == 9

        assert convert.unifiedNet.getAllPlaces().size() == 14
        assert convert.unifiedNet.getAllTransitions().size() == 10
        assert convert.unifiedNet.getAllArcs().size() == 24
        assert convert.unifiedNet.getAllNets().size() == 11
    }

    void testUnifyEventFact() {
        LPNHandler convert = batchConvert("-> a.")
        printResult(convert)

        assert convert.net.getAllPlaces().size() == 0
        assert convert.net.getAllTransitions().size() == 1
        assert convert.net.getAllArcs().size() == 0
        assert convert.net.getAllNets().size() == 2

        assert convert.unifiedNet.getAllPlaces().size() == 0
        assert convert.unifiedNet.getAllTransitions().size() == 1
        assert convert.unifiedNet.getAllArcs().size() == 0
        assert convert.unifiedNet.getAllNets().size() == 2
    }

    void testUnifyEventFact2() {
        LPNHandler convert = batchConvert("-> a seq b.")
        printResult(convert)

        assert convert.net.getAllPlaces().size() == 3
        assert convert.net.getAllTransitions().size() == 4
        assert convert.net.getAllArcs().size() == 6
        assert convert.net.getAllNets().size() == 4

        assert convert.unifiedNet.getAllPlaces().size() == 3
        assert convert.unifiedNet.getAllTransitions().size() == 4
        assert convert.unifiedNet.getAllArcs().size() == 6
        assert convert.unifiedNet.getAllNets().size() == 4
    }

    void testUnifyProcessFact() {
        LPNHandler convert = batchConvert("a seq b.")
        printResult(convert)

        assert convert.net.getAllPlaces().size() == 8
        assert convert.net.getAllTransitions().size() == 5
        assert convert.net.getAllArcs().size() == 14
        assert convert.net.getAllNets().size() == 4

        assert convert.unifiedNet.getAllPlaces().size() == 13
        assert convert.unifiedNet.getAllTransitions().size() == 8
        assert convert.unifiedNet.getAllArcs().size() == 20
        assert convert.unifiedNet.getAllNets().size() == 7
    }

    void testUnifyECARule() {
        LPNHandler convert = batchConvert("a in b -> p.")
        printResult(convert)

        assert convert.net.getAllPlaces().size() == 6
        assert convert.net.getAllTransitions().size() == 3
        assert convert.net.getAllArcs().size() == 8
        assert convert.net.getAllNets().size() == 5

        assert convert.unifiedNet.getAllPlaces().size() == 7
        assert convert.unifiedNet.getAllTransitions().size() == 4
        assert convert.unifiedNet.getAllArcs().size() == 10
        assert convert.unifiedNet.getAllNets().size() == 6
    }

    void testUnifyDefinition() {
        LPNHandler convert = batchConvert("a := b.")
        printResult(convert)

        assert convert.net.getAllPlaces().size() == 2
        assert convert.net.getAllTransitions().size() == 2
        assert convert.net.getAllArcs().size() == 4
        assert convert.net.getAllNets().size() == 4

        assert convert.unifiedNet.getAllPlaces().size() == 4
        assert convert.unifiedNet.getAllTransitions().size() == 4
        assert convert.unifiedNet.getAllArcs().size() == 8
        assert convert.unifiedNet.getAllNets().size() == 6
    }

    void testUnifyCompoundDefinition() {
        LPNHandler conversion = batchConvert("p := a and b.")
        // printResult(conversion)

        assert conversion.net.getAllPlaces().size() == 10
        assert conversion.net.getAllTransitions().size() == 5
        assert conversion.net.getAllArcs().size() == 14
        assert conversion.net.getAllNets().size() == 4

        assert conversion.unifiedNet.getAllPlaces().size() == 13
        assert conversion.unifiedNet.getAllTransitions().size() == 8
        assert conversion.unifiedNet.getAllArcs().size() == 20
        assert conversion.unifiedNet.getAllNets().size() == 7
    }


    void testUnifyCausalCompoundRule() {
        LPNHandler convert = batchConvert("a seq b seq c -> p.")
        printResult(convert)

        assert convert.net.getAllPlaces().size() == 10
        assert convert.net.getAllTransitions().size() == 5
        assert convert.net.getAllArcs().size() == 14
        assert convert.net.getAllNets().size() == 4

        assert convert.unifiedNet.getAllPlaces().size() == 13
        assert convert.unifiedNet.getAllTransitions().size() == 8
        assert convert.unifiedNet.getAllArcs().size() == 20
        assert convert.unifiedNet.getAllNets().size() == 7
    }

    void testUnifyCausalLogicCompoundRule() {
        LPNHandler convert = batchConvert("a seq b -> p. q and r :- p.")
        printResult(convert)

        assert convert.net.getAllPlaces().size() == 10
        assert convert.net.getAllTransitions().size() == 5
        assert convert.net.getAllArcs().size() == 14
        assert convert.net.getAllNets().size() == 4

        assert convert.unifiedNet.getAllPlaces().size() == 13
        assert convert.unifiedNet.getAllTransitions().size() == 8
        assert convert.unifiedNet.getAllArcs().size() == 20
        assert convert.unifiedNet.getAllNets().size() == 7
    }

}
