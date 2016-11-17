// Generated from /home/giovanni/dev/lppneu/antlr4/LPPN.g4 by ANTLR 4.5.1
package lppneu.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LPPNParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, AND=2, OR=3, XOR=4, SEQ=5, PAR=6, ALT=7, NEG=8, NOT=9, NULL=10, 
		WHEN=11, CAUSES=12, IS_IMPLIED_BY=13, IS_EQUIVALENT_TO=14, PLUS=15, MINUS=16, 
		TILDE=17, DOT=18, COMMA=19, COLON=20, LPAR=21, RPAR=22, LACC=23, RACC=24, 
		EQ=25, NEQ=26, GT=27, LT=28, GE=29, LE=30, DOMAIN=31, RANGE=32, INTEGER=33, 
		IDENTIFIER=34, VARIABLE=35, SINGLE_LINE_COMMENT=36, MULTILINE_COMMENT=37;
	public static final int
		RULE_program = 0, RULE_situationfact = 1, RULE_eventfact = 2, RULE_logicrule = 3, 
		RULE_normrule = 4, RULE_constraint = 5, RULE_causalrule = 6, RULE_head = 7, 
		RULE_body = 8, RULE_list_literals = 9, RULE_situation = 10, RULE_head_expression = 11, 
		RULE_event = 12, RULE_operation = 13, RULE_body_expression = 14, RULE_body_constraint = 15, 
		RULE_num_expression = 16, RULE_ext_literal = 17, RULE_literal = 18, RULE_pos_literal = 19, 
		RULE_list_parameters = 20, RULE_parameter = 21, RULE_predicate = 22, RULE_identifier = 23, 
		RULE_constant = 24, RULE_variable_structure = 25, RULE_variable = 26;
	public static final String[] ruleNames = {
		"program", "situationfact", "eventfact", "logicrule", "normrule", "constraint", 
		"causalrule", "head", "body", "list_literals", "situation", "head_expression", 
		"event", "operation", "body_expression", "body_constraint", "num_expression", 
		"ext_literal", "literal", "pos_literal", "list_parameters", "parameter", 
		"predicate", "identifier", "constant", "variable_structure", "variable"
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

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "LPPN.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LPPNParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(LPPNParser.EOF, 0); }
		public List<SituationfactContext> situationfact() {
			return getRuleContexts(SituationfactContext.class);
		}
		public SituationfactContext situationfact(int i) {
			return getRuleContext(SituationfactContext.class,i);
		}
		public List<EventfactContext> eventfact() {
			return getRuleContexts(EventfactContext.class);
		}
		public EventfactContext eventfact(int i) {
			return getRuleContext(EventfactContext.class,i);
		}
		public List<LogicruleContext> logicrule() {
			return getRuleContexts(LogicruleContext.class);
		}
		public LogicruleContext logicrule(int i) {
			return getRuleContext(LogicruleContext.class,i);
		}
		public List<CausalruleContext> causalrule() {
			return getRuleContexts(CausalruleContext.class);
		}
		public CausalruleContext causalrule(int i) {
			return getRuleContext(CausalruleContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEG) | (1L << NOT) | (1L << NULL) | (1L << CAUSES) | (1L << IS_IMPLIED_BY) | (1L << LPAR) | (1L << INTEGER) | (1L << IDENTIFIER) | (1L << VARIABLE))) != 0)) {
				{
				setState(58);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(54);
					situationfact();
					}
					break;
				case 2:
					{
					setState(55);
					eventfact();
					}
					break;
				case 3:
					{
					setState(56);
					logicrule();
					}
					break;
				case 4:
					{
					setState(57);
					causalrule();
					}
					break;
				}
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SituationfactContext extends ParserRuleContext {
		public HeadContext head() {
			return getRuleContext(HeadContext.class,0);
		}
		public TerminalNode DOT() { return getToken(LPPNParser.DOT, 0); }
		public SituationfactContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_situationfact; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterSituationfact(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitSituationfact(this);
		}
	}

	public final SituationfactContext situationfact() throws RecognitionException {
		SituationfactContext _localctx = new SituationfactContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_situationfact);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			head();
			setState(66);
			match(DOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EventfactContext extends ParserRuleContext {
		public TerminalNode CAUSES() { return getToken(LPPNParser.CAUSES, 0); }
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public TerminalNode DOT() { return getToken(LPPNParser.DOT, 0); }
		public EventfactContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eventfact; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterEventfact(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitEventfact(this);
		}
	}

	public final EventfactContext eventfact() throws RecognitionException {
		EventfactContext _localctx = new EventfactContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_eventfact);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(CAUSES);
			setState(69);
			operation(0);
			setState(70);
			match(DOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicruleContext extends ParserRuleContext {
		public NormruleContext normrule() {
			return getRuleContext(NormruleContext.class,0);
		}
		public ConstraintContext constraint() {
			return getRuleContext(ConstraintContext.class,0);
		}
		public LogicruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicrule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterLogicrule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitLogicrule(this);
		}
	}

	public final LogicruleContext logicrule() throws RecognitionException {
		LogicruleContext _localctx = new LogicruleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_logicrule);
		try {
			setState(74);
			switch (_input.LA(1)) {
			case NEG:
			case NULL:
			case LPAR:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				normrule();
				}
				break;
			case IS_IMPLIED_BY:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				constraint();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NormruleContext extends ParserRuleContext {
		public HeadContext head() {
			return getRuleContext(HeadContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode DOT() { return getToken(LPPNParser.DOT, 0); }
		public TerminalNode IS_IMPLIED_BY() { return getToken(LPPNParser.IS_IMPLIED_BY, 0); }
		public TerminalNode IS_EQUIVALENT_TO() { return getToken(LPPNParser.IS_EQUIVALENT_TO, 0); }
		public NormruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_normrule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterNormrule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitNormrule(this);
		}
	}

	public final NormruleContext normrule() throws RecognitionException {
		NormruleContext _localctx = new NormruleContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_normrule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			head();
			setState(77);
			_la = _input.LA(1);
			if ( !(_la==IS_IMPLIED_BY || _la==IS_EQUIVALENT_TO) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(78);
			body();
			setState(79);
			match(DOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstraintContext extends ParserRuleContext {
		public TerminalNode IS_IMPLIED_BY() { return getToken(LPPNParser.IS_IMPLIED_BY, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode DOT() { return getToken(LPPNParser.DOT, 0); }
		public ConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitConstraint(this);
		}
	}

	public final ConstraintContext constraint() throws RecognitionException {
		ConstraintContext _localctx = new ConstraintContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(IS_IMPLIED_BY);
			setState(82);
			body();
			setState(83);
			match(DOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CausalruleContext extends ParserRuleContext {
		public Body_expressionContext body_expression() {
			return getRuleContext(Body_expressionContext.class,0);
		}
		public TerminalNode CAUSES() { return getToken(LPPNParser.CAUSES, 0); }
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public TerminalNode DOT() { return getToken(LPPNParser.DOT, 0); }
		public CausalruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_causalrule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterCausalrule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitCausalrule(this);
		}
	}

	public final CausalruleContext causalrule() throws RecognitionException {
		CausalruleContext _localctx = new CausalruleContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_causalrule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			body_expression(0);
			setState(86);
			match(CAUSES);
			setState(87);
			operation(0);
			setState(88);
			match(DOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeadContext extends ParserRuleContext {
		public Head_expressionContext head_expression() {
			return getRuleContext(Head_expressionContext.class,0);
		}
		public HeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_head; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterHead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitHead(this);
		}
	}

	public final HeadContext head() throws RecognitionException {
		HeadContext _localctx = new HeadContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_head);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			head_expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BodyContext extends ParserRuleContext {
		public Body_expressionContext body_expression() {
			return getRuleContext(Body_expressionContext.class,0);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitBody(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			body_expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class List_literalsContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(LPPNParser.COMMA, 0); }
		public List_literalsContext list_literals() {
			return getRuleContext(List_literalsContext.class,0);
		}
		public List_literalsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_literals; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterList_literals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitList_literals(this);
		}
	}

	public final List_literalsContext list_literals() throws RecognitionException {
		List_literalsContext _localctx = new List_literalsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_list_literals);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			literal();
			setState(97);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(95);
				match(COMMA);
				setState(96);
				list_literals();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SituationContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public SituationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_situation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterSituation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitSituation(this);
		}
	}

	public final SituationContext situation() throws RecognitionException {
		SituationContext _localctx = new SituationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_situation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			literal();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Head_expressionContext extends ParserRuleContext {
		public TerminalNode NEG() { return getToken(LPPNParser.NEG, 0); }
		public List<Head_expressionContext> head_expression() {
			return getRuleContexts(Head_expressionContext.class);
		}
		public Head_expressionContext head_expression(int i) {
			return getRuleContext(Head_expressionContext.class,i);
		}
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public TerminalNode WHEN() { return getToken(LPPNParser.WHEN, 0); }
		public SituationContext situation() {
			return getRuleContext(SituationContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(LPPNParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(LPPNParser.RPAR, 0); }
		public TerminalNode SEQ() { return getToken(LPPNParser.SEQ, 0); }
		public TerminalNode PAR() { return getToken(LPPNParser.PAR, 0); }
		public TerminalNode ALT() { return getToken(LPPNParser.ALT, 0); }
		public TerminalNode AND() { return getToken(LPPNParser.AND, 0); }
		public TerminalNode OR() { return getToken(LPPNParser.OR, 0); }
		public TerminalNode XOR() { return getToken(LPPNParser.XOR, 0); }
		public Head_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_head_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterHead_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitHead_expression(this);
		}
	}

	public final Head_expressionContext head_expression() throws RecognitionException {
		return head_expression(0);
	}

	private Head_expressionContext head_expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Head_expressionContext _localctx = new Head_expressionContext(_ctx, _parentState);
		Head_expressionContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_head_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(102);
				match(NEG);
				setState(103);
				head_expression(7);
				}
				break;
			case 2:
				{
				setState(104);
				operation(0);
				setState(105);
				match(WHEN);
				setState(106);
				head_expression(6);
				}
				break;
			case 3:
				{
				setState(108);
				situation();
				}
				break;
			case 4:
				{
				setState(109);
				match(LPAR);
				setState(110);
				head_expression(0);
				setState(111);
				match(RPAR);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(129);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(127);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new Head_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_head_expression);
						setState(115);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(116);
						match(SEQ);
						setState(117);
						head_expression(5);
						}
						break;
					case 2:
						{
						_localctx = new Head_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_head_expression);
						setState(118);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(119);
						_la = _input.LA(1);
						if ( !(_la==PAR || _la==ALT) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(120);
						head_expression(4);
						}
						break;
					case 3:
						{
						_localctx = new Head_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_head_expression);
						setState(121);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(122);
						match(AND);
						setState(123);
						head_expression(3);
						}
						break;
					case 4:
						{
						_localctx = new Head_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_head_expression);
						setState(124);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(125);
						_la = _input.LA(1);
						if ( !(_la==OR || _la==XOR) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(126);
						head_expression(2);
						}
						break;
					}
					} 
				}
				setState(131);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class EventContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public EventContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_event; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitEvent(this);
		}
	}

	public final EventContext event() throws RecognitionException {
		EventContext _localctx = new EventContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_event);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			literal();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationContext extends ParserRuleContext {
		public EventContext event() {
			return getRuleContext(EventContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(LPPNParser.LPAR, 0); }
		public List<OperationContext> operation() {
			return getRuleContexts(OperationContext.class);
		}
		public OperationContext operation(int i) {
			return getRuleContext(OperationContext.class,i);
		}
		public TerminalNode RPAR() { return getToken(LPPNParser.RPAR, 0); }
		public TerminalNode SEQ() { return getToken(LPPNParser.SEQ, 0); }
		public TerminalNode PAR() { return getToken(LPPNParser.PAR, 0); }
		public TerminalNode ALT() { return getToken(LPPNParser.ALT, 0); }
		public OperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitOperation(this);
		}
	}

	public final OperationContext operation() throws RecognitionException {
		return operation(0);
	}

	private OperationContext operation(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		OperationContext _localctx = new OperationContext(_ctx, _parentState);
		OperationContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_operation, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			switch (_input.LA(1)) {
			case NEG:
			case NULL:
			case IDENTIFIER:
				{
				setState(135);
				event();
				}
				break;
			case LPAR:
				{
				setState(136);
				match(LPAR);
				setState(137);
				operation(0);
				setState(138);
				match(RPAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(150);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(148);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new OperationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_operation);
						setState(142);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(143);
						match(SEQ);
						setState(144);
						operation(3);
						}
						break;
					case 2:
						{
						_localctx = new OperationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_operation);
						setState(145);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(146);
						_la = _input.LA(1);
						if ( !(_la==PAR || _la==ALT) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(147);
						operation(2);
						}
						break;
					}
					} 
				}
				setState(152);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Body_expressionContext extends ParserRuleContext {
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public TerminalNode WHEN() { return getToken(LPPNParser.WHEN, 0); }
		public List<Body_expressionContext> body_expression() {
			return getRuleContexts(Body_expressionContext.class);
		}
		public Body_expressionContext body_expression(int i) {
			return getRuleContext(Body_expressionContext.class,i);
		}
		public TerminalNode NOT() { return getToken(LPPNParser.NOT, 0); }
		public TerminalNode NEG() { return getToken(LPPNParser.NEG, 0); }
		public SituationContext situation() {
			return getRuleContext(SituationContext.class,0);
		}
		public Body_constraintContext body_constraint() {
			return getRuleContext(Body_constraintContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(LPPNParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(LPPNParser.RPAR, 0); }
		public TerminalNode SEQ() { return getToken(LPPNParser.SEQ, 0); }
		public TerminalNode PAR() { return getToken(LPPNParser.PAR, 0); }
		public TerminalNode ALT() { return getToken(LPPNParser.ALT, 0); }
		public TerminalNode AND() { return getToken(LPPNParser.AND, 0); }
		public TerminalNode OR() { return getToken(LPPNParser.OR, 0); }
		public TerminalNode XOR() { return getToken(LPPNParser.XOR, 0); }
		public Body_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterBody_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitBody_expression(this);
		}
	}

	public final Body_expressionContext body_expression() throws RecognitionException {
		return body_expression(0);
	}

	private Body_expressionContext body_expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Body_expressionContext _localctx = new Body_expressionContext(_ctx, _parentState);
		Body_expressionContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_body_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(154);
				operation(0);
				setState(155);
				match(WHEN);
				setState(156);
				body_expression(8);
				}
				break;
			case 2:
				{
				setState(158);
				match(NOT);
				setState(159);
				body_expression(4);
				}
				break;
			case 3:
				{
				setState(160);
				match(NEG);
				setState(161);
				body_expression(3);
				}
				break;
			case 4:
				{
				setState(162);
				situation();
				}
				break;
			case 5:
				{
				setState(163);
				body_constraint();
				}
				break;
			case 6:
				{
				setState(164);
				match(LPAR);
				setState(165);
				body_expression(0);
				setState(166);
				match(RPAR);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(184);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(182);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new Body_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_body_expression);
						setState(170);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(171);
						match(SEQ);
						setState(172);
						body_expression(7);
						}
						break;
					case 2:
						{
						_localctx = new Body_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_body_expression);
						setState(173);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(174);
						_la = _input.LA(1);
						if ( !(_la==PAR || _la==ALT) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(175);
						body_expression(6);
						}
						break;
					case 3:
						{
						_localctx = new Body_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_body_expression);
						setState(176);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(177);
						match(AND);
						setState(178);
						body_expression(3);
						}
						break;
					case 4:
						{
						_localctx = new Body_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_body_expression);
						setState(179);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(180);
						_la = _input.LA(1);
						if ( !(_la==OR || _la==XOR) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(181);
						body_expression(2);
						}
						break;
					}
					} 
				}
				setState(186);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Body_constraintContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(LPPNParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(LPPNParser.NEQ, 0); }
		public TerminalNode LT() { return getToken(LPPNParser.LT, 0); }
		public TerminalNode LE() { return getToken(LPPNParser.LE, 0); }
		public TerminalNode GT() { return getToken(LPPNParser.GT, 0); }
		public TerminalNode GE() { return getToken(LPPNParser.GE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public List<TerminalNode> INTEGER() { return getTokens(LPPNParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(LPPNParser.INTEGER, i);
		}
		public List<Num_expressionContext> num_expression() {
			return getRuleContexts(Num_expressionContext.class);
		}
		public Num_expressionContext num_expression(int i) {
			return getRuleContext(Num_expressionContext.class,i);
		}
		public TerminalNode IDENTIFIER() { return getToken(LPPNParser.IDENTIFIER, 0); }
		public TerminalNode VARIABLE() { return getToken(LPPNParser.VARIABLE, 0); }
		public Body_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterBody_constraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitBody_constraint(this);
		}
	}

	public final Body_constraintContext body_constraint() throws RecognitionException {
		Body_constraintContext _localctx = new Body_constraintContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_body_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(187);
				identifier();
				}
				break;
			case 2:
				{
				setState(188);
				variable();
				}
				break;
			case 3:
				{
				setState(189);
				match(INTEGER);
				}
				break;
			case 4:
				{
				setState(190);
				num_expression();
				}
				break;
			}
			setState(193);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << GT) | (1L << LT) | (1L << GE) | (1L << LE))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(198);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(194);
				match(IDENTIFIER);
				}
				break;
			case 2:
				{
				setState(195);
				match(VARIABLE);
				}
				break;
			case 3:
				{
				setState(196);
				match(INTEGER);
				}
				break;
			case 4:
				{
				setState(197);
				num_expression();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Num_expressionContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(LPPNParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(LPPNParser.MINUS, 0); }
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public List<TerminalNode> INTEGER() { return getTokens(LPPNParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(LPPNParser.INTEGER, i);
		}
		public Num_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_num_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterNum_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitNum_expression(this);
		}
	}

	public final Num_expressionContext num_expression() throws RecognitionException {
		Num_expressionContext _localctx = new Num_expressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_num_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			switch (_input.LA(1)) {
			case VARIABLE:
				{
				setState(200);
				variable();
				}
				break;
			case INTEGER:
				{
				setState(201);
				match(INTEGER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(204);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(207);
			switch (_input.LA(1)) {
			case VARIABLE:
				{
				setState(205);
				variable();
				}
				break;
			case INTEGER:
				{
				setState(206);
				match(INTEGER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ext_literalContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode NOT() { return getToken(LPPNParser.NOT, 0); }
		public Ext_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ext_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterExt_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitExt_literal(this);
		}
	}

	public final Ext_literalContext ext_literal() throws RecognitionException {
		Ext_literalContext _localctx = new Ext_literalContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_ext_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(209);
				match(NOT);
				}
			}

			setState(212);
			literal();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public Pos_literalContext pos_literal() {
			return getRuleContext(Pos_literalContext.class,0);
		}
		public TerminalNode NULL() { return getToken(LPPNParser.NULL, 0); }
		public TerminalNode NEG() { return getToken(LPPNParser.NEG, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			_la = _input.LA(1);
			if (_la==NEG || _la==NULL) {
				{
				setState(214);
				_la = _input.LA(1);
				if ( !(_la==NEG || _la==NULL) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(217);
			pos_literal();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Pos_literalContext extends ParserRuleContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(LPPNParser.LPAR, 0); }
		public List_parametersContext list_parameters() {
			return getRuleContext(List_parametersContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(LPPNParser.RPAR, 0); }
		public Pos_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pos_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterPos_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitPos_literal(this);
		}
	}

	public final Pos_literalContext pos_literal() throws RecognitionException {
		Pos_literalContext _localctx = new Pos_literalContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_pos_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			predicate();
			setState(224);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(220);
				match(LPAR);
				setState(221);
				list_parameters();
				setState(222);
				match(RPAR);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class List_parametersContext extends ParserRuleContext {
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(LPPNParser.COMMA, 0); }
		public List_parametersContext list_parameters() {
			return getRuleContext(List_parametersContext.class,0);
		}
		public List_parametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterList_parameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitList_parameters(this);
		}
	}

	public final List_parametersContext list_parameters() throws RecognitionException {
		List_parametersContext _localctx = new List_parametersContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_list_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			parameter();
			setState(229);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(227);
				match(COMMA);
				setState(228);
				list_parameters();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public Variable_structureContext variable_structure() {
			return getRuleContext(Variable_structureContext.class,0);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public Pos_literalContext pos_literal() {
			return getRuleContext(Pos_literalContext.class,0);
		}
		public Num_expressionContext num_expression() {
			return getRuleContext(Num_expressionContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitParameter(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_parameter);
		try {
			setState(235);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(231);
				variable_structure();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(232);
				constant();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(233);
				pos_literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(234);
				num_expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LPPNParser.IDENTIFIER, 0); }
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitPredicate(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LPPNParser.IDENTIFIER, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(LPPNParser.INTEGER, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitConstant(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variable_structureContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TerminalNode COLON() { return getToken(LPPNParser.COLON, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public Variable_structureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_structure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterVariable_structure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitVariable_structure(this);
		}
	}

	public final Variable_structureContext variable_structure() throws RecognitionException {
		Variable_structureContext _localctx = new Variable_structureContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_variable_structure);
		try {
			setState(252);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(243);
				variable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(244);
				variable();
				setState(245);
				match(COLON);
				setState(246);
				variable();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(248);
				variable();
				setState(249);
				match(COLON);
				setState(250);
				constant();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(LPPNParser.VARIABLE, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(VARIABLE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return head_expression_sempred((Head_expressionContext)_localctx, predIndex);
		case 13:
			return operation_sempred((OperationContext)_localctx, predIndex);
		case 14:
			return body_expression_sempred((Body_expressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean head_expression_sempred(Head_expressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		case 2:
			return precpred(_ctx, 2);
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean operation_sempred(OperationContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 2);
		case 5:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean body_expression_sempred(Body_expressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 6);
		case 7:
			return precpred(_ctx, 5);
		case 8:
			return precpred(_ctx, 2);
		case 9:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\'\u0103\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\7\2=\n\2\f\2\16\2@\13\2"+
		"\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\5\5M\n\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13"+
		"\5\13d\n\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5"+
		"\rt\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u0082\n\r"+
		"\f\r\16\r\u0085\13\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u008f"+
		"\n\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u0097\n\17\f\17\16\17\u009a\13"+
		"\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\5\20\u00ab\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\7\20\u00b9\n\20\f\20\16\20\u00bc\13\20\3\21\3\21\3\21"+
		"\3\21\5\21\u00c2\n\21\3\21\3\21\3\21\3\21\3\21\5\21\u00c9\n\21\3\22\3"+
		"\22\5\22\u00cd\n\22\3\22\3\22\3\22\5\22\u00d2\n\22\3\23\5\23\u00d5\n\23"+
		"\3\23\3\23\3\24\5\24\u00da\n\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\5\25"+
		"\u00e3\n\25\3\26\3\26\3\26\5\26\u00e8\n\26\3\27\3\27\3\27\3\27\5\27\u00ee"+
		"\n\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\5\33\u00ff\n\33\3\34\3\34\3\34\2\5\30\34\36\35\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66\2\b\3\2\17\20\3\2\b\t"+
		"\3\2\5\6\3\2\33 \3\2\21\22\4\2\n\n\f\f\u0111\2>\3\2\2\2\4C\3\2\2\2\6F"+
		"\3\2\2\2\bL\3\2\2\2\nN\3\2\2\2\fS\3\2\2\2\16W\3\2\2\2\20\\\3\2\2\2\22"+
		"^\3\2\2\2\24`\3\2\2\2\26e\3\2\2\2\30s\3\2\2\2\32\u0086\3\2\2\2\34\u008e"+
		"\3\2\2\2\36\u00aa\3\2\2\2 \u00c1\3\2\2\2\"\u00cc\3\2\2\2$\u00d4\3\2\2"+
		"\2&\u00d9\3\2\2\2(\u00dd\3\2\2\2*\u00e4\3\2\2\2,\u00ed\3\2\2\2.\u00ef"+
		"\3\2\2\2\60\u00f1\3\2\2\2\62\u00f3\3\2\2\2\64\u00fe\3\2\2\2\66\u0100\3"+
		"\2\2\28=\5\4\3\29=\5\6\4\2:=\5\b\5\2;=\5\16\b\2<8\3\2\2\2<9\3\2\2\2<:"+
		"\3\2\2\2<;\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?A\3\2\2\2@>\3\2\2\2A"+
		"B\7\2\2\3B\3\3\2\2\2CD\5\20\t\2DE\7\24\2\2E\5\3\2\2\2FG\7\16\2\2GH\5\34"+
		"\17\2HI\7\24\2\2I\7\3\2\2\2JM\5\n\6\2KM\5\f\7\2LJ\3\2\2\2LK\3\2\2\2M\t"+
		"\3\2\2\2NO\5\20\t\2OP\t\2\2\2PQ\5\22\n\2QR\7\24\2\2R\13\3\2\2\2ST\7\17"+
		"\2\2TU\5\22\n\2UV\7\24\2\2V\r\3\2\2\2WX\5\36\20\2XY\7\16\2\2YZ\5\34\17"+
		"\2Z[\7\24\2\2[\17\3\2\2\2\\]\5\30\r\2]\21\3\2\2\2^_\5\36\20\2_\23\3\2"+
		"\2\2`c\5&\24\2ab\7\25\2\2bd\5\24\13\2ca\3\2\2\2cd\3\2\2\2d\25\3\2\2\2"+
		"ef\5&\24\2f\27\3\2\2\2gh\b\r\1\2hi\7\n\2\2it\5\30\r\tjk\5\34\17\2kl\7"+
		"\r\2\2lm\5\30\r\bmt\3\2\2\2nt\5\26\f\2op\7\27\2\2pq\5\30\r\2qr\7\30\2"+
		"\2rt\3\2\2\2sg\3\2\2\2sj\3\2\2\2sn\3\2\2\2so\3\2\2\2t\u0083\3\2\2\2uv"+
		"\f\6\2\2vw\7\7\2\2w\u0082\5\30\r\7xy\f\5\2\2yz\t\3\2\2z\u0082\5\30\r\6"+
		"{|\f\4\2\2|}\7\4\2\2}\u0082\5\30\r\5~\177\f\3\2\2\177\u0080\t\4\2\2\u0080"+
		"\u0082\5\30\r\4\u0081u\3\2\2\2\u0081x\3\2\2\2\u0081{\3\2\2\2\u0081~\3"+
		"\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084"+
		"\31\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0087\5&\24\2\u0087\33\3\2\2\2\u0088"+
		"\u0089\b\17\1\2\u0089\u008f\5\32\16\2\u008a\u008b\7\27\2\2\u008b\u008c"+
		"\5\34\17\2\u008c\u008d\7\30\2\2\u008d\u008f\3\2\2\2\u008e\u0088\3\2\2"+
		"\2\u008e\u008a\3\2\2\2\u008f\u0098\3\2\2\2\u0090\u0091\f\4\2\2\u0091\u0092"+
		"\7\7\2\2\u0092\u0097\5\34\17\5\u0093\u0094\f\3\2\2\u0094\u0095\t\3\2\2"+
		"\u0095\u0097\5\34\17\4\u0096\u0090\3\2\2\2\u0096\u0093\3\2\2\2\u0097\u009a"+
		"\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\35\3\2\2\2\u009a"+
		"\u0098\3\2\2\2\u009b\u009c\b\20\1\2\u009c\u009d\5\34\17\2\u009d\u009e"+
		"\7\r\2\2\u009e\u009f\5\36\20\n\u009f\u00ab\3\2\2\2\u00a0\u00a1\7\13\2"+
		"\2\u00a1\u00ab\5\36\20\6\u00a2\u00a3\7\n\2\2\u00a3\u00ab\5\36\20\5\u00a4"+
		"\u00ab\5\26\f\2\u00a5\u00ab\5 \21\2\u00a6\u00a7\7\27\2\2\u00a7\u00a8\5"+
		"\36\20\2\u00a8\u00a9\7\30\2\2\u00a9\u00ab\3\2\2\2\u00aa\u009b\3\2\2\2"+
		"\u00aa\u00a0\3\2\2\2\u00aa\u00a2\3\2\2\2\u00aa\u00a4\3\2\2\2\u00aa\u00a5"+
		"\3\2\2\2\u00aa\u00a6\3\2\2\2\u00ab\u00ba\3\2\2\2\u00ac\u00ad\f\b\2\2\u00ad"+
		"\u00ae\7\7\2\2\u00ae\u00b9\5\36\20\t\u00af\u00b0\f\7\2\2\u00b0\u00b1\t"+
		"\3\2\2\u00b1\u00b9\5\36\20\b\u00b2\u00b3\f\4\2\2\u00b3\u00b4\7\4\2\2\u00b4"+
		"\u00b9\5\36\20\5\u00b5\u00b6\f\3\2\2\u00b6\u00b7\t\4\2\2\u00b7\u00b9\5"+
		"\36\20\4\u00b8\u00ac\3\2\2\2\u00b8\u00af\3\2\2\2\u00b8\u00b2\3\2\2\2\u00b8"+
		"\u00b5\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2"+
		"\2\2\u00bb\37\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00c2\5\60\31\2\u00be"+
		"\u00c2\5\66\34\2\u00bf\u00c2\7#\2\2\u00c0\u00c2\5\"\22\2\u00c1\u00bd\3"+
		"\2\2\2\u00c1\u00be\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c0\3\2\2\2\u00c2"+
		"\u00c3\3\2\2\2\u00c3\u00c8\t\5\2\2\u00c4\u00c9\7$\2\2\u00c5\u00c9\7%\2"+
		"\2\u00c6\u00c9\7#\2\2\u00c7\u00c9\5\"\22\2\u00c8\u00c4\3\2\2\2\u00c8\u00c5"+
		"\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c7\3\2\2\2\u00c9!\3\2\2\2\u00ca"+
		"\u00cd\5\66\34\2\u00cb\u00cd\7#\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cb\3"+
		"\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d1\t\6\2\2\u00cf\u00d2\5\66\34\2\u00d0"+
		"\u00d2\7#\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d0\3\2\2\2\u00d2#\3\2\2\2\u00d3"+
		"\u00d5\7\13\2\2\u00d4\u00d3\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\3"+
		"\2\2\2\u00d6\u00d7\5&\24\2\u00d7%\3\2\2\2\u00d8\u00da\t\7\2\2\u00d9\u00d8"+
		"\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00dc\5(\25\2\u00dc"+
		"\'\3\2\2\2\u00dd\u00e2\5.\30\2\u00de\u00df\7\27\2\2\u00df\u00e0\5*\26"+
		"\2\u00e0\u00e1\7\30\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00de\3\2\2\2\u00e2"+
		"\u00e3\3\2\2\2\u00e3)\3\2\2\2\u00e4\u00e7\5,\27\2\u00e5\u00e6\7\25\2\2"+
		"\u00e6\u00e8\5*\26\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8+\3"+
		"\2\2\2\u00e9\u00ee\5\64\33\2\u00ea\u00ee\5\62\32\2\u00eb\u00ee\5(\25\2"+
		"\u00ec\u00ee\5\"\22\2\u00ed\u00e9\3\2\2\2\u00ed\u00ea\3\2\2\2\u00ed\u00eb"+
		"\3\2\2\2\u00ed\u00ec\3\2\2\2\u00ee-\3\2\2\2\u00ef\u00f0\7$\2\2\u00f0/"+
		"\3\2\2\2\u00f1\u00f2\7$\2\2\u00f2\61\3\2\2\2\u00f3\u00f4\7#\2\2\u00f4"+
		"\63\3\2\2\2\u00f5\u00ff\5\66\34\2\u00f6\u00f7\5\66\34\2\u00f7\u00f8\7"+
		"\26\2\2\u00f8\u00f9\5\66\34\2\u00f9\u00ff\3\2\2\2\u00fa\u00fb\5\66\34"+
		"\2\u00fb\u00fc\7\26\2\2\u00fc\u00fd\5\62\32\2\u00fd\u00ff\3\2\2\2\u00fe"+
		"\u00f5\3\2\2\2\u00fe\u00f6\3\2\2\2\u00fe\u00fa\3\2\2\2\u00ff\65\3\2\2"+
		"\2\u0100\u0101\7%\2\2\u0101\67\3\2\2\2\31<>Lcs\u0081\u0083\u008e\u0096"+
		"\u0098\u00aa\u00b8\u00ba\u00c1\u00c8\u00cc\u00d1\u00d4\u00d9\u00e2\u00e7"+
		"\u00ed\u00fe";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}