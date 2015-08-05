import org.leibnizcenter.lppneu.builders.LPPN2LPN
import org.leibnizcenter.lppneu.components.language.*
import org.leibnizcenter.lppneu.parser.LPPNLoader
import org.leibnizcenter.pneu.builders.PN2dot
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

        assert conversion.net.getAllPlaces().size() == 2
        assert conversion.net.getAllTransitions().size() == 0
        assert conversion.net.getAllArcs().size() == 0

        assert conversion.unifiedNet.getAllPlaces().size() == 1
        assert conversion.unifiedNet.getAllTransitions().size() == 0
        assert conversion.unifiedNet.getAllArcs().size() == 0
    }

//    void testUnifyLogicRules() {
//        LPPN2LPN conversion = batchConvert("b :- a. c :- b.")
//        assert conversion.net.getAllPlaces().size() == 4
//        assert conversion.net.getAllTransitions().size() == 2
//        assert conversion.net.getAllArcs().size() == 4
//
//        assert conversion.unifiedNet.getAllPlaces().size() == 4
//        assert conversion.unifiedNet.getAllTransitions().size() == 2
//        assert conversion.unifiedNet.getAllArcs().size() == 4
//    }
//
//    void testUnifyCausalRules() {
//        LPPN2LPN conversion = batchConvert("a -> b. b -> a.")
//
//        assert conversion.net.getAllPlaces().size() == 10
//        assert conversion.net.getAllTransitions().size() == 6
//        assert conversion.net.getAllArcs().size() == 14
//
//        assert conversion.unifiedNet.getAllPlaces().size() == 12
//        assert conversion.unifiedNet.getAllTransitions().size() == 8
//        assert conversion.unifiedNet.getAllArcs().size() == 20
//    }


}
