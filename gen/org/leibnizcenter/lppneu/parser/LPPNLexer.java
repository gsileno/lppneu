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
		WS=1, IS_IMPLIED_BY=2, WHEN=3, DOT=4, COMMA=5, LPAR=6, RPAR=7, LACC=8, 
		RACC=9, EQ=10, NEQ=11, GT=12, LT=13, GE=14, LE=15, AND=16, OR=17, XOR=18, 
		SEQ=19, PAR=20, ALT=21, CAUSES=22, NOT=23, NEG=24, PLUS=25, MINUS=26, 
		TILDE=27, DOMAIN=28, RANGE=29, INTEGER=30, IDENTIFIER=31, VARIABLE=32, 
		SINGLE_LINE_COMMENT=33, MULTILINE_COMMENT=34;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"WS", "IS_IMPLIED_BY", "WHEN", "DOT", "COMMA", "LPAR", "RPAR", "LACC", 
		"RACC", "EQ", "NEQ", "GT", "LT", "GE", "LE", "AND", "OR", "XOR", "SEQ", 
		"PAR", "ALT", "CAUSES", "NULL", "NEG", "PLUS", "MINUS", "TILDE", "DOMAIN",
		"RANGE", "INTEGER", "IDENTIFIER", "VARIABLE", "SINGLE_LINE_COMMENT", "MULTILINE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "':-'", null, "'.'", "','", "'('", "')'", "'{'", "'}'", "'='", 
		"'!='", "'>'", "'<'", "'>='", "'<='", null, null, null, null, null, null, 
		"'->'", null, null, "'+'", "'-'", "'~'", "'#domain'", "'..'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "IS_IMPLIED_BY", "WHEN", "DOT", "COMMA", "LPAR", "RPAR", "LACC", 
		"RACC", "EQ", "NEQ", "GT", "LT", "GE", "LE", "AND", "OR", "XOR", "SEQ", 
		"PAR", "ALT", "CAUSES", "NULL", "NEG", "PLUS", "MINUS", "TILDE", "DOMAIN",
		"RANGE", "INTEGER", "IDENTIFIER", "VARIABLE", "SINGLE_LINE_COMMENT", "MULTILINE_COMMENT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2$\u00fa\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\6\2I\n\2\r\2\16\2J\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\5\4U\n\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13"+
		"\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\5\21y\n\21\3\22\3\22\3\22\3\22\3\22\5\22\u0080"+
		"\n\22\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u0088\n\23\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\5\24\u0090\n\24\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u0098\n"+
		"\25\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u00a0\n\26\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\5\30\u00ab\n\30\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\5\31\u00b3\n\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\7\37\u00c9\n\37\f\37\16"+
		"\37\u00cc\13\37\5\37\u00ce\n\37\3 \3 \7 \u00d2\n \f \16 \u00d5\13 \3!"+
		"\3!\7!\u00d9\n!\f!\16!\u00dc\13!\3\"\3\"\3\"\5\"\u00e1\n\"\3\"\7\"\u00e4"+
		"\n\"\f\"\16\"\u00e7\13\"\3\"\3\"\3#\3#\3#\3#\7#\u00ef\n#\f#\16#\u00f2"+
		"\13#\3#\3#\3#\5#\u00f7\n#\3#\3#\3\u00f0\2$\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$\3\2\t\5\2\13\f"+
		"\16\17\"\"\3\2\63;\3\2\62;\3\2c|\6\2\62;C\\aac|\4\2C\\aa\4\2\f\f\17\17"+
		"\u010d\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2"+
		"\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\3"+
		"H\3\2\2\2\5N\3\2\2\2\7T\3\2\2\2\tV\3\2\2\2\13X\3\2\2\2\rZ\3\2\2\2\17\\"+
		"\3\2\2\2\21^\3\2\2\2\23`\3\2\2\2\25b\3\2\2\2\27d\3\2\2\2\31g\3\2\2\2\33"+
		"i\3\2\2\2\35k\3\2\2\2\37n\3\2\2\2!x\3\2\2\2#\177\3\2\2\2%\u0087\3\2\2"+
		"\2\'\u008f\3\2\2\2)\u0097\3\2\2\2+\u009f\3\2\2\2-\u00a1\3\2\2\2/\u00aa"+
		"\3\2\2\2\61\u00b2\3\2\2\2\63\u00b4\3\2\2\2\65\u00b6\3\2\2\2\67\u00b8\3"+
		"\2\2\29\u00ba\3\2\2\2;\u00c2\3\2\2\2=\u00cd\3\2\2\2?\u00cf\3\2\2\2A\u00d6"+
		"\3\2\2\2C\u00e0\3\2\2\2E\u00ea\3\2\2\2GI\t\2\2\2HG\3\2\2\2IJ\3\2\2\2J"+
		"H\3\2\2\2JK\3\2\2\2KL\3\2\2\2LM\b\2\2\2M\4\3\2\2\2NO\7<\2\2OP\7/\2\2P"+
		"\6\3\2\2\2QU\7<\2\2RS\7k\2\2SU\7p\2\2TQ\3\2\2\2TR\3\2\2\2U\b\3\2\2\2V"+
		"W\7\60\2\2W\n\3\2\2\2XY\7.\2\2Y\f\3\2\2\2Z[\7*\2\2[\16\3\2\2\2\\]\7+\2"+
		"\2]\20\3\2\2\2^_\7}\2\2_\22\3\2\2\2`a\7\177\2\2a\24\3\2\2\2bc\7?\2\2c"+
		"\26\3\2\2\2de\7#\2\2ef\7?\2\2f\30\3\2\2\2gh\7@\2\2h\32\3\2\2\2ij\7>\2"+
		"\2j\34\3\2\2\2kl\7@\2\2lm\7?\2\2m\36\3\2\2\2no\7>\2\2op\7?\2\2p \3\2\2"+
		"\2qy\7(\2\2rs\7C\2\2st\7P\2\2ty\7F\2\2uv\7c\2\2vw\7p\2\2wy\7f\2\2xq\3"+
		"\2\2\2xr\3\2\2\2xu\3\2\2\2y\"\3\2\2\2z\u0080\7~\2\2{|\7Q\2\2|\u0080\7"+
		"T\2\2}~\7q\2\2~\u0080\7t\2\2\177z\3\2\2\2\177{\3\2\2\2\177}\3\2\2\2\u0080"+
		"$\3\2\2\2\u0081\u0082\7Z\2\2\u0082\u0083\7Q\2\2\u0083\u0088\7T\2\2\u0084"+
		"\u0085\7z\2\2\u0085\u0086\7q\2\2\u0086\u0088\7t\2\2\u0087\u0081\3\2\2"+
		"\2\u0087\u0084\3\2\2\2\u0088&\3\2\2\2\u0089\u008a\7U\2\2\u008a\u008b\7"+
		"G\2\2\u008b\u0090\7S\2\2\u008c\u008d\7u\2\2\u008d\u008e\7g\2\2\u008e\u0090"+
		"\7s\2\2\u008f\u0089\3\2\2\2\u008f\u008c\3\2\2\2\u0090(\3\2\2\2\u0091\u0092"+
		"\7R\2\2\u0092\u0093\7C\2\2\u0093\u0098\7T\2\2\u0094\u0095\7r\2\2\u0095"+
		"\u0096\7c\2\2\u0096\u0098\7t\2\2\u0097\u0091\3\2\2\2\u0097\u0094\3\2\2"+
		"\2\u0098*\3\2\2\2\u0099\u009a\7C\2\2\u009a\u009b\7N\2\2\u009b\u00a0\7"+
		"V\2\2\u009c\u009d\7c\2\2\u009d\u009e\7n\2\2\u009e\u00a0\7v\2\2\u009f\u0099"+
		"\3\2\2\2\u009f\u009c\3\2\2\2\u00a0,\3\2\2\2\u00a1\u00a2\7/\2\2\u00a2\u00a3"+
		"\7@\2\2\u00a3.\3\2\2\2\u00a4\u00a5\7p\2\2\u00a5\u00a6\7q\2\2\u00a6\u00ab"+
		"\7v\2\2\u00a7\u00a8\7P\2\2\u00a8\u00a9\7Q\2\2\u00a9\u00ab\7V\2\2\u00aa"+
		"\u00a4\3\2\2\2\u00aa\u00a7\3\2\2\2\u00ab\60\3\2\2\2\u00ac\u00ad\7p\2\2"+
		"\u00ad\u00ae\7g\2\2\u00ae\u00b3\7i\2\2\u00af\u00b0\7P\2\2\u00b0\u00b1"+
		"\7G\2\2\u00b1\u00b3\7I\2\2\u00b2\u00ac\3\2\2\2\u00b2\u00af\3\2\2\2\u00b3"+
		"\62\3\2\2\2\u00b4\u00b5\7-\2\2\u00b5\64\3\2\2\2\u00b6\u00b7\7/\2\2\u00b7"+
		"\66\3\2\2\2\u00b8\u00b9\7\u0080\2\2\u00b98\3\2\2\2\u00ba\u00bb\7%\2\2"+
		"\u00bb\u00bc\7f\2\2\u00bc\u00bd\7q\2\2\u00bd\u00be\7o\2\2\u00be\u00bf"+
		"\7c\2\2\u00bf\u00c0\7k\2\2\u00c0\u00c1\7p\2\2\u00c1:\3\2\2\2\u00c2\u00c3"+
		"\7\60\2\2\u00c3\u00c4\7\60\2\2\u00c4<\3\2\2\2\u00c5\u00ce\7\62\2\2\u00c6"+
		"\u00ca\t\3\2\2\u00c7\u00c9\t\4\2\2\u00c8\u00c7\3\2\2\2\u00c9\u00cc\3\2"+
		"\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc"+
		"\u00ca\3\2\2\2\u00cd\u00c5\3\2\2\2\u00cd\u00c6\3\2\2\2\u00ce>\3\2\2\2"+
		"\u00cf\u00d3\t\5\2\2\u00d0\u00d2\t\6\2\2\u00d1\u00d0\3\2\2\2\u00d2\u00d5"+
		"\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4@\3\2\2\2\u00d5"+
		"\u00d3\3\2\2\2\u00d6\u00da\t\7\2\2\u00d7\u00d9\t\6\2\2\u00d8\u00d7\3\2"+
		"\2\2\u00d9\u00dc\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db"+
		"B\3\2\2\2\u00dc\u00da\3\2\2\2\u00dd\u00e1\7\'\2\2\u00de\u00df\7\61\2\2"+
		"\u00df\u00e1\7\61\2\2\u00e0\u00dd\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1\u00e5"+
		"\3\2\2\2\u00e2\u00e4\n\b\2\2\u00e3\u00e2\3\2\2\2\u00e4\u00e7\3\2\2\2\u00e5"+
		"\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e8\3\2\2\2\u00e7\u00e5\3\2"+
		"\2\2\u00e8\u00e9\b\"\3\2\u00e9D\3\2\2\2\u00ea\u00eb\7\61\2\2\u00eb\u00ec"+
		"\7,\2\2\u00ec\u00f0\3\2\2\2\u00ed\u00ef\13\2\2\2\u00ee\u00ed\3\2\2\2\u00ef"+
		"\u00f2\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00f6\3\2"+
		"\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00f4\7,\2\2\u00f4\u00f7\7\61\2\2\u00f5"+
		"\u00f7\7\2\2\3\u00f6\u00f3\3\2\2\2\u00f6\u00f5\3\2\2\2\u00f7\u00f8\3\2"+
		"\2\2\u00f8\u00f9\b#\3\2\u00f9F\3\2\2\2\25\2JTx\177\u0087\u008f\u0097\u009f"+
		"\u00aa\u00b2\u00ca\u00cd\u00d3\u00da\u00e0\u00e5\u00f0\u00f6\4\b\2\2\2"+
		"\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}