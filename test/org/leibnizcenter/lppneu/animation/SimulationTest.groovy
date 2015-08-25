package org.leibnizcenter.lppneu.animation

import org.leibnizcenter.lppneu.components.lppetrinets.LPNet
import org.leibnizcenter.lppneu.components.lppetrinets.LPPlace
import org.leibnizcenter.lppneu.components.lppetrinets.LPToken
import org.leibnizcenter.pneu.animation.monolithic.NetRunner
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Transition
import org.leibnizcenter.pneu.parsers.PNML2PN

class SimulationTest extends GroovyTestCase {

    void test0EmptyPlace() {
        Net net = new LPNet()

        net.createPlace("rain")

        NetRunner runner = new NetRunner()
        runner.load(net)
        assert(runner.run() == 0)
        assert(runner.execution.places.size() == 1)
    }

    void test0PlaceFilledWith1Token() {
        Net net = new LPNet()

        Place p = net.createPlace("dog(Dog)")
        p.createToken("fido")

        NetRunner runner = new NetRunner()
        runner.load(net)
        assert(runner.run() == 0)
        assert(runner.execution.places.size() == 1)
        assert(runner.execution.places[0].marking.size() == 1)
    }

    void test0PlaceFilledWithAnonymousToken() {
        Net net = new LPNet()

        Place p = net.createPlace("dog(Dog)")
        p.createToken()
        p.createToken()

        NetRunner runner = new NetRunner()
        runner.load(net)
        assert(runner.run() == 0)
        assert(runner.execution.places.size() == 1)
        assert(runner.execution.places[0].marking.size() == 2)

        assert p.marking[0] != p.marking[1]
    }

    void test0PlaceFilledWith3Tokens() {
        Net net = new LPNet()

        Place p = net.createPlace("dog(Dog)")
        p.createToken("fido")
        p.createToken("argo")
        p.createToken("rintintin")

        NetRunner runner = new NetRunner()
        runner.load(net)
        assert(runner.run() == 0)
        assert(runner.execution.places.size() == 1)
        assert(runner.execution.places[0].marking.size() == 3)
    }

    void test0PlaceWithVariableAndConstantFilledWith3Tokens() {
        Net net = new LPNet()

        Place p = net.createPlace("dog(Dog, park)")
        p.createToken("fido")
        p.createToken("argo")
        p.createToken("rintintin")

        NetRunner runner = new NetRunner()
        runner.load(net)
        assert(runner.run() == 0)
        assert(runner.execution.places.size() == 1)
        assert(runner.execution.places[0].marking.size() == 3)
    }

    // test for execution based on transitions
    // only one transition is fired per step.
    // therefore at 100 there is no token in the place (collector consumed it)
    // at 101 there is a token (emitter consumed it)
    void test1TransitionBased() {
        Net net = new LPNet()

        Transition tIn = net.createEmitterTransition()
        Place p1 = net.createPlace("dog(Dog)")
        Transition t = net.createTransition("bark(Dog)")
        Place p2 = net.createPlace("dog(Dog)")
        Transition tOut = net.createCollectorTransition()

        net.createArc(tIn, p1)
        net.createArc(p1, t)
        net.createArc(t, p2)
        net.createArc(p2, tOut)

        net.resetIds()

        NetRunner runner = new NetRunner()
        runner.load(net)
        assert(runner.run(100) == 100)
        assert(runner.execution.places.size() == 2)
        assert(runner.execution.places[1].marking.size() == 0)
        assert(runner.run(1) == 1)
        assert(runner.execution.places[1].marking.size() == 1)
        assert(runner.execution.nTokenEmitted == runner.execution.nTokenCollected + 1)
    }

}