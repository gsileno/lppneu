// Generated from /home/giovanni/dev/lppneu/antlr4/LPPN.g4 by ANTLR 4.5.1
package org.leibnizcenter.lppneu.parsers;
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
		WS=1, AND=2, OR=3, XOR=4, SEQ=5, PAR=6, ALT=7, NOT=8, NEG=9, CAUSES=10, 
		IS_IMPLIED_BY=11, IS_EQUIVALENT_TO=12, WHEN=13, PLUS=14, MINUS=15, TILDE=16, 
		DOT=17, COMMA=18, LPAR=19, RPAR=20, LACC=21, RACC=22, EQ=23, NEQ=24, GT=25, 
		LT=26, GE=27, LE=28, DOMAIN=29, RANGE=30, INTEGER=31, IDENTIFIER=32, VARIABLE=33, 
		SINGLE_LINE_COMMENT=34, MULTILINE_COMMENT=35;
	public static final int
		RULE_program = 0, RULE_situationfact = 1, RULE_eventfact = 2, RULE_logicrule = 3, 
		RULE_normrule = 4, RULE_constraint = 5, RULE_causalrule = 6, RULE_head = 7, 
		RULE_body = 8, RULE_list_literals = 9, RULE_head_situation = 10, RULE_head_expression = 11, 
		RULE_event = 12, RULE_operation = 13, RULE_body_situation = 14, RULE_body_expression = 15, 
		RULE_body_constraint = 16, RULE_num_expression = 17, RULE_ext_literal = 18, 
		RULE_literal = 19, RULE_pos_literal = 20, RULE_list_parameters = 21, RULE_parameter = 22, 
		RULE_predicate = 23, RULE_identifier = 24, RULE_constant = 25, RULE_variable = 26;
	public static final String[] ruleNames = {
		"program", "situationfact", "eventfact", "logicrule", "normrule", "constraint", 
		"causalrule", "head", "body", "list_literals", "head_situation", "head_expression", 
		"event", "operation", "body_situation", "body_expression", "body_constraint", 
		"num_expression", "ext_literal", "literal", "pos_literal", "list_parameters", 
		"parameter", "predicate", "identifier", "constant", "variable"
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << NEG) | (1L << CAUSES) | (1L << IS_IMPLIED_BY) | (1L << MINUS) | (1L << TILDE) | (1L << LPAR) | (1L << INTEGER) | (1L << IDENTIFIER) | (1L << VARIABLE))) != 0)) {
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
			case MINUS:
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

	public static class Head_situationContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public Head_situationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_head_situation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterHead_situation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitHead_situation(this);
		}
	}

	public final Head_situationContext head_situation() throws RecognitionException {
		Head_situationContext _localctx = new Head_situationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_head_situation);
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
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public TerminalNode WHEN() { return getToken(LPPNParser.WHEN, 0); }
		public List<Head_expressionContext> head_expression() {
			return getRuleContexts(Head_expressionContext.class);
		}
		public Head_expressionContext head_expression(int i) {
			return getRuleContext(Head_expressionContext.class,i);
		}
		public Head_situationContext head_situation() {
			return getRuleContext(Head_situationContext.class,0);
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
			setState(111);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(102);
				operation(0);
				setState(103);
				match(WHEN);
				setState(104);
				head_expression(6);
				}
				break;
			case 2:
				{
				setState(106);
				head_situation();
				}
				break;
			case 3:
				{
				setState(107);
				match(LPAR);
				setState(108);
				head_expression(0);
				setState(109);
				match(RPAR);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(127);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(125);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new Head_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_head_expression);
						setState(113);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(114);
						match(SEQ);
						setState(115);
						head_expression(5);
						}
						break;
					case 2:
						{
						_localctx = new Head_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_head_expression);
						setState(116);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(117);
						_la = _input.LA(1);
						if ( !(_la==PAR || _la==ALT) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(118);
						head_expression(4);
						}
						break;
					case 3:
						{
						_localctx = new Head_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_head_expression);
						setState(119);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(120);
						match(AND);
						setState(121);
						head_expression(3);
						}
						break;
					case 4:
						{
						_localctx = new Head_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_head_expression);
						setState(122);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(123);
						_la = _input.LA(1);
						if ( !(_la==OR || _la==XOR) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(124);
						head_expression(2);
						}
						break;
					}
					} 
				}
				setState(129);
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
			setState(130);
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
			setState(138);
			switch (_input.LA(1)) {
			case NEG:
			case MINUS:
			case IDENTIFIER:
				{
				setState(133);
				event();
				}
				break;
			case LPAR:
				{
				setState(134);
				match(LPAR);
				setState(135);
				operation(0);
				setState(136);
				match(RPAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(148);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(146);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new OperationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_operation);
						setState(140);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(141);
						match(SEQ);
						setState(142);
						operation(3);
						}
						break;
					case 2:
						{
						_localctx = new OperationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_operation);
						setState(143);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(144);
						_la = _input.LA(1);
						if ( !(_la==PAR || _la==ALT) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(145);
						operation(2);
						}
						break;
					}
					} 
				}
				setState(150);
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

	public static class Body_situationContext extends ParserRuleContext {
		public Ext_literalContext ext_literal() {
			return getRuleContext(Ext_literalContext.class,0);
		}
		public Body_situationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body_situation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterBody_situation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitBody_situation(this);
		}
	}

	public final Body_situationContext body_situation() throws RecognitionException {
		Body_situationContext _localctx = new Body_situationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_body_situation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			ext_literal();
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
		public Body_situationContext body_situation() {
			return getRuleContext(Body_situationContext.class,0);
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
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_body_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(154);
				operation(0);
				setState(155);
				match(WHEN);
				setState(156);
				body_expression(6);
				}
				break;
			case 2:
				{
				setState(158);
				body_situation();
				}
				break;
			case 3:
				{
				setState(159);
				body_constraint();
				}
				break;
			case 4:
				{
				setState(160);
				match(LPAR);
				setState(161);
				body_expression(0);
				setState(162);
				match(RPAR);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(180);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(178);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new Body_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_body_expression);
						setState(166);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(167);
						match(SEQ);
						setState(168);
						body_expression(5);
						}
						break;
					case 2:
						{
						_localctx = new Body_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_body_expression);
						setState(169);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(170);
						_la = _input.LA(1);
						if ( !(_la==PAR || _la==ALT) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(171);
						body_expression(4);
						}
						break;
					case 3:
						{
						_localctx = new Body_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_body_expression);
						setState(172);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(173);
						match(AND);
						setState(174);
						body_expression(3);
						}
						break;
					case 4:
						{
						_localctx = new Body_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_body_expression);
						setState(175);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(176);
						_la = _input.LA(1);
						if ( !(_la==OR || _la==XOR) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(177);
						body_expression(2);
						}
						break;
					}
					} 
				}
				setState(182);
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
		enterRule(_localctx, 32, RULE_body_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(183);
				identifier();
				}
				break;
			case 2:
				{
				setState(184);
				variable();
				}
				break;
			case 3:
				{
				setState(185);
				match(INTEGER);
				}
				break;
			case 4:
				{
				setState(186);
				num_expression();
				}
				break;
			}
			setState(189);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << GT) | (1L << LT) | (1L << GE) | (1L << LE))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(194);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(190);
				match(IDENTIFIER);
				}
				break;
			case 2:
				{
				setState(191);
				match(VARIABLE);
				}
				break;
			case 3:
				{
				setState(192);
				match(INTEGER);
				}
				break;
			case 4:
				{
				setState(193);
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
		enterRule(_localctx, 34, RULE_num_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			switch (_input.LA(1)) {
			case VARIABLE:
				{
				setState(196);
				variable();
				}
				break;
			case INTEGER:
				{
				setState(197);
				match(INTEGER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(200);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(203);
			switch (_input.LA(1)) {
			case VARIABLE:
				{
				setState(201);
				variable();
				}
				break;
			case INTEGER:
				{
				setState(202);
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
		public TerminalNode TILDE() { return getToken(LPPNParser.TILDE, 0); }
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
		enterRule(_localctx, 36, RULE_ext_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			_la = _input.LA(1);
			if (_la==NOT || _la==TILDE) {
				{
				setState(205);
				_la = _input.LA(1);
				if ( !(_la==NOT || _la==TILDE) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(208);
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
		public TerminalNode NEG() { return getToken(LPPNParser.NEG, 0); }
		public TerminalNode MINUS() { return getToken(LPPNParser.MINUS, 0); }
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
		enterRule(_localctx, 38, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			_la = _input.LA(1);
			if (_la==NEG || _la==MINUS) {
				{
				setState(210);
				_la = _input.LA(1);
				if ( !(_la==NEG || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(213);
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
		enterRule(_localctx, 40, RULE_pos_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			predicate();
			setState(220);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(216);
				match(LPAR);
				setState(217);
				list_parameters();
				setState(218);
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
		enterRule(_localctx, 42, RULE_list_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			parameter();
			setState(225);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(223);
				match(COMMA);
				setState(224);
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
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
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
		enterRule(_localctx, 44, RULE_parameter);
		try {
			setState(231);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(227);
				variable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(228);
				constant();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(229);
				pos_literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(230);
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
		enterRule(_localctx, 46, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
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
		enterRule(_localctx, 48, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
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
		enterRule(_localctx, 50, RULE_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
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
			setState(239);
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
		case 15:
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
			return precpred(_ctx, 4);
		case 7:
			return precpred(_ctx, 3);
		case 8:
			return precpred(_ctx, 2);
		case 9:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3%\u00f4\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\7\2=\n\2\f\2\16\2@\13\2"+
		"\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\5\5M\n\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13"+
		"\5\13d\n\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\rr\n\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u0080\n\r\f\r\16\r"+
		"\u0083\13\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u008d\n\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\7\17\u0095\n\17\f\17\16\17\u0098\13\17\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00a7"+
		"\n\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21"+
		"\u00b5\n\21\f\21\16\21\u00b8\13\21\3\22\3\22\3\22\3\22\5\22\u00be\n\22"+
		"\3\22\3\22\3\22\3\22\3\22\5\22\u00c5\n\22\3\23\3\23\5\23\u00c9\n\23\3"+
		"\23\3\23\3\23\5\23\u00ce\n\23\3\24\5\24\u00d1\n\24\3\24\3\24\3\25\5\25"+
		"\u00d6\n\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\5\26\u00df\n\26\3\27\3"+
		"\27\3\27\5\27\u00e4\n\27\3\30\3\30\3\30\3\30\5\30\u00ea\n\30\3\31\3\31"+
		"\3\32\3\32\3\33\3\33\3\34\3\34\3\34\2\5\30\34 \35\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\66\2\t\3\2\r\16\3\2\b\t\3\2\5\6\3"+
		"\2\31\36\3\2\20\21\4\2\n\n\22\22\4\2\13\13\21\21\u00fd\2>\3\2\2\2\4C\3"+
		"\2\2\2\6F\3\2\2\2\bL\3\2\2\2\nN\3\2\2\2\fS\3\2\2\2\16W\3\2\2\2\20\\\3"+
		"\2\2\2\22^\3\2\2\2\24`\3\2\2\2\26e\3\2\2\2\30q\3\2\2\2\32\u0084\3\2\2"+
		"\2\34\u008c\3\2\2\2\36\u0099\3\2\2\2 \u00a6\3\2\2\2\"\u00bd\3\2\2\2$\u00c8"+
		"\3\2\2\2&\u00d0\3\2\2\2(\u00d5\3\2\2\2*\u00d9\3\2\2\2,\u00e0\3\2\2\2."+
		"\u00e9\3\2\2\2\60\u00eb\3\2\2\2\62\u00ed\3\2\2\2\64\u00ef\3\2\2\2\66\u00f1"+
		"\3\2\2\28=\5\4\3\29=\5\6\4\2:=\5\b\5\2;=\5\16\b\2<8\3\2\2\2<9\3\2\2\2"+
		"<:\3\2\2\2<;\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?A\3\2\2\2@>\3\2\2\2"+
		"AB\7\2\2\3B\3\3\2\2\2CD\5\20\t\2DE\7\23\2\2E\5\3\2\2\2FG\7\f\2\2GH\5\34"+
		"\17\2HI\7\23\2\2I\7\3\2\2\2JM\5\n\6\2KM\5\f\7\2LJ\3\2\2\2LK\3\2\2\2M\t"+
		"\3\2\2\2NO\5\20\t\2OP\t\2\2\2PQ\5\22\n\2QR\7\23\2\2R\13\3\2\2\2ST\7\r"+
		"\2\2TU\5\22\n\2UV\7\23\2\2V\r\3\2\2\2WX\5 \21\2XY\7\f\2\2YZ\5\34\17\2"+
		"Z[\7\23\2\2[\17\3\2\2\2\\]\5\30\r\2]\21\3\2\2\2^_\5 \21\2_\23\3\2\2\2"+
		"`c\5(\25\2ab\7\24\2\2bd\5\24\13\2ca\3\2\2\2cd\3\2\2\2d\25\3\2\2\2ef\5"+
		"(\25\2f\27\3\2\2\2gh\b\r\1\2hi\5\34\17\2ij\7\17\2\2jk\5\30\r\bkr\3\2\2"+
		"\2lr\5\26\f\2mn\7\25\2\2no\5\30\r\2op\7\26\2\2pr\3\2\2\2qg\3\2\2\2ql\3"+
		"\2\2\2qm\3\2\2\2r\u0081\3\2\2\2st\f\6\2\2tu\7\7\2\2u\u0080\5\30\r\7vw"+
		"\f\5\2\2wx\t\3\2\2x\u0080\5\30\r\6yz\f\4\2\2z{\7\4\2\2{\u0080\5\30\r\5"+
		"|}\f\3\2\2}~\t\4\2\2~\u0080\5\30\r\4\177s\3\2\2\2\177v\3\2\2\2\177y\3"+
		"\2\2\2\177|\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082"+
		"\3\2\2\2\u0082\31\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085\5(\25\2\u0085"+
		"\33\3\2\2\2\u0086\u0087\b\17\1\2\u0087\u008d\5\32\16\2\u0088\u0089\7\25"+
		"\2\2\u0089\u008a\5\34\17\2\u008a\u008b\7\26\2\2\u008b\u008d\3\2\2\2\u008c"+
		"\u0086\3\2\2\2\u008c\u0088\3\2\2\2\u008d\u0096\3\2\2\2\u008e\u008f\f\4"+
		"\2\2\u008f\u0090\7\7\2\2\u0090\u0095\5\34\17\5\u0091\u0092\f\3\2\2\u0092"+
		"\u0093\t\3\2\2\u0093\u0095\5\34\17\4\u0094\u008e\3\2\2\2\u0094\u0091\3"+
		"\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097"+
		"\35\3\2\2\2\u0098\u0096\3\2\2\2\u0099\u009a\5&\24\2\u009a\37\3\2\2\2\u009b"+
		"\u009c\b\21\1\2\u009c\u009d\5\34\17\2\u009d\u009e\7\17\2\2\u009e\u009f"+
		"\5 \21\b\u009f\u00a7\3\2\2\2\u00a0\u00a7\5\36\20\2\u00a1\u00a7\5\"\22"+
		"\2\u00a2\u00a3\7\25\2\2\u00a3\u00a4\5 \21\2\u00a4\u00a5\7\26\2\2\u00a5"+
		"\u00a7\3\2\2\2\u00a6\u009b\3\2\2\2\u00a6\u00a0\3\2\2\2\u00a6\u00a1\3\2"+
		"\2\2\u00a6\u00a2\3\2\2\2\u00a7\u00b6\3\2\2\2\u00a8\u00a9\f\6\2\2\u00a9"+
		"\u00aa\7\7\2\2\u00aa\u00b5\5 \21\7\u00ab\u00ac\f\5\2\2\u00ac\u00ad\t\3"+
		"\2\2\u00ad\u00b5\5 \21\6\u00ae\u00af\f\4\2\2\u00af\u00b0\7\4\2\2\u00b0"+
		"\u00b5\5 \21\5\u00b1\u00b2\f\3\2\2\u00b2\u00b3\t\4\2\2\u00b3\u00b5\5 "+
		"\21\4\u00b4\u00a8\3\2\2\2\u00b4\u00ab\3\2\2\2\u00b4\u00ae\3\2\2\2\u00b4"+
		"\u00b1\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2"+
		"\2\2\u00b7!\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00be\5\62\32\2\u00ba\u00be"+
		"\5\66\34\2\u00bb\u00be\7!\2\2\u00bc\u00be\5$\23\2\u00bd\u00b9\3\2\2\2"+
		"\u00bd\u00ba\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00bc\3\2\2\2\u00be\u00bf"+
		"\3\2\2\2\u00bf\u00c4\t\5\2\2\u00c0\u00c5\7\"\2\2\u00c1\u00c5\7#\2\2\u00c2"+
		"\u00c5\7!\2\2\u00c3\u00c5\5$\23\2\u00c4\u00c0\3\2\2\2\u00c4\u00c1\3\2"+
		"\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c3\3\2\2\2\u00c5#\3\2\2\2\u00c6\u00c9"+
		"\5\66\34\2\u00c7\u00c9\7!\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c7\3\2\2\2"+
		"\u00c9\u00ca\3\2\2\2\u00ca\u00cd\t\6\2\2\u00cb\u00ce\5\66\34\2\u00cc\u00ce"+
		"\7!\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00cc\3\2\2\2\u00ce%\3\2\2\2\u00cf\u00d1"+
		"\t\7\2\2\u00d0\u00cf\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2"+
		"\u00d3\5(\25\2\u00d3\'\3\2\2\2\u00d4\u00d6\t\b\2\2\u00d5\u00d4\3\2\2\2"+
		"\u00d5\u00d6\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\5*\26\2\u00d8)\3"+
		"\2\2\2\u00d9\u00de\5\60\31\2\u00da\u00db\7\25\2\2\u00db\u00dc\5,\27\2"+
		"\u00dc\u00dd\7\26\2\2\u00dd\u00df\3\2\2\2\u00de\u00da\3\2\2\2\u00de\u00df"+
		"\3\2\2\2\u00df+\3\2\2\2\u00e0\u00e3\5.\30\2\u00e1\u00e2\7\24\2\2\u00e2"+
		"\u00e4\5,\27\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4-\3\2\2\2"+
		"\u00e5\u00ea\5\66\34\2\u00e6\u00ea\5\64\33\2\u00e7\u00ea\5*\26\2\u00e8"+
		"\u00ea\5$\23\2\u00e9\u00e5\3\2\2\2\u00e9\u00e6\3\2\2\2\u00e9\u00e7\3\2"+
		"\2\2\u00e9\u00e8\3\2\2\2\u00ea/\3\2\2\2\u00eb\u00ec\7\"\2\2\u00ec\61\3"+
		"\2\2\2\u00ed\u00ee\7\"\2\2\u00ee\63\3\2\2\2\u00ef\u00f0\7!\2\2\u00f0\65"+
		"\3\2\2\2\u00f1\u00f2\7#\2\2\u00f2\67\3\2\2\2\30<>Lcq\177\u0081\u008c\u0094"+
		"\u0096\u00a6\u00b4\u00b6\u00bd\u00c4\u00c8\u00cd\u00d0\u00d5\u00de\u00e3"+
		"\u00e9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}