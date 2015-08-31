package org.leibnizcenter.lppneu.animation

import org.leibnizcenter.lppneu.examples.MarketModel
import org.leibnizcenter.pneu.animation.monolithic.NetRunner

class MarketModelTest extends GroovyTestCase {

    NetRunner runner = new NetRunner()

    void testBasicSaleInstance1() {
        runner.load(MarketModel.basicSaleInstance1())
        assert (runner.analyse(10) == 6)
        assert (runner.analysis.storyBase.getSize() == 1)
        runner.analysis.exportToLog("BasicSaleInstance1")
    }

    void testBasicSaleModel() {
        runner.load(MarketModel.basicSaleModel())
        runner.analyse()
        assert (runner.analysis.storyBase.getSize() == 2)
        runner.analysis.exportToLog("BasicSaleModel")
    }

    void testBasicSaleWith2Parties() {
        runner.load(MarketModel.basicSaleWith2Parties())
        runner.analyse()
        assert (runner.analysis.storyBase.getSize() == 3)
        runner.analysis.exportToLog("BasicSaleWith2Parties")
    }

    void testBasicSaleWithWorld() {
        runner.load(MarketModel.basicSaleWithWorld())
        runner.analyse()
        assert (runner.analysis.storyBase.getSize() == 9)
        runner.analysis.exportToLog("BasicSaleWithWorld")
    }

    void testBasicSaleWithWorldAndTimeline() {
        runner.load(MarketModel.basicSaleWithWorldAndTimeline())
        runner.analyse()
        assert (runner.analysis.storyBase.getSize() == 8)
        runner.analysis.exportToLog("BasicSaleWithWorldAndTimeline")
    }

    void testBasicSwapSale() {
        runner.load(MarketModel.basicSwapSale())
        runner.execution.net.exportToLog("BasicSwapSale")
        runner.execution.net.exportToDot("BasicSwapSale")
        runner.analyse()
        runner.analysis.exportToLog("BasicSwapSale")
        assert (runner.analysis.storyBase.getSize() == 1)
    }
}