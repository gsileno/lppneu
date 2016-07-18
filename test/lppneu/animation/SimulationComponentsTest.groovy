package lppneu.animation

import lppneu.components.language.Variable
import lppneu.components.lppetrinets.LPNet
import lppneu.components.lppetrinets.LPTransition
import pneu.components.petrinet.Net
import pneu.components.petrinet.Place
import pneu.components.petrinet.Token
import pneu.components.petrinet.Transition

class SimulationComponentsTest extends GroovyTestCase {

    void testTransition1() {
        Net net = new LPNet()

        Transition tIn = net.createEmitterTransition()
        Place p1 = net.createPlace("a(A)")
        Transition t = net.createTransition("b(A)")
        Place p2 = net.createPlace("c(A)")
        Transition tOut = net.createCollectorTransition()

        net.createArc(tIn, p1)
        net.createArc(p1, t)
        net.createArc(t, p2)
        net.createArc(p2, tOut)

        net.resetIds()

        assert p1.expression.getVariables().size() == 1
        assert p1.expression.getParameters().size() == 1
        assert p2.expression.getVariables().size() == 1
        assert p2.expression.getParameters().size() == 1

        LPTransition lpt = (LPTransition) t
        lpt.initializeUnificationFilter()
        assert lpt.inputCommonVarList.size() == 0

        assert !t.isEnabled()
        p1.createToken()

        assert t.isEnabled()

    }

    void testTransition2() {
        Net net = new LPNet()

        Transition tIn = net.createEmitterTransition()
        Place p1 = net.createPlace("a(A)")
        Place p2 = net.createPlace("b(B)")
        Transition t = net.createTransition("c(A, B)")
        Place p3 = net.createPlace("d(A, B, c)")
        Transition tOut = net.createCollectorTransition()

        assert p1.expression.getVariables().size() == 1
        assert p1.expression.getParameters().size() == 1
        assert p2.expression.getVariables().size() == 1
        assert p2.expression.getParameters().size() == 1
        assert p3.expression.getVariables().size() == 2
        assert p3.expression.getParameters().size() == 3

        net.createArc(tIn, p1)
        net.createArc(tIn, p2)
        net.createArc(p1, t)
        net.createArc(p2, t)
        net.createArc(t, p3)
        net.createArc(p3, tOut)

        net.resetIds()

        LPTransition lpt = (LPTransition) t
        lpt.initializeUnificationFilter()
        assert lpt.inputCommonVarList.size() == 0

        assert !t.isEnabled()
        p1.createToken()
        assert !t.isEnabled()
        p2.createToken()
        assert t.isEnabled()

    }

    void testTransition3() {
        Net net = new LPNet()

        Transition tIn = net.createEmitterTransition()
        Place p1 = net.createPlace("a(A)")
        Place p2 = net.createPlace("b(A, c)")
        Transition t = net.createTransition("b(A, B)")
        Place p3 = net.createPlace("c(A, B)")
        Transition tOut = net.createCollectorTransition()

        net.createArc(tIn, p1)
        net.createArc(tIn, p2)
        net.createArc(p1, t)
        net.createArc(p2, t)
        net.createArc(t, p3)
        net.createArc(p3, tOut)

        net.resetIds()

        assert p1.expression.getVariables().size() == 1
        assert p1.expression.getParameters().size() == 1
        assert p2.expression.getVariables().size() == 1
        assert p2.expression.getParameters().size() == 2
        assert p3.expression.getVariables().size() == 2
        assert p3.expression.getParameters().size() == 2

        LPTransition lpt = (LPTransition) t
        lpt.initializeUnificationFilter()
        assert lpt.inputCommonVarList.size() == 1
        assert lpt.inputCommonVarList[0] == Variable.build("A")

        assert !t.isEnabled()
        p1.createToken("a")
        assert p1.marking.size() == 1
        assert !t.isEnabled()
        p2.createToken("b")
        assert p2.marking.size() == 1
        assert !t.isEnabled()
        p2.createToken("a")
        assert p2.marking.size() == 2

        // these are the variables given by this place
        List<Variable> localVarList = p2.expression.getVariables()
        assert localVarList.size() == 1

        List<Variable> localCommonVarList = localVarList - (localVarList - lpt.inputCommonVarList)
        assert localCommonVarList.size() == 1

        // for the variables contained take the local values
        List<Map<String, String>> localVarWithValuesList = p2.getFilterList(localCommonVarList)
        assert localVarWithValuesList.size() == 2

        assert t.isEnabled()

    }

    void testTransition4() {
        Net net = new LPNet()

        Transition tIn = net.createEmitterTransition()
        Place p1 = net.createPlace("a(A)")
        Place p2 = net.createPlace("b(A, c)")
        Transition t = net.createTransition("b(A, B)")
        Place p3 = net.createPlace("c(A, B)")
        Transition tOut = net.createCollectorTransition()

        net.createArc(tIn, p1)
        net.createArc(tIn, p2)
        net.createArc(p1, t)
        net.createArc(p2, t)
        net.createArc(t, p3)
        net.createArc(p3, tOut)

        net.resetIds()

        assert p1.expression.getVariables().size() == 1
        assert p1.expression.getParameters().size() == 1
        assert p2.expression.getVariables().size() == 1
        assert p2.expression.getParameters().size() == 2
        assert p3.expression.getVariables().size() == 2
        assert p3.expression.getParameters().size() == 2

        LPTransition lpt = (LPTransition) t
        lpt.initializeUnificationFilter()
        assert lpt.inputCommonVarList.size() == 1
        assert lpt.inputCommonVarList[0] == Variable.build("A")

        assert !t.isEnabled()
        p1.createToken("a")
        assert p1.marking.size() == 1
        assert !t.isEnabled()
        p2.createToken("b")
        assert p2.marking.size() == 1
        assert !t.isEnabled()
        p2.createToken("a")
        assert p2.marking.size() == 2


        // these are the variables given by this place
        List<Variable> localVarList = p2.expression.getVariables()
        assert localVarList.size() == 1

        List<Variable> localCommonVarList = localVarList - (localVarList - lpt.inputCommonVarList)
        assert localCommonVarList.size() == 1

        // for the variables contained take the local values
        List<Map<String, String>> localVarWithValuesList = p2.getFilterList(localCommonVarList)
        assert localVarWithValuesList.size() == 2

        assert t.isEnabled()

    }

    void testTransition5() {
        Net net = new LPNet()

        Transition tIn = net.createEmitterTransition()
        Place p1 = net.createPlace("a(A, C)")
        Place p2 = net.createPlace("b(B, A)")
        Transition t = net.createCollectorTransition()

        net.createArc(tIn, p1)
        net.createArc(tIn, p2)
        net.createArc(p1, t)
        net.createArc(p2, t)

        net.resetIds()

        LPTransition lpt = (LPTransition) t
        lpt.initializeUnificationFilter()
        assert lpt.inputCommonVarList.size() == 1 // A is a common variable

        assert !t.isEnabled()

        p1.createToken(["c", "c"])
        p1.createToken(["a", "c"])
        p1.createToken(["b", "c"])
        p2.createToken(["b", "d"])

        assert !t.isEnabled()

        p2.createToken(["b", "a"])

        assert t.isEnabled()

        assert p1.marking.size() == 3
        assert p2.marking.size() == 2

        assert lpt.getFilterList().size() == 1

        p2.createToken(["b", "b"])

        assert lpt.getFilterList().size() == 2

        assert t.isEnabled()

        t.fire()

        assert p1.marking.size() == 2
        assert p2.marking.size() == 2

        assert t.isEnabled()

        t.fire()

        assert p1.marking.size() == 1
        assert p2.marking.size() == 1

        assert !t.isEnabled()
    }


    void testTransition6() {
        Net net = new LPNet()

        Transition tIn = net.createEmitterTransition()
        Place p1 = net.createPlace("a(A, C)")
        Place p2 = net.createPlace("b(B, A)")
        Transition t = net.createLinkTransition()
        Place p3 = net.createPlace("c(A, B, C)")
        Transition tOut = net.createCollectorTransition()

        net.createArc(tIn, p1)
        net.createArc(tIn, p2)
        net.createArc(p1, t)
        net.createArc(p2, t)
        net.createArc(t, p3)
        net.createArc(p3, tOut)

        net.resetIds()

        p1.createToken(["c", "c"])
        p1.createToken(["a", "c"])
        p1.createToken(["b", "c"])
        p2.createToken(["b", "d"])
        p2.createToken(["b", "a"])
        p2.createToken(["b", "b"])

        assert p1.marking.size() == 3
        assert p2.marking.size() == 3
        assert p3.marking.size() == 0

        assert (t.isEnabled())

        t.fire()

        assert p1.marking.size() == 2
        assert p2.marking.size() == 2
        assert p3.marking.size() == 1

        assert (t.isEnabled())

        t.fire()

        assert p1.marking.size() == 1
        assert p2.marking.size() == 1
        assert p3.marking.size() == 2

        assert (!t.isEnabled())

    }

    void testTransition7() {
        Net net = new LPNet()

        Place p1 = net.createPlace("a(A, C, E)")
        Place p2 = net.createPlace("b(B, A, D)")
        Transition t = net.createLinkTransition()
        Place p3 = net.createPlace("c(A, B, C, F)")

        net.createArc(p1, t)
        net.createArc(p2, t)
        net.createArc(t, p3)
        net.resetIds()

        List<Token> firableContentList
        LPTransition lpt = (LPTransition) t

        p1.createToken(["a1", "c1", "e1"])
        assert lpt.fireableEvents().size() == 0
        // no firing possible

        p2.createToken(["b1", "a1", "d1"])
        assert lpt.fireableEvents().size() == 1
        // a1, b1, c1, d1, e1

        p1.createToken(["a2", "c1", "e2"])
        assert lpt.fireableEvents().size() == 1
        // a1, b1, c1, d1, e1

        p2.createToken(["b1", "a2", "d1"])
        assert lpt.fireableEvents().size() == 2
        // a1, b1, c1, d1, e1 + a2, b1, c1, d1, e2

        p1.createToken(["a2", "c2", "e2"])
        assert lpt.fireableEvents().size() == 3
        // a1, b1, c1, d1, e1 + a2, b1, c1, d1, e2 + a2, b1, d1, c2, e2

        p1.createToken(["a2", "c3", "e3"])
        assert lpt.fireableEvents().size() == 4

        p2.createToken(["b1", "a1", "d2"])
        assert lpt.fireableEvents().size() == 5

        p2.createToken(["b2", "a1", "d2"])
        assert lpt.fireableEvents().size() == 6

        p2.createToken(["b2", "a1", "d3"])
        assert lpt.fireableEvents().size() == 7

    }
}

