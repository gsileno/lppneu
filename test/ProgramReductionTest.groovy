

import org.leibnizcenter.lppneu.components.language.*
import org.leibnizcenter.lppneu.parser.LPPNLoader

class ProgramReductionTest extends GroovyTestCase {

    Literal literalP = Literal.build(Atom.build("p"))
    Literal literalQ = Literal.build(Atom.build("q"))
    Literal literalR = Literal.build(Atom.build("r"))

    Situation situationP = Situation.build(literalP)
    Situation situationQ = Situation.build(literalQ)
    Situation situationR = Situation.build(literalR)

    Expression expressionP = Expression.build(situationP)
    Expression expressionQ = Expression.build(situationQ)
    Expression expressionR = Expression.build(situationR)
    Expression expressionPANDQ = Expression.buildFromExpressions([expressionP, expressionQ], Operator.AND)
    Expression expressionPANDQXORQ = Expression.buildFromExpressions([expressionPANDQ, expressionQ], Operator.XOR)
    Expression complexExpression = Expression.buildFromExpressions(
            [ expressionR,
              Expression.buildFromExpressions(
                 [ Expression.buildFromExpressions([expressionQ, expressionPANDQ], Operator.SEQ),
                   Expression.buildFromExpressions([
                           Expression.buildFromExpressions([expressionPANDQ, expressionR], Operator.OR),
                           expressionR ], Operator.PAR)
                 ], Operator.OR)
            ], Operator.AND)

    void testReduceComplexExpression() {
        Program program = new Program()
        program.reduceExpression(complexExpression.formula)
        assert program.reducedExpressionMap.size() == 5
    }

    void testReduceFact() {
        Program program = LPPNLoader.parseString("a or b.")
        assert program.logicRules.size() == 1

        Program reducedProgram = program.reduce()
        assert reducedProgram.logicRules.size() == 1
    }

    void testReduceCompoundFact() {
        Program program = LPPNLoader.parseString("a or (c and b).")
        assert program.logicRules.size() == 1

        Program reducedProgram = program.reduce()
        assert reducedProgram.logicRules.size() == 3
    }

    void testReduceCompoundFact2() {
        Program program = LPPNLoader.parseString("a and (a and b).")
        assert program.logicRules.size() == 1

        Program reducedProgram = program.reduce()
        assert reducedProgram.logicRules.size() == 3
    }

    void testReduceProcessFact() {
        Program program = LPPNLoader.parseString("a seq b.")
        assert program.logicRules.size() == 1

        Program reducedProgram = program.reduce()
        assert reducedProgram.logicRules.size() == 1
    }

    void testReduceCompoundProcessFact() {
        Program program = LPPNLoader.parseString("a seq (b par c).")
        assert program.logicRules.size() == 1

        Program reducedProgram = program.reduce()
        assert reducedProgram.logicRules.size() == 3
    }

    void testReduceCompoundProcessFact2() {
        Program program = LPPNLoader.parseString("a seq (a seq c).")
        assert program.logicRules.size() == 1

        Program reducedProgram = program.reduce()
        assert reducedProgram.logicRules.size() == 3
    }

    void testReduceLogicRule() {
        Program program = new Program()
        program.logicRules << new LogicRule(head: expressionPANDQ, body: expressionPANDQXORQ)

        assert program.logicRules.size() == 1
        Program reducedProgram = program.reduce()
        assert reducedProgram.logicRules.size() == 3
    }

    void testReduceLogicRule2() {
        Program program = LPPNLoader.parseString("(a and b) seq ((a and b) and (a or b)) :- p.")
        assert program.logicRules.size() == 1

        Program reducedProgram = program.reduce()
        assert reducedProgram.logicRules.size() == 7
    }

    void testReduceCausalRule() {
        Program program = LPPNLoader.parseString("a and b -> p.")
        assert program.logicRules.size() == 0
        assert program.causalRules.size() == 1

        Program reducedProgram = program.reduce()
        assert reducedProgram.logicRules.size() == 0
        assert reducedProgram.causalRules.size() == 1
    }

    void testReduceCausalRule2() {
        Program program = LPPNLoader.parseString("(a and b) seq ((a and b) and (a or b)) -> p.")
        assert program.logicRules.size() == 0
        assert program.causalRules.size() == 1

        Program reducedProgram = program.reduce()
        assert reducedProgram.logicRules.size() == 6
        assert reducedProgram.causalRules.size() == 1
    }

    void testReduceYaleShooting() {
        Program program = LPPNLoader.parseFile("examples/basic/yaleshooting.lppn")
        assert program.logicRules.size() == 2
        assert program.causalRules.size() == 3

        Program reducedProgram = program.reduce()
        assert reducedProgram.logicRules.size() == 6
        assert reducedProgram.causalRules.size() == 3
    }

}
