package org.leibnizcenter.lppneu.parsers

import groovy.io.FileType
import groovy.json.JsonSlurper
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.lppetrinets.LPNet
import org.leibnizcenter.lppneu.components.lppetrinets.LPPlace
import org.leibnizcenter.lppneu.components.lppetrinets.LPTransition
import org.leibnizcenter.pneu.components.petrinet.*

class json2LPN {

    static List<Net> parseFiles(String path) {
        List<Net> netList = []
        File dir = new File(path);
        List<String> files = []
        dir.eachFileMatch(FileType.FILES, ~/.*\.json$/) {
            files << it.name
        }
        files.sort()
        for (filename in files) {
            netList << parseFile(path+"/"+filename)
        }
        return netList
    }

    static Net parseFile(String filename) {

        def inputFile = new File(filename)
        def records = new JsonSlurper().parseText(inputFile.text)

        loadJson(records)
    }

    static Net parseText(String text) {
        def records = new JsonSlurper().parseText(text)
        loadJson(records)
    }

    static Net loadJson(records, Map<String, Net> netMap = [:], Map<String, LPPlace> lPlaceMap = [:], Map<String, LPTransition> lTransitionMap = [:]) {

        if (netMap[records["id"].toString()]) {
            return netMap[records["id"].toString()]
        }

        Net net = new LPNet()

        if (records["cluster"]) {

        }

        for (item in records["places"]) {
            if (!lPlaceMap[item["id"].toString()]) {
                Expression expression = LPPNLoader.parseString(item["label"].toString()+".").logicRules[0].head
                LPPlace p = new LPPlace(id: item["id"], expression: expression)
                lPlaceMap[item["id"].toString()] = p
            }
            net.placeList << lPlaceMap[item["id"].toString()]
        }

        for (item in records["transitions"]) {
            if (!lTransitionMap[item["id"].toString()]) {
                Expression expression = LPPNLoader.parseString(item["label"].toString()+".").logicRules[0].head
                LPTransition t = new LPTransition(id: item["id"], operation: expression.toOperation())
                lTransitionMap[item["id"].toString()] = t
            }
            net.transitionList << lTransitionMap[item["id"].toString()]
        }

        for (item in records["subnets"]) {
            net.subNets << loadJson(item, netMap, lPlaceMap, lTransitionMap)
        }

        for (item in records["arcs"]) {
            Arc a = new Arc()

            a.id = item["id"].toString()
            if (lPlaceMap.containsKey(item["source"].toString())) {
                a.source = lPlaceMap[item["source"].toString()]
                a.target = lTransitionMap[item["target"].toString()]
            } else {
                a.source = lTransitionMap[item["source"].toString()]
                a.target = lPlaceMap[item["target"].toString()]
            }

            net.arcList << a
        }

        net
    }
}
