// Generated from /home/giovanni/dev/lppneu/antlr4/LPPN.g4 by ANTLR 4.5.1
package org.leibnizcenter.lppneu.parsers;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LPPNParser}.
 */
public interface LPPNListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LPPNParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(LPPNParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(LPPNParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#situationfact}.
	 * @param ctx the parse tree
	 */
	void enterSituationfact(LPPNParser.SituationfactContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#situationfact}.
	 * @param ctx the parse tree
	 */
	void exitSituationfact(LPPNParser.SituationfactContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#eventfact}.
	 * @param ctx the parse tree
	 */
	void enterEventfact(LPPNParser.EventfactContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#eventfact}.
	 * @param ctx the parse tree
	 */
	void exitEventfact(LPPNParser.EventfactContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#logicrule}.
	 * @param ctx the parse tree
	 */
	void enterLogicrule(LPPNParser.LogicruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#logicrule}.
	 * @param ctx the parse tree
	 */
	void exitLogicrule(LPPNParser.LogicruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#normrule}.
	 * @param ctx the parse tree
	 */
	void enterNormrule(LPPNParser.NormruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#normrule}.
	 * @param ctx the parse tree
	 */
	void exitNormrule(LPPNParser.NormruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterConstraint(LPPNParser.ConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitConstraint(LPPNParser.ConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#causalrule}.
	 * @param ctx the parse tree
	 */
	void enterCausalrule(LPPNParser.CausalruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#causalrule}.
	 * @param ctx the parse tree
	 */
	void exitCausalrule(LPPNParser.CausalruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#head}.
	 * @param ctx the parse tree
	 */
	void enterHead(LPPNParser.HeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#head}.
	 * @param ctx the parse tree
	 */
	void exitHead(LPPNParser.HeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(LPPNParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(LPPNParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#list_literals}.
	 * @param ctx the parse tree
	 */
	void enterList_literals(LPPNParser.List_literalsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#list_literals}.
	 * @param ctx the parse tree
	 */
	void exitList_literals(LPPNParser.List_literalsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#head_situation}.
	 * @param ctx the parse tree
	 */
	void enterHead_situation(LPPNParser.Head_situationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#head_situation}.
	 * @param ctx the parse tree
	 */
	void exitHead_situation(LPPNParser.Head_situationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#head_expression}.
	 * @param ctx the parse tree
	 */
	void enterHead_expression(LPPNParser.Head_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#head_expression}.
	 * @param ctx the parse tree
	 */
	void exitHead_expression(LPPNParser.Head_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#event}.
	 * @param ctx the parse tree
	 */
	void enterEvent(LPPNParser.EventContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#event}.
	 * @param ctx the parse tree
	 */
	void exitEvent(LPPNParser.EventContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation(LPPNParser.OperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation(LPPNParser.OperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#body_situation}.
	 * @param ctx the parse tree
	 */
	void enterBody_situation(LPPNParser.Body_situationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#body_situation}.
	 * @param ctx the parse tree
	 */
	void exitBody_situation(LPPNParser.Body_situationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#body_expression}.
	 * @param ctx the parse tree
	 */
	void enterBody_expression(LPPNParser.Body_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#body_expression}.
	 * @param ctx the parse tree
	 */
	void exitBody_expression(LPPNParser.Body_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#body_constraint}.
	 * @param ctx the parse tree
	 */
	void enterBody_constraint(LPPNParser.Body_constraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#body_constraint}.
	 * @param ctx the parse tree
	 */
	void exitBody_constraint(LPPNParser.Body_constraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#num_expression}.
	 * @param ctx the parse tree
	 */
	void enterNum_expression(LPPNParser.Num_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#num_expression}.
	 * @param ctx the parse tree
	 */
	void exitNum_expression(LPPNParser.Num_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#ext_literal}.
	 * @param ctx the parse tree
	 */
	void enterExt_literal(LPPNParser.Ext_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#ext_literal}.
	 * @param ctx the parse tree
	 */
	void exitExt_literal(LPPNParser.Ext_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(LPPNParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(LPPNParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#pos_literal}.
	 * @param ctx the parse tree
	 */
	void enterPos_literal(LPPNParser.Pos_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#pos_literal}.
	 * @param ctx the parse tree
	 */
	void exitPos_literal(LPPNParser.Pos_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#list_parameters}.
	 * @param ctx the parse tree
	 */
	void enterList_parameters(LPPNParser.List_parametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#list_parameters}.
	 * @param ctx the parse tree
	 */
	void exitList_parameters(LPPNParser.List_parametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(LPPNParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(LPPNParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(LPPNParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(LPPNParser.PredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(LPPNParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(LPPNParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(LPPNParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(LPPNParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(LPPNParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(LPPNParser.VariableContext ctx);
}