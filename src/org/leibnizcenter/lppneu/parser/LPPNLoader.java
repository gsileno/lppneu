package org.leibnizcenter.lppneu.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.leibnizcenter.lppneu.components.language.Program;

import java.io.*;

public class LPPNLoader {

    public LPPNLoader() {}

    public static Program parseString(String text) {

        InputStream is = null;

        try {
            is = new ByteArrayInputStream(text.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return parse(is);
    }

    public static Program parseFile(String filename) throws FileNotFoundException {

        InputStream is = null;

        is = new FileInputStream(filename);

        return parse(is);
    }


    static Program parse(InputStream is) {

        ANTLRInputStream input = null;
        Program program = new Program();

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

        // parser.setErrorHandler(new LPPNLoaderErrorStrategy());
        ParseTree tree = parser.program();

        // create a standard ANTLR parse tree walker
        ParseTreeWalker walker = new ParseTreeWalker();
        // create listener then feed to walker
        LPPNLoaderListener loader = new LPPNLoaderListener();

        walker.walk(loader, tree); // walk parse tree

        program.setParsingErrors(errorListener.errors); // record the parsing errors
        program.setCausalRules(loader.getProgram().getCausalRules());
        program.setLogicRules(loader.getProgram().getLogicRules());

        return program;
    }
}
