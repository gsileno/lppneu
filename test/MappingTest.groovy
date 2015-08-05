import org.leibnizcenter.lppneu.builders.LPPN2LPN
import org.leibnizcenter.lppneu.components.language.Program
import org.leibnizcenter.lppneu.parser.LPPNLoader
import org.leibnizcenter.pneu.builders.PN2dot
import org.leibnizcenter.pneu.components.petrinet.Net

class MappingTest extends GroovyTestCase {

    static LPPN2LPN batchConvert(String code) {
        Program program = LPPNLoader.parseString(code)
        LPPN2LPN conversion = new LPPN2LPN()
        conversion.mapNet(LPPN2LPN.buildProgramNet(program))
        conversion
    }

    void testConversionBasicFact() {
        LPPN2LPN conversion = batchConvert("p.")
        assert conversion.expressionPlaceMap.size() == 1
        assert conversion.expressionPlaceMap[conversion.expressionPlaceMap.keySet()[0]].size() == 1
        assert conversion.operationTransitionMap.size() == 0
    }

    void testConversionCompoundLogicFact() {
        LPPN2LPN conversion = batchConvert("p or q.")
        assert conversion.expressionPlaceMap.size() == 3
        assert conversion.expressionPlaceMap[conversion.expressionPlaceMap.keySet()[0]].size() == 1
        assert conversion.expressionPlaceMap[conversion.expressionPlaceMap.keySet()[1]].size() == 1
        assert conversion.expressionPlaceMap[conversion.expressionPlaceMap.keySet()[2]].size() == 1
        assert conversion.operationTransitionMap.size() == 0

    }

    void testConversionParFact() {
        LPPN2LPN conversion = batchConvert("p par q.")
        assert conversion.expressionPlaceMap.size() == 9
        assert conversion.operationTransitionMap.size() == 2
    }

    void testConversionSimpleLogicRule() {
        LPPN2LPN conversion = batchConvert("q :- p.")
        assert conversion.expressionPlaceMap.size() == 4
        assert conversion.expressionPlaceMap[conversion.expressionPlaceMap.keySet()[0]].size() == 1
        assert conversion.expressionPlaceMap[conversion.expressionPlaceMap.keySet()[1]].size() == 1
        assert conversion.expressionPlaceMap[conversion.expressionPlaceMap.keySet()[2]].size() == 1
        assert conversion.expressionPlaceMap[conversion.expressionPlaceMap.keySet()[3]].size() == 1
        assert conversion.operationTransitionMap.size() == 0
    }

    void testConversionLogicRuleAndFact() {
        LPPN2LPN conversion = batchConvert("q :- p. p.")
        assert conversion.expressionPlaceMap.size() == 4
        assert conversion.expressionPlaceMap[conversion.expressionPlaceMap.keySet()[0]].size() == 2
        assert conversion.expressionPlaceMap[conversion.expressionPlaceMap.keySet()[1]].size() == 1
        assert conversion.expressionPlaceMap[conversion.expressionPlaceMap.keySet()[2]].size() == 1
        assert conversion.expressionPlaceMap[conversion.expressionPlaceMap.keySet()[3]].size() == 1
        assert conversion.operationTransitionMap.size() == 0
    }

    void testConversionSimpleCausalRule() {
        LPPN2LPN conversion = batchConvert("a -> b.")
        assert conversion.expressionPlaceMap.size() == 5
        assert conversion.expressionPlaceMap[conversion.expressionPlaceMap.keySet()[0]].size() == 1
        assert conversion.expressionPlaceMap[conversion.expressionPlaceMap.keySet()[1]].size() == 1
        assert conversion.expressionPlaceMap[conversion.expressionPlaceMap.keySet()[2]].size() == 1
        assert conversion.expressionPlaceMap[conversion.expressionPlaceMap.keySet()[3]].size() == 1
        assert conversion.expressionPlaceMap[conversion.expressionPlaceMap.keySet()[4]].size() == 1
        assert conversion.operationTransitionMap.size() == 2
    }

}
