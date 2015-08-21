package org.leibnizcenter.lppneu.components.position

import org.leibnizcenter.lppneu.components.language.Operation
import org.leibnizcenter.lppneu.components.lppetrinets.LPPlace
import org.leibnizcenter.lppneu.components.lppetrinets.LPTransition

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
