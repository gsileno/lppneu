package org.leibnizcenter.lppneu.animation

import org.leibnizcenter.lppneu.components.lppetrinets.LPNet
import org.leibnizcenter.lppneu.components.lppetrinets.LPPlace
import org.leibnizcenter.lppneu.components.lppetrinets.LPToken
import org.leibnizcenter.pneu.animation.monolithic.NetRunner
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Transition
import org.leibnizcenter.pneu.parsers.PNML2PN

class BasicSimulationTest extends GroovyTestCase {

//    void test0EmptyPlace() {
//        Net net = new LPNet()
//
//        net.createPlace("rain")
//
//        NetRunner runner = new NetRunner()
//        runner.load(net)
//        assert(runner.run() == 0)
//        assert(runner.execution.places.size() == 1)
//    }
//
//    void test0PlaceFilledWith1Token() {
//        Net net = new LPNet()
//
//        Place p = net.createPlace("dog(Dog)")
//        p.createToken("fido")
//
//        NetRunner runner = new NetRunner()
//        runner.load(net)
//        assert(runner.run() == 0)
//        assert(runner.execution.places.size() == 1)
//        assert(runner.execution.places[0].marking.size() == 1)
//    }
//
//    void test0PlaceFilledWithAnonymousToken() {
//        Net net = new LPNet()
//
//        Place p = net.createPlace("dog(Dog)")
//        p.createToken()
//        p.createToken()
//
//        NetRunner runner = new NetRunner()
//        runner.load(net)
//        assert(runner.run() == 0)
//        assert(runner.execution.places.size() == 1)
//        assert(runner.execution.places[0].marking.size() == 2)
//
//        assert p.marking[0] != p.marking[1]
//    }
//
//    void test0PlaceFilledWith3Tokens() {
//        Net net = new LPNet()
//
//        Place p = net.createPlace("dog(Dog)")
//        p.createToken("fido")
//        p.createToken("argo")
//        p.createToken("rintintin")
//
//        NetRunner runner = new NetRunner()
//        runner.load(net)
//        assert(runner.run() == 0)
//        assert(runner.execution.places.size() == 1)
//        assert(runner.execution.places[0].marking.size() == 3)
//    }

//    void test0PlaceWithVariableAndConstantFilledWith3Tokens() {
//        Net net = new LPNet()
//
//        Place p = net.createPlace("dog(Dog, park)")
//        p.createToken("fido")
//        p.createToken("argo")
//        p.createToken("rintintin")
//
//        NetRunner runner = new NetRunner()
//        runner.load(net)
//        assert(runner.run() == 0)
//        assert(runner.execution.places.size() == 1)
//        assert(runner.execution.places[0].marking.size() == 3)
//    }

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
        assert(runner.execution.places[2].marking.size() == 0)
        assert(runner.run(1) == 1)
        assert(runner.execution.places[2].marking.size() == 1)
        assert(runner.execution.nTokenEmitted == runner.execution.nTokenCollected + 1)
    }

////
////    // test for execution based on places
////    void test1PlaceBased(PNRunner runner) {
////        Net net = PNML2PN.parseFile("examples/basic/1transition.pnml")
////        runner.load(net)
////        // this execution does not handle emitters,
////        assert(runner.run() == 0)
////        // so we have to artificially create tokens
////        net.placeList.find { it.id == 'pl4' }.marking += [new Token()] * 100
////        assert(runner.execution.places.find { it.id == 'pl4' }.marking.size() == 100)
////        // and reset the marked representing places
////        runner.execution.resetMarkedRepresentingPlaces()
////        // it consumes it in just one turn
////        assert(runner.run() == 100)
////        assert(runner.execution.places.find { it.id == 'pl4' }.marking.size() == 0)
////        assert(runner.execution.nTokenEmitted == runner.execution.nTokenCollected - 20)
////    }
////
////    void test1TransitionStaticRepresentingPlaces() {
////        Net net = PNMLparser.parseFile("examples/basic/1transition.pnml")
////
////        PNRunner runner = new PNRunner(ExecutionMode.StaticRepresentingPlaces)
////        test1PlaceBased(net, runner)
////
////    }
////
////    void test1TransitionDynamicRepresentingPlaces() {
////        Net net = PNMLparser.parseFile("examples/basic/1transition.pnml")
////
////        PNRunner runner = new PNRunner(ExecutionMode.DynamicRepresentingPlaces)
////        test1PlaceBased(net, runner)
////    }
//
//    // test for execution based on transitions
//    static void test2TransitionBased(NetRunner runner) {
//        Net net = PNML2PN.parseFile("examples/basic/2chaining.pnml")
//        runner.load(net)
//        assert(runner.run() == 100)
//        assert(runner.execution.places.size() == 2)
//        assert(runner.execution.places.find { it.id == 'pl1' }.marking.size() == 1)
//        assert(runner.execution.places.find { it.id == 'pl3' }.marking.size() == 0)
//        assert(runner.run(1) == 1)
//        assert(runner.execution.places.find { it.id == 'pl1' }.marking.size() == 0)
//        assert(runner.execution.places.find { it.id == 'pl3' }.marking.size() == 1)
//        assert(runner.execution.nTokenEmitted == runner.execution.nTokenCollected + 1)
//    }
//
//    void test2ChainingBruteForce() {
//        NetRunner runner = new NetRunner()
//        test2TransitionBased(runner)
//    }
//
////    void test2ChainingEnabledTransitions() {
////        PNRunner runner = new PNRunner(ExecutionMode.EnabledTransition)
////        test2TransitionBased(runner)
////    }
//
//    // test for execution based on transitions
//    static void test3TransitionBased(NetRunner runner) {
//        Net net = PNML2PN.parseFile("examples/basic/3doublearc.pnml")
//        runner.load(net)
//        assert(runner.run() == 100)
//        assert(runner.execution.places.size() == 4)
//        assert(runner.execution.places.find { it.id == 'pl1' }.marking.size() == 46)
//        assert(runner.execution.places.find { it.id == 'pl2' }.marking.size() == 1)
//        assert(runner.execution.places.find { it.id == 'pl4' }.marking.size() == 0)
//        assert(runner.run(2) == 2)
//        assert(runner.execution.places.find { it.id == 'pl1' }.marking.size() == 47)
//        assert(runner.execution.places.find { it.id == 'pl3' }.marking.size() == 1)
//        assert(runner.execution.places.find { it.id == 'pl4' }.marking.size() == 0)
//        assert(runner.execution.nTokenEmitted == runner.execution.nTokenCollected + 38)
//    }
//
//    void test3DoubleArcBruteForce() {
//        NetRunner runner = new NetRunner()
//        test3TransitionBased(runner)
//    }
//
////    void test3DoubleArcEnabledTransitions() {
////        PNRunner runner = new PNRunner(ExecutionMode.EnabledTransition)
////        test3TransitionBased(runner)
////    }
//
//    // test for execution based on transitions
//    static void test4TransitionBased(NetRunner runner) {
//        Net net = PNML2PN.parseFile("examples/basic/4conflict.pnml")
//        runner.load(net)
//        assert(runner.execution.places.size() == 3)
//        assert(runner.run(1))
//        assert(runner.execution.places.find { it.id == 'pl2' }.marking.size() == 1)
//        assert(runner.execution.places.find { it.id == 'pl4' }.marking.size() == 1)
//        assert(runner.execution.places.find { it.id == 'pl3' }.marking.size() == 0)
//        assert(runner.run(1))
//        assert(runner.execution.places.find { it.id == 'pl2' }.marking.size() == 0)
//        assert(runner.execution.places.find { it.id == 'pl3' }.marking.size() == 1)
//        assert(runner.execution.places.find { it.id == 'pl4' }.marking.size() == 0)
//        assert(runner.run(101))
//        assert(runner.execution.nTokenEmitted == runner.execution.nTokenCollected+1)
//    }
//
//    void test4ConflictBruteForce() {
//        NetRunner runner = new NetRunner()
//        test4TransitionBased(runner)
//    }
//
////    void test4ConflictEnabledTransitions() {
////        PNRunner runner = new PNRunner(ExecutionMode.EnabledTransition)
////        test4TransitionBased(runner)
////    }
//
//    // test for execution based on transitions
//    static void test5TransitionBased(NetRunner runner) {
//        Net net = PNML2PN.parseFile("examples/basic/5inhibitor.pnml")
//        runner.load(net)
//        assert(runner.run() == 100)
//
//        assert(runner.execution.places.find { it.id == 'pl4' }.marking.size() == 100)
//        assert(runner.execution.places.find { it.id == 'pl3' }.marking.size() == 0)
//        assert(runner.execution.places.find { it.id == 'pl2' }.marking.size() == 100)
//        assert(runner.execution.nTokenEmitted == 100)
//        assert(runner.execution.nTokenCollected == 0)
//    }
//
//    void test5InhibitorBruteForce() {
//        NetRunner runner = new NetRunner()
//        test5TransitionBased(runner)
//    }
//
////    void test5InhibitorEnabledTransitions() {
////        PNRunner runner = new PNRunner(ExecutionMode.EnabledTransition)
////        test5TransitionBased(runner)
////    }
//
//    // test for execution based on transitions
//    static void test6TransitionBased(NetRunner runner) {
//        Net net = PNML2PN.parseFile("examples/basic/6biflow.pnml")
//        runner.load(net)
//        assert(runner.run() == 100)
//    }
//
//    void test6BiflowBruteForce() {
//        NetRunner runner = new NetRunner()
//        test6TransitionBased(runner)
//    }
////    void test6BiflowEnabledTransitions() {
////        PNRunner runner = new PNRunner(ExecutionMode.EnabledTransition)
////        test6TransitionBased(runner)
////    }
//
//    static void test7TransitionBased(NetRunner runner) {
//        Net net = PNML2PN.parseFile("examples/basic/7reset.pnml")
//        runner.load(net)
//        assert(runner.run() == 100)
//    }
//
//    void test7ResetBruteForce() {
//        NetRunner runner = new NetRunner()
//        test7TransitionBased(runner)
//    }
//
////    void test7ResetEnabledTransitions() {
////        PNRunner runner = new PNRunner(ExecutionMode.EnabledTransition)
////        test7TransitionBased(runner)
////    }

}