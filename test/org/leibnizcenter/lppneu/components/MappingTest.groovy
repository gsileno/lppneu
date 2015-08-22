package org.leibnizcenter.lppneu.components

import org.leibnizcenter.lppneu.builders.LPPN2LPN
import org.leibnizcenter.lppneu.components.language.LPPNProgram
import org.leibnizcenter.lppneu.parsers.LPPNLoader

class MappingTest extends GroovyTestCase {

    static LPPN2LPN batchConvert(String code) {
        LPPNProgram program = LPPNLoader.parseString(code)
        LPPN2LPN conversion = new LPPN2LPN()
        conversion.mapper.mapNet(LPPN2LPN.buildProgramNet(program))
        conversion
    }

    void testConversionBasicFact() {
        LPPN2LPN conversion = batchConvert("p.")
        assert conversion.mapper.expressionPlaceMap.size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[0]].size() == 1
        assert conversion.mapper.operationTransitionMap.size() == 0
    }

    void testConversionCompoundLogicFact() {
        LPPN2LPN conversion = batchConvert("p or q.")
        assert conversion.mapper.expressionPlaceMap.size() == 3
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[0]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[1]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[2]].size() == 1
        assert conversion.mapper.operationTransitionMap.size() == 0

    }

    void testConversionParFact() {
        LPPN2LPN conversion = batchConvert("p par q.")
        assert conversion.mapper.expressionPlaceMap.size() == 9
        assert conversion.mapper.operationTransitionMap.size() == 2
    }

    void testConversionSimpleLogicRule() {
        LPPN2LPN conversion = batchConvert("q :- p.")
        assert conversion.mapper.expressionPlaceMap.size() == 4
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[0]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[1]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[2]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[3]].size() == 1
        assert conversion.mapper.operationTransitionMap.size() == 0
    }

    void testConversionLogicRuleAndFact() {
        LPPN2LPN conversion = batchConvert("q :- p. p.")
        assert conversion.mapper.expressionPlaceMap.size() == 4
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[0]].size() == 2
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[1]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[2]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[3]].size() == 1
        assert conversion.mapper.operationTransitionMap.size() == 0
    }

    void testConversionSimpleCausalRule() {
        LPPN2LPN conversion = batchConvert("a -> b.")
        assert conversion.mapper.expressionPlaceMap.size() == 5
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[0]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[1]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[2]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[3]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[4]].size() == 1
        assert conversion.mapper.operationTransitionMap.size() == 2
    }

}
