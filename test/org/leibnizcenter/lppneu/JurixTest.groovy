package org.leibnizcenter.lppneu

import org.leibnizcenter.lppneu.components.lppetrinets.LPNet
import org.leibnizcenter.lppneu.examples.MarketModel
import org.leibnizcenter.pneu.animation.monolithic.NetRunner
import org.leibnizcenter.pneu.animation.monolithic.analysis.Analysis
import org.leibnizcenter.pneu.builders.PN2LaTeX
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Place
import org.leibnizcenter.pneu.components.petrinet.Transition
import org.leibnizcenter.pneu.decomposition.Subsumption
import org.leibnizcenter.pneu.parsers.PNML2PN

class JurixTest extends GroovyTestCase {

    static Analysis batchAnalysis(Net net) {
        Net model = MarketModel.groundSaleInstance()
        NetRunner runner = new NetRunner()
        runner.load(model)
        runner.analyse()
        runner.status()
        runner.analysis
    }


    void testGroundNetsExports() {
        MarketModel.groundSaleInstance().exportToDot("groundSaleInstance")
        MarketModel.groundSaleModel().exportToDot("groundSaleModel")
        MarketModel.groundSaleNormativeModel().exportToDot("groundSaleNormativeModel")
        MarketModel.groundSaleNormativeModel().exportToDot("groundSaleScriptModel")
    }

    void testGroundNetsAnalysis1() {
        Analysis analysis = batchAnalysis(MarketModel.groundSaleInstance())
        analysis.exportToLog("groundSaleInstance")
    }

    void testGroundNetsAnalysis2() {
        Analysis analysis = batchAnalysis(MarketModel.groundSaleModel())
        analysis.exportToLog("groundSaleModel")
    }

    void testGroundNetsAnalysis3() {
        Analysis analysis = batchAnalysis(MarketModel.groundSaleNormativeModel())
        analysis.exportToLog("groundSaleNormativeModel")
    }

    void testGroundNetsAnalysis4() {
        Analysis analysis = batchAnalysis(MarketModel.groundSaleScriptModel())
        analysis.exportToLog("groundSaleScriptModel")
    }

    void testSubsumptionIdentity() {
        assert Subsumption.subsumes(MarketModel.groundSaleInstance(), MarketModel.groundSaleInstance())
        assert Subsumption.subsumes(MarketModel.groundSaleModel(), MarketModel.groundSaleModel())
        assert Subsumption.subsumes(MarketModel.groundSaleNormativeModel(), MarketModel.groundSaleNormativeModel())
        // Subsumption.subsumes(MarketModel.groundSaleScriptModel(), MarketModel.groundSaleScriptModel())
    }

    void testSubsumption1() {
//        Subsumption.subsumes(MarketModel.groundSaleModel(), MarketModel.groundSaleInstance())
        assert !Subsumption.subsumes(MarketModel.groundSaleNormativeModel(), MarketModel.groundSaleModel())
        assert Subsumption.subsumes(MarketModel.groundSaleModel(), MarketModel.groundSaleNormativeModel())
        // Subsumption.subsumes(MarketModel.groundSaleScriptModel(), MarketModel.groundSaleScriptModel())
    }




}