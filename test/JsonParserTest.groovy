import org.leibnizcenter.lppneu.components.lppetrinets.LPPlace
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.lppneu.parser.json2LPN

class JsonParserTest extends GroovyTestCase {

    void test0EmptyPlace() {
        Net net = json2LPN.parseFile("out/json/simpleFact.original.json")

        assert net.subNets.size() == 1
        assert net.subNets[0].placeList.size() == 1
        assert ((LPPlace) net.subNets[0].placeList[0]).expression.toString() == "a"
    }

}
