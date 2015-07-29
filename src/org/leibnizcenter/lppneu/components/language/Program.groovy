package org.leibnizcenter.lppneu.components.language

import commons.base.Formula
import groovy.util.logging.Log4j

@Log4j
class Program {

    List<String> parsingErrors = []
    List<LogicRule> logicRules = []
    List<CausalRule> causalRules = []

    Map<Expression, Expression> reducedExpressionMap = [:]

    Program() {}

    void print() {

        for (rule in logicRules) {
            println rule
        }

        for (rule in causalRules) {
            println rule
        }

    }

    List<Parameter> getParameters(Formula<Situation> formula) {

        List<Parameter> parameters = []

        if (formula.isCompound()) {
            for (inputFormula in formula.inputFormulas) {
                parameters += getParameters(inputFormula) - parameters
            }
        } else {
            for (inputPort in formula.inputPorts) {

                // neglect propositions
                if (inputPort.parameters != null) {
                  parameters += inputPort.parameters - parameters
                }
            }
        }

        parameters
    }

    Atom newLogicPredicate() {
        return Atom.build("_e"+reducedExpressionMap.size())
    }

    Expression reduceExpression(Formula<Situation> formula) {

        List<Expression> inputReducedExpressions = []
        Expression expression, reducedExpression

        // Formula is compound
        if (formula.isCompound()) {
            // first reduce all the input formulas
            for (input in formula.inputFormulas) {
                inputReducedExpressions << reduceExpression(input)
            }

            // combine the reduction into a new expression
            expression = Expression.buildFromExpressions(inputReducedExpressions, formula.operator)

            // check if this compound expression has been already recorded
            for (coupling in reducedExpressionMap) {
                log.trace(coupling.key.toString() + ": " + coupling.value.toString() + " ?= " + expression.toString() + " ")

                if (coupling.value == expression) {
                    return coupling.key
                }
            }

            // otherwise this compound expression is new, record it

            // construct a new reduced expression out of it
            // 1. take all relevant parameters
            List<Parameter> parameters = getParameters(formula)
            // 2. create a new functor
            Atom functor = newLogicPredicate()
            // 3. create such reduced expression
            reducedExpression = Expression.build(Literal.build(functor, parameters))

            log.trace("reduced expression: "+reducedExpression+" (for "+expression+")")

            // 4. associate compound with reduced expression
            reducedExpressionMap.put(reducedExpression, expression)

            return reducedExpression

        // Formula is simple
        } else {
            return Expression.buildFromSituations(formula.inputPorts, formula.operator)
        }

    }

    EventConditionExpression reduceEventConditionExpression(Formula<EventCondition> formula) {

        List<EventConditionExpression> inputReducedExpressions = []

        log.trace("reduce event condition expression: "+formula.toString())

        if (formula.isCompound()) {
            log.trace("formula is compound (${formula.dump()})")
            for (input in formula.inputFormulas) {
                inputReducedExpressions << reduceEventConditionExpression(input)
            }
        } else {
            log.trace("formula is simple (${formula.dump()})")
            for (input in formula.inputPorts) {
                inputReducedExpressions << EventConditionExpression.build(input.event, reduceExpression(input.condition.formula))
            }
        }

        log.trace("reduced event condition expressions: "+inputReducedExpressions+" (for ${formula.toString()})")
        return EventConditionExpression.buildFromExpressions(inputReducedExpressions, formula.operator)
    }

    Program reduce() {

        Program reducedProgram = new Program()

        for (logicRule in logicRules) {

            if (logicRule.isRule()) {
                LogicRule reducedLogicRule = logicRule.clone()
                reducedLogicRule.head = reducedProgram.reduceExpression(logicRule.head.formula)
                reducedLogicRule.body = reducedProgram.reduceExpression(logicRule.body.formula)
                reducedProgram.logicRules << reducedLogicRule
            } else {
                reducedProgram.logicRules << logicRule
            }
        }

        for (causalRule in causalRules) {
            if (causalRule.isMechanism()) {
                CausalRule reducedCausalRule = causalRule.clone()
                reducedCausalRule.trigger = reducedProgram.reduceEventConditionExpression(causalRule.trigger.formula)
                reducedCausalRule.action = causalRule.action
                reducedProgram.causalRules << reducedCausalRule
            } else {
                reducedProgram.causalRules << causalRule
            }
        }

        // introduce definitions as logic rules
        for (coupling in reducedProgram.reducedExpressionMap) {
            reducedProgram.logicRules << new LogicRule(head: coupling.key, body: coupling.value)
            reducedProgram.logicRules << new LogicRule(head: coupling.value, body: coupling.key)
        }

        reducedProgram

    }

}
