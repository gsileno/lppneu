package org.leibnizcenter.lppneu.animation

import org.leibnizcenter.lppneu.components.lppetrinets.LPNet
import org.leibnizcenter.pneu.animation.monolithic.NetRunner
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Transition

class AnalysisTest extends GroovyTestCase {

    void testNexusStructure() {
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

        net.createNexus([pInput], [pOutput], [pBiflow], [pDiode], [pInhibitor])

        net.resetIds()

        NetRunner runner = new NetRunner()
        runner.load(net)
        runner.analyse()
        runner.analysis.exportToLog("NexusStructure")

    }

    void testNexusStructure2() {
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

        net.createNexus([pInput], [pOutput], [pBiflow], [pDiode], [pInhibitor])

        net.resetIds()

        NetRunner runner = new NetRunner()
        runner.load(net)
        runner.analyse()
        runner.analysis.exportToLog("NexusStructure2")
    }
}