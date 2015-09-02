package org.leibnizcenter.lppneu.components

import org.leibnizcenter.lppneu.builders.LPNHandler
import org.leibnizcenter.lppneu.builders.LPPN2LPN
import org.leibnizcenter.lppneu.components.language.LPPNProgram
import org.leibnizcenter.lppneu.parsers.LPPNLoader

class MappingTest extends GroovyTestCase {

    static LPNHandler batchConvert(String code) {
        LPPNProgram program = LPPNLoader.parseString(code)
        LPNHandler conversion = new LPNHandler()
        conversion.mapper.mapNet(LPPN2LPN.buildProgramNet(program))
        conversion
    }

    void testConversionBasicFact() {
        LPNHandler conversion = batchConvert("p.")
        assert conversion.mapper.expressionPlaceMap.size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[0]].size() == 1
        assert conversion.mapper.operationTransitionMap.size() == 0
    }

    void testConversionCompoundLogicFact() {
        LPNHandler conversion = batchConvert("p or q.")
        assert conversion.mapper.expressionPlaceMap.size() == 3
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[0]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[1]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[2]].size() == 1
        assert conversion.mapper.operationTransitionMap.size() == 0

    }

    void testConversionParFact() {
        LPNHandler conversion = batchConvert("p par q.")
        assert conversion.mapper.expressionPlaceMap.size() == 9
        assert conversion.mapper.operationTransitionMap.size() == 2
    }

    void testConversionSimpleLogicRule() {
        LPNHandler conversion = batchConvert("q :- p.")
        assert conversion.mapper.expressionPlaceMap.size() == 4
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[0]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[1]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[2]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[3]].size() == 1
        assert conversion.mapper.operationTransitionMap.size() == 0
    }

    void testConversionLogicRuleAndFact() {
        LPNHandler conversion = batchConvert("q :- p. p.")
        assert conversion.mapper.expressionPlaceMap.size() == 4
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[0]].size() == 2
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[1]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[2]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[3]].size() == 1
        assert conversion.mapper.operationTransitionMap.size() == 0
    }

    void testConversionSimpleCausalRule() {
        LPNHandler conversion = batchConvert("a -> b.")
        assert conversion.mapper.expressionPlaceMap.size() == 5
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[0]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[1]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[2]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[3]].size() == 1
        assert conversion.mapper.expressionPlaceMap[conversion.mapper.expressionPlaceMap.keySet()[4]].size() == 1
        assert conversion.mapper.operationTransitionMap.size() == 2
    }

}
