package lppneu

import lppneu.components.lppetrinets.LPNet
import pneu.animation.monolithic.NetRunner
import pneu.components.petrinet.Net
import pneu.components.petrinet.Place
import pneu.components.petrinet.Transition

class ReadmeTest extends GroovyTestCase {

    void testReadme() {

        Net net = new LPNet()
        Transition tIn = net.createEmitterTransition()

        Place pIn = net.createPlace("object(Object)")
        Transition tAction = net.createTransition("cutsInTwoPieces(Person, Object)")
        Place pOut1 = net.createPlace("object(leftPiece(Object))")
        Place pOut2 = net.createPlace("object(rightPiece(Object))")

        net.createArc(tIn, pIn)
        net.createArc(pIn, tAction)
        net.createArc(tAction, pOut1)
        net.createArc(tAction, pOut2)

        Transition tOut = net.createCollectorTransition()

        net.createArc(pOut1, tOut)
        net.createArc(pOut2, tOut)

        net.resetIds()

        NetRunner runner = new NetRunner()
        runner.load(net)

        net.exportToJson("readmeNet.step0")

        runner.run(1)

        net.exportToJson("readmeNet.step1")

        runner.run(1)
        net.exportToJson("readmeNet.step2")
        runner.run(1)
        net.exportToJson("readmeNet.step3")
        net.exportToDot("readmeNet")


//        NetRunner runner = new NetRunner()
//        runner.load(net)
//        runner.analyse()
//        runner.analysis.exportToLog("readmeNet")

    }

}
