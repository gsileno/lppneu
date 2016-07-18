package lppneu.components.position

import lppneu.components.language.Operation
import lppneu.components.lppetrinets.LPPlace
import lppneu.components.lppetrinets.LPTransition

abstract class AbstractTriple {

    AbstractPosition positive
    AbstractPosition negative
    AbstractPosition nullified

    List<Operation> posOperationList = []
    List<Operation> negOperationList = []
    List<Operation> nullOperationList = []

    // Petri Net Mapping

    LPPlace positivePlace
    LPPlace negativePlace
    LPPlace nullPlace

    List<LPTransition> posTransitionList = []
    List<LPTransition> negTransitionList = []
    List<LPTransition> nullTransitionList = []

}
