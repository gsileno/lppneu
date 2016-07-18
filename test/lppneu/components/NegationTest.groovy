package lppneu.components

import lppneu.components.language.Atom
import lppneu.components.language.Event
import lppneu.components.language.Expression
import lppneu.components.language.Literal
import lppneu.components.language.PosLiteral
import lppneu.components.language.Operation
import lppneu.components.language.Operator
import lppneu.components.language.Situation

class NegationTest extends GroovyTestCase {

    PosLiteral literalP = PosLiteral.build(Atom.build("p"))
    PosLiteral literalQ = PosLiteral.build(Atom.build("q"))
    PosLiteral literalR = PosLiteral.build(Atom.build("r"))
    Literal extLiteralNEGP = Literal.buildNegation(Literal.build(literalP))
    Literal extLiteralNOTR = Literal.buildNull(Literal.build(literalR))

    Event eventP = Event.build(literalP)
    Event eventQ = Event.build(literalQ)
    Event eventR = Event.build(literalR)
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
    Expression expressionNEGNEGP = Expression.build(expressionNEGP, Operator.NEG)
    Expression expressionPANDQ = Expression.buildFromExpressions([expressionP, expressionQ], Operator.AND)
    Expression expressionQANDR = Expression.buildFromExpressions([expressionQ, expressionR], Operator.AND)
    Expression expressionNEGPORNOTR = Expression.buildFromSituations([situationNEGP, situationNOTR], Operator.OR)
    Expression expressionPANDQXORQ = Expression.buildFromExpressions([expressionPANDQ, expressionQ], Operator.XOR)
    Expression expressionPSEQQ = Expression.buildFromExpressions([expressionP, expressionQ], Operator.SEQ)
    Expression expressionPPARQ = Expression.buildFromSituations([situationP, situationQ], Operator.PAR)
    Expression expressionPANDQSEQQANDR = Expression.buildFromExpressions([expressionPANDQ, expressionQANDR], Operator.SEQ)
    Expression expressionQSEQPANDQ = Expression.buildFromExpressions([expressionQ, expressionPANDQ], Operator.SEQ)
    Expression expressionQSEQPPARQ = Expression.buildFromExpressions([expressionQ, expressionPPARQ], Operator.SEQ)
    Expression expressionQALTNEGP = Expression.buildFromExpressions([expressionQ, expressionNEGP], Operator.ALT)
    Expression expressionPSEQNEGPSEQQ = Expression.buildFromExpressions([expressionP, expressionNEGP, expressionQ], Operator.SEQ)
    Expression expressionQALTNEGPSEQQ = Expression.buildFromExpressions([expressionQALTNEGP, expressionQ], Operator.SEQ)
    Expression expressionComplex = Expression.buildFromExpressions(
            [ expressionR,
              Expression.buildFromExpressions(
                      [ Expression.buildFromExpressions([expressionQ, expressionPANDQ], Operator.SEQ),
                        Expression.buildFromExpressions([
                                Expression.buildFromExpressions([expressionPANDQ, expressionR], Operator.OR),
                                expressionR ], Operator.PAR)
                      ], Operator.OR)
            ], Operator.AND)

    Operation operationNEGP = Operation.build(extLiteralNEGP)
    Operation operationP = Operation.build(eventP)
    Operation operationQ = Operation.build(eventQ)
    Operation operationNEGNEGP = Operation.build(operationNEGP, Operator.NEG)
    Operation operationPANDQ = Operation.buildFromOperations([operationP, operationQ], Operator.AND)
    Operation operationNEGPORNOTR = Operation.buildFromEvents([eventNEGP, eventNOTR], Operator.OR)
    Operation operationPANDQXORQ = Operation.buildFromOperations([operationPANDQ, operationQ], Operator.XOR)
    Operation operationPSEQQ = Operation.buildFromOperations([operationP, operationQ], Operator.SEQ)
    Operation operationQSEQPANDQ = Operation.buildFromOperations([operationQ, operationPANDQ], Operator.SEQ)
    Operation operationPPARQ = Operation.buildFromEvents([eventP, eventQ], Operator.PAR)
    Operation operationQALTNEGP = Operation.buildFromOperations([operationQ, operationNEGP], Operator.ALT)

    Operation operationPSEQNEGPSEQQ = Operation.buildFromOperations([operationP, operationNEGP, operationQ], Operator.SEQ)

    void testNegation() {

        assert (expressionP.negate() == expressionNEGP)
        assert (expressionPANDQ.negate() == Expression.build(expressionPANDQ, Operator.NEG))
        assert (expressionR.nullify() == Expression.build(situationNOTR))
        assert (expressionPSEQQ.negate() == Expression.build(expressionPSEQQ, Operator.NEG))
        assert (operationPSEQQ.toExpression().negate() == Expression.build(expressionPSEQQ, Operator.NEG))

    }

}
