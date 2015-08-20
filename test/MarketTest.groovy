import org.leibnizcenter.lppneu.examples.MarketModelsFactory
import org.leibnizcenter.pneu.components.petrinet.Net

class MarketTest extends GroovyTestCase {

    void testBasicSaleInstance1() {
        Net net = MarketModelsFactory.basicSaleInstance1()


    }

    void testBasicSaleInstance2() {
        Net net = MarketModelsFactory.basicSaleInstance2()
    }

    void testBasicSaleModel() {
        Net net = MarketModelsFactory.basicSaleModel()
    }

    void testBasicSaleWith2Parties() {
        Net net = MarketModelsFactory.basicSaleWith2Parties()
    }

    void testBasicSaleWithWorld() {
        Net net = MarketModelsFactory.basicSaleWithWorld()
    }

    void testBasicSaleWithWorldAndTimeline() {
        Net net = MarketModelsFactory.basicSaleWithWorldAndTimeline()
    }

}
