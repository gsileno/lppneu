package org.leibnizcenter.lppneu.parser

import org.leibnizcenter.lppneu.components.language.Program

class DecoratorTest extends GroovyTestCase {

    void testDecorationCommitment1() {

        Program program = LPPNLoader.parseFile("examples/basic/crywolf.lppn")

        program.print()

    }

}