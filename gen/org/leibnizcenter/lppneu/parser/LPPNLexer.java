// Generated from /home/giovanni/dev/lppneu/antlr4/LPPN.g4 by ANTLR 4.5
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
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, AND=2, OR=3, XOR=4, SEQ=5, PAR=6, ALT=7, NEG=8, NOT=9, NULL=10, 
		WHEN=11, CAUSES=12, IS_IMPLIED_BY=13, IS_EQUIVALENT_TO=14, PLUS=15, MINUS=16, 
		TILDE=17, DOT=18, COMMA=19, COLON=20, LPAR=21, RPAR=22, LACC=23, RACC=24, 
		EQ=25, NEQ=26, GT=27, LT=28, GE=29, LE=30, DOMAIN=31, RANGE=32, INTEGER=33, 
		IDENTIFIER=34, VARIABLE=35, SINGLE_LINE_COMMENT=36, MULTILINE_COMMENT=37;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"WS", "AND", "OR", "XOR", "SEQ", "PAR", "ALT", "NEG", "NOT", "NULL", "WHEN", 
		"CAUSES", "IS_IMPLIED_BY", "IS_EQUIVALENT_TO", "PLUS", "MINUS", "TILDE", 
		"DOT", "COMMA", "COLON", "LPAR", "RPAR", "LACC", "RACC", "EQ", "NEQ", 
		"GT", "LT", "GE", "LE", "DOMAIN", "RANGE", "INTEGER", "IDENTIFIER", "VARIABLE", 
		"SINGLE_LINE_COMMENT", "MULTILINE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "':-'", null, "'+'", "'-'", "'~'", "'.'", "','", "':'", "'('", "')'", 
		"'{'", "'}'", "'='", "'!='", "'>'", "'<'", "'>='", "'<='", "'#domain'", 
		"'..'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "AND", "OR", "XOR", "SEQ", "PAR", "ALT", "NEG", "NOT", "NULL", 
		"WHEN", "CAUSES", "IS_IMPLIED_BY", "IS_EQUIVALENT_TO", "PLUS", "MINUS", 
		"TILDE", "DOT", "COMMA", "COLON", "LPAR", "RPAR", "LACC", "RACC", "EQ", 
		"NEQ", "GT", "LT", "GE", "LE", "DOMAIN", "RANGE", "INTEGER", "IDENTIFIER", 
		"VARIABLE", "SINGLE_LINE_COMMENT", "MULTILINE_COMMENT"
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
	@NotNull
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\'\u0118\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\6\2O\n\2\r\2\16\2P\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\5\3\\\n\3\3\4\3\4\3\4\3\4\3\4\5\4c\n\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\5\5k\n\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6s\n\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\5\7{\n\7\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0083\n\b\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\5\t\u008c\n\t\3\n\3\n\3\n\3\n\5\n\u0092\n\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u009c\n\13\3\f\3\f\3\f\5\f\u00a1"+
		"\n\f\3\r\3\r\3\r\3\r\5\r\u00a7\n\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\5\17\u00b4\n\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23"+
		"\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32"+
		"\3\32\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37"+
		"\3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\7\"\u00e7\n\"\f\"\16\"\u00ea"+
		"\13\"\5\"\u00ec\n\"\3#\3#\7#\u00f0\n#\f#\16#\u00f3\13#\3$\3$\7$\u00f7"+
		"\n$\f$\16$\u00fa\13$\3%\3%\3%\5%\u00ff\n%\3%\7%\u0102\n%\f%\16%\u0105"+
		"\13%\3%\3%\3&\3&\3&\3&\7&\u010d\n&\f&\16&\u0110\13&\3&\3&\3&\5&\u0115"+
		"\n&\3&\3&\3\u010e\2\'\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'\3\2\t\5\2\13\f\16\17\"\"\3\2\63"+
		";\3\2\62;\3\2c|\6\2\62;C\\aac|\4\2C\\aa\4\2\f\f\17\17\u0130\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2"+
		"\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3"+
		"\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2"+
		"\2\2K\3\2\2\2\3N\3\2\2\2\5[\3\2\2\2\7b\3\2\2\2\tj\3\2\2\2\13r\3\2\2\2"+
		"\rz\3\2\2\2\17\u0082\3\2\2\2\21\u008b\3\2\2\2\23\u0091\3\2\2\2\25\u009b"+
		"\3\2\2\2\27\u00a0\3\2\2\2\31\u00a6\3\2\2\2\33\u00a8\3\2\2\2\35\u00b3\3"+
		"\2\2\2\37\u00b5\3\2\2\2!\u00b7\3\2\2\2#\u00b9\3\2\2\2%\u00bb\3\2\2\2\'"+
		"\u00bd\3\2\2\2)\u00bf\3\2\2\2+\u00c1\3\2\2\2-\u00c3\3\2\2\2/\u00c5\3\2"+
		"\2\2\61\u00c7\3\2\2\2\63\u00c9\3\2\2\2\65\u00cb\3\2\2\2\67\u00ce\3\2\2"+
		"\29\u00d0\3\2\2\2;\u00d2\3\2\2\2=\u00d5\3\2\2\2?\u00d8\3\2\2\2A\u00e0"+
		"\3\2\2\2C\u00eb\3\2\2\2E\u00ed\3\2\2\2G\u00f4\3\2\2\2I\u00fe\3\2\2\2K"+
		"\u0108\3\2\2\2MO\t\2\2\2NM\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QR\3\2"+
		"\2\2RS\b\2\2\2S\4\3\2\2\2T\\\7(\2\2UV\7C\2\2VW\7P\2\2W\\\7F\2\2XY\7c\2"+
		"\2YZ\7p\2\2Z\\\7f\2\2[T\3\2\2\2[U\3\2\2\2[X\3\2\2\2\\\6\3\2\2\2]c\7~\2"+
		"\2^_\7Q\2\2_c\7T\2\2`a\7q\2\2ac\7t\2\2b]\3\2\2\2b^\3\2\2\2b`\3\2\2\2c"+
		"\b\3\2\2\2de\7Z\2\2ef\7Q\2\2fk\7T\2\2gh\7z\2\2hi\7q\2\2ik\7t\2\2jd\3\2"+
		"\2\2jg\3\2\2\2k\n\3\2\2\2lm\7U\2\2mn\7G\2\2ns\7S\2\2op\7u\2\2pq\7g\2\2"+
		"qs\7s\2\2rl\3\2\2\2ro\3\2\2\2s\f\3\2\2\2tu\7R\2\2uv\7C\2\2v{\7T\2\2wx"+
		"\7r\2\2xy\7c\2\2y{\7t\2\2zt\3\2\2\2zw\3\2\2\2{\16\3\2\2\2|}\7C\2\2}~\7"+
		"N\2\2~\u0083\7V\2\2\177\u0080\7c\2\2\u0080\u0081\7n\2\2\u0081\u0083\7"+
		"v\2\2\u0082|\3\2\2\2\u0082\177\3\2\2\2\u0083\20\3\2\2\2\u0084\u0085\7"+
		"p\2\2\u0085\u0086\7g\2\2\u0086\u008c\7i\2\2\u0087\u0088\7P\2\2\u0088\u0089"+
		"\7G\2\2\u0089\u008c\7I\2\2\u008a\u008c\5!\21\2\u008b\u0084\3\2\2\2\u008b"+
		"\u0087\3\2\2\2\u008b\u008a\3\2\2\2\u008c\22\3\2\2\2\u008d\u008e\7p\2\2"+
		"\u008e\u008f\7q\2\2\u008f\u0092\7v\2\2\u0090\u0092\5#\22\2\u0091\u008d"+
		"\3\2\2\2\u0091\u0090\3\2\2\2\u0092\24\3\2\2\2\u0093\u0094\7p\2\2\u0094"+
		"\u0095\7w\2\2\u0095\u0096\7n\2\2\u0096\u009c\7n\2\2\u0097\u0098\7P\2\2"+
		"\u0098\u0099\7W\2\2\u0099\u009a\7N\2\2\u009a\u009c\7N\2\2\u009b\u0093"+
		"\3\2\2\2\u009b\u0097\3\2\2\2\u009c\26\3\2\2\2\u009d\u00a1\5)\25\2\u009e"+
		"\u009f\7k\2\2\u009f\u00a1\7p\2\2\u00a0\u009d\3\2\2\2\u00a0\u009e\3\2\2"+
		"\2\u00a1\30\3\2\2\2\u00a2\u00a3\7/\2\2\u00a3\u00a7\7@\2\2\u00a4\u00a5"+
		"\7?\2\2\u00a5\u00a7\7@\2\2\u00a6\u00a2\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7"+
		"\32\3\2\2\2\u00a8\u00a9\7<\2\2\u00a9\u00aa\7/\2\2\u00aa\34\3\2\2\2\u00ab"+
		"\u00ac\7<\2\2\u00ac\u00ad\7<\2\2\u00ad\u00b4\7/\2\2\u00ae\u00af\7<\2\2"+
		"\u00af\u00b4\7?\2\2\u00b0\u00b1\7<\2\2\u00b1\u00b2\7<\2\2\u00b2\u00b4"+
		"\7?\2\2\u00b3\u00ab\3\2\2\2\u00b3\u00ae\3\2\2\2\u00b3\u00b0\3\2\2\2\u00b4"+
		"\36\3\2\2\2\u00b5\u00b6\7-\2\2\u00b6 \3\2\2\2\u00b7\u00b8\7/\2\2\u00b8"+
		"\"\3\2\2\2\u00b9\u00ba\7\u0080\2\2\u00ba$\3\2\2\2\u00bb\u00bc\7\60\2\2"+
		"\u00bc&\3\2\2\2\u00bd\u00be\7.\2\2\u00be(\3\2\2\2\u00bf\u00c0\7<\2\2\u00c0"+
		"*\3\2\2\2\u00c1\u00c2\7*\2\2\u00c2,\3\2\2\2\u00c3\u00c4\7+\2\2\u00c4."+
		"\3\2\2\2\u00c5\u00c6\7}\2\2\u00c6\60\3\2\2\2\u00c7\u00c8\7\177\2\2\u00c8"+
		"\62\3\2\2\2\u00c9\u00ca\7?\2\2\u00ca\64\3\2\2\2\u00cb\u00cc\7#\2\2\u00cc"+
		"\u00cd\7?\2\2\u00cd\66\3\2\2\2\u00ce\u00cf\7@\2\2\u00cf8\3\2\2\2\u00d0"+
		"\u00d1\7>\2\2\u00d1:\3\2\2\2\u00d2\u00d3\7@\2\2\u00d3\u00d4\7?\2\2\u00d4"+
		"<\3\2\2\2\u00d5\u00d6\7>\2\2\u00d6\u00d7\7?\2\2\u00d7>\3\2\2\2\u00d8\u00d9"+
		"\7%\2\2\u00d9\u00da\7f\2\2\u00da\u00db\7q\2\2\u00db\u00dc\7o\2\2\u00dc"+
		"\u00dd\7c\2\2\u00dd\u00de\7k\2\2\u00de\u00df\7p\2\2\u00df@\3\2\2\2\u00e0"+
		"\u00e1\7\60\2\2\u00e1\u00e2\7\60\2\2\u00e2B\3\2\2\2\u00e3\u00ec\7\62\2"+
		"\2\u00e4\u00e8\t\3\2\2\u00e5\u00e7\t\4\2\2\u00e6\u00e5\3\2\2\2\u00e7\u00ea"+
		"\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea"+
		"\u00e8\3\2\2\2\u00eb\u00e3\3\2\2\2\u00eb\u00e4\3\2\2\2\u00ecD\3\2\2\2"+
		"\u00ed\u00f1\t\5\2\2\u00ee\u00f0\t\6\2\2\u00ef\u00ee\3\2\2\2\u00f0\u00f3"+
		"\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2F\3\2\2\2\u00f3"+
		"\u00f1\3\2\2\2\u00f4\u00f8\t\7\2\2\u00f5\u00f7\t\6\2\2\u00f6\u00f5\3\2"+
		"\2\2\u00f7\u00fa\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9"+
		"H\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb\u00ff\7\'\2\2\u00fc\u00fd\7\61\2\2"+
		"\u00fd\u00ff\7\61\2\2\u00fe\u00fb\3\2\2\2\u00fe\u00fc\3\2\2\2\u00ff\u0103"+
		"\3\2\2\2\u0100\u0102\n\b\2\2\u0101\u0100\3\2\2\2\u0102\u0105\3\2\2\2\u0103"+
		"\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0106\3\2\2\2\u0105\u0103\3\2"+
		"\2\2\u0106\u0107\b%\3\2\u0107J\3\2\2\2\u0108\u0109\7\61\2\2\u0109\u010a"+
		"\7,\2\2\u010a\u010e\3\2\2\2\u010b\u010d\13\2\2\2\u010c\u010b\3\2\2\2\u010d"+
		"\u0110\3\2\2\2\u010e\u010f\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0114\3\2"+
		"\2\2\u0110\u010e\3\2\2\2\u0111\u0112\7,\2\2\u0112\u0115\7\61\2\2\u0113"+
		"\u0115\7\2\2\3\u0114\u0111\3\2\2\2\u0114\u0113\3\2\2\2\u0115\u0116\3\2"+
		"\2\2\u0116\u0117\b&\3\2\u0117L\3\2\2\2\30\2P[bjrz\u0082\u008b\u0091\u009b"+
		"\u00a0\u00a6\u00b3\u00e8\u00eb\u00f1\u00f8\u00fe\u0103\u010e\u0114\4\b"+
		"\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}