package org.leibnizcenter.lppneu.components

import org.leibnizcenter.lppneu.builders.LPNHandler
import org.leibnizcenter.lppneu.components.language.*
import org.leibnizcenter.lppneu.parsers.LPPNLoader
import org.leibnizcenter.pneu.components.petrinet.Net

class NetEqualityTest extends GroovyTestCase {

    static Net batchConvert(String code) {
        LPPNProgram program = LPPNLoader.parseString(code)
        LPNHandler conversion = new LPNHandler()
        conversion.convert(program)
        return conversion.net
    }

    void testNetEquality1() {
        assert Net.compare(batchConvert("a."), batchConvert("a."))
    }

    void testNetUnEquality1() {
        assert !Net.compare(batchConvert("a."), batchConvert("b."))
    }

    void testNetEquality2() {
        assert Net.compare(batchConvert("a. b."), batchConvert("b. a."))
    }

    void testNetUnEquality2() {
        assert !Net.compare(batchConvert("a. b."), batchConvert("b. c."))
    }

//    void testNetEquality3() {
//        assert Comparison.compare(batchConvert("a and b."), batchConvert("b and a."))
//    }

    void testNetUnEquality3() {
        assert !Net.compare(batchConvert("a and b."), batchConvert("b and (b and a)."))
    }

    void testNetUnEquality4() {
        assert !Net.compare(batchConvert("a seq b."), batchConvert("b seq a."))
    }

    void testNetEquality5() {
        assert Net.compare(batchConvert("a :- b."), batchConvert("a :- b."))
    }

    void testNetUnEquality5() {
        assert !Net.compare(batchConvert("a :- b."), batchConvert("b :- a."))
    }

    void testNetEquality6() {
        assert Net.compare(batchConvert("a -> b."), batchConvert("a -> b."))
    }

    void testNetUnEquality6() {
        assert !Net.compare(batchConvert("a -> b."), batchConvert("b -> a."))
    }

//    void testNetEquality7() {
//        assert Comparison.compare(batchConvert("a ::- b."), batchConvert("b ::- a."))
//    }

}
