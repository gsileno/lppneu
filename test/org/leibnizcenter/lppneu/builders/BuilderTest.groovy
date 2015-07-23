package org.leibnizcenter.lppneu.builders

import groovy.xml.XmlUtil
import org.leibnizcenter.lppneu.components.language.Atom
import org.leibnizcenter.lppneu.components.language.Event
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.ExtLiteral
import org.leibnizcenter.lppneu.components.language.Literal
import org.leibnizcenter.lppneu.components.language.Operation
import org.leibnizcenter.lppneu.components.language.Operator
import org.leibnizcenter.lppneu.components.language.Situation

import org.leibnizcenter.pneu.builders.PN2PNML
import org.leibnizcenter.pneu.components.petrinet.Net
import org.leibnizcenter.pneu.components.petrinet.Transition
import org.leibnizcenter.pneu.builders.PN2dot
import org.leibnizcenter.pneu.parsers.PNML2PN

class BuilderTest extends GroovyTestCase {

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

    void batchExport(Net net, String filename) {

        def folder = new File('examples/out/pnml/')
        if (!folder.exists()) folder.mkdirs()

        String outputFile = "examples/out/pnml/" + filename + ".pnml"

        new File(outputFile).withWriter {
            out -> out.println(XmlUtil.serialize(PN2PNML.buildPNML(net)))
        }
        println("lpetri net exported to " + outputFile)

        folder = new File('examples/out/dot/')
        if (!folder.exists()) folder.mkdirs()

        outputFile = "examples/out/dot/" + filename + ".dot"

        new File(outputFile).withWriter {
            out -> out.println(PN2dot.simpleConversion(net))
        }
        println "lpetri net exported to " + outputFile
    }

//    void testPropositionalTripleBuilder() {
//        LPPN2LPN conversion = new LPPN2LPN()
//        Net net = conversion.buildPropositionalTriple(situationP)
//        PN2PNML.buildPNML(net)
//    }

    void testEvent() {
        LPPN2LPN conversion = new LPPN2LPN()
        Net net = conversion.buildEventNet(eventP)
        batchExport(net, "operation.p")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/operation.p.pnml")
        assert(pnml.transitionList.size() == 1)
        assert(pnml.placeList.size() == 0)
        assert(pnml.arcList.size() == 0)
//        assert(pnml.transitionList[0].position.x == 33)
//        assert(pnml.transitionList[0].position.y == 33)
    }

    void testOperationSEQ() {
        LPPN2LPN conversion = new LPPN2LPN()
        Net net = conversion.buildOperationNet(operationPSEQQ)
        batchExport(net, "operation.pSEQq")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/operation.pSEQq.pnml")
        assert(pnml.transitionList.size() == 4)
        assert(pnml.placeList.size() == 3)
        assert(pnml.arcList.size() == 6)
//        assert(pnml.transitionList[0].position.x == 33)
//        assert(pnml.transitionList[0].position.y == 33)

        Transition t

        t = pnml.transitionList.find() { it.name == operationP.toString() }
//        assert(t.position.x == 165)
//        assert(t.position.y == 33)

        t = pnml.transitionList.find() { it.name == operationQ.toString() }
//        assert(t.position.x == 297)
//        assert(t.position.y == 33)

    }

    void testOperationSEQ2() {
        LPPN2LPN conversion = new LPPN2LPN()
        Net net = conversion.buildOperationNet(operationPSEQNEGPSEQQ)
        batchExport(net, "operation.pSEQNEGpSEQq")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/operation.pSEQNEGpSEQq.pnml")
        assert(pnml.transitionList.size() == 5)
        assert(pnml.placeList.size() == 4)
        assert(pnml.arcList.size() == 8)

    }

    void testOperationPAR() {
        LPPN2LPN conversion = new LPPN2LPN()
        Net net = conversion.buildOperationNet(operationPPARQ)
        batchExport(net, "operation.pPARq")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/operation.pPARq.pnml")
        assert(pnml.transitionList.size() == 4)
        assert(pnml.placeList.size() == 4)
        assert(pnml.arcList.size() == 8)
//        assert(pnml.transitionList[0].position.x == 33)
//        assert(pnml.transitionList[0].position.y == 33)

        Transition t

        t = pnml.transitionList.find() { it.name == operationP.toString() }
//        assert(t.position.x == 165)
//        assert(t.position.y == 33)

        t = pnml.transitionList.find() { it.name == operationQ.toString() }
//        assert(t.position.x == 165)
//        assert(t.position.y == 99)

    }

    void testOperationALT() {
        LPPN2LPN conversion = new LPPN2LPN()
        Net net = conversion.buildOperationNet(operationQALTNEGP)
        batchExport(net, "operation.qALTNEGp")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/operation.qALTNEGp.pnml")
        assert(pnml.transitionList.size() == 4)
        assert(pnml.placeList.size() == 2)
        assert(pnml.arcList.size() == 6)
//        assert(pnml.transitionList[0].position.x == 33)
//        assert(pnml.transitionList[0].position.y == 33)

        Transition t

        t = pnml.transitionList.find() { it.name == operationQ.toString() }
//        assert(t.position.x == 165)
//        assert(t.position.y == 33)

        t = pnml.transitionList.find() { it.name == operationNEGP.toString() }
//        assert(t.position.x == 165)
//        assert(t.position.y == 99)
    }

    void testSituation() {
        LPPN2LPN conversion = new LPPN2LPN()
        Net net = conversion.buildSituationNet(situationP)
        batchExport(net, "situation.p")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/situation.p.pnml")
        assert(pnml.transitionList.size() == 0)
        assert(pnml.placeList.size() == 1)
        assert(pnml.arcList.size() == 0)
        assert(pnml.placeList[0].name == situationP.toString())
//        assert(pnml.placeList[0].position.x == 33)
//        assert(pnml.placeList[0].position.y == 33)
    }

    void testExpressionNEG() {
        LPPN2LPN conversion = new LPPN2LPN()
        Net net = conversion.buildExpressionNet(expressionNEGP)
        batchExport(net, "expression.NEGp")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/expression.NEGp.pnml")
        assert(pnml.transitionList.size() == 0)
        assert(pnml.placeList.size() == 1)
        assert(pnml.arcList.size() == 0)
        assert(pnml.placeList[0].name == situationNEGP.toString())
//        assert(pnml.placeList[0].position.x == 33)
//        assert(pnml.placeList[0].position.y == 33)
    }

    void testExpressionNEGNEG() {
        LPPN2LPN conversion = new LPPN2LPN()

        Net net = conversion.buildExpressionNet(expressionNEGNEGP)

        batchExport(net, "expression.NEGNEGp")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/expression.NEGNEGp.pnml")
        assert(pnml.transitionList.size() == 0)
        assert(pnml.placeList.size() == 1)
        assert(pnml.arcList.size() == 0)
        assert(pnml.placeList[0].name == situationP.toString())
//        assert(pnml.placeList[0].position.x == 33)
//        assert(pnml.placeList[0].position.y == 33)
    }

    void testExpressionAND() {
        LPPN2LPN conversion = new LPPN2LPN()

        Net net = conversion.buildExpressionNet(expressionPANDQ)

        batchExport(net, "expression.pANDq")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/expression.pANDq.pnml")
        assert (pnml.transitionList.size() == 1)
        assert (pnml.placeList.size() == 3)
        assert (pnml.arcList.size() == 5)

//        Place p
//
//        p = pnml.placeList.find() { it.name == situationP.toString() }
//        assert (p.position.x == 33)
//        assert (p.position.y == 33)
//
//        p = pnml.placeList.find() { it.name == situationQ.toString() }
//        assert (p.position.x == 33)
//        assert (p.position.y == 99)
    }

    void testExpressionOR() {
        LPPN2LPN conversion = new LPPN2LPN()

        Net net = conversion.buildExpressionNet(expressionNEGPORNOTR)

        batchExport(net, "expression.NEGpORNOTr")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/expression.NEGpORNOTr.pnml")

        assert (pnml.transitionList.size() == 2)
        assert (pnml.placeList.size() == 3)
        assert (pnml.arcList.size() == 6)

//        Place p
//
//        p = pnml.placeList.find() { it.name == expressionNEGP.toString() }
//        assert (p.position.x == 33)
//        assert (p.position.y == 33)
//
//        p = pnml.placeList.find() { it.name == "NULL(r)" }
//        assert (p.position.x == 33)
//        assert (p.position.y == 99)
    }

    void testExpressionXOR() {
        LPPN2LPN conversion = new LPPN2LPN()

        Net net = conversion.buildExpressionNet(expressionPANDQXORQ)

        batchExport(net, "expression.pANDqXORq")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/expression.pANDqXORq.pnml")

        assert (pnml.transitionList.size() == 3)
        assert (pnml.placeList.size() == 5)
        assert (pnml.arcList.size() == 13)

//        Place p
//
//        p = pnml.placeList.find() { it.name == expressionP.toString() }
//        assert (p.position.x == 33)
//        assert (p.position.y == 33)
//
//        p = pnml.placeList.find() { it.name == expressionPANDQXORQ.toString() }
//        assert (p.position.x == 297)
//        assert (p.position.y == 33)
    }

    void testExpressionSEQ() {
        LPPN2LPN conversion = new LPPN2LPN()

        Net net = conversion.buildExpressionNet(expressionPSEQQ)

        batchExport(net, "expression.pSEQq")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/expression.pSEQq.pnml")

        assert (pnml.transitionList.size() == 4)
        assert (pnml.placeList.size() == 4)
        assert (pnml.arcList.size() == 9)

//        Place p
//
//        p = pnml.placeList.find() { it.name == expressionP.toString() }
//        assert (p.position.x == 99)
//        assert (p.position.y == 33)
//
//        p = pnml.placeList.find() { it.name == expressionPSEQQ.toString() }
//        assert (p.position.x == 231)
//        assert (p.position.y == 33)
    }

    void testExpressionSEQ2() {
        LPPN2LPN conversion = new LPPN2LPN()

        Net net = conversion.buildExpressionNet(expressionQSEQPANDQ)

        batchExport(net, "expression.qSEQpANDq")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/expression.qSEQpANDq.pnml")

        assert (pnml.transitionList.size() == 6)
        assert (pnml.placeList.size() == 6)
        assert (pnml.arcList.size() == 15)

//        Place p
//
//        p = pnml.placeList.find() { it.name == expressionQ.toString() }
//        assert (p.position.x == 231)
//        assert (p.position.y == 33)
//
//        p = pnml.placeList.find() { it.name == expressionQSEQPANDQ.toString() }
//        assert (p.position.x == 363)
//        assert (p.position.y == 33)
    }

    void testExpressionSEQ2b() {
        LPPN2LPN conversion = new LPPN2LPN()

        Net net = conversion.buildExpressionNet(expressionQSEQPPARQ)

        batchExport(net, "expression.qSEQpPARq")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/expression.qSEQpPARq.pnml")

        assert (pnml.transitionList.size() == 8)
        assert (pnml.placeList.size() == 6)
        assert (pnml.arcList.size() == 17)

    }

    void testExpressionSEQ3() {
        LPPN2LPN conversion = new LPPN2LPN()

        Net net = conversion.buildExpressionNet(expressionPSEQNEGPSEQQ)

        batchExport(net, "expression.pSEQNEGpSEQq")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/expression.pSEQNEGpSEQq.pnml")

        assert (pnml.transitionList.size() == 6)
        assert (pnml.placeList.size() == 6)
        assert (pnml.arcList.size() == 14)

//        Place p
//
//        p = pnml.placeList.find() { it.name == expressionP.toString() }
//        assert (p.position.x == 99)
//        assert (p.position.y == 33)

    }


    void testExpressionSEQ4() {
        LPPN2LPN conversion = new LPPN2LPN()

        Net net = conversion.buildExpressionNet(expressionPANDQSEQQANDR)

        batchExport(net, "expression.pANDqSEQqANDr")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/expression.pANDqSEQqANDr.pnml")

        assert (pnml.transitionList.size() == 8)
        assert (pnml.placeList.size() == 8)
        assert (pnml.arcList.size() == 21)
    }

    void testExpressionPAR() {
        LPPN2LPN conversion = new LPPN2LPN()

        Net net = conversion.buildExpressionNet(expressionPPARQ)

        batchExport(net, "expression.pPARq")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/expression.pPARq.pnml")

        assert (pnml.transitionList.size() == 3)
        assert (pnml.placeList.size() == 3)
        assert (pnml.arcList.size() == 7)

//        Place p
//
//        p = pnml.placeList.find() { it.name == expressionP.toString() }
//        assert (p.position.x == 99)
//        assert (p.position.y == 33)
//
//        p = pnml.placeList.find() { it.name == expressionPPARQ.toString() }
//        assert (p.position.x == 231)
//        assert (p.position.y == 33)

    }

    void testExpressionALT() {
        LPPN2LPN conversion = new LPPN2LPN()

        Net net = conversion.buildExpressionNet(expressionQALTNEGP)

        batchExport(net, "expression.qALTNEGp")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/expression.qALTNEGp.pnml")

        assert (pnml.transitionList.size() == 4)
        assert (pnml.placeList.size() == 3)
        assert (pnml.arcList.size() == 10)

//        Place p
//
//        p = pnml.placeList.find() { it.name == expressionQ.toString() }
//        assert (p.position.x == 99)
//        assert (p.position.y == 33)
//
//        p = pnml.placeList.find() { it.name == expressionNEGP.toString() }
//        assert (p.position.x == 99)
//        assert (p.position.y == 99)
//
//        p = pnml.placeList.find() { it.name == expressionQALTNEGP.toString() }
//        assert (p.position.x == 231)
//        assert (p.position.y == 33)
    }

    void testExpressionALT2() {
        LPPN2LPN conversion = new LPPN2LPN()

        Net net = conversion.buildExpressionNet(expressionQALTNEGPSEQQ)

        batchExport(net, "expression.qALTNEGpSEQq")

        Net pnml = PNML2PN.parseFile("examples/out/pnml/expression.qALTNEGpSEQq.pnml")

        assert (pnml.transitionList.size() == 9)
        assert (pnml.placeList.size() == 6)
        assert (pnml.arcList.size() == 20)

    }


}
