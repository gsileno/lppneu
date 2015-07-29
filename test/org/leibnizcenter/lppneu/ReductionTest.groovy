package org.leibnizcenter.lppneu

import org.leibnizcenter.lppneu.components.language.*
import org.leibnizcenter.lppneu.parser.LPPNLoader

class ReductionTest extends GroovyTestCase {

    Literal literalP = Literal.build(Atom.build("p"))
    Literal literalQ = Literal.build(Atom.build("q"))
    Literal literalR = Literal.build(Atom.build("r"))
    ExtLiteral extLiteralNEGP = ExtLiteral.buildNegation(ExtLiteral.build(literalP))
    ExtLiteral extLiteralNOTR = ExtLiteral.buildNull(ExtLiteral.build(literalR))

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
    Expression complexExpression = Expression.buildFromExpressions(
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

//    void testReduceComplexExpression() {
//        Program program = new Program()
//        program.reduceExpression(complexExpression.formula)
//        assert program.reducedExpressionMap.size() == 6
//    }
//
//    void testReduceProgramLogicRules() {
//        Program program = new Program()
//        program.logicRules << new LogicRule(head: expressionPANDQ, body: expressionPANDQXORQ)
//
//        assert program.logicRules.size() == 1
//        Program reducedProgram = program.reduce()
//        assert reducedProgram.logicRules.size() == 5
//    }

    void testReduceYaleShooting() {
        Program program = LPPNLoader.parseFile("examples/basic/yaleshooting.lppn")
        // program.print()

        Program reducedProgram = program.reduce()
        reducedProgram.print()
    }
}
