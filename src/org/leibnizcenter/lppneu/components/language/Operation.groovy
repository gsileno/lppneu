package org.leibnizcenter.lppneu.components.language

import commons.base.Formula
import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.parser.LPPNLoader

/**
 * Represents an expression of actions
 */
@Log4j @EqualsAndHashCode
class Operation {

    Formula<Event> formula

    // a not elegant way to solve problem with non-static types of Formula
    private static Formula<Event> PROTOTYPE = new Formula<>()

    static Operation build(Literal literal) {
        new Operation(
                formula: PROTOTYPE.build(Event.build(literal))
        )
    }

    static Operation build(ExtLiteral extLiteral) {
        new Operation(
                formula: PROTOTYPE.build(Event.build(extLiteral))
        )
    }

    static Operation buildFromSituation(Situation situation) {
        new Operation(
                formula: PROTOTYPE.build(situation.toEvent())
        )
    }

    static Operation buildFromSituationFormula(Formula<Situation> formula) {
        new Operation(
                formula: formula
        )
    }

    static Operation build(Event event) {
        new Operation(
                formula: PROTOTYPE.build(event)
        )
    }

    // for special cases: NULL (THIS | INSTANCE), NEG (THIS | INSTANCE)
    static Operation build(Operator op) {
        new Operation(
                formula: PROTOTYPE.build(op)
        )
    }

    static Operation build(Formula<Event> formula) {
        new Operation(
                formula: formula
        )
    }

    static Operation build(Operation operation, Operator op) {
        new Operation(
                formula: PROTOTYPE.build(operation.formula, op)
        )
    }

    static Operation build(Operation left, Operation right, Operator op) {

        new Operation(
                formula: PROTOTYPE.build(left.formula, right.formula, op)
        )
    }

    static Operation buildFromOperations(List<Operation> operations, Operator op) {
        List<Formula> formulas = []
        for (operation in operations) {
            formulas << operation.formula
        }
        new Operation(
                formula: PROTOTYPE.buildFromFormulas(formulas, op)
        )
    }

    static Operation buildFromEvents(List<Event> events, Operator op) {
        List<Formula> formulas = []
        for (event in events) {
            formulas << PROTOTYPE.build(event)
        }
        new Operation (
                formula: PROTOTYPE.buildFromFormulas(formulas, op)
        )
    }

    // get all the variables in the operation
    List<Variable> getVariables() {
        List<Variable> varList = []
        for (event in formula.inputPorts) {
            List<Variable> localVarList = event.getVariables()
            varList = (varList - localVarList) + localVarList
        }
        varList
    }

    Expression toExpression() {

        List<Expression> inputExpressions = []

        if (!formula.isAtomic()) {
            for (input in formula.inputFormulas) {
                inputExpressions << build(input).toExpression()
            }
        } else {
            for (input in formula.inputPorts) {
                inputExpressions << Expression.build(input.toSituation())
            }
        }

        Expression.buildFromExpressions(inputExpressions, formula.operator)

    }

    // create an expression from string
    static Operation parse(String code) {
        // first create the expression and then transform it in a operation
        return Expression.parse(code).toOperation()
    }

    String toString() {
        formula.toString()
    }

}
