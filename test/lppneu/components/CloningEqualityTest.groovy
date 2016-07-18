package lppneu.components

import lppneu.components.language.Atom
import lppneu.components.language.Expression
import lppneu.components.language.PosLiteral
import lppneu.components.language.Parameter
import lppneu.components.language.Variable

class CloningEqualityTest extends GroovyTestCase {

    void testExpressionEquality() {
        assert Expression.build(PosLiteral.build(Atom.build("dog"), [Parameter.build(Variable.build("Dog"))])) ==
               Expression.build(PosLiteral.build(Atom.build("dog"), [Parameter.build(Variable.build("Dog"))]))
    }

    void testExpressionCloning() {
        Expression exp = Expression.build(PosLiteral.build(Atom.build("dog"), [Parameter.build(Variable.build("Dog"))]))
        Expression clone = exp.minimalClone()
        assert (exp == clone)
    }

    void testLiteralEquality() {
        PosLiteral elem1 = PosLiteral.build(Atom.build("dog"), [Parameter.build(Variable.build("Dog"))])
        PosLiteral elem2 = PosLiteral.build(Atom.build("dog"), [Parameter.build(Variable.build("Dog"))])
        assert (elem1 == elem2)
    }

    void testLiteralCloning() {
        PosLiteral elem = PosLiteral.build(Atom.build("dog"), [Parameter.build(Variable.build("Dog"))])
        PosLiteral clone = elem.minimalClone()
        assert (elem == clone)
    }

    void testParameterEquality() {
        Parameter elem1 = Parameter.build(Variable.build("Dog"))
        Parameter elem2 = Parameter.build(Variable.build("Dog"))
        assert (elem1 == elem2)
    }

    void testParameterCloning() {
        Parameter elem = Parameter.build(Variable.build("Dog"))
        Parameter clone = elem.minimalClone()
        assert (elem == clone)
    }

    void testVariableEquality() {
        Variable elem1 = Variable.build("Dog")
        Variable elem2 = Variable.build("Dog")
        assert (elem1 == elem2)
    }

    void testVariableCloning() {
        Variable elem = Variable.build("Dog")
        Variable clone = elem.minimalClone()
        assert (elem == clone)
    }
}
