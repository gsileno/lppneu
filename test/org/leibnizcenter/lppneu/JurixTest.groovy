package org.leibnizcenter.lppneu

import org.leibnizcenter.lppneu.examples.MarketModel
import org.leibnizcenter.pneu.animation.monolithic.NetRunner
import org.leibnizcenter.pneu.animation.monolithic.analysis.Analysis
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.decomposition.Subsumption

class JurixTest extends GroovyTestCase {

    static Analysis batchAnalysis(String filename, Net net) {

        Analysis analysis = null

//        Analysis analysis = Analysis.loadFromFile(filename)

        if (!analysis) {
            NetRunner runner = new NetRunner()
            runner.load(net)
            runner.analyse()
            runner.status()
            analysis = runner.analysis
//            analysis.saveToFile(filename)
        }

        analysis
    }

    void testGroundNetsExports() {
        MarketModel.groundSaleInstance().exportToDot("groundSaleInstance")
        MarketModel.groundSaleModel().exportToDot("groundSaleModel")
        MarketModel.groundSaleNormativeModel().exportToDot("groundSaleNormativeModel")
        MarketModel.groundSaleScriptModel().exportToDot("groundSaleScriptModel")
    }

    void testGroundNetsAnalysis1() {
        String filename = "groundSaleInstance"
        Analysis analysis = batchAnalysis(filename, MarketModel.groundSaleInstance())
        analysis.exportToLog(filename)
    }

    void testGroundNetsAnalysis2() {
        String filename = "groundSaleModel"
        Analysis analysis = batchAnalysis(filename, MarketModel.groundSaleModel())
        analysis.exportToLog(filename)
    }

    void testGroundNetsAnalysis3() {
        String filename = "groundSaleNormativeModel"
        Analysis analysis = batchAnalysis(filename, MarketModel.groundSaleNormativeModel())
        analysis.exportToLog(filename)
    }

    void testGroundNetsAnalysis4() {
        String filename = "groundSaleScriptModel"
        Analysis analysis = batchAnalysis(filename, MarketModel.groundSaleScriptModel())
        analysis.exportToLog(filename)
    }

    void testSubsumptionIdentity() {
        assert Subsumption.subsumes(MarketModel.groundSaleInstance(), MarketModel.groundSaleInstance())
        assert Subsumption.subsumes(MarketModel.groundSaleModel(), MarketModel.groundSaleModel())
        assert Subsumption.subsumes(MarketModel.groundSaleNormativeModel(), MarketModel.groundSaleNormativeModel())
        assert Subsumption.subsumes(MarketModel.groundSaleScriptModel(), MarketModel.groundSaleScriptModel())
    }

    void testSubsumption1() {
      assert Subsumption.subsumes(MarketModel.groundSaleModel(), MarketModel.groundSaleInstance())
    }

    void testSubsumption1bis() {
        assert !Subsumption.subsumes(MarketModel.groundSaleInstance(), MarketModel.groundSaleModel())
    }

    void testSubsumption2() {
        assert Subsumption.subsumes(MarketModel.groundSaleNormativeModel(), MarketModel.groundSaleModel())
    }

    void testSubsumption2bis() {
        assert !Subsumption.subsumes(MarketModel.groundSaleModel(), MarketModel.groundSaleNormativeModel())
    }

    void testSubsumption3() {
        assert Subsumption.subsumes(MarketModel.groundSaleScriptModel(), MarketModel.groundSaleNormativeModel())
    }

    void testSubsumption3bis() {
        assert !Subsumption.subsumes(MarketModel.groundSaleNormativeModel(), MarketModel.groundSaleScriptModel())
    }

}