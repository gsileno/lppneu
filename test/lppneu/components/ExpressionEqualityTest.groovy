package lppneu.components

import lppneu.components.language.*

class ExpressionEqualityTest extends GroovyTestCase {

    PosLiteral posLiteralP = PosLiteral.build(Atom.build("p"))
    PosLiteral posLiteralQ = PosLiteral.build(Atom.build("q"))
    PosLiteral posLiteralR = PosLiteral.build(Atom.build("r"))
    Literal literalNEGP = Literal.buildNegation(Literal.build(posLiteralP))
    Literal literalNULLR = Literal.buildNull(Literal.build(posLiteralR))

    Event eventP = Event.build(posLiteralP)
    Event eventQ = Event.build(posLiteralQ)
    Event eventR = Event.build(posLiteralR)
    Event eventNEGP = Event.build(literalNEGP)
    Event eventNULLR = Event.build(literalNULLR)

    Situation situationP = Situation.build(posLiteralP)
    Situation situationQ = Situation.build(posLiteralQ)
    Situation situationR = Situation.build(posLiteralR)
    Situation situationNEGP = Situation.build(literalNEGP)
    Situation situationNULLR = Situation.build(literalNULLR)

    Expression expressionNEGP = Expression.build(literalNEGP)
    Expression expressionP = Expression.build(situationP)
    Expression expressionQ = Expression.build(situationQ)
    Expression expressionR = Expression.build(situationR)
    Expression expressionNEGNEGP = Expression.build(expressionNEGP, Operator.NEG)
    Expression expressionPANDQ = Expression.buildFromExpressions([expressionP, expressionQ], Operator.AND)
    Expression expressionQANDR = Expression.buildFromExpressions([expressionQ, expressionR], Operator.AND)
    Expression expressionNEGPORNULLR = Expression.buildFromSituations([situationNEGP, situationNULLR], Operator.OR)
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

    Operation operationNEGP = Operation.build(literalNEGP)
    Operation operationP = Operation.build(eventP)
    Operation operationQ = Operation.build(eventQ)
    Operation operationNEGNEGP = Operation.build(operationNEGP, Operator.NEG)
    Operation operationPANDQ = Operation.buildFromOperations([operationP, operationQ], Operator.AND)
    Operation operationNEGPORNULLR = Operation.buildFromEvents([eventNEGP, eventNULLR], Operator.OR)
    Operation operationPANDQXORQ = Operation.buildFromOperations([operationPANDQ, operationQ], Operator.XOR)
    Operation operationPSEQQ = Operation.buildFromOperations([operationP, operationQ], Operator.SEQ)
    Operation operationQSEQPANDQ = Operation.buildFromOperations([operationQ, operationPANDQ], Operator.SEQ)
    Operation operationPPARQ = Operation.buildFromEvents([eventP, eventQ], Operator.PAR)
    Operation operationQALTNEGP = Operation.buildFromOperations([operationQ, operationNEGP], Operator.ALT)

    Operation operationPSEQNEGPSEQQ = Operation.buildFromOperations([operationP, operationNEGP, operationQ], Operator.SEQ)


    void testEquality() {

        assert (expressionP == Expression.build(posLiteralP))
        assert (expressionP != Expression.build(literalNEGP))

        assert (expressionP.formula.inputPorts[0].factLiteral.functor.name != expressionQ.formula.inputPorts[0].factLiteral.functor.name)
        assert (expressionP.formula.inputPorts[0].factLiteral.functor != expressionQ.formula.inputPorts[0].factLiteral.functor)
        assert (expressionP.formula.inputPorts[0].factLiteral != expressionQ.formula.inputPorts[0].factLiteral)
        assert (expressionP.formula.inputPorts[0] != expressionQ.formula.inputPorts[0])
        assert (expressionP.formula != expressionQ.formula)
        assert (expressionP != expressionQ)

        assert (expressionP.formula == Expression.build(posLiteralP).formula)
        assert (expressionP.formula != Expression.build(literalNEGP).formula)

        assert (posLiteralP == Literal.build(PosLiteral.build(Atom.build("p")), Polarity.POS).literal)
        assert (literalNEGP == Literal.build(posLiteralP, Polarity.NEG))
        assert (posLiteralP != Expression.build(literalNEGP))

    }

    Map<Expression, Expression> testMap = [:]

    void testHashmap() {

        testMap.put(expressionP, expressionP)
        testMap.put(expressionQ, expressionQ)
        testMap.put(expressionNEGP, expressionP)
        testMap.put(expressionP, expressionNEGP)
        testMap.put(Expression.build(posLiteralP), expressionP)

        assert (testMap.size() == 3)
    }

}
