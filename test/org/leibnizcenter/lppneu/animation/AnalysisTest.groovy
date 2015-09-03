package org.leibnizcenter.lppneu.animation

import org.leibnizcenter.lppneu.components.lppetrinets.LPNet
import org.leibnizcenter.pneu.animation.monolithic.NetRunner
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Transition

class AnalysisTest extends GroovyTestCase {

    void testSimpleBasicOut() {
        Net net = new LPNet()

        Transition tInput = net.createEmitterTransition()
        Transition tOutput = net.createCollectorTransition()

        Place p = net.createPlace("p")
        net.createArc(tInput, p)
        net.createArc(p, tOutput)

        net.resetIds()

        NetRunner runner = new NetRunner()
        runner.load(net)

        assert runner.analyse() == 2
        assert runner.analysis.storyBase.base.size() == 1
        assert runner.analysis.stateBase.base.size() == 2

    }

    void testSimpleLPOut() {
        Net net = new LPNet()

        Transition tInput = net.createEmitterTransition()
        Transition tOutput = net.createCollectorTransition()

        Place p = net.createPlace("p(A)")
        net.createArc(tInput, p)
        net.createArc(p, tOutput)

        net.resetIds()

        NetRunner runner = new NetRunner()
        runner.load(net)

        assert runner.analyse() == 2
        assert runner.analysis.storyBase.base.size() == 1
        assert runner.analysis.stateBase.base.size() == 2
    }

    void testMoreBasicNexusStructure() {
        Net net = new LPNet()

        Transition tInput = net.createEmitterTransition()
        Transition tOutput = net.createCollectorTransition()

        Place pInput = net.createPlace("a(A)")
        Place pSecondInput = net.createPlace("b(B)")
        Place pOutput = net.createPlace("c(A, B)")

        net.createArc(tInput, pInput)
        net.createArc(pOutput, tOutput)
        net.createArc(tInput, pSecondInput)

        net.createTransitionNexus([pInput, pSecondInput], [pOutput], [], [], [])

        net.resetIds()

        NetRunner runner = new NetRunner()
        runner.load(net)
        runner.analyse()

        runner.analysis.stateBase.base.size() == 3
        runner.analysis.storyBase.base.size() == 1
        runner.analysis.storyBase.base[0].steps.size() == 3

    }

    void testMoreBasicNexusStructure2() {
        Net net = new LPNet()

        Transition tInput = net.createEmitterTransition()
        Transition tOutput = net.createCollectorTransition()

        Place pInput = net.createPlace("a(A)")
        Place pSecondInput = net.createPlace("b(B)")
        Place pOutput = net.createPlace("c(A, B)")

        net.createArc(tInput, pInput)
        net.createArc(pOutput, tOutput)
        net.createArc(tInput, pSecondInput)

        Transition nexus = net.createTransition("d(A)")
        net.createArc(pInput, nexus)
        net.createArc(pSecondInput, nexus)
        net.createArc(nexus, pOutput)

        net.resetIds()
        NetRunner runner

        // for normal execution
        runner = new NetRunner()
        runner.load(net)

        runner.execution.net.placeList.size() == 3
        runner.execution.net.transitionList.size() == 3
        assert runner.execution.net.placeList[0].marking.size() == 0
        assert runner.execution.net.placeList[1].marking.size() == 0
        assert runner.execution.net.placeList[2].marking.size() == 0
        runner.run(1)
        assert runner.execution.net.placeList[0].marking.size() == 1
        assert runner.execution.net.placeList[1].marking.size() == 1
        assert runner.execution.net.placeList[2].marking.size() == 0
        runner.run(1)
        assert runner.execution.net.placeList[0].marking.size() == 0
        assert runner.execution.net.placeList[1].marking.size() == 0
        assert runner.execution.net.placeList[2].marking.size() == 1
        runner.run(1)
        assert runner.execution.net.placeList[0].marking.size() == 0
        assert runner.execution.net.placeList[1].marking.size() == 0
        assert runner.execution.net.placeList[2].marking.size() == 0

        // for analysis
        runner = new NetRunner()
        runner.load(net)
        runner.analyse()

        runner.analysis.stateBase.base.size() == 3
        runner.analysis.storyBase.base.size() == 1
        runner.analysis.storyBase.base[0].steps.size() == 3
    }

    void testMoreBasicNexusStructure3() {
        Net net = new LPNet()

        Transition tInput = net.createEmitterTransition()
        Transition tOutput = net.createCollectorTransition()

        Place pInput = net.createPlace("a(A)")
        Place pSecondInput = net.createPlace("b(A, B)")
        Place pOutput = net.createPlace("c(A, B)")

        net.createArc(tInput, pInput)
        net.createArc(pOutput, tOutput)
        net.createArc(tInput, pSecondInput)

        Transition nexus = net.createTransition("d(A)")
        net.createArc(pInput, nexus)
        net.createArc(pSecondInput, nexus)
        net.createArc(nexus, pOutput)

        net.resetIds()

        // for analysis
        NetRunner runner = new NetRunner()
        runner.load(net)
        runner.analyse()

        runner.analysis.stateBase.base.size() == 3
        runner.analysis.storyBase.base.size() == 1
        runner.analysis.storyBase.base[0].steps.size() == 3
    }

    void testBasicNexusStructure() {
        Net net = new LPNet()

        Transition tInput = net.createEmitterTransition()
        Place pInput = net.createPlace("input")
        net.createArc(tInput, pInput)

        Transition tOutput = net.createCollectorTransition()
        Place pOutput = net.createPlace("output")
        net.createArc(pOutput, tOutput)

        Transition tInhibitor = net.createEmitterTransition()
        Place pInhibitor = net.createPlace("inhibitor")
        net.createArc(tInhibitor, pInhibitor)

        Transition tBiflow = net.createEmitterTransition()
        Place pBiflow = net.createPlace("biflow")
        net.createArc(tBiflow, pBiflow)

        Transition tDiode = net.createEmitterTransition()
        Place pDiode = net.createPlace("diode")
        net.createArc(tDiode, pDiode)

        net.createTransitionNexus([pInput], [pOutput], [pBiflow], [pDiode], [pInhibitor])

        net.resetIds()

        NetRunner runner = new NetRunner()
        runner.load(net)
        runner.analyse()

        assert runner.analysis.stateBase.base.size() == 22
        assert runner.analysis.storyBase.base.size() == 23
    }

    void testNexusStructureWithDifferentVariables() {
        Net net = new LPNet()

        Transition tInput = net.createEmitterTransition()
        Place pInput = net.createPlace("input(A)")
        net.createArc(tInput, pInput)

        Transition tOutput = net.createCollectorTransition()
        Place pOutput = net.createPlace("output(B)")
        net.createArc(pOutput, tOutput)

        Transition tInhibitor = net.createEmitterTransition()
        Place pInhibitor = net.createPlace("inhibitor(C)")
        net.createArc(tInhibitor, pInhibitor)

        Transition tBiflow = net.createEmitterTransition()
        Place pBiflow = net.createPlace("biflow(D)")
        net.createArc(tBiflow, pBiflow)

        Transition tDiode = net.createEmitterTransition()
        Place pDiode = net.createPlace("diode(E)")
        net.createArc(tDiode, pDiode)

        net.createTransitionNexus([pInput], [pOutput], [pBiflow], [pDiode], [pInhibitor])

        net.resetIds()

        NetRunner runner = new NetRunner()
        runner.load(net)

        assert runner.analyse(30) == 30

    }

    void testNexusStructureWithSameVariable() {
        Net net = new LPNet()

        Transition tInput = net.createEmitterTransition()
        Place pInput = net.createPlace("input(A)")
        net.createArc(tInput, pInput)

        Transition tOutput = net.createCollectorTransition()
        Place pOutput = net.createPlace("output(A)")
        net.createArc(pOutput, tOutput)

        Transition tInhibitor = net.createEmitterTransition()
        Place pInhibitor = net.createPlace("inhibitor(A)")
        net.createArc(tInhibitor, pInhibitor)

        Transition tBiflow = net.createEmitterTransition()
        Place pBiflow = net.createPlace("biflow(A)")
        net.createArc(tBiflow, pBiflow)

        Transition tDiode = net.createEmitterTransition()
        Place pDiode = net.createPlace("diode(A)")
        net.createArc(tDiode, pDiode)

        net.createTransitionNexus([pInput], [pOutput], [pBiflow], [pDiode], [pInhibitor])

        net.resetIds()

        NetRunner runner = new NetRunner()
        runner.load(net)
        runner.analyse()

        assert runner.analysis.stateBase.base.size() == 22
        assert runner.analysis.storyBase.base.size() == 23
    }

    void testNexusStructureWithFeeder() {
        Net net = new LPNet()

        Transition tEmitter = net.createEmitterTransition()

        Transition tInput = net.createLinkTransition()
        Place pInput = net.createPlace("input(A)")
        net.createArc(tInput, pInput)

        Transition tOutput = net.createCollectorTransition()
        Place pOutput = net.createPlace("output(A)")
        net.createArc(pOutput, tOutput)

        Transition tInhibitor = net.createLinkTransition()
        Place pInhibitor = net.createPlace("inhibitor(A)")
        net.createArc(tInhibitor, pInhibitor)

        Transition tBiflow = net.createLinkTransition()
        Place pBiflow = net.createPlace("biflow(A)")
        net.createArc(tBiflow, pBiflow)

        Transition tDiode = net.createLinkTransition()
        Place pDiode = net.createPlace("diode(A)")
        net.createArc(tDiode, pDiode)

        net.createTransitionNexus([pInput], [pOutput], [pBiflow], [pDiode], [pInhibitor])

        // to feed all transitions with the same token
        // I centralize the emitted token in a place
        Place pFeeder = net.createPlace("case(A)")
        net.createArc(tEmitter, pFeeder)
        Transition tFeeder = net.createTransition("case(A)")

        net.createArc(pFeeder, tFeeder)
        net.createBridge(tFeeder, tInput)
        net.createBridge(tFeeder, tInhibitor)
        net.createBridge(tFeeder, tBiflow)
        net.createBridge(tFeeder, tDiode)

        net.resetIds()

        NetRunner runner = new NetRunner()
        runner.load(net)
        runner.analyse()
    }

}