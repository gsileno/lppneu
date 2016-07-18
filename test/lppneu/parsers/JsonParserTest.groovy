package lppneu.parsers

import lppneu.components.lppetrinets.LPPlace
import pneu.components.petrinet.Net

class JsonParserTest extends GroovyTestCase {

    void test0EmptyPlace() {
        Net net = Json2LPN.parseFile("out/json/simpleFact.original.json")

        assert net.subNets.size() == 1
        assert net.subNets[0].placeList.size() == 1
        assert ((LPPlace) net.subNets[0].placeList[0]).expression.toString() == "a"
    }

}
