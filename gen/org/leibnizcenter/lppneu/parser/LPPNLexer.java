// Generated from /home/giovanni/dev/lppneu/antlr4/LPPN.g4 by ANTLR 4.5.1
package org.leibnizcenter.lppneu.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LPPNLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, AND=2, OR=3, XOR=4, SEQ=5, PAR=6, ALT=7, NEG=8, NOT=9, NULL=10, 
		CAUSES=11, IS_IMPLIED_BY=12, IS_EQUIVALENT_TO=13, WHEN=14, PLUS=15, MINUS=16, 
		TILDE=17, DOT=18, COMMA=19, LPAR=20, RPAR=21, LACC=22, RACC=23, EQ=24, 
		NEQ=25, GT=26, LT=27, GE=28, LE=29, DOMAIN=30, RANGE=31, INTEGER=32, IDENTIFIER=33, 
		VARIABLE=34, SINGLE_LINE_COMMENT=35, MULTILINE_COMMENT=36;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"WS", "AND", "OR", "XOR", "SEQ", "PAR", "ALT", "NEG", "NOT", "NULL", "CAUSES", 
		"IS_IMPLIED_BY", "IS_EQUIVALENT_TO", "WHEN", "PLUS", "MINUS", "TILDE", 
		"DOT", "COMMA", "LPAR", "RPAR", "LACC", "RACC", "EQ", "NEQ", "GT", "LT", 
		"GE", "LE", "DOMAIN", "RANGE", "INTEGER", "IDENTIFIER", "VARIABLE", "SINGLE_LINE_COMMENT", 
		"MULTILINE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"':-'", null, null, "'+'", "'-'", "'~'", "'.'", "','", "'('", "')'", "'{'", 
		"'}'", "'='", "'!='", "'>'", "'<'", "'>='", "'<='", "'#domain'", "'..'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "AND", "OR", "XOR", "SEQ", "PAR", "ALT", "NEG", "NOT", "NULL", 
		"CAUSES", "IS_IMPLIED_BY", "IS_EQUIVALENT_TO", "WHEN", "PLUS", "MINUS", 
		"TILDE", "DOT", "COMMA", "LPAR", "RPAR", "LACC", "RACC", "EQ", "NEQ", 
		"GT", "LT", "GE", "LE", "DOMAIN", "RANGE", "INTEGER", "IDENTIFIER", "VARIABLE", 
		"SINGLE_LINE_COMMENT", "MULTILINE_COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public LPPNLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "LPPN.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2&\u0114\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\6\2M\n\2\r\2\16\2N\3\2\3\2\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\5\3Z\n\3\3\4\3\4\3\4\3\4\3\4\5\4a\n\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\5\5i\n\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6q\n\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\5\7y\n\7\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0081\n\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\5\t\u008a\n\t\3\n\3\n\3\n\3\n\5\n\u0090\n\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\5\13\u009a\n\13\3\f\3\f\3\f\3\f\5\f\u00a0\n"+
		"\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00ad\n\16"+
		"\3\17\3\17\3\17\5\17\u00b2\n\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23"+
		"\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32"+
		"\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3!\3!\3!\7!\u00e3\n!\f!\16!\u00e6\13"+
		"!\5!\u00e8\n!\3\"\3\"\7\"\u00ec\n\"\f\"\16\"\u00ef\13\"\3#\3#\7#\u00f3"+
		"\n#\f#\16#\u00f6\13#\3$\3$\3$\5$\u00fb\n$\3$\7$\u00fe\n$\f$\16$\u0101"+
		"\13$\3$\3$\3%\3%\3%\3%\7%\u0109\n%\f%\16%\u010c\13%\3%\3%\3%\5%\u0111"+
		"\n%\3%\3%\3\u010a\2&\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%I&\3\2\t\5\2\13\f\16\17\"\"\3\2\63;\3"+
		"\2\62;\3\2c|\6\2\62;C\\aac|\4\2C\\aa\4\2\f\f\17\17\u012c\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2"+
		"\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2"+
		"\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\3"+
		"L\3\2\2\2\5Y\3\2\2\2\7`\3\2\2\2\th\3\2\2\2\13p\3\2\2\2\rx\3\2\2\2\17\u0080"+
		"\3\2\2\2\21\u0089\3\2\2\2\23\u008f\3\2\2\2\25\u0099\3\2\2\2\27\u009f\3"+
		"\2\2\2\31\u00a1\3\2\2\2\33\u00ac\3\2\2\2\35\u00b1\3\2\2\2\37\u00b3\3\2"+
		"\2\2!\u00b5\3\2\2\2#\u00b7\3\2\2\2%\u00b9\3\2\2\2\'\u00bb\3\2\2\2)\u00bd"+
		"\3\2\2\2+\u00bf\3\2\2\2-\u00c1\3\2\2\2/\u00c3\3\2\2\2\61\u00c5\3\2\2\2"+
		"\63\u00c7\3\2\2\2\65\u00ca\3\2\2\2\67\u00cc\3\2\2\29\u00ce\3\2\2\2;\u00d1"+
		"\3\2\2\2=\u00d4\3\2\2\2?\u00dc\3\2\2\2A\u00e7\3\2\2\2C\u00e9\3\2\2\2E"+
		"\u00f0\3\2\2\2G\u00fa\3\2\2\2I\u0104\3\2\2\2KM\t\2\2\2LK\3\2\2\2MN\3\2"+
		"\2\2NL\3\2\2\2NO\3\2\2\2OP\3\2\2\2PQ\b\2\2\2Q\4\3\2\2\2RZ\7(\2\2ST\7C"+
		"\2\2TU\7P\2\2UZ\7F\2\2VW\7c\2\2WX\7p\2\2XZ\7f\2\2YR\3\2\2\2YS\3\2\2\2"+
		"YV\3\2\2\2Z\6\3\2\2\2[a\7~\2\2\\]\7Q\2\2]a\7T\2\2^_\7q\2\2_a\7t\2\2`["+
		"\3\2\2\2`\\\3\2\2\2`^\3\2\2\2a\b\3\2\2\2bc\7Z\2\2cd\7Q\2\2di\7T\2\2ef"+
		"\7z\2\2fg\7q\2\2gi\7t\2\2hb\3\2\2\2he\3\2\2\2i\n\3\2\2\2jk\7U\2\2kl\7"+
		"G\2\2lq\7S\2\2mn\7u\2\2no\7g\2\2oq\7s\2\2pj\3\2\2\2pm\3\2\2\2q\f\3\2\2"+
		"\2rs\7R\2\2st\7C\2\2ty\7T\2\2uv\7r\2\2vw\7c\2\2wy\7t\2\2xr\3\2\2\2xu\3"+
		"\2\2\2y\16\3\2\2\2z{\7C\2\2{|\7N\2\2|\u0081\7V\2\2}~\7c\2\2~\177\7n\2"+
		"\2\177\u0081\7v\2\2\u0080z\3\2\2\2\u0080}\3\2\2\2\u0081\20\3\2\2\2\u0082"+
		"\u0083\7p\2\2\u0083\u0084\7g\2\2\u0084\u008a\7i\2\2\u0085\u0086\7P\2\2"+
		"\u0086\u0087\7G\2\2\u0087\u008a\7I\2\2\u0088\u008a\5!\21\2\u0089\u0082"+
		"\3\2\2\2\u0089\u0085\3\2\2\2\u0089\u0088\3\2\2\2\u008a\22\3\2\2\2\u008b"+
		"\u008c\7p\2\2\u008c\u008d\7q\2\2\u008d\u0090\7v\2\2\u008e\u0090\5#\22"+
		"\2\u008f\u008b\3\2\2\2\u008f\u008e\3\2\2\2\u0090\24\3\2\2\2\u0091\u0092"+
		"\7p\2\2\u0092\u0093\7w\2\2\u0093\u0094\7n\2\2\u0094\u009a\7n\2\2\u0095"+
		"\u0096\7P\2\2\u0096\u0097\7W\2\2\u0097\u0098\7N\2\2\u0098\u009a\7N\2\2"+
		"\u0099\u0091\3\2\2\2\u0099\u0095\3\2\2\2\u009a\26\3\2\2\2\u009b\u009c"+
		"\7/\2\2\u009c\u00a0\7@\2\2\u009d\u009e\7?\2\2\u009e\u00a0\7@\2\2\u009f"+
		"\u009b\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\30\3\2\2\2\u00a1\u00a2\7<\2\2"+
		"\u00a2\u00a3\7/\2\2\u00a3\32\3\2\2\2\u00a4\u00a5\7<\2\2\u00a5\u00a6\7"+
		"<\2\2\u00a6\u00ad\7/\2\2\u00a7\u00a8\7<\2\2\u00a8\u00ad\7?\2\2\u00a9\u00aa"+
		"\7<\2\2\u00aa\u00ab\7<\2\2\u00ab\u00ad\7?\2\2\u00ac\u00a4\3\2\2\2\u00ac"+
		"\u00a7\3\2\2\2\u00ac\u00a9\3\2\2\2\u00ad\34\3\2\2\2\u00ae\u00b2\7<\2\2"+
		"\u00af\u00b0\7k\2\2\u00b0\u00b2\7p\2\2\u00b1\u00ae\3\2\2\2\u00b1\u00af"+
		"\3\2\2\2\u00b2\36\3\2\2\2\u00b3\u00b4\7-\2\2\u00b4 \3\2\2\2\u00b5\u00b6"+
		"\7/\2\2\u00b6\"\3\2\2\2\u00b7\u00b8\7\u0080\2\2\u00b8$\3\2\2\2\u00b9\u00ba"+
		"\7\60\2\2\u00ba&\3\2\2\2\u00bb\u00bc\7.\2\2\u00bc(\3\2\2\2\u00bd\u00be"+
		"\7*\2\2\u00be*\3\2\2\2\u00bf\u00c0\7+\2\2\u00c0,\3\2\2\2\u00c1\u00c2\7"+
		"}\2\2\u00c2.\3\2\2\2\u00c3\u00c4\7\177\2\2\u00c4\60\3\2\2\2\u00c5\u00c6"+
		"\7?\2\2\u00c6\62\3\2\2\2\u00c7\u00c8\7#\2\2\u00c8\u00c9\7?\2\2\u00c9\64"+
		"\3\2\2\2\u00ca\u00cb\7@\2\2\u00cb\66\3\2\2\2\u00cc\u00cd\7>\2\2\u00cd"+
		"8\3\2\2\2\u00ce\u00cf\7@\2\2\u00cf\u00d0\7?\2\2\u00d0:\3\2\2\2\u00d1\u00d2"+
		"\7>\2\2\u00d2\u00d3\7?\2\2\u00d3<\3\2\2\2\u00d4\u00d5\7%\2\2\u00d5\u00d6"+
		"\7f\2\2\u00d6\u00d7\7q\2\2\u00d7\u00d8\7o\2\2\u00d8\u00d9\7c\2\2\u00d9"+
		"\u00da\7k\2\2\u00da\u00db\7p\2\2\u00db>\3\2\2\2\u00dc\u00dd\7\60\2\2\u00dd"+
		"\u00de\7\60\2\2\u00de@\3\2\2\2\u00df\u00e8\7\62\2\2\u00e0\u00e4\t\3\2"+
		"\2\u00e1\u00e3\t\4\2\2\u00e2\u00e1\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4\u00e2"+
		"\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e8\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7"+
		"\u00df\3\2\2\2\u00e7\u00e0\3\2\2\2\u00e8B\3\2\2\2\u00e9\u00ed\t\5\2\2"+
		"\u00ea\u00ec\t\6\2\2\u00eb\u00ea\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb"+
		"\3\2\2\2\u00ed\u00ee\3\2\2\2\u00eeD\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0"+
		"\u00f4\t\7\2\2\u00f1\u00f3\t\6\2\2\u00f2\u00f1\3\2\2\2\u00f3\u00f6\3\2"+
		"\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5F\3\2\2\2\u00f6\u00f4"+
		"\3\2\2\2\u00f7\u00fb\7\'\2\2\u00f8\u00f9\7\61\2\2\u00f9\u00fb\7\61\2\2"+
		"\u00fa\u00f7\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb\u00ff\3\2\2\2\u00fc\u00fe"+
		"\n\b\2\2\u00fd\u00fc\3\2\2\2\u00fe\u0101\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff"+
		"\u0100\3\2\2\2\u0100\u0102\3\2\2\2\u0101\u00ff\3\2\2\2\u0102\u0103\b$"+
		"\3\2\u0103H\3\2\2\2\u0104\u0105\7\61\2\2\u0105\u0106\7,\2\2\u0106\u010a"+
		"\3\2\2\2\u0107\u0109\13\2\2\2\u0108\u0107\3\2\2\2\u0109\u010c\3\2\2\2"+
		"\u010a\u010b\3\2\2\2\u010a\u0108\3\2\2\2\u010b\u0110\3\2\2\2\u010c\u010a"+
		"\3\2\2\2\u010d\u010e\7,\2\2\u010e\u0111\7\61\2\2\u010f\u0111\7\2\2\3\u0110"+
		"\u010d\3\2\2\2\u0110\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0113\b%"+
		"\3\2\u0113J\3\2\2\2\30\2NY`hpx\u0080\u0089\u008f\u0099\u009f\u00ac\u00b1"+
		"\u00e4\u00e7\u00ed\u00f4\u00fa\u00ff\u010a\u0110\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}