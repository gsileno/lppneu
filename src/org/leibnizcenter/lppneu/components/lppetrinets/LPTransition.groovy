package org.leibnizcenter.lppneu.components.lppetrinets

import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.Operation
import org.leibnizcenter.lppneu.components.language.Operator
import org.leibnizcenter.lppneu.components.language.Parameter
import org.leibnizcenter.lppneu.components.language.Variable
import org.leibnizcenter.pneu.components.petrinet.Arc
import org.leibnizcenter.pneu.components.petrinet.ArcType
import org.leibnizcenter.pneu.components.petrinet.Token
import org.leibnizcenter.pneu.components.petrinet.Transition

@Log4j
class LPTransition extends Transition {

    // for causal dependencies
    Operation operation

    // for logic dependencies
    Operator operator

    // if they are just synchronization places
    Boolean link = false

    String toString() {
        if (operation != null) {
            operation.toString() // + " LTransition@"+hashCode()
        } else if (operator != null) {
            return operator.toString() // + " LTransition@"+hashCode()
        } else if (link) {
            "*"
        } else {
            ""
        }
    }

    String label() {
        toString()
    }

    Boolean isLink() {
        return link
    }

    Transition clone() {
        return new LPTransition(
                operation: operation,
                operator: operator,
                link: link
        )
    }

    static Transition build() {
        return new LPTransition()
    }

    static Transition build(String label) {
        return new LPTransition(operation: Operation.parse(label))
    }

    static Boolean compare(Transition t1, Transition t2) {
        return compare((LPTransition) t1, (LPTransition) t2)
    }

    static Boolean compare(LPTransition t1, LPTransition t2) {
        if (t1 == t2) return true
        if (t1.operation != t2.operation) return false
        if (t1.operator != t2.operator) return false
        if (t1.link != t2.link) return false
        return true
    }

    Boolean isEnabledForAnalysis() {
        if (inputs.size() == 0) {
            return true
        }

        isEnabled()
    }

    // filter to unify tokens
    List<Variable> commonVarList

    // map to anchor values satisfying the filter
    Map<Arc, List<Token>> filteredMarkingMap = [:]

    // set the filter
    void initializeUnificationFilter() {

        List<Variable> allVarList = []

        // for each input place
        for (elem in inputs) {
            LPPlace pl = (LPPlace) elem.source

            // for each variable which is not in the filter
            for (var in (pl.expression.getVariables() - commonVarList)) {

                // if it is already in the list of variables, add to the filter
                if (allVarList.contains(var)) {
                    commonVarList << var
                    // otherwise add it to the list of variables
                } else {
                    allVarList << var
                }
            }
        }
    }

    // Operational Semantics

    Boolean isEnabledIncludingEmission() {
        isEnabled()
    }

    Boolean isEnabled() {

        // initialize filter to unify tokens
        if (!commonVarList) {
            initializeUnificationFilter()
        }

        // map to anchor values satisfying the filter
        filteredMarkingMap = [:]

        // the first input sets the initial filters, the other inputs prune it
        Boolean first = true

        // add all possible filters
        List<List<Parameter>> filters = []

        // filter tokens
        for (elem in inputs) {
            LPPlace pl = (LPPlace) elem.source

            List<Variable> localVarList = pl.expression.getVariables()
            List<Parameter> localParamList = pl.expression.getParameters()

            // for each variable which is part of the common variables
            for (var in localVarList - (localVarList - commonVarList)) {

                // find in which position it is placed
                Integer i = localParamList.findIndexOf { (it.variable == var) }

                // for optimization - there are no tokens here
                if (elem.type == ArcType.NORMAL && pl.marking.size() == 0) {
                    return false
                }

                // for each token
                for (token in pl.marking) {

                    // check its value
                    if (!token.expression.parameters[i].literal) {
                        throw new RuntimeException("The token has no concrete value. Error.")
                    }

                    if (first) {
                        token.expression.parameters[i].literal
                    }
                }
            }
        }

        return true
    }

    void fire() {
        consumeInputTokens()
        produceOutputTokens()
    }

    void consumeInputTokens() {
        List<LPToken> tokens = []
        for (elem in inputs) {
            for (int i = 0; i < elem.weight; i++) {
                tokens << ((LPPlace) elem.source).marking.pop()
            }
        }
    }

    void produceOutputTokens() {
        for (elem in outputs) {
            if (elem.type == ArcType.NORMAL) {
                if (elem.weight > 1)
                    throw new RuntimeException("Not yet implemented")

                ((LPPlace) elem.target).marking.push(new LPToken())
            } else if (elem.type == ArcType.RESET) {
                ((LPPlace) elem.target).flush()
            }
        }
    }

}
