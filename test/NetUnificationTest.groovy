import org.leibnizcenter.lppneu.builders.LPPN2LPN
import org.leibnizcenter.lppneu.components.language.*
import org.leibnizcenter.lppneu.parser.LPPNLoader
import org.leibnizcenter.pneu.components.petrinet.Net

class NetUnificationTest extends GroovyTestCase {

    static LPPN2LPN batchConvert(String code) {
        Program program = LPPNLoader.parseString(code)
        LPPN2LPN conversion = new LPPN2LPN()
        conversion.convert(program)
        return conversion
    }

    void testUnifyFact() {
        LPPN2LPN conversion = batchConvert("a. a.")

        assert conversion.net.placeList.size() == 0
        assert conversion.net.transitionList.size() == 0
        assert conversion.net.arcList.size() == 0
        assert conversion.unifiedNet.placeList.size() == 1
        assert conversion.unifiedNet.transitionList.size() == 2
        assert conversion.unifiedNet.arcList.size() == 4
    }

    void testUnifyLogicRules() {
        LPPN2LPN conversion = batchConvert("b :- a. c :- b.")
        conversion.net.print()
        conversion.unifiedNet.print()
        assert conversion.net.placeList.size() == 0
        assert conversion.net.transitionList.size() == 0
        assert conversion.net.arcList.size() == 0
        assert conversion.unifiedNet.placeList.size() == 1
        assert conversion.unifiedNet.transitionList.size() == 2
        assert conversion.unifiedNet.arcList.size() == 4
    }

    void testUnifyCausalRules() {
        LPPN2LPN conversion = batchConvert("a -> b. b -> a.")
        conversion.net.print()
        conversion.unifiedNet.print()
        assert conversion.net.placeList.size() == 0
        assert conversion.net.transitionList.size() == 0
        assert conversion.net.arcList.size() == 0
        assert conversion.unifiedNet.placeList.size() == 10
        assert conversion.unifiedNet.transitionList.size() == 36
        assert conversion.unifiedNet.arcList.size() == 64
    }


}
