package org.leibnizcenter.lppneu.components.language

import commons.base.Base

class Program {

    List<String> parsingErrors = []
    List<LogicRule> logicRules = []
    List<CausalRule> causalRules = []

    Program() {}

    void print() {

        for (rule in logicRules) {
            println rule
        }

        for (rule in causalRules) {
            println rule
        }

    }

}
