package org.leibnizcenter.lppneu.animation

import org.leibnizcenter.lppneu.examples.MarketModel
import org.leibnizcenter.pneu.animation.monolithic.NetRunner

class MarketModelTest extends GroovyTestCase {

    NetRunner runner = new NetRunner()

    void testBasicSaleInstance1() {
        runner.load(MarketModel.basicSaleInstance1())
        runner.analyse()
        runner.analysis.exportToLog("BasicSaleInstance1")
        runner.analysis.execution.net.exportToDot("BasicSaleInstance1")
        assert (runner.analysis.storyBase.getSize() == 1)
        assert (runner.analysis.storyBase.base[0].steps.size() == 7)
        assert (runner.analysis.stateBase.base.size() == 6)

    }

    void testBasicSaleInstance2() {
        runner.load(MarketModel.basicSaleInstance2())
        runner.analyse()
        runner.analysis.exportToLog("BasicSaleInstance2")
        assert (runner.analysis.storyBase.getSize() == 1)
        assert (runner.analysis.storyBase.base[0].steps.size() == 7)
        assert (runner.analysis.stateBase.base.size() == 6)

    }

    void testBasicSaleModel() {
        runner.load(MarketModel.basicSaleModel())
        runner.analyse()
        runner.analysis.exportToLog("BasicSaleModel")
        runner.execution.net.exportToDot("BasicSaleModel")
        assert (runner.analysis.storyBase.getSize() == 2)
        assert (runner.analysis.storyBase.base[0].steps.size() == 7)
        assert (runner.analysis.stateBase.base.size() == 7)

    }

    void testBasicSaleWith2Parties() {
        runner.load(MarketModel.basicSaleWith2Parties())
        runner.analyse()
        runner.analysis.exportToLog("BasicSaleWith2Parties")
        assert (runner.analysis.storyBase.getSize() == 3)
        assert (runner.analysis.storyBase.base[0].steps.size() == 9)
        assert (runner.analysis.stateBase.base.size() == 10)

    }

    void testBasicSaleWithWorld() {
        runner.load(MarketModel.basicSaleWithWorld())
        runner.analyse()
        runner.analysis.exportToLog("BasicSaleWithWorld")
        assert (runner.analysis.storyBase.getSize() == 9)
        assert (runner.analysis.storyBase.base[0].steps.size() == 13)
        assert (runner.analysis.stateBase.base.size() == 20)
    }

    void testBasicSaleWithWorldAndTimeline() {
        runner.load(MarketModel.basicSaleWithWorldAndTimeline())
        runner.analyse()
        runner.analysis.exportToLog("BasicSaleWithWorldAndTimeline")
        assert (runner.analysis.storyBase.getSize() == 8)
        assert (runner.analysis.storyBase.base[0].steps.size() == 13)
        assert (runner.analysis.stateBase.base.size() == 19)
    }

    void testBasicSwapSale() {
        runner.load(MarketModel.basicSwapSale())
        runner.analyse()
        runner.analysis.exportToLog("BasicSwapSale")
        runner.execution.net.exportToLog("BasicSwapSale")
        runner.execution.net.exportToDot("BasicSwapSale")
        assert (runner.analysis.storyBase.getSize() == 1)
    }
}