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
		WS=1, AND=2, OR=3, XOR=4, SEQ=5, PAR=6, ALT=7, NOT=8, NEG=9, CAUSES=10, 
		IS_IMPLIED_BY=11, WHEN=12, PLUS=13, MINUS=14, TILDE=15, DOT=16, COMMA=17, 
		LPAR=18, RPAR=19, LACC=20, RACC=21, EQ=22, NEQ=23, GT=24, LT=25, GE=26, 
		LE=27, DOMAIN=28, RANGE=29, INTEGER=30, IDENTIFIER=31, VARIABLE=32, SINGLE_LINE_COMMENT=33, 
		MULTILINE_COMMENT=34;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"WS", "AND", "OR", "XOR", "SEQ", "PAR", "ALT", "NOT", "NEG", "CAUSES", 
		"IS_IMPLIED_BY", "WHEN", "PLUS", "MINUS", "TILDE", "DOT", "COMMA", "LPAR", 
		"RPAR", "LACC", "RACC", "EQ", "NEQ", "GT", "LT", "GE", "LE", "DOMAIN", 
		"RANGE", "INTEGER", "IDENTIFIER", "VARIABLE", "SINGLE_LINE_COMMENT", "MULTILINE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, "':-'", 
		null, "'+'", "'-'", "'~'", "'.'", "','", "'('", "')'", "'{'", "'}'", "'='", 
		"'!='", "'>'", "'<'", "'>='", "'<='", "'#domain'", "'..'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "AND", "OR", "XOR", "SEQ", "PAR", "ALT", "NOT", "NEG", "CAUSES", 
		"IS_IMPLIED_BY", "WHEN", "PLUS", "MINUS", "TILDE", "DOT", "COMMA", "LPAR", 
		"RPAR", "LACC", "RACC", "EQ", "NEQ", "GT", "LT", "GE", "LE", "DOMAIN", 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2$\u00fd\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\6\2I\n\2\r\2\16\2J\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\5\3V\n\3\3\4\3\4\3\4\3\4\3\4\5\4]\n\4\3\5\3\5\3\5\3\5\3\5\3\5\5"+
		"\5e\n\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6m\n\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7u"+
		"\n\7\3\b\3\b\3\b\3\b\3\b\3\b\5\b}\n\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0085"+
		"\n\t\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u008d\n\n\3\13\3\13\3\13\3\13\5\13\u0093"+
		"\n\13\3\f\3\f\3\f\3\r\3\r\3\r\5\r\u009b\n\r\3\16\3\16\3\17\3\17\3\20\3"+
		"\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3"+
		"\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3"+
		"\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\7"+
		"\37\u00cc\n\37\f\37\16\37\u00cf\13\37\5\37\u00d1\n\37\3 \3 \7 \u00d5\n"+
		" \f \16 \u00d8\13 \3!\3!\7!\u00dc\n!\f!\16!\u00df\13!\3\"\3\"\3\"\5\""+
		"\u00e4\n\"\3\"\7\"\u00e7\n\"\f\"\16\"\u00ea\13\"\3\"\3\"\3#\3#\3#\3#\7"+
		"#\u00f2\n#\f#\16#\u00f5\13#\3#\3#\3#\5#\u00fa\n#\3#\3#\3\u00f3\2$\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C#E$\3\2\t\5\2\13\f\16\17\"\"\3\2\63;\3\2\62;\3\2c|\6\2\62;C\\aac|"+
		"\4\2C\\aa\4\2\f\f\17\17\u0111\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"+
		"\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2"+
		"+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2"+
		"\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2"+
		"C\3\2\2\2\2E\3\2\2\2\3H\3\2\2\2\5U\3\2\2\2\7\\\3\2\2\2\td\3\2\2\2\13l"+
		"\3\2\2\2\rt\3\2\2\2\17|\3\2\2\2\21\u0084\3\2\2\2\23\u008c\3\2\2\2\25\u0092"+
		"\3\2\2\2\27\u0094\3\2\2\2\31\u009a\3\2\2\2\33\u009c\3\2\2\2\35\u009e\3"+
		"\2\2\2\37\u00a0\3\2\2\2!\u00a2\3\2\2\2#\u00a4\3\2\2\2%\u00a6\3\2\2\2\'"+
		"\u00a8\3\2\2\2)\u00aa\3\2\2\2+\u00ac\3\2\2\2-\u00ae\3\2\2\2/\u00b0\3\2"+
		"\2\2\61\u00b3\3\2\2\2\63\u00b5\3\2\2\2\65\u00b7\3\2\2\2\67\u00ba\3\2\2"+
		"\29\u00bd\3\2\2\2;\u00c5\3\2\2\2=\u00d0\3\2\2\2?\u00d2\3\2\2\2A\u00d9"+
		"\3\2\2\2C\u00e3\3\2\2\2E\u00ed\3\2\2\2GI\t\2\2\2HG\3\2\2\2IJ\3\2\2\2J"+
		"H\3\2\2\2JK\3\2\2\2KL\3\2\2\2LM\b\2\2\2M\4\3\2\2\2NV\7(\2\2OP\7C\2\2P"+
		"Q\7P\2\2QV\7F\2\2RS\7c\2\2ST\7p\2\2TV\7f\2\2UN\3\2\2\2UO\3\2\2\2UR\3\2"+
		"\2\2V\6\3\2\2\2W]\7~\2\2XY\7Q\2\2Y]\7T\2\2Z[\7q\2\2[]\7t\2\2\\W\3\2\2"+
		"\2\\X\3\2\2\2\\Z\3\2\2\2]\b\3\2\2\2^_\7Z\2\2_`\7Q\2\2`e\7T\2\2ab\7z\2"+
		"\2bc\7q\2\2ce\7t\2\2d^\3\2\2\2da\3\2\2\2e\n\3\2\2\2fg\7U\2\2gh\7G\2\2"+
		"hm\7S\2\2ij\7u\2\2jk\7g\2\2km\7s\2\2lf\3\2\2\2li\3\2\2\2m\f\3\2\2\2no"+
		"\7R\2\2op\7C\2\2pu\7T\2\2qr\7r\2\2rs\7c\2\2su\7t\2\2tn\3\2\2\2tq\3\2\2"+
		"\2u\16\3\2\2\2vw\7C\2\2wx\7N\2\2x}\7V\2\2yz\7c\2\2z{\7n\2\2{}\7v\2\2|"+
		"v\3\2\2\2|y\3\2\2\2}\20\3\2\2\2~\177\7p\2\2\177\u0080\7q\2\2\u0080\u0085"+
		"\7v\2\2\u0081\u0082\7P\2\2\u0082\u0083\7Q\2\2\u0083\u0085\7V\2\2\u0084"+
		"~\3\2\2\2\u0084\u0081\3\2\2\2\u0085\22\3\2\2\2\u0086\u0087\7p\2\2\u0087"+
		"\u0088\7g\2\2\u0088\u008d\7i\2\2\u0089\u008a\7P\2\2\u008a\u008b\7G\2\2"+
		"\u008b\u008d\7I\2\2\u008c\u0086\3\2\2\2\u008c\u0089\3\2\2\2\u008d\24\3"+
		"\2\2\2\u008e\u008f\7/\2\2\u008f\u0093\7@\2\2\u0090\u0091\7?\2\2\u0091"+
		"\u0093\7@\2\2\u0092\u008e\3\2\2\2\u0092\u0090\3\2\2\2\u0093\26\3\2\2\2"+
		"\u0094\u0095\7<\2\2\u0095\u0096\7/\2\2\u0096\30\3\2\2\2\u0097\u009b\7"+
		"<\2\2\u0098\u0099\7k\2\2\u0099\u009b\7p\2\2\u009a\u0097\3\2\2\2\u009a"+
		"\u0098\3\2\2\2\u009b\32\3\2\2\2\u009c\u009d\7-\2\2\u009d\34\3\2\2\2\u009e"+
		"\u009f\7/\2\2\u009f\36\3\2\2\2\u00a0\u00a1\7\u0080\2\2\u00a1 \3\2\2\2"+
		"\u00a2\u00a3\7\60\2\2\u00a3\"\3\2\2\2\u00a4\u00a5\7.\2\2\u00a5$\3\2\2"+
		"\2\u00a6\u00a7\7*\2\2\u00a7&\3\2\2\2\u00a8\u00a9\7+\2\2\u00a9(\3\2\2\2"+
		"\u00aa\u00ab\7}\2\2\u00ab*\3\2\2\2\u00ac\u00ad\7\177\2\2\u00ad,\3\2\2"+
		"\2\u00ae\u00af\7?\2\2\u00af.\3\2\2\2\u00b0\u00b1\7#\2\2\u00b1\u00b2\7"+
		"?\2\2\u00b2\60\3\2\2\2\u00b3\u00b4\7@\2\2\u00b4\62\3\2\2\2\u00b5\u00b6"+
		"\7>\2\2\u00b6\64\3\2\2\2\u00b7\u00b8\7@\2\2\u00b8\u00b9\7?\2\2\u00b9\66"+
		"\3\2\2\2\u00ba\u00bb\7>\2\2\u00bb\u00bc\7?\2\2\u00bc8\3\2\2\2\u00bd\u00be"+
		"\7%\2\2\u00be\u00bf\7f\2\2\u00bf\u00c0\7q\2\2\u00c0\u00c1\7o\2\2\u00c1"+
		"\u00c2\7c\2\2\u00c2\u00c3\7k\2\2\u00c3\u00c4\7p\2\2\u00c4:\3\2\2\2\u00c5"+
		"\u00c6\7\60\2\2\u00c6\u00c7\7\60\2\2\u00c7<\3\2\2\2\u00c8\u00d1\7\62\2"+
		"\2\u00c9\u00cd\t\3\2\2\u00ca\u00cc\t\4\2\2\u00cb\u00ca\3\2\2\2\u00cc\u00cf"+
		"\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf"+
		"\u00cd\3\2\2\2\u00d0\u00c8\3\2\2\2\u00d0\u00c9\3\2\2\2\u00d1>\3\2\2\2"+
		"\u00d2\u00d6\t\5\2\2\u00d3\u00d5\t\6\2\2\u00d4\u00d3\3\2\2\2\u00d5\u00d8"+
		"\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7@\3\2\2\2\u00d8"+
		"\u00d6\3\2\2\2\u00d9\u00dd\t\7\2\2\u00da\u00dc\t\6\2\2\u00db\u00da\3\2"+
		"\2\2\u00dc\u00df\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de"+
		"B\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0\u00e4\7\'\2\2\u00e1\u00e2\7\61\2\2"+
		"\u00e2\u00e4\7\61\2\2\u00e3\u00e0\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e8"+
		"\3\2\2\2\u00e5\u00e7\n\b\2\2\u00e6\u00e5\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8"+
		"\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00eb\3\2\2\2\u00ea\u00e8\3\2"+
		"\2\2\u00eb\u00ec\b\"\3\2\u00ecD\3\2\2\2\u00ed\u00ee\7\61\2\2\u00ee\u00ef"+
		"\7,\2\2\u00ef\u00f3\3\2\2\2\u00f0\u00f2\13\2\2\2\u00f1\u00f0\3\2\2\2\u00f2"+
		"\u00f5\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4\u00f9\3\2"+
		"\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00f7\7,\2\2\u00f7\u00fa\7\61\2\2\u00f8"+
		"\u00fa\7\2\2\3\u00f9\u00f6\3\2\2\2\u00f9\u00f8\3\2\2\2\u00fa\u00fb\3\2"+
		"\2\2\u00fb\u00fc\b#\3\2\u00fcF\3\2\2\2\26\2JU\\dlt|\u0084\u008c\u0092"+
		"\u009a\u00cd\u00d0\u00d6\u00dd\u00e3\u00e8\u00f3\u00f9\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}