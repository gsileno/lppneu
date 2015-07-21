// Generated from /home/giovanni/dev/lppneu/antlr4/LPPN.g4 by ANTLR 4.5
package org.leibnizcenter.lppneu.parser;
import org.antlr.v4.runtime.misc.NotNull;
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
	void enterProgram(@NotNull LPPNParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull LPPNParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#situationfact}.
	 * @param ctx the parse tree
	 */
	void enterSituationfact(@NotNull LPPNParser.SituationfactContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#situationfact}.
	 * @param ctx the parse tree
	 */
	void exitSituationfact(@NotNull LPPNParser.SituationfactContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#eventfact}.
	 * @param ctx the parse tree
	 */
	void enterEventfact(@NotNull LPPNParser.EventfactContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#eventfact}.
	 * @param ctx the parse tree
	 */
	void exitEventfact(@NotNull LPPNParser.EventfactContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#logicrule}.
	 * @param ctx the parse tree
	 */
	void enterLogicrule(@NotNull LPPNParser.LogicruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#logicrule}.
	 * @param ctx the parse tree
	 */
	void exitLogicrule(@NotNull LPPNParser.LogicruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#normrule}.
	 * @param ctx the parse tree
	 */
	void enterNormrule(@NotNull LPPNParser.NormruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#normrule}.
	 * @param ctx the parse tree
	 */
	void exitNormrule(@NotNull LPPNParser.NormruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterConstraint(@NotNull LPPNParser.ConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitConstraint(@NotNull LPPNParser.ConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#causalrule}.
	 * @param ctx the parse tree
	 */
	void enterCausalrule(@NotNull LPPNParser.CausalruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#causalrule}.
	 * @param ctx the parse tree
	 */
	void exitCausalrule(@NotNull LPPNParser.CausalruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#carule}.
	 * @param ctx the parse tree
	 */
	void enterCarule(@NotNull LPPNParser.CaruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#carule}.
	 * @param ctx the parse tree
	 */
	void exitCarule(@NotNull LPPNParser.CaruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#ecarule}.
	 * @param ctx the parse tree
	 */
	void enterEcarule(@NotNull LPPNParser.EcaruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#ecarule}.
	 * @param ctx the parse tree
	 */
	void exitEcarule(@NotNull LPPNParser.EcaruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#head}.
	 * @param ctx the parse tree
	 */
	void enterHead(@NotNull LPPNParser.HeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#head}.
	 * @param ctx the parse tree
	 */
	void exitHead(@NotNull LPPNParser.HeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(@NotNull LPPNParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(@NotNull LPPNParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#list_literals}.
	 * @param ctx the parse tree
	 */
	void enterList_literals(@NotNull LPPNParser.List_literalsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#list_literals}.
	 * @param ctx the parse tree
	 */
	void exitList_literals(@NotNull LPPNParser.List_literalsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#head_situation}.
	 * @param ctx the parse tree
	 */
	void enterHead_situation(@NotNull LPPNParser.Head_situationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#head_situation}.
	 * @param ctx the parse tree
	 */
	void exitHead_situation(@NotNull LPPNParser.Head_situationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#head_expression}.
	 * @param ctx the parse tree
	 */
	void enterHead_expression(@NotNull LPPNParser.Head_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#head_expression}.
	 * @param ctx the parse tree
	 */
	void exitHead_expression(@NotNull LPPNParser.Head_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#event}.
	 * @param ctx the parse tree
	 */
	void enterEvent(@NotNull LPPNParser.EventContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#event}.
	 * @param ctx the parse tree
	 */
	void exitEvent(@NotNull LPPNParser.EventContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation(@NotNull LPPNParser.OperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation(@NotNull LPPNParser.OperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#body_situation}.
	 * @param ctx the parse tree
	 */
	void enterBody_situation(@NotNull LPPNParser.Body_situationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#body_situation}.
	 * @param ctx the parse tree
	 */
	void exitBody_situation(@NotNull LPPNParser.Body_situationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#body_expression}.
	 * @param ctx the parse tree
	 */
	void enterBody_expression(@NotNull LPPNParser.Body_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#body_expression}.
	 * @param ctx the parse tree
	 */
	void exitBody_expression(@NotNull LPPNParser.Body_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#body_constraint}.
	 * @param ctx the parse tree
	 */
	void enterBody_constraint(@NotNull LPPNParser.Body_constraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#body_constraint}.
	 * @param ctx the parse tree
	 */
	void exitBody_constraint(@NotNull LPPNParser.Body_constraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#num_expression}.
	 * @param ctx the parse tree
	 */
	void enterNum_expression(@NotNull LPPNParser.Num_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#num_expression}.
	 * @param ctx the parse tree
	 */
	void exitNum_expression(@NotNull LPPNParser.Num_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#ext_literal}.
	 * @param ctx the parse tree
	 */
	void enterExt_literal(@NotNull LPPNParser.Ext_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#ext_literal}.
	 * @param ctx the parse tree
	 */
	void exitExt_literal(@NotNull LPPNParser.Ext_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(@NotNull LPPNParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(@NotNull LPPNParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#pos_literal}.
	 * @param ctx the parse tree
	 */
	void enterPos_literal(@NotNull LPPNParser.Pos_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#pos_literal}.
	 * @param ctx the parse tree
	 */
	void exitPos_literal(@NotNull LPPNParser.Pos_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#list_parameters}.
	 * @param ctx the parse tree
	 */
	void enterList_parameters(@NotNull LPPNParser.List_parametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#list_parameters}.
	 * @param ctx the parse tree
	 */
	void exitList_parameters(@NotNull LPPNParser.List_parametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(@NotNull LPPNParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(@NotNull LPPNParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(@NotNull LPPNParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(@NotNull LPPNParser.PredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(@NotNull LPPNParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(@NotNull LPPNParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(@NotNull LPPNParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(@NotNull LPPNParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPPNParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull LPPNParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPPNParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull LPPNParser.VariableContext ctx);
}