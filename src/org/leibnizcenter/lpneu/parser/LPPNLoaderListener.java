package org.leibnizcenter.lpneu.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class LPPNLoaderListener extends LparseASPBaseListener {

    private final static Logger log = Logger.getLogger("LparseASPLoaderListener");

    // Mapping of nodes
    private ParseTreeProperty<Atom> atomNodes = new ParseTreeProperty<Atom>();
    private ParseTreeProperty<Parameter> parameterNodes = new ParseTreeProperty<Parameter>();
    private ParseTreeProperty<Literal> literalNodes = new ParseTreeProperty<Literal>();
    private ParseTreeProperty<ExtLiteral> extLiteralNodes = new ParseTreeProperty<ExtLiteral>();

    // Mapping of (list-type) nodes
    private ParseTreeProperty<List<Object>> listNodes = new ParseTreeProperty<List<Object>>();

    public void addToDecorationList(ParseTree node, Object decoration) {
        List<Object> list = listNodes.get(node);
        if (list == null) {
            list = new ArrayList<Object>();
            listNodes.put(node, list);
        }
        list.add(decoration);
    }

    public List<Object> getDecorationList(ParseTree node) {
        return listNodes.get(node);
    }

    ///////////////// LISTENERS


}

