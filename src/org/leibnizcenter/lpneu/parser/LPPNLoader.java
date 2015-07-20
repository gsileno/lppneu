package org.leibnizcenter.lpneu.parser;

import commons.base.Base;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DiagnosticErrorListener;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.leibnizcenter.grasp.components.Atom;
import org.leibnizcenter.grasp.components.Execution;
import org.leibnizcenter.grasp.components.Formula;
import org.leibnizcenter.grasp.components.Rule;

import java.io.*;

public class LPPNLoader {

    private static Base<Atom> atomBase;
    private static Base<Formula> formulaBase;
    private static Base<Rule> ruleBase;

    static Execution parseString(String text) {

        InputStream is = null;

        try {
            is = new ByteArrayInputStream(text.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return parse(is);
    }

    static Execution parseFile(String filename) throws FileNotFoundException {

        InputStream is = null;

        is = new FileInputStream(filename);

        return parse(is);
    }

    static Execution parse(InputStream is) {

        ANTLRInputStream input = null;
        Execution execution = new Execution();

        try {
            input = new ANTLRInputStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LPPNLoaderErrorListener errorListener = new LPPNLoaderErrorListener();

        // Get our lexer
        LparseASPLexer lexer = new LparseASPLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);

        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LparseASPParser parser = new LparseASPParser(tokens);

        //** TO LET ANTLR GIVES FEEDBACK ON THE GRAMMAR **//
        parser.removeErrorListeners();
        parser.addErrorListener(new DiagnosticErrorListener());
        // parser.getInterpreter().setPredictionMode(PredictionMode.LL_EXACT_AMBIG_DETECTION);

        ParseTree tree = parser.program();

        // create a standard ANTLR parse tree walker
        ParseTreeWalker walker = new ParseTreeWalker();
        // create listener then feed to walker
        LPPNLoaderListener loader = new LPPNLoaderListener();

        walker.walk(loader, tree); // walk parse tree

        execution.setOutputParsingErrors(errorListener.errors); // store the parsing errors

        return execution;
    }
}
