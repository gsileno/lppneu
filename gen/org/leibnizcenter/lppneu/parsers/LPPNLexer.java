// Generated from /home/giovanni/dev/lppneu/antlr4/LPPN.g4 by ANTLR 4.5.1
package org.leibnizcenter.lppneu.parsers;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LPPNLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, AND=2, OR=3, XOR=4, SEQ=5, PAR=6, ALT=7, NOT=8, NEG=9, CAUSES=10, 
		IS_IMPLIED_BY=11, IS_EQUIVALENT_TO=12, WHEN=13, PLUS=14, MINUS=15, TILDE=16, 
		DOT=17, COMMA=18, LPAR=19, RPAR=20, LACC=21, RACC=22, EQ=23, NEQ=24, GT=25, 
		LT=26, GE=27, LE=28, DOMAIN=29, RANGE=30, INTEGER=31, IDENTIFIER=32, VARIABLE=33, 
		SINGLE_LINE_COMMENT=34, MULTILINE_COMMENT=35;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"WS", "AND", "OR", "XOR", "SEQ", "PAR", "ALT", "NOT", "NEG", "CAUSES", 
		"IS_IMPLIED_BY", "IS_EQUIVALENT_TO", "WHEN", "PLUS", "MINUS", "TILDE", 
		"DOT", "COMMA", "LPAR", "RPAR", "LACC", "RACC", "EQ", "NEQ", "GT", "LT", 
		"GE", "LE", "DOMAIN", "RANGE", "INTEGER", "IDENTIFIER", "VARIABLE", "SINGLE_LINE_COMMENT", 
		"MULTILINE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, "':-'", 
		null, null, "'+'", "'-'", "'~'", "'.'", "','", "'('", "')'", "'{'", "'}'", 
		"'='", "'!='", "'>'", "'<'", "'>='", "'<='", "'#domain'", "'..'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "AND", "OR", "XOR", "SEQ", "PAR", "ALT", "NOT", "NEG", "CAUSES", 
		"IS_IMPLIED_BY", "IS_EQUIVALENT_TO", "WHEN", "PLUS", "MINUS", "TILDE", 
		"DOT", "COMMA", "LPAR", "RPAR", "LACC", "RACC", "EQ", "NEQ", "GT", "LT", 
		"GE", "LE", "DOMAIN", "RANGE", "INTEGER", "IDENTIFIER", "VARIABLE", "SINGLE_LINE_COMMENT", 
		"MULTILINE_COMMENT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2%\u0109\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\6\2K\n\2\r\2\16\2L\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\5\3X\n\3\3\4\3\4\3\4\3\4\3\4\5\4_\n\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\5\5g\n\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6o\n\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\5\7w\n\7\3\b\3\b\3\b\3\b\3\b\3\b\5\b\177\n\b\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\5\t\u0087\n\t\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u008f\n\n\3\13\3\13\3\13\3"+
		"\13\5\13\u0095\n\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00a2"+
		"\n\r\3\16\3\16\3\16\5\16\u00a7\n\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31"+
		"\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \7 \u00d8\n \f "+
		"\16 \u00db\13 \5 \u00dd\n \3!\3!\7!\u00e1\n!\f!\16!\u00e4\13!\3\"\3\""+
		"\7\"\u00e8\n\"\f\"\16\"\u00eb\13\"\3#\3#\3#\5#\u00f0\n#\3#\7#\u00f3\n"+
		"#\f#\16#\u00f6\13#\3#\3#\3$\3$\3$\3$\7$\u00fe\n$\f$\16$\u0101\13$\3$\3"+
		"$\3$\5$\u0106\n$\3$\3$\3\u00ff\2%\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%\3\2\t\5\2\13\f\16\17\"\""+
		"\3\2\63;\3\2\62;\3\2c|\6\2\62;C\\aac|\4\2C\\aa\4\2\f\f\17\17\u011f\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2"+
		"\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2"+
		"\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2"+
		"\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\3"+
		"J\3\2\2\2\5W\3\2\2\2\7^\3\2\2\2\tf\3\2\2\2\13n\3\2\2\2\rv\3\2\2\2\17~"+
		"\3\2\2\2\21\u0086\3\2\2\2\23\u008e\3\2\2\2\25\u0094\3\2\2\2\27\u0096\3"+
		"\2\2\2\31\u00a1\3\2\2\2\33\u00a6\3\2\2\2\35\u00a8\3\2\2\2\37\u00aa\3\2"+
		"\2\2!\u00ac\3\2\2\2#\u00ae\3\2\2\2%\u00b0\3\2\2\2\'\u00b2\3\2\2\2)\u00b4"+
		"\3\2\2\2+\u00b6\3\2\2\2-\u00b8\3\2\2\2/\u00ba\3\2\2\2\61\u00bc\3\2\2\2"+
		"\63\u00bf\3\2\2\2\65\u00c1\3\2\2\2\67\u00c3\3\2\2\29\u00c6\3\2\2\2;\u00c9"+
		"\3\2\2\2=\u00d1\3\2\2\2?\u00dc\3\2\2\2A\u00de\3\2\2\2C\u00e5\3\2\2\2E"+
		"\u00ef\3\2\2\2G\u00f9\3\2\2\2IK\t\2\2\2JI\3\2\2\2KL\3\2\2\2LJ\3\2\2\2"+
		"LM\3\2\2\2MN\3\2\2\2NO\b\2\2\2O\4\3\2\2\2PX\7(\2\2QR\7C\2\2RS\7P\2\2S"+
		"X\7F\2\2TU\7c\2\2UV\7p\2\2VX\7f\2\2WP\3\2\2\2WQ\3\2\2\2WT\3\2\2\2X\6\3"+
		"\2\2\2Y_\7~\2\2Z[\7Q\2\2[_\7T\2\2\\]\7q\2\2]_\7t\2\2^Y\3\2\2\2^Z\3\2\2"+
		"\2^\\\3\2\2\2_\b\3\2\2\2`a\7Z\2\2ab\7Q\2\2bg\7T\2\2cd\7z\2\2de\7q\2\2"+
		"eg\7t\2\2f`\3\2\2\2fc\3\2\2\2g\n\3\2\2\2hi\7U\2\2ij\7G\2\2jo\7S\2\2kl"+
		"\7u\2\2lm\7g\2\2mo\7s\2\2nh\3\2\2\2nk\3\2\2\2o\f\3\2\2\2pq\7R\2\2qr\7"+
		"C\2\2rw\7T\2\2st\7r\2\2tu\7c\2\2uw\7t\2\2vp\3\2\2\2vs\3\2\2\2w\16\3\2"+
		"\2\2xy\7C\2\2yz\7N\2\2z\177\7V\2\2{|\7c\2\2|}\7n\2\2}\177\7v\2\2~x\3\2"+
		"\2\2~{\3\2\2\2\177\20\3\2\2\2\u0080\u0081\7p\2\2\u0081\u0082\7q\2\2\u0082"+
		"\u0087\7v\2\2\u0083\u0084\7P\2\2\u0084\u0085\7Q\2\2\u0085\u0087\7V\2\2"+
		"\u0086\u0080\3\2\2\2\u0086\u0083\3\2\2\2\u0087\22\3\2\2\2\u0088\u0089"+
		"\7p\2\2\u0089\u008a\7g\2\2\u008a\u008f\7i\2\2\u008b\u008c\7P\2\2\u008c"+
		"\u008d\7G\2\2\u008d\u008f\7I\2\2\u008e\u0088\3\2\2\2\u008e\u008b\3\2\2"+
		"\2\u008f\24\3\2\2\2\u0090\u0091\7/\2\2\u0091\u0095\7@\2\2\u0092\u0093"+
		"\7?\2\2\u0093\u0095\7@\2\2\u0094\u0090\3\2\2\2\u0094\u0092\3\2\2\2\u0095"+
		"\26\3\2\2\2\u0096\u0097\7<\2\2\u0097\u0098\7/\2\2\u0098\30\3\2\2\2\u0099"+
		"\u009a\7<\2\2\u009a\u009b\7<\2\2\u009b\u00a2\7/\2\2\u009c\u009d\7<\2\2"+
		"\u009d\u00a2\7?\2\2\u009e\u009f\7<\2\2\u009f\u00a0\7<\2\2\u00a0\u00a2"+
		"\7?\2\2\u00a1\u0099\3\2\2\2\u00a1\u009c\3\2\2\2\u00a1\u009e\3\2\2\2\u00a2"+
		"\32\3\2\2\2\u00a3\u00a7\7<\2\2\u00a4\u00a5\7k\2\2\u00a5\u00a7\7p\2\2\u00a6"+
		"\u00a3\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\34\3\2\2\2\u00a8\u00a9\7-\2\2"+
		"\u00a9\36\3\2\2\2\u00aa\u00ab\7/\2\2\u00ab \3\2\2\2\u00ac\u00ad\7\u0080"+
		"\2\2\u00ad\"\3\2\2\2\u00ae\u00af\7\60\2\2\u00af$\3\2\2\2\u00b0\u00b1\7"+
		".\2\2\u00b1&\3\2\2\2\u00b2\u00b3\7*\2\2\u00b3(\3\2\2\2\u00b4\u00b5\7+"+
		"\2\2\u00b5*\3\2\2\2\u00b6\u00b7\7}\2\2\u00b7,\3\2\2\2\u00b8\u00b9\7\177"+
		"\2\2\u00b9.\3\2\2\2\u00ba\u00bb\7?\2\2\u00bb\60\3\2\2\2\u00bc\u00bd\7"+
		"#\2\2\u00bd\u00be\7?\2\2\u00be\62\3\2\2\2\u00bf\u00c0\7@\2\2\u00c0\64"+
		"\3\2\2\2\u00c1\u00c2\7>\2\2\u00c2\66\3\2\2\2\u00c3\u00c4\7@\2\2\u00c4"+
		"\u00c5\7?\2\2\u00c58\3\2\2\2\u00c6\u00c7\7>\2\2\u00c7\u00c8\7?\2\2\u00c8"+
		":\3\2\2\2\u00c9\u00ca\7%\2\2\u00ca\u00cb\7f\2\2\u00cb\u00cc\7q\2\2\u00cc"+
		"\u00cd\7o\2\2\u00cd\u00ce\7c\2\2\u00ce\u00cf\7k\2\2\u00cf\u00d0\7p\2\2"+
		"\u00d0<\3\2\2\2\u00d1\u00d2\7\60\2\2\u00d2\u00d3\7\60\2\2\u00d3>\3\2\2"+
		"\2\u00d4\u00dd\7\62\2\2\u00d5\u00d9\t\3\2\2\u00d6\u00d8\t\4\2\2\u00d7"+
		"\u00d6\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2"+
		"\2\2\u00da\u00dd\3\2\2\2\u00db\u00d9\3\2\2\2\u00dc\u00d4\3\2\2\2\u00dc"+
		"\u00d5\3\2\2\2\u00dd@\3\2\2\2\u00de\u00e2\t\5\2\2\u00df\u00e1\t\6\2\2"+
		"\u00e0\u00df\3\2\2\2\u00e1\u00e4\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3"+
		"\3\2\2\2\u00e3B\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e5\u00e9\t\7\2\2\u00e6"+
		"\u00e8\t\6\2\2\u00e7\u00e6\3\2\2\2\u00e8\u00eb\3\2\2\2\u00e9\u00e7\3\2"+
		"\2\2\u00e9\u00ea\3\2\2\2\u00eaD\3\2\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00f0"+
		"\7\'\2\2\u00ed\u00ee\7\61\2\2\u00ee\u00f0\7\61\2\2\u00ef\u00ec\3\2\2\2"+
		"\u00ef\u00ed\3\2\2\2\u00f0\u00f4\3\2\2\2\u00f1\u00f3\n\b\2\2\u00f2\u00f1"+
		"\3\2\2\2\u00f3\u00f6\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5"+
		"\u00f7\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f7\u00f8\b#\3\2\u00f8F\3\2\2\2\u00f9"+
		"\u00fa\7\61\2\2\u00fa\u00fb\7,\2\2\u00fb\u00ff\3\2\2\2\u00fc\u00fe\13"+
		"\2\2\2\u00fd\u00fc\3\2\2\2\u00fe\u0101\3\2\2\2\u00ff\u0100\3\2\2\2\u00ff"+
		"\u00fd\3\2\2\2\u0100\u0105\3\2\2\2\u0101\u00ff\3\2\2\2\u0102\u0103\7,"+
		"\2\2\u0103\u0106\7\61\2\2\u0104\u0106\7\2\2\3\u0105\u0102\3\2\2\2\u0105"+
		"\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0108\b$\3\2\u0108H\3\2\2\2\27"+
		"\2LW^fnv~\u0086\u008e\u0094\u00a1\u00a6\u00d9\u00dc\u00e2\u00e9\u00ef"+
		"\u00f4\u00ff\u0105\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}