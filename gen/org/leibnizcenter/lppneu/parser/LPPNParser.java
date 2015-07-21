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
		RULE_normrule = 4, RULE_constraint = 5, RULE_causalrule = 6, RULE_carule = 7, 
		RULE_ecarule = 8, RULE_head = 9, RULE_body = 10, RULE_list_literals = 11, 
		RULE_head_situation = 12, RULE_head_expression = 13, RULE_event = 14, 
		RULE_operation = 15, RULE_body_situation = 16, RULE_body_expression = 17, 
		RULE_body_constraint = 18, RULE_num_expression = 19, RULE_ext_literal = 20, 
		RULE_literal = 21, RULE_pos_literal = 22, RULE_list_parameters = 23, RULE_parameter = 24, 
		RULE_predicate = 25, RULE_identifier = 26, RULE_constant = 27, RULE_variable = 28;
	public static final String[] ruleNames = {
		"program", "situationfact", "eventfact", "logicrule", "normrule", "constraint", 
		"causalrule", "carule", "ecarule", "head", "body", "list_literals", "head_situation", 
		"head_expression", "event", "operation", "body_situation", "body_expression", 
		"body_constraint", "num_expression", "ext_literal", "literal", "pos_literal", 
		"list_parameters", "parameter", "predicate", "identifier", "constant", 
		"variable"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "':-'", "':'", "'.'", "','", "'('", "')'", "'{'", "'}'", "'='", 
		"'!='", "'>'", "'<'", "'>='", "'<='", null, null, null, null, null, null, 
		"'->'", null, null, "'+'", "'-'", "'~'", "'#domain'", "'..'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "IS_IMPLIED_BY", "WHEN", "DOT", "COMMA", "LPAR", "RPAR", "LACC", 
		"RACC", "EQ", "NEQ", "GT", "LT", "GE", "LE", "AND", "OR", "XOR", "SEQ", 
		"PAR", "ALT", "CAUSES", "NOT", "NEG", "PLUS", "MINUS", "TILDE", "DOMAIN", 
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IS_IMPLIED_BY) | (1L << LPAR) | (1L << CAUSES) | (1L << NOT) | (1L << MINUS) | (1L << TILDE) | (1L << INTEGER) | (1L << IDENTIFIER) | (1L << VARIABLE))) != 0)) {
				{
				setState(62);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(58); 
					situationfact();
					}
					break;
				case 2:
					{
					setState(59); 
					eventfact();
					}
					break;
				case 3:
					{
					setState(60); 
					logicrule();
					}
					break;
				case 4:
					{
					setState(61); 
					causalrule();
					}
					break;
				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67); 
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
			setState(69); 
			head();
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
			setState(72); 
			match(CAUSES);
			setState(73); 
			operation(0);
			setState(74); 
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
			setState(78);
			switch (_input.LA(1)) {
			case LPAR:
			case MINUS:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(76); 
				normrule();
				}
				break;
			case IS_IMPLIED_BY:
				enterOuterAlt(_localctx, 2);
				{
				setState(77); 
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
			setState(80); 
			head();
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
			setState(85); 
			match(IS_IMPLIED_BY);
			setState(86); 
			body();
			setState(87); 
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
		public CaruleContext carule() {
			return getRuleContext(CaruleContext.class,0);
		}
		public EcaruleContext ecarule() {
			return getRuleContext(EcaruleContext.class,0);
		}
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
			setState(91);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(89); 
				carule();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(90); 
				ecarule();
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

	public static class CaruleContext extends ParserRuleContext {
		public Body_expressionContext body_expression() {
			return getRuleContext(Body_expressionContext.class,0);
		}
		public TerminalNode CAUSES() { return getToken(LPPNParser.CAUSES, 0); }
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public TerminalNode DOT() { return getToken(LPPNParser.DOT, 0); }
		public CaruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_carule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterCarule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitCarule(this);
		}
	}

	public final CaruleContext carule() throws RecognitionException {
		CaruleContext _localctx = new CaruleContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_carule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93); 
			body_expression(0);
			setState(94); 
			match(CAUSES);
			setState(95); 
			operation(0);
			setState(96); 
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

	public static class EcaruleContext extends ParserRuleContext {
		public EventContext event() {
			return getRuleContext(EventContext.class,0);
		}
		public TerminalNode WHEN() { return getToken(LPPNParser.WHEN, 0); }
		public Body_expressionContext body_expression() {
			return getRuleContext(Body_expressionContext.class,0);
		}
		public TerminalNode CAUSES() { return getToken(LPPNParser.CAUSES, 0); }
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public TerminalNode DOT() { return getToken(LPPNParser.DOT, 0); }
		public EcaruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ecarule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).enterEcarule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPPNListener ) ((LPPNListener)listener).exitEcarule(this);
		}
	}

	public final EcaruleContext ecarule() throws RecognitionException {
		EcaruleContext _localctx = new EcaruleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_ecarule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98); 
			event();
			setState(99); 
			match(WHEN);
			setState(100); 
			body_expression(0);
			setState(101); 
			match(CAUSES);
			setState(102); 
			operation(0);
			setState(103); 
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
		enterRule(_localctx, 18, RULE_head);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105); 
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
		enterRule(_localctx, 20, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107); 
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
		enterRule(_localctx, 22, RULE_list_literals);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109); 
			literal();
			setState(112);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(110); 
				match(COMMA);
				setState(111); 
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
		enterRule(_localctx, 24, RULE_head_situation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114); 
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
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_head_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			switch (_input.LA(1)) {
			case MINUS:
			case IDENTIFIER:
				{
				setState(117); 
				head_situation();
				}
				break;
			case LPAR:
				{
				setState(118); 
				match(LPAR);
				setState(119); 
				head_expression(0);
				setState(120); 
				match(RPAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(138);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(136);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new Head_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_head_expression);
						setState(124);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(125); 
						match(SEQ);
						setState(126); 
						head_expression(5);
						}
						break;
					case 2:
						{
						_localctx = new Head_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_head_expression);
						setState(127);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(128);
						_la = _input.LA(1);
						if ( !(_la==PAR || _la==ALT) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(129); 
						head_expression(4);
						}
						break;
					case 3:
						{
						_localctx = new Head_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_head_expression);
						setState(130);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(131); 
						match(AND);
						setState(132); 
						head_expression(3);
						}
						break;
					case 4:
						{
						_localctx = new Head_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_head_expression);
						setState(133);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(134);
						_la = _input.LA(1);
						if ( !(_la==OR || _la==XOR) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(135); 
						head_expression(2);
						}
						break;
					}
					} 
				}
				setState(140);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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
		enterRule(_localctx, 28, RULE_event);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141); 
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
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_operation, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			switch (_input.LA(1)) {
			case MINUS:
			case IDENTIFIER:
				{
				setState(144); 
				event();
				}
				break;
			case LPAR:
				{
				setState(145); 
				match(LPAR);
				setState(146); 
				operation(0);
				setState(147); 
				match(RPAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(159);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(157);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						_localctx = new OperationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_operation);
						setState(151);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(152); 
						match(SEQ);
						setState(153); 
						operation(3);
						}
						break;
					case 2:
						{
						_localctx = new OperationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_operation);
						setState(154);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(155);
						_la = _input.LA(1);
						if ( !(_la==PAR || _la==ALT) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(156); 
						operation(2);
						}
						break;
					}
					} 
				}
				setState(161);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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
		enterRule(_localctx, 32, RULE_body_situation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162); 
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
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_body_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(165); 
				body_situation();
				}
				break;
			case 2:
				{
				setState(166); 
				body_constraint();
				}
				break;
			case 3:
				{
				setState(167); 
				match(LPAR);
				setState(168); 
				body_expression(0);
				setState(169); 
				match(RPAR);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(187);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(185);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new Body_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_body_expression);
						setState(173);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(174); 
						match(SEQ);
						setState(175); 
						body_expression(5);
						}
						break;
					case 2:
						{
						_localctx = new Body_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_body_expression);
						setState(176);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(177);
						_la = _input.LA(1);
						if ( !(_la==PAR || _la==ALT) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(178); 
						body_expression(4);
						}
						break;
					case 3:
						{
						_localctx = new Body_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_body_expression);
						setState(179);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(180); 
						match(AND);
						setState(181); 
						body_expression(3);
						}
						break;
					case 4:
						{
						_localctx = new Body_expressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_body_expression);
						setState(182);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(183);
						_la = _input.LA(1);
						if ( !(_la==OR || _la==XOR) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(184); 
						body_expression(2);
						}
						break;
					}
					} 
				}
				setState(189);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
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
		enterRule(_localctx, 36, RULE_body_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(190); 
				identifier();
				}
				break;
			case 2:
				{
				setState(191); 
				variable();
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
			setState(196);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << GT) | (1L << LT) | (1L << GE) | (1L << LE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(201);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(197); 
				match(IDENTIFIER);
				}
				break;
			case 2:
				{
				setState(198); 
				match(VARIABLE);
				}
				break;
			case 3:
				{
				setState(199); 
				match(INTEGER);
				}
				break;
			case 4:
				{
				setState(200); 
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
		enterRule(_localctx, 38, RULE_num_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			switch (_input.LA(1)) {
			case VARIABLE:
				{
				setState(203); 
				variable();
				}
				break;
			case INTEGER:
				{
				setState(204); 
				match(INTEGER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(207);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(210);
			switch (_input.LA(1)) {
			case VARIABLE:
				{
				setState(208); 
				variable();
				}
				break;
			case INTEGER:
				{
				setState(209); 
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
		enterRule(_localctx, 40, RULE_ext_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			_la = _input.LA(1);
			if (_la==NOT || _la==TILDE) {
				{
				setState(212);
				_la = _input.LA(1);
				if ( !(_la==NOT || _la==TILDE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
			}

			setState(215); 
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
		enterRule(_localctx, 42, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(217); 
				match(MINUS);
				}
			}

			setState(220); 
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
		enterRule(_localctx, 44, RULE_pos_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222); 
			predicate();
			setState(227);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(223); 
				match(LPAR);
				setState(224); 
				list_parameters();
				setState(225); 
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
		enterRule(_localctx, 46, RULE_list_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229); 
			parameter();
			setState(232);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(230); 
				match(COMMA);
				setState(231); 
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
		enterRule(_localctx, 48, RULE_parameter);
		try {
			setState(238);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(234); 
				variable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(235); 
				constant();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(236); 
				pos_literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(237); 
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
		enterRule(_localctx, 50, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240); 
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
		enterRule(_localctx, 52, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242); 
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
		enterRule(_localctx, 54, RULE_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244); 
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
		enterRule(_localctx, 56, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246); 
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
		case 13: 
			return head_expression_sempred((Head_expressionContext)_localctx, predIndex);
		case 15: 
			return operation_sempred((OperationContext)_localctx, predIndex);
		case 17: 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3$\u00fb\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3\2\7\2"+
		"A\n\2\f\2\16\2D\13\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\5\5Q"+
		"\n\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\5\b^\n\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\5\r"+
		"s\n\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\5\17}\n\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u008b\n\17\f\17\16"+
		"\17\u008e\13\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0098\n\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u00a0\n\21\f\21\16\21\u00a3\13\21"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00ae\n\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u00bc\n\23\f\23"+
		"\16\23\u00bf\13\23\3\24\3\24\3\24\3\24\5\24\u00c5\n\24\3\24\3\24\3\24"+
		"\3\24\3\24\5\24\u00cc\n\24\3\25\3\25\5\25\u00d0\n\25\3\25\3\25\3\25\5"+
		"\25\u00d5\n\25\3\26\5\26\u00d8\n\26\3\26\3\26\3\27\5\27\u00dd\n\27\3\27"+
		"\3\27\3\30\3\30\3\30\3\30\3\30\5\30\u00e6\n\30\3\31\3\31\3\31\5\31\u00eb"+
		"\n\31\3\32\3\32\3\32\3\32\5\32\u00f1\n\32\3\33\3\33\3\34\3\34\3\35\3\35"+
		"\3\36\3\36\3\36\2\5\34 $\37\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"$&(*,.\60\62\64\668:\2\7\3\2\26\27\3\2\23\24\3\2\f\21\3\2\33\34\4\2\31"+
		"\31\35\35\u0101\2B\3\2\2\2\4G\3\2\2\2\6J\3\2\2\2\bP\3\2\2\2\nR\3\2\2\2"+
		"\fW\3\2\2\2\16]\3\2\2\2\20_\3\2\2\2\22d\3\2\2\2\24k\3\2\2\2\26m\3\2\2"+
		"\2\30o\3\2\2\2\32t\3\2\2\2\34|\3\2\2\2\36\u008f\3\2\2\2 \u0097\3\2\2\2"+
		"\"\u00a4\3\2\2\2$\u00ad\3\2\2\2&\u00c4\3\2\2\2(\u00cf\3\2\2\2*\u00d7\3"+
		"\2\2\2,\u00dc\3\2\2\2.\u00e0\3\2\2\2\60\u00e7\3\2\2\2\62\u00f0\3\2\2\2"+
		"\64\u00f2\3\2\2\2\66\u00f4\3\2\2\28\u00f6\3\2\2\2:\u00f8\3\2\2\2<A\5\4"+
		"\3\2=A\5\6\4\2>A\5\b\5\2?A\5\16\b\2@<\3\2\2\2@=\3\2\2\2@>\3\2\2\2@?\3"+
		"\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2CE\3\2\2\2DB\3\2\2\2EF\7\2\2\3F\3"+
		"\3\2\2\2GH\5\24\13\2HI\7\6\2\2I\5\3\2\2\2JK\7\30\2\2KL\5 \21\2LM\7\6\2"+
		"\2M\7\3\2\2\2NQ\5\n\6\2OQ\5\f\7\2PN\3\2\2\2PO\3\2\2\2Q\t\3\2\2\2RS\5\24"+
		"\13\2ST\7\4\2\2TU\5\26\f\2UV\7\6\2\2V\13\3\2\2\2WX\7\4\2\2XY\5\26\f\2"+
		"YZ\7\6\2\2Z\r\3\2\2\2[^\5\20\t\2\\^\5\22\n\2][\3\2\2\2]\\\3\2\2\2^\17"+
		"\3\2\2\2_`\5$\23\2`a\7\30\2\2ab\5 \21\2bc\7\6\2\2c\21\3\2\2\2de\5\36\20"+
		"\2ef\7\5\2\2fg\5$\23\2gh\7\30\2\2hi\5 \21\2ij\7\6\2\2j\23\3\2\2\2kl\5"+
		"\34\17\2l\25\3\2\2\2mn\5$\23\2n\27\3\2\2\2or\5,\27\2pq\7\7\2\2qs\5\30"+
		"\r\2rp\3\2\2\2rs\3\2\2\2s\31\3\2\2\2tu\5,\27\2u\33\3\2\2\2vw\b\17\1\2"+
		"w}\5\32\16\2xy\7\b\2\2yz\5\34\17\2z{\7\t\2\2{}\3\2\2\2|v\3\2\2\2|x\3\2"+
		"\2\2}\u008c\3\2\2\2~\177\f\6\2\2\177\u0080\7\25\2\2\u0080\u008b\5\34\17"+
		"\7\u0081\u0082\f\5\2\2\u0082\u0083\t\2\2\2\u0083\u008b\5\34\17\6\u0084"+
		"\u0085\f\4\2\2\u0085\u0086\7\22\2\2\u0086\u008b\5\34\17\5\u0087\u0088"+
		"\f\3\2\2\u0088\u0089\t\3\2\2\u0089\u008b\5\34\17\4\u008a~\3\2\2\2\u008a"+
		"\u0081\3\2\2\2\u008a\u0084\3\2\2\2\u008a\u0087\3\2\2\2\u008b\u008e\3\2"+
		"\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\35\3\2\2\2\u008e\u008c"+
		"\3\2\2\2\u008f\u0090\5,\27\2\u0090\37\3\2\2\2\u0091\u0092\b\21\1\2\u0092"+
		"\u0098\5\36\20\2\u0093\u0094\7\b\2\2\u0094\u0095\5 \21\2\u0095\u0096\7"+
		"\t\2\2\u0096\u0098\3\2\2\2\u0097\u0091\3\2\2\2\u0097\u0093\3\2\2\2\u0098"+
		"\u00a1\3\2\2\2\u0099\u009a\f\4\2\2\u009a\u009b\7\25\2\2\u009b\u00a0\5"+
		" \21\5\u009c\u009d\f\3\2\2\u009d\u009e\t\2\2\2\u009e\u00a0\5 \21\4\u009f"+
		"\u0099\3\2\2\2\u009f\u009c\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2"+
		"\2\2\u00a1\u00a2\3\2\2\2\u00a2!\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a5"+
		"\5*\26\2\u00a5#\3\2\2\2\u00a6\u00a7\b\23\1\2\u00a7\u00ae\5\"\22\2\u00a8"+
		"\u00ae\5&\24\2\u00a9\u00aa\7\b\2\2\u00aa\u00ab\5$\23\2\u00ab\u00ac\7\t"+
		"\2\2\u00ac\u00ae\3\2\2\2\u00ad\u00a6\3\2\2\2\u00ad\u00a8\3\2\2\2\u00ad"+
		"\u00a9\3\2\2\2\u00ae\u00bd\3\2\2\2\u00af\u00b0\f\6\2\2\u00b0\u00b1\7\25"+
		"\2\2\u00b1\u00bc\5$\23\7\u00b2\u00b3\f\5\2\2\u00b3\u00b4\t\2\2\2\u00b4"+
		"\u00bc\5$\23\6\u00b5\u00b6\f\4\2\2\u00b6\u00b7\7\22\2\2\u00b7\u00bc\5"+
		"$\23\5\u00b8\u00b9\f\3\2\2\u00b9\u00ba\t\3\2\2\u00ba\u00bc\5$\23\4\u00bb"+
		"\u00af\3\2\2\2\u00bb\u00b2\3\2\2\2\u00bb\u00b5\3\2\2\2\u00bb\u00b8\3\2"+
		"\2\2\u00bc\u00bf\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be"+
		"%\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0\u00c5\5\66\34\2\u00c1\u00c5\5:\36"+
		"\2\u00c2\u00c5\7 \2\2\u00c3\u00c5\5(\25\2\u00c4\u00c0\3\2\2\2\u00c4\u00c1"+
		"\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6"+
		"\u00cb\t\4\2\2\u00c7\u00cc\7!\2\2\u00c8\u00cc\7\"\2\2\u00c9\u00cc\7 \2"+
		"\2\u00ca\u00cc\5(\25\2\u00cb\u00c7\3\2\2\2\u00cb\u00c8\3\2\2\2\u00cb\u00c9"+
		"\3\2\2\2\u00cb\u00ca\3\2\2\2\u00cc\'\3\2\2\2\u00cd\u00d0\5:\36\2\u00ce"+
		"\u00d0\7 \2\2\u00cf\u00cd\3\2\2\2\u00cf\u00ce\3\2\2\2\u00d0\u00d1\3\2"+
		"\2\2\u00d1\u00d4\t\5\2\2\u00d2\u00d5\5:\36\2\u00d3\u00d5\7 \2\2\u00d4"+
		"\u00d2\3\2\2\2\u00d4\u00d3\3\2\2\2\u00d5)\3\2\2\2\u00d6\u00d8\t\6\2\2"+
		"\u00d7\u00d6\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00da"+
		"\5,\27\2\u00da+\3\2\2\2\u00db\u00dd\7\34\2\2\u00dc\u00db\3\2\2\2\u00dc"+
		"\u00dd\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00df\5.\30\2\u00df-\3\2\2\2"+
		"\u00e0\u00e5\5\64\33\2\u00e1\u00e2\7\b\2\2\u00e2\u00e3\5\60\31\2\u00e3"+
		"\u00e4\7\t\2\2\u00e4\u00e6\3\2\2\2\u00e5\u00e1\3\2\2\2\u00e5\u00e6\3\2"+
		"\2\2\u00e6/\3\2\2\2\u00e7\u00ea\5\62\32\2\u00e8\u00e9\7\7\2\2\u00e9\u00eb"+
		"\5\60\31\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\61\3\2\2\2\u00ec"+
		"\u00f1\5:\36\2\u00ed\u00f1\58\35\2\u00ee\u00f1\5.\30\2\u00ef\u00f1\5("+
		"\25\2\u00f0\u00ec\3\2\2\2\u00f0\u00ed\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0"+
		"\u00ef\3\2\2\2\u00f1\63\3\2\2\2\u00f2\u00f3\7!\2\2\u00f3\65\3\2\2\2\u00f4"+
		"\u00f5\7!\2\2\u00f5\67\3\2\2\2\u00f6\u00f7\7 \2\2\u00f79\3\2\2\2\u00f8"+
		"\u00f9\7\"\2\2\u00f9;\3\2\2\2\31@BP]r|\u008a\u008c\u0097\u009f\u00a1\u00ad"+
		"\u00bb\u00bd\u00c4\u00cb\u00cf\u00d4\u00d7\u00dc\u00e5\u00ea\u00f0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}