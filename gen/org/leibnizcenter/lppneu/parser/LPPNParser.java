// Generated from /home/giovanni/dev/lppneu/antlr4/LPPN.g4 by ANTLR 4.5
package org.leibnizcenter.lppneu.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LPPNParser extends Parser {
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
	public static final int
		RULE_program = 0, RULE_situationfact = 1, RULE_eventfact = 2, RULE_logicrule = 3, 
		RULE_normrule = 4, RULE_constraint = 5, RULE_causalrule = 6, RULE_eventcondition_expression = 7, 
		RULE_head = 8, RULE_body = 9, RULE_list_literals = 10, RULE_head_situation = 11, 
		RULE_head_expression = 12, RULE_event = 13, RULE_operation = 14, RULE_body_situation = 15, 
		RULE_body_expression = 16, RULE_body_constraint = 17, RULE_num_expression = 18, 
		RULE_ext_literal = 19, RULE_literal = 20, RULE_pos_literal = 21, RULE_list_parameters = 22, 
		RULE_parameter = 23, RULE_predicate = 24, RULE_identifier = 25, RULE_constant = 26, 
		RULE_variable = 27;
	public static final String[] ruleNames = {
		"program", "situationfact", "eventfact", "logicrule", "normrule", "constraint", 
		"causalrule", "eventcondition_expression", "head", "body", "list_literals", 
		"head_situation", "head_expression", "event", "operation", "body_situation", 
		"body_expression", "body_constraint", "num_expression", "ext_literal", 
		"literal", "pos_literal", "list_parameters", "parameter", "predicate", 
		"identifier", "constant", "variable"
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
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(60);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						setState(56); 
						situationfact();
						}
						break;
					case 2:
						{
						setState(57); 
						eventfact();
						}
						break;
					case 3:
						{
						setState(58); 
						logicrule();
						}
						break;
					case 4:
						{
						setState(59); 
						causalrule();
						}
						break;
					}
					} 
				}
				setState(64);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(65); 
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
			setState(67); 
			head();
			setState(68); 
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
			setState(70); 
			match(CAUSES);
			setState(71); 
			operation(0);
			setState(72); 
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
			setState(76);
			switch (_input.LA(1)) {
			case LPAR:
			case NEG:
			case MINUS:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(74); 
				normrule();
				}
				break;
			case IS_IMPLIED_BY:
				enterOuterAlt(_localctx, 2);
				{
				setState(75); 
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
		public TerminalNode IS_IMPLIED_BY() { return getToken(LPPNParser.IS_IMPLIED_BY, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode DOT() { return getToken(LPPNParser.DOT, 0); }
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78); 
			head();
			setState(79); 
			match(IS_IMPLIED_BY);
			setState(80); 
			body();
			setState(81); 
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
			setState(83); 
			match(IS_IMPLIED_BY);
			setState(84); 
			body();
			setState(85); 
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
		public Eventcondition_expressionContext eventcondition_expression() {
			return getRuleContext(Eventcondition_expressionContext.class,0);
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
			setState(87); 
			eventcondition_expression(0);
			setState(88); 
			match(CAUSES);
			setState(89); 
			operation(0);
			setState(90); 
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

	public static class Eventcondition_expressionContext extends ParserRuleContext {
		public Body_situationContext body_situation() {
			return getRuleContext(Body_situationContext.class,0);
		}
		public EventContext event() {
			return getRuleContext(EventContext.class,0);
		}
		public TerminalNode WHEN() { return getToken(LPPNParser.WHEN, 0); }
		public List<Body_expressionContext> body_expression() {
			return getRuleContexts(Body_expressionContext.class);
		}
		public Body_expressionContext body_expression(int i) {
			return getRuleContext(Body_expressionContext.class,i);
		}
		public TerminalNode LPAR() { return getToken(LPPNParser.LPAR, 0); }
		public List<Eventcondition_expressionContext> eventcondition_expression() {
			return getRuleContexts(Eventcondition_expressionContext.class);
		}
		public Eventcondition_expressionContext eventcondition_expression(int i) {
			return getRuleContext(Eventcondition_expressionContext.class,i);
		}
		public TerminalNode RPAR() { return getToken(LPPNParser.RPAR, 0); }
		public TerminalNode AND() { return getToken(LPPNParser.AND, 0); }
		public TerminalNode OR() { return getToken(LPPNParser.OR, 0); }
		public TerminalNode XOR() { return getToken(LPPNParser.XOR, 0); }
		public TerminalNode SEQ() { return getToken(LPPNParser.SEQ, 0); }
		public TerminalNode PAR() { return getToken(LPPNParser.PAR, 0); }
		public TerminalNode ALT() { return getToken(LPPNParser.ALT, 0); }
		public Eventcondition_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eventcondition_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterEventcondition_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitEventcondition_expression(this);
		}
	}

	public final Eventcondition_expressionContext eventcondition_expression() throws RecognitionException {
		return eventcondition_expression(0);
	}

	private Eventcondition_expressionContext eventcondition_expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Eventcondition_expressionContext _localctx = new Eventcondition_expressionContext(_ctx, _parentState);
		Eventcondition_expressionContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_eventcondition_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(93); 
				body_situation();
				}
				break;
			case 2:
				{
				}
				break;
			case 3:
				{
				setState(95); 
				event();
				setState(96); 
				match(WHEN);
				setState(97); 
				body_expression(0);
				}
				break;
			case 4:
				{
				setState(99); 
				match(LPAR);
				setState(100); 
				eventcondition_expression(0);
				setState(101); 
				match(RPAR);
				}
				break;
			case 5:
				{
				setState(103); 
				body_expression(0);
				setState(104); 
				match(AND);
				setState(105); 
				body_expression(0);
				}
				break;
			case 6:
				{
				setState(107); 
				body_expression(0);
				setState(108);
				_la = _input.LA(1);
				if ( !(_la==OR || _la==XOR) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(109); 
				body_expression(0);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(121);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(119);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						_localctx = new Eventcondition_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_eventcondition_expression);
						setState(113);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(114); 
						match(SEQ);
						setState(115); 
						eventcondition_expression(5);
						}
						break;
					case 2:
						{
						_localctx = new Eventcondition_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_eventcondition_expression);
						setState(116);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(117);
						_la = _input.LA(1);
						if ( !(_la==PAR || _la==ALT) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(118); 
						eventcondition_expression(4);
						}
						break;
					}
					} 
				}
				setState(123);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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
		enterRule(_localctx, 16, RULE_head);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124); 
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
		enterRule(_localctx, 18, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126); 
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
		enterRule(_localctx, 20, RULE_list_literals);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128); 
			literal();
			setState(131);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(129); 
				match(COMMA);
				setState(130); 
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
		enterRule(_localctx, 22, RULE_head_situation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133); 
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
		public Head_situationContext head_situation() {
			return getRuleContext(Head_situationContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(LPPNParser.LPAR, 0); }
		public List<Head_expressionContext> head_expression() {
			return getRuleContexts(Head_expressionContext.class);
		}
		public Head_expressionContext head_expression(int i) {
			return getRuleContext(Head_expressionContext.class,i);
		}
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
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_head_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			switch (_input.LA(1)) {
			case NEG:
			case MINUS:
			case IDENTIFIER:
				{
				setState(136); 
				head_situation();
				}
				break;
			case LPAR:
				{
				setState(137); 
				match(LPAR);
				setState(138); 
				head_expression(0);
				setState(139); 
				match(RPAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(157);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(155);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new Head_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_head_expression);
						setState(143);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(144); 
						match(SEQ);
						setState(145); 
						head_expression(5);
						}
						break;
					case 2:
						{
						_localctx = new Head_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_head_expression);
						setState(146);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(147);
						_la = _input.LA(1);
						if ( !(_la==PAR || _la==ALT) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(148); 
						head_expression(4);
						}
						break;
					case 3:
						{
						_localctx = new Head_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_head_expression);
						setState(149);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(150); 
						match(AND);
						setState(151); 
						head_expression(3);
						}
						break;
					case 4:
						{
						_localctx = new Head_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_head_expression);
						setState(152);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(153);
						_la = _input.LA(1);
						if ( !(_la==OR || _la==XOR) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(154); 
						head_expression(2);
						}
						break;
					}
					} 
				}
				setState(159);
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
		enterRule(_localctx, 26, RULE_event);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160); 
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
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_operation, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			switch (_input.LA(1)) {
			case NEG:
			case MINUS:
			case IDENTIFIER:
				{
				setState(163); 
				event();
				}
				break;
			case LPAR:
				{
				setState(164); 
				match(LPAR);
				setState(165); 
				operation(0);
				setState(166); 
				match(RPAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(178);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(176);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new OperationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_operation);
						setState(170);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(171); 
						match(SEQ);
						setState(172); 
						operation(3);
						}
						break;
					case 2:
						{
						_localctx = new OperationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_operation);
						setState(173);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(174);
						_la = _input.LA(1);
						if ( !(_la==PAR || _la==ALT) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(175); 
						operation(2);
						}
						break;
					}
					} 
				}
				setState(180);
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
		enterRule(_localctx, 30, RULE_body_situation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181); 
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
		public Body_situationContext body_situation() {
			return getRuleContext(Body_situationContext.class,0);
		}
		public Body_constraintContext body_constraint() {
			return getRuleContext(Body_constraintContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(LPPNParser.LPAR, 0); }
		public List<Body_expressionContext> body_expression() {
			return getRuleContexts(Body_expressionContext.class);
		}
		public Body_expressionContext body_expression(int i) {
			return getRuleContext(Body_expressionContext.class,i);
		}
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
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_body_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(184); 
				body_situation();
				}
				break;
			case 2:
				{
				setState(185); 
				body_constraint();
				}
				break;
			case 3:
				{
				setState(186); 
				match(LPAR);
				setState(187); 
				body_expression(0);
				setState(188); 
				match(RPAR);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(206);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(204);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						_localctx = new Body_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_body_expression);
						setState(192);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(193); 
						match(SEQ);
						setState(194); 
						body_expression(5);
						}
						break;
					case 2:
						{
						_localctx = new Body_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_body_expression);
						setState(195);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(196);
						_la = _input.LA(1);
						if ( !(_la==PAR || _la==ALT) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(197); 
						body_expression(4);
						}
						break;
					case 3:
						{
						_localctx = new Body_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_body_expression);
						setState(198);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(199); 
						match(AND);
						setState(200); 
						body_expression(3);
						}
						break;
					case 4:
						{
						_localctx = new Body_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_body_expression);
						setState(201);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(202);
						_la = _input.LA(1);
						if ( !(_la==OR || _la==XOR) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(203); 
						body_expression(2);
						}
						break;
					}
					} 
				}
				setState(208);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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
		enterRule(_localctx, 34, RULE_body_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(209); 
				identifier();
				}
				break;
			case 2:
				{
				setState(210); 
				variable();
				}
				break;
			case 3:
				{
				setState(211); 
				match(INTEGER);
				}
				break;
			case 4:
				{
				setState(212); 
				num_expression();
				}
				break;
			}
			setState(215);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << GT) | (1L << LT) | (1L << GE) | (1L << LE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(220);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(216); 
				match(IDENTIFIER);
				}
				break;
			case 2:
				{
				setState(217); 
				match(VARIABLE);
				}
				break;
			case 3:
				{
				setState(218); 
				match(INTEGER);
				}
				break;
			case 4:
				{
				setState(219); 
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
		enterRule(_localctx, 36, RULE_num_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			switch (_input.LA(1)) {
			case VARIABLE:
				{
				setState(222); 
				variable();
				}
				break;
			case INTEGER:
				{
				setState(223); 
				match(INTEGER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(226);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(229);
			switch (_input.LA(1)) {
			case VARIABLE:
				{
				setState(227); 
				variable();
				}
				break;
			case INTEGER:
				{
				setState(228); 
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
		enterRule(_localctx, 38, RULE_ext_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			_la = _input.LA(1);
			if (_la==NOT || _la==TILDE) {
				{
				setState(231);
				_la = _input.LA(1);
				if ( !(_la==NOT || _la==TILDE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
			}

			setState(234); 
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
		enterRule(_localctx, 40, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			_la = _input.LA(1);
			if (_la==NEG || _la==MINUS) {
				{
				setState(236);
				_la = _input.LA(1);
				if ( !(_la==NEG || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
			}

			setState(239); 
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
		enterRule(_localctx, 42, RULE_pos_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241); 
			predicate();
			setState(246);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(242); 
				match(LPAR);
				setState(243); 
				list_parameters();
				setState(244); 
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
		enterRule(_localctx, 44, RULE_list_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248); 
			parameter();
			setState(251);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(249); 
				match(COMMA);
				setState(250); 
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
		enterRule(_localctx, 46, RULE_parameter);
		try {
			setState(257);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(253); 
				variable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(254); 
				constant();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(255); 
				pos_literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(256); 
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
		enterRule(_localctx, 48, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259); 
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
		enterRule(_localctx, 50, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261); 
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
		enterRule(_localctx, 52, RULE_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263); 
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
		enterRule(_localctx, 54, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265); 
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
		case 7: 
			return eventcondition_expression_sempred((Eventcondition_expressionContext) _localctx, predIndex);
		case 12: 
			return head_expression_sempred((Head_expressionContext)_localctx, predIndex);
		case 14: 
			return operation_sempred((OperationContext)_localctx, predIndex);
		case 16: 
			return body_expression_sempred((Body_expressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean eventcondition_expression_sempred(Eventcondition_expressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: 
			return precpred(_ctx, 4);
		case 1: 
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean head_expression_sempred(Head_expressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: 
			return precpred(_ctx, 4);
		case 3: 
			return precpred(_ctx, 3);
		case 4: 
			return precpred(_ctx, 2);
		case 5: 
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean operation_sempred(OperationContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6: 
			return precpred(_ctx, 2);
		case 7: 
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean body_expression_sempred(Body_expressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8: 
			return precpred(_ctx, 4);
		case 9: 
			return precpred(_ctx, 3);
		case 10: 
			return precpred(_ctx, 2);
		case 11: 
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3$\u010e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\2\3\2\7\2?\n\2\f\2"+
		"\16\2B\13\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\5\5O\n\5\3\6\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tr\n\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\7\tz\n\t\f\t\16\t}\13\t\3\n\3\n\3\13\3\13\3\f\3\f"+
		"\3\f\5\f\u0086\n\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0090\n"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u009e"+
		"\n\16\f\16\16\16\u00a1\13\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\5"+
		"\20\u00ab\n\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u00b3\n\20\f\20\16\20"+
		"\u00b6\13\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00c1\n"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u00cf"+
		"\n\22\f\22\16\22\u00d2\13\22\3\23\3\23\3\23\3\23\5\23\u00d8\n\23\3\23"+
		"\3\23\3\23\3\23\3\23\5\23\u00df\n\23\3\24\3\24\5\24\u00e3\n\24\3\24\3"+
		"\24\3\24\5\24\u00e8\n\24\3\25\5\25\u00eb\n\25\3\25\3\25\3\26\5\26\u00f0"+
		"\n\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\5\27\u00f9\n\27\3\30\3\30\3\30"+
		"\5\30\u00fe\n\30\3\31\3\31\3\31\3\31\5\31\u0104\n\31\3\32\3\32\3\33\3"+
		"\33\3\34\3\34\3\35\3\35\3\35\2\6\20\32\36\"\36\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\64\668\2\b\3\2\23\24\3\2\26\27\3\2\f\21"+
		"\3\2\33\34\4\2\31\31\35\35\4\2\32\32\34\34\u011b\2@\3\2\2\2\4E\3\2\2\2"+
		"\6H\3\2\2\2\bN\3\2\2\2\nP\3\2\2\2\fU\3\2\2\2\16Y\3\2\2\2\20q\3\2\2\2\22"+
		"~\3\2\2\2\24\u0080\3\2\2\2\26\u0082\3\2\2\2\30\u0087\3\2\2\2\32\u008f"+
		"\3\2\2\2\34\u00a2\3\2\2\2\36\u00aa\3\2\2\2 \u00b7\3\2\2\2\"\u00c0\3\2"+
		"\2\2$\u00d7\3\2\2\2&\u00e2\3\2\2\2(\u00ea\3\2\2\2*\u00ef\3\2\2\2,\u00f3"+
		"\3\2\2\2.\u00fa\3\2\2\2\60\u0103\3\2\2\2\62\u0105\3\2\2\2\64\u0107\3\2"+
		"\2\2\66\u0109\3\2\2\28\u010b\3\2\2\2:?\5\4\3\2;?\5\6\4\2<?\5\b\5\2=?\5"+
		"\16\b\2>:\3\2\2\2>;\3\2\2\2><\3\2\2\2>=\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A"+
		"\3\2\2\2AC\3\2\2\2B@\3\2\2\2CD\7\2\2\3D\3\3\2\2\2EF\5\22\n\2FG\7\6\2\2"+
		"G\5\3\2\2\2HI\7\30\2\2IJ\5\36\20\2JK\7\6\2\2K\7\3\2\2\2LO\5\n\6\2MO\5"+
		"\f\7\2NL\3\2\2\2NM\3\2\2\2O\t\3\2\2\2PQ\5\22\n\2QR\7\4\2\2RS\5\24\13\2"+
		"ST\7\6\2\2T\13\3\2\2\2UV\7\4\2\2VW\5\24\13\2WX\7\6\2\2X\r\3\2\2\2YZ\5"+
		"\20\t\2Z[\7\30\2\2[\\\5\36\20\2\\]\7\6\2\2]\17\3\2\2\2^_\b\t\1\2_r\5 "+
		"\21\2`r\3\2\2\2ab\5\34\17\2bc\7\5\2\2cd\5\"\22\2dr\3\2\2\2ef\7\b\2\2f"+
		"g\5\20\t\2gh\7\t\2\2hr\3\2\2\2ij\5\"\22\2jk\7\22\2\2kl\5\"\22\2lr\3\2"+
		"\2\2mn\5\"\22\2no\t\2\2\2op\5\"\22\2pr\3\2\2\2q^\3\2\2\2q`\3\2\2\2qa\3"+
		"\2\2\2qe\3\2\2\2qi\3\2\2\2qm\3\2\2\2r{\3\2\2\2st\f\6\2\2tu\7\25\2\2uz"+
		"\5\20\t\7vw\f\5\2\2wx\t\3\2\2xz\5\20\t\6ys\3\2\2\2yv\3\2\2\2z}\3\2\2\2"+
		"{y\3\2\2\2{|\3\2\2\2|\21\3\2\2\2}{\3\2\2\2~\177\5\32\16\2\177\23\3\2\2"+
		"\2\u0080\u0081\5\"\22\2\u0081\25\3\2\2\2\u0082\u0085\5*\26\2\u0083\u0084"+
		"\7\7\2\2\u0084\u0086\5\26\f\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2"+
		"\u0086\27\3\2\2\2\u0087\u0088\5*\26\2\u0088\31\3\2\2\2\u0089\u008a\b\16"+
		"\1\2\u008a\u0090\5\30\r\2\u008b\u008c\7\b\2\2\u008c\u008d\5\32\16\2\u008d"+
		"\u008e\7\t\2\2\u008e\u0090\3\2\2\2\u008f\u0089\3\2\2\2\u008f\u008b\3\2"+
		"\2\2\u0090\u009f\3\2\2\2\u0091\u0092\f\6\2\2\u0092\u0093\7\25\2\2\u0093"+
		"\u009e\5\32\16\7\u0094\u0095\f\5\2\2\u0095\u0096\t\3\2\2\u0096\u009e\5"+
		"\32\16\6\u0097\u0098\f\4\2\2\u0098\u0099\7\22\2\2\u0099\u009e\5\32\16"+
		"\5\u009a\u009b\f\3\2\2\u009b\u009c\t\2\2\2\u009c\u009e\5\32\16\4\u009d"+
		"\u0091\3\2\2\2\u009d\u0094\3\2\2\2\u009d\u0097\3\2\2\2\u009d\u009a\3\2"+
		"\2\2\u009e\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0"+
		"\33\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a3\5*\26\2\u00a3\35\3\2\2\2\u00a4"+
		"\u00a5\b\20\1\2\u00a5\u00ab\5\34\17\2\u00a6\u00a7\7\b\2\2\u00a7\u00a8"+
		"\5\36\20\2\u00a8\u00a9\7\t\2\2\u00a9\u00ab\3\2\2\2\u00aa\u00a4\3\2\2\2"+
		"\u00aa\u00a6\3\2\2\2\u00ab\u00b4\3\2\2\2\u00ac\u00ad\f\4\2\2\u00ad\u00ae"+
		"\7\25\2\2\u00ae\u00b3\5\36\20\5\u00af\u00b0\f\3\2\2\u00b0\u00b1\t\3\2"+
		"\2\u00b1\u00b3\5\36\20\4\u00b2\u00ac\3\2\2\2\u00b2\u00af\3\2\2\2\u00b3"+
		"\u00b6\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\37\3\2\2"+
		"\2\u00b6\u00b4\3\2\2\2\u00b7\u00b8\5(\25\2\u00b8!\3\2\2\2\u00b9\u00ba"+
		"\b\22\1\2\u00ba\u00c1\5 \21\2\u00bb\u00c1\5$\23\2\u00bc\u00bd\7\b\2\2"+
		"\u00bd\u00be\5\"\22\2\u00be\u00bf\7\t\2\2\u00bf\u00c1\3\2\2\2\u00c0\u00b9"+
		"\3\2\2\2\u00c0\u00bb\3\2\2\2\u00c0\u00bc\3\2\2\2\u00c1\u00d0\3\2\2\2\u00c2"+
		"\u00c3\f\6\2\2\u00c3\u00c4\7\25\2\2\u00c4\u00cf\5\"\22\7\u00c5\u00c6\f"+
		"\5\2\2\u00c6\u00c7\t\3\2\2\u00c7\u00cf\5\"\22\6\u00c8\u00c9\f\4\2\2\u00c9"+
		"\u00ca\7\22\2\2\u00ca\u00cf\5\"\22\5\u00cb\u00cc\f\3\2\2\u00cc\u00cd\t"+
		"\2\2\2\u00cd\u00cf\5\"\22\4\u00ce\u00c2\3\2\2\2\u00ce\u00c5\3\2\2\2\u00ce"+
		"\u00c8\3\2\2\2\u00ce\u00cb\3\2\2\2\u00cf\u00d2\3\2\2\2\u00d0\u00ce\3\2"+
		"\2\2\u00d0\u00d1\3\2\2\2\u00d1#\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d3\u00d8"+
		"\5\64\33\2\u00d4\u00d8\58\35\2\u00d5\u00d8\7 \2\2\u00d6\u00d8\5&\24\2"+
		"\u00d7\u00d3\3\2\2\2\u00d7\u00d4\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d6"+
		"\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00de\t\4\2\2\u00da\u00df\7!\2\2\u00db"+
		"\u00df\7\"\2\2\u00dc\u00df\7 \2\2\u00dd\u00df\5&\24\2\u00de\u00da\3\2"+
		"\2\2\u00de\u00db\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00dd\3\2\2\2\u00df"+
		"%\3\2\2\2\u00e0\u00e3\58\35\2\u00e1\u00e3\7 \2\2\u00e2\u00e0\3\2\2\2\u00e2"+
		"\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e7\t\5\2\2\u00e5\u00e8\58"+
		"\35\2\u00e6\u00e8\7 \2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e6\3\2\2\2\u00e8"+
		"\'\3\2\2\2\u00e9\u00eb\t\6\2\2\u00ea\u00e9\3\2\2\2\u00ea\u00eb\3\2\2\2"+
		"\u00eb\u00ec\3\2\2\2\u00ec\u00ed\5*\26\2\u00ed)\3\2\2\2\u00ee\u00f0\t"+
		"\7\2\2\u00ef\u00ee\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1"+
		"\u00f2\5,\27\2\u00f2+\3\2\2\2\u00f3\u00f8\5\62\32\2\u00f4\u00f5\7\b\2"+
		"\2\u00f5\u00f6\5.\30\2\u00f6\u00f7\7\t\2\2\u00f7\u00f9\3\2\2\2\u00f8\u00f4"+
		"\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9-\3\2\2\2\u00fa\u00fd\5\60\31\2\u00fb"+
		"\u00fc\7\7\2\2\u00fc\u00fe\5.\30\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2"+
		"\2\2\u00fe/\3\2\2\2\u00ff\u0104\58\35\2\u0100\u0104\5\66\34\2\u0101\u0104"+
		"\5,\27\2\u0102\u0104\5&\24\2\u0103\u00ff\3\2\2\2\u0103\u0100\3\2\2\2\u0103"+
		"\u0101\3\2\2\2\u0103\u0102\3\2\2\2\u0104\61\3\2\2\2\u0105\u0106\7!\2\2"+
		"\u0106\63\3\2\2\2\u0107\u0108\7!\2\2\u0108\65\3\2\2\2\u0109\u010a\7 \2"+
		"\2\u010a\67\3\2\2\2\u010b\u010c\7\"\2\2\u010c9\3\2\2\2\33>@Nqy{\u0085"+
		"\u008f\u009d\u009f\u00aa\u00b2\u00b4\u00c0\u00ce\u00d0\u00d7\u00de\u00e2"+
		"\u00e7\u00ea\u00ef\u00f8\u00fd\u0103";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}