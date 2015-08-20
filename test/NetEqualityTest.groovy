import org.leibnizcenter.lppneu.builders.LPPN2LPN
import org.leibnizcenter.lppneu.comparison.NetComparison
import org.leibnizcenter.lppneu.components.language.*
import org.leibnizcenter.lppneu.parser.LPPNLoader
import org.leibnizcenter.pneu.components.petrinet.Net

class NetEqualityTest extends GroovyTestCase {

    static Net batchConvert(String code) {
        Program program = LPPNLoader.parseString(code)
        LPPN2LPN conversion = new LPPN2LPN()
        conversion.convert(program)
        return conversion.net
    }

    void testNetEquality1() {
        assert NetComparison.compare(batchConvert("a."), batchConvert("a."))
    }

    void testNetUnEquality1() {
        assert !NetComparison.compare(batchConvert("a."), batchConvert("b."))
    }

    void testNetEquality2() {
        assert NetComparison.compare(batchConvert("a. b."), batchConvert("b. a."))
    }

    void testNetUnEquality2() {
        assert !NetComparison.compare(batchConvert("a. b."), batchConvert("b. c."))
    }

//    void testNetEquality3() {
//        assert Comparison.compare(batchConvert("a and b."), batchConvert("b and a."))
//    }

    void testNetUnEquality3() {
        assert !NetComparison.compare(batchConvert("a and b."), batchConvert("b and (b and a)."))
    }

    void testNetUnEquality4() {
        assert !NetComparison.compare(batchConvert("a seq b."), batchConvert("b seq a."))
    }

    void testNetEquality5() {
        assert NetComparison.compare(batchConvert("a :- b."), batchConvert("a :- b."))
    }

    void testNetUnEquality5() {
        assert !NetComparison.compare(batchConvert("a :- b."), batchConvert("b :- a."))
    }

    void testNetEquality6() {
        assert NetComparison.compare(batchConvert("a -> b."), batchConvert("a -> b."))
    }

    void testNetUnEquality6() {
        assert !NetComparison.compare(batchConvert("a -> b."), batchConvert("b -> a."))
    }

//    void testNetEquality7() {
//        assert Comparison.compare(batchConvert("a ::- b."), batchConvert("b ::- a."))
//    }

}
