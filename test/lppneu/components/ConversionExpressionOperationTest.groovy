package lppneu.components

import lppneu.components.language.*

class ConversionExpressionOperationTest extends GroovyTestCase {

    PosLiteral literalP = PosLiteral.build(Atom.build("p"))
    PosLiteral literalQ = PosLiteral.build(Atom.build("q"))
    PosLiteral literalR = PosLiteral.build(Atom.build("r"))
    Literal extLiteralNEGP = Literal.buildNegation(Literal.build(literalP))
    Literal extLiteralNOTR = Literal.buildNull(Literal.build(literalR))

    Event eventP = Event.build(literalP)
    Event eventQ = Event.build(literalQ)
    Event eventNEGP = Event.build(extLiteralNEGP)
    Event eventNOTR = Event.build(extLiteralNOTR)

    Situation situationP = Situation.build(literalP)
    Situation situationQ = Situation.build(literalQ)
    Situation situationR = Situation.build(literalR)
    Situation situationNEGP = Situation.build(extLiteralNEGP)
    Situation situationNOTR = Situation.build(extLiteralNOTR)

    Expression expressionNEGP = Expression.build(extLiteralNEGP)
    Expression expressionP = Expression.build(situationP)
    Expression expressionQ = Expression.build(situationQ)
    Expression expressionR = Expression.build(situationR)
    Expression expressionPANDQ = Expression.buildFromExpressions([expressionP, expressionQ], Operator.AND)
    Expression expressionQANDR = Expression.buildFromExpressions([expressionQ, expressionR], Operator.AND)
    Expression expressionPPARQ = Expression.buildFromSituations([situationP, situationQ], Operator.PAR)
    Expression expressionQALTNEGP = Expression.buildFromExpressions([expressionQ, expressionNEGP], Operator.ALT)

    Operation operationNEGP = Operation.build(extLiteralNEGP)
    Operation operationP = Operation.build(eventP)
    Operation operationQ = Operation.build(eventQ)
    Operation operationPANDQ = Operation.buildFromOperations([operationP, operationQ], Operator.AND)
    Operation operationPPARQ = Operation.buildFromEvents([eventP, eventQ], Operator.PAR)

    void testConversion() {
        assert operationP.toExpression() == expressionP
        assert expressionP.toOperation() == operationP
        assert operationPPARQ.toExpression() == expressionPPARQ
        assert expressionPPARQ.toOperation() == operationPPARQ
        assert expressionPANDQ.toOperation() == operationPANDQ
    }
}
