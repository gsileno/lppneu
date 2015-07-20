package org.leibnizcenter.lpneu.parser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.ArrayList;
import java.util.List;

public class LPPNLoaderErrorListener extends BaseErrorListener {

   public List<String> errors = new ArrayList<String>();

   @Override
   public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
      throws ParseCancellationException
      {
         errors.add("line " + line + ":" + charPositionInLine + " " + msg);
         // throw new ParseCancellationException("line " + line + ":" + charPositionInLine + " " + msg);
      }
}