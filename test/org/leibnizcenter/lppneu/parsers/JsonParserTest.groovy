package org.leibnizcenter.lppneu.parsers

import org.leibnizcenter.lppneu.components.lppetrinets.LPPlace
import org.leibnizcenter.pneu.components.petrinet.Net

class JsonParserTest extends GroovyTestCase {

    void test0EmptyPlace() {
        Net net = Json2LPN.parseFile("out/json/simpleFact.original.json")

        assert net.subNets.size() == 1
        assert net.subNets[0].placeList.size() == 1
        assert ((LPPlace) net.subNets[0].placeList[0]).expression.toString() == "a"
    }

}
