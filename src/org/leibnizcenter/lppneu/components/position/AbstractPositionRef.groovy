package org.leibnizcenter.lppneu.components.position

import org.leibnizcenter.lppneu.components.language.Parameter
import org.leibnizcenter.lppneu.components.language.Variable

abstract class AbstractPositionRef {

    abstract AbstractPositionRef reify()

    abstract String toLabel()

    abstract List<Variable> getVariables()
    abstract List<Parameter> getParameters()
}
