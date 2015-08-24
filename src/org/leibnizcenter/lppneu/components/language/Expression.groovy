package org.leibnizcenter.lppneu.components.language

import commons.base.Formula
import groovy.transform.EqualsAndHashCode
import org.leibnizcenter.lppneu.parsers.LPPNLoader

@EqualsAndHashCode
class Expression {

    Formula<Situation> formula

    // a not elegant way to solve problem with non-static types of Formula
    private static Formula<Situation> PROTOTYPE = new Formula<>()

    static Expression build(Literal literal) {
        new Expression(
                formula: PROTOTYPE.build(Situation.build(literal))
        )
    }

    static Expression build(ExtLiteral extLiteral) {
        new Expression(
                formula: PROTOTYPE.build(Situation.build(extLiteral))
        )
    }

    static Expression build(Situation situation) {
        new Expression(
                formula: PROTOTYPE.build(situation)
        )
    }

    static Expression build(Formula<Situation> formula) {
        new Expression(
                formula: formula
        )
    }

    static Expression build(Formula<Situation> formula, Operator op) {
        new Expression(
                formula: PROTOTYPE.build(formula, op)
        )
    }

    static Expression build(Expression expression, Operator op) {
        new Expression(
                formula: PROTOTYPE.build(expression.formula, op)
        )
    }

    static Expression build(Expression left, Expression right, Operator op) {
        new Expression(
                formula: PROTOTYPE.build(left.formula, right.formula, op)
        )
    }

    static Expression buildFromExpressions(List<Expression> expressions, Operator op) {
        List<Formula> formulas = []
        for (expression in expressions) {
            formulas << expression.formula
        }
        new Expression(
                formula: PROTOTYPE.buildFromFormulas(formulas, op)
        )
    }

    static Expression buildFromSituations(List<Situation> situations, Operator op) {
        List<Formula> formulas = []
        for (situation in situations) {
            formulas << PROTOTYPE.build(situation)
        }
        new Expression(
                formula: PROTOTYPE.buildFromFormulas(formulas, op)
        )
    }

    // for LPPN, bridge places
    static Expression buildBridgeFromVarStringList(List<String> varStringList) {
        List<Parameter> parameters = []
        for (varString in varStringList) {
            parameters << Parameter.build(Variable.build(varString))
        }

        new Expression(
                formula: PROTOTYPE.build(Situation.build(Literal.build(Atom.build("bridge"), parameters)))
        )
    }

    Polarity polarity() {
        if (formula.operator == Operator.NEG)
            return Polarity.NEG
        else if (formula.operator == Operator.NULL)
            return Polarity.NULL
        else
            return Polarity.POS
    }

    Expression negate() {
        return build(this, Operator.NEG)
    }

    Expression nullify() {
        return build(this, Operator.NULL)
    }

    // to obtain the positive content we can take the internal part of the proposition
    Expression positive() {
        if (!isPositive()) {
            if (!this.formula.isAtomic())
                return build(this.formula.inputFormulas[0])
            else
                return build(this.formula.inputPorts[0].rootLiteral)
        } else {
            return build(this.formula)
        }
    }

    // to obtain the negated content we can negate the internal part
    Expression negative() {
        return this.positive().negate()
    }

    Expression minimalClone() {
        new Expression(formula: formula.minimalClone())
    }

    Boolean isPositive() {
        !isNegative() && !isNull()
    }

    Boolean isNegative() {
        formula.operator == Operator.NEG ||
                (formula.inputPorts.size() == 1 && formula.inputPorts[0].polarity == Polarity.NEG)
    }

    Boolean isNull() {
        formula.operator == Operator.NULL ||
                (formula.inputPorts.size() == 1 && formula.inputPorts[0].polarity == Polarity.NULL)
    }

    String toString() {
        formula.toString()
    }

    // get all the variables in the expression
    List<Variable> getVariables() {
        List<Variable> varList = []
        for (situation in formula.inputPorts) {
            List<Variable> localVarList = situation.getVariables()
            varList = (varList - localVarList) + localVarList
        }
        varList
    }

    // get all the parameters in the expression
    List<Parameter> getParameters() {
        List<Parameter> paramList = []
        for (situation in formula.inputPorts) {
            List<Parameter> localParamList = situation.getParameters()
            paramList = (paramList - localParamList) + localParamList
        }
        paramList
    }

    // transform the expression in operation (TODO: check for AND, etc.)
    Operation toOperation() {
        List<Operation> inputOperations = []

        if (!formula.isAtomic()) {
            for (input in formula.inputFormulas) {
                inputOperations += build(input).toOperation()
            }
        } else {
            for (input in formula.inputPorts) {
                inputOperations += Operation.build(input.toEvent())
            }
        }

        Operation.buildFromOperations(inputOperations, formula.operator)

    }

    // create an expression from string
    static Expression parse(String code) {

        // I force the expression as a fact, and then parse it as a program
        LPPNProgram program = LPPNLoader.parseString(code + ".")

        if (program.parsingErrors.size() > 0)
            throw new RuntimeException("Parsing errors: " + program.parsingErrors)

        if (program.logicRules.size() > 1 || program.logicRules.size() == 0 || program.causalRules.size() != 0 || program.logicRules[0].body != null)
            throw new RuntimeException("Invalid input: only simple facts expected.")

        return program.logicRules[0].head
    }
}
