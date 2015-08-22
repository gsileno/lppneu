package org.leibnizcenter.lppneu.components

import org.leibnizcenter.lppneu.builders.LPPN2LPN
import org.leibnizcenter.lppneu.components.language.Atom
import org.leibnizcenter.lppneu.components.language.Expression
import org.leibnizcenter.lppneu.components.language.LPPNProgram
import org.leibnizcenter.lppneu.components.language.Literal
import org.leibnizcenter.lppneu.components.language.Parameter
import org.leibnizcenter.lppneu.components.language.Variable
import org.leibnizcenter.lppneu.parsers.LPPNLoader
import org.leibnizcenter.pneu.components.petrinet.Net

class CloningEqualityTest extends GroovyTestCase {

    void testExpressionEquality() {
        assert Expression.build(Literal.build(Atom.build("dog"), [Parameter.build(Variable.build("Dog"))])) ==
               Expression.build(Literal.build(Atom.build("dog"), [Parameter.build(Variable.build("Dog"))]))
    }

    void testExpressionCloning() {
        Expression exp = Expression.build(Literal.build(Atom.build("dog"), [Parameter.build(Variable.build("Dog"))]))
        Expression clone = exp.minimalClone()
        assert (exp == clone)
    }

    void testLiteralEquality() {
        Literal elem1 = Literal.build(Atom.build("dog"), [Parameter.build(Variable.build("Dog"))])
        Literal elem2 = Literal.build(Atom.build("dog"), [Parameter.build(Variable.build("Dog"))])
        assert (elem1 == elem2)
    }

    void testLiteralCloning() {
        Literal elem = Literal.build(Atom.build("dog"), [Parameter.build(Variable.build("Dog"))])
        Literal clone = elem.minimalClone()
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
