package org.leibnizcenter.lpneu.components.language

import commons.base.Base

class Program {

    List<String> parsingErrors = []
    Base<Position> positionBase

    public Program() {
        positionBase = new Base<Position>()
    }

    void print() {
    }

}
