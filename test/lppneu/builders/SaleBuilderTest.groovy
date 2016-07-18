package lppneu.builders

import lppneu.components.language.LPPNProgram
import lppneu.parsers.LPPNLoader
import pneu.components.petrinet.Net


class SaleBuilderTest extends GroovyTestCase {

    static Net batchConvert(String filename) {
        LPPNProgram program = LPPNLoader.parseFile(filename)
        LPNHandler handler = new LPNHandler()
        handler.convert(program)
        return handler.net
    }

    void testSale() {
        Net net = batchConvert("examples/complex/sale.lppn")
        net.exportToDot("sale")
    }

    void testSaleNorm() {
        Net net = batchConvert("examples/complex/salenorm.lppn")
        net.exportToDot("salenorm")
    }

    void testBuyer() {
        Net net = batchConvert("examples/complex/buyer.lppn")
        net.exportToDot("buyer")
    }

    void testSeller() {
        Net net = batchConvert("examples/complex/seller.lppn")
        net.exportToDot("seller")
    }
}
