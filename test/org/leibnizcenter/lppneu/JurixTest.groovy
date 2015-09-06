package org.leibnizcenter.lppneu

import org.leibnizcenter.lppneu.components.lppetrinets.LPNet
import org.leibnizcenter.lppneu.examples.MarketModel
import org.leibnizcenter.pneu.animation.monolithic.NetRunner
import org.leibnizcenter.pneu.builders.PN2LaTeX
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Transition
import org.leibnizcenter.pneu.parsers.PNML2PN

class JurixTest extends GroovyTestCase {


    void testGroundNetsExports() {

        MarketModel.groundSaleInstance().exportToDot("groundSaleInstance")
        MarketModel.groundSaleModel().exportToDot("groundSaleModel")
        MarketModel.groundSaleNormativeModel().exportToDot("groundSaleNormativeModel")

    }

    void testGroundNetsAnalysis1() {

        Net model = MarketModel.groundSaleInstance()

        NetRunner runner = new NetRunner()
        runner.load(model)
        runner.analyse()
        runner.status()

    }

    void testGroundNetsAnalysis2() {

        Net model = MarketModel.groundSaleModel()

        NetRunner runner = new NetRunner()
        runner.load(model)
        runner.analyse()
        runner.status()

    }

    void testGroundNetsAnalysis3() {

        Net model = MarketModel.groundSaleNormativeModel()

        NetRunner runner = new NetRunner()
        runner.load(model)
        runner.analyse()
        runner.status()

        runner.analysis.exportToLog("groundSaleNormativeModel")

    }

    void testDecomposition1() {

        Net model = MarketModel.groundSaleInstance()

        NetRunner runner = new NetRunner()
        runner.load(model)
        runner.analyse()

        D

    }

    void testDecomposition2() {

        Net model = MarketModel.groundSaleModel()

        NetRunner runner = new NetRunner()
        runner.load(model)
        runner.analyse()
        runner.status()

    }

    void testDecomposition3() {

        Net model = MarketModel.groundSaleNormativeModel()

        NetRunner runner = new NetRunner()
        runner.load(model)
        runner.analyse()
        runner.status()

        runner.analysis.exportToLog("groundSaleNormativeModel")

    }

}