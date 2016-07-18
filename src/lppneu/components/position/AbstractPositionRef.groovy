package lppneu.components.position

import lppneu.components.language.Parameter
import lppneu.components.language.Variable

abstract class AbstractPositionRef {

    abstract AbstractPositionRef reify()

    abstract String toLabel()

    abstract List<Variable> getVariables()
    abstract List<Parameter> getParameters()
}
