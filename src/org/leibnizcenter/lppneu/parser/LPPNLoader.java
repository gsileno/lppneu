package org.leibnizcenter.lppneu.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.leibnizcenter.lppneu.components.language.LPPNProgram;

import java.io.*;

public class LPPNLoader {

    public LPPNLoader() {}

    public static LPPNProgram parseString(String text) {

        InputStream is = null;

        try {
            is = new ByteArrayInputStream(text.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return parse(is);
    }

    public static LPPNProgram parseFile(String filename) throws FileNotFoundException {

        InputStream is = null;

        is = new FileInputStream(filename);

        return parse(is);
    }


    static LPPNProgram parse(InputStream is) {

        ANTLRInputStream input = null;
        LPPNProgram program = new LPPNProgram();

        try {
            input = new ANTLRInputStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LPPNLoaderErrorListener errorListener = new LPPNLoaderErrorListener();

        // Get our lexer
        LPPNLexer lexer = new LPPNLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);

        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LPPNParser parser = new LPPNParser(tokens);

        //** TO LET ANTLR GIVES FEEDBACK ON THE GRAMMAR **//
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        // PARSING

        // parser.setErrorHandler(new LPPNLoaderErrorStrategy());
        ParseTree tree = parser.program();

        program.setParsingErrors(errorListener.errors); // record the parsing errors
        if (program.getParsingErrors().size() > 0) {
            return program;
        }

        // DECORATION

        // create a standard ANTLR parse tree walker
        ParseTreeWalker walker = new ParseTreeWalker();
        // create listener then feed to walker
        LPPNLoaderListener loader = new LPPNLoaderListener();

        walker.walk(loader, tree); // walk parse tree

        program.setCausalRules(loader.getProgram().getCausalRules());
        program.setLogicRules(loader.getProgram().getLogicRules());

        return program;
    }
}
