package org.leibnizcenter.lppneu.animation

import org.leibnizcenter.lppneu.components.lppetrinets.LPNet
import org.leibnizcenter.pneu.animation.monolithic.NetRunner
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Transition

class AnalysisTest extends GroovyTestCase {

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
        runner.analysis.exportToLog("BasicLPNetNexusStructure")

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
        runner.analyse()
        runner.analysis.exportToLog("WithDifferentVariable")

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
        runner.analysis.exportToLog("NexusStructureWithSameVariable")
    }

//    void testNexusStructureWithFeeder() {
//        Net net = new LPNet()
//
//        Transition tEmitter = net.createEmitterTransition()
//
//        Transition tInput = net.createTransition()
//        Place pInput = net.createPlace("input(A)")
//        net.createArc(tInput, pInput)
//
//        Transition tOutput = net.createCollectorTransition()
//        Place pOutput = net.createPlace("output(A)")
//        net.createArc(pOutput, tOutput)
//
//        Transition tInhibitor = net.createTransition()
//        Place pInhibitor = net.createPlace("inhibitor(A)")
//        net.createArc(tInhibitor, pInhibitor)
//
//        Transition tBiflow = net.createTransition()
//        Place pBiflow = net.createPlace("biflow(A)")
//        net.createArc(tBiflow, pBiflow)
//
//        Transition tDiode = net.createTransition()
//        Place pDiode = net.createPlace("diode(A)")
//        net.createArc(tDiode, pDiode)
//
//        net.createTransitionNexus([pInput], [pOutput], [pBiflow], [pDiode], [pInhibitor])
//
//        // to feed all transitions with the same token
//        // I centralize the emitted token in a place
//        Place pFeeder = net.createPlace("case(A)")
//        net.createArc(tEmitter, pFeeder)
//        Transition tFeeder = net.createTransition("case(A)")
//
//        net.createArc(pFeeder, tFeeder)
//        net.createBridge(tFeeder, tInput)
//        net.createBridge(tFeeder, tInhibitor)
//        net.createBridge(tFeeder, tBiflow)
//        net.createBridge(tFeeder, tDiode)
//
//        net.resetIds()
//
//        NetRunner runner = new NetRunner()
//        runner.load(net)
//        runner.analyse()
//        runner.analysis.exportToLog("NexusStructureWithFeeder")
//    }

}