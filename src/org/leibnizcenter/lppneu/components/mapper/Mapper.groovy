package org.leibnizcenter.lppneu.components.mapper

import org.leibnizcenter.lppneu.components.position.AbstractTriple
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Operation
import org.leibnizcenter.lppneu.components.lppetrinets.LPPlace
import org.leibnizcenter.lppneu.components.lppetrinets.LPTransition
import org.leibnizcenter.pneu.components.petrinet.Net

class Mapper {

    Map<Expression, List<LPPlace>> expressionPlaceMap
    Map<Operation, List<LPTransition>> operationTransitionMap
    Map<Expression, AbstractTriple> expressionTripleMap

    void mapPlace(LPPlace place) {
        if (place.expression) {
            if (expressionPlaceMap[place.expression] == null) expressionPlaceMap[place.expression] = []
            expressionPlaceMap[place.expression] << place
        } else if (place.link) {
            return
        } else {
            throw new RuntimeException()
        }
    }

    void mapTransition(LPTransition transition) {
        if (transition.operation) {
            if (operationTransitionMap[transition.operation] == null) operationTransitionMap[transition.operation] = []
            operationTransitionMap[transition.operation] << transition
        } else if (transition.operator) {
            return
        } else if (transition.link) {
            return
        } else {
            throw new RuntimeException("Unknown transition: ${transition}")
        }
    }

    // map all places and transitions indexed by their logic content
    // inner function for traversal
    void innerMapNet(Net net, List<Net> alreadyRecordedNets = []) {

        // map places
        for (p in net.placeList) {
            mapPlace(p)
        }
        // map transitions
        for (t in net.transitionList) {
            mapTransition(t)
        }

        // nested nets
        for (subNet in net.subNets) {
            if (!alreadyRecordedNets.contains(subNet)) {
                innerMapNet(subNet, alreadyRecordedNets)
                alreadyRecordedNets << subNet
            }
        }
    }

    // map all places and transitions indexed by their logic content
    // outer function for reset of maps
    void mapNet(Net net) {
        // set the maps as empty
        expressionPlaceMap = [:]
        operationTransitionMap = [:]

        innerMapNet(net)
    }

}
