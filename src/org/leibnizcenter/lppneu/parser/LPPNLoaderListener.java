package org.leibnizcenter.lppneu.parser;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.apache.log4j.Logger;
import org.leibnizcenter.lppneu.components.language.*;

import java.util.ArrayList;
import java.util.List;


public class LPPNLoaderListener extends LPPNBaseListener {

    private Program program;

    public Program getProgram() { return program; }

    private final static Logger log = Logger.getLogger("LoaderListener");

    // to enrich the parsing tree with ids to already computed values (atoms, formulas, ...)

    // Mapping of nodes
    private ParseTreeProperty<Atom> atomNodes = new ParseTreeProperty<Atom>();
    private ParseTreeProperty<Parameter> parameterNodes = new ParseTreeProperty<Parameter>();
    private ParseTreeProperty<Literal> literalNodes = new ParseTreeProperty<Literal>();
    private ParseTreeProperty<ExtLiteral> extLiteralNodes = new ParseTreeProperty<ExtLiteral>();
    private ParseTreeProperty<Situation> situationNodes = new ParseTreeProperty<Situation>();
    private ParseTreeProperty<Expression> expressionNodes = new ParseTreeProperty<Expression>();
    private ParseTreeProperty<Event> eventNodes = new ParseTreeProperty<Event>();
    private ParseTreeProperty<Operation> operationNodes = new ParseTreeProperty<Operation>();
    private ParseTreeProperty<LogicRule> logicRuleNodes = new ParseTreeProperty<LogicRule>();
    private ParseTreeProperty<CausalRule> causalRuleNodes = new ParseTreeProperty<CausalRule>();

    // Mapping of (list-type) nodes
    private ParseTreeProperty<List<Object>> listNodes = new ParseTreeProperty<List<Object>>();

    public void addToDecorationList(ParseTree node, Object decoration) {
        List<Object> list = listNodes.get(node);
        if (list == null) {
            list = new ArrayList<Object>();
            listNodes.put(node, list);
        }
        list.add(decoration);
    }

    public List<Object> getDecorationList(ParseTree node) {
        return listNodes.get(node);
    }

    ///////////////// LISTENERS

    public void exitPredicate(LPPNParser.PredicateContext ctx) {
        Atom predicate = Atom.build(ctx.IDENTIFIER().getText());
        atomNodes.put(ctx, predicate);
        log.trace("attaching predicate " + predicate + " to node.");
    }

    public void exitIdentifier(LPPNParser.IdentifierContext ctx) {
        Atom predicate = Atom.build(ctx.IDENTIFIER().getText());
        atomNodes.put(ctx, predicate);
        log.trace("attaching predicate " + predicate + " to node.");
    }

    public void exitParameter(LPPNParser.ParameterContext ctx) {
        if (ctx.pos_literal() != null) {
            log.warn("to be implemented");
        } else if (ctx.variable() != null) {
            log.warn("to be implemented");
        } else if (ctx.constant() != null) {
            log.warn("to be implemented");
        } else if (ctx.num_expression() != null) {
            log.warn("to be implemented");
        }
    }

    // note: the list is constructed in inverse order
    public void exitList_parameters(LPPNParser.List_parametersContext ctx) {

        Parameter parameter = parameterNodes.get(ctx.parameter());
        addToDecorationList(ctx, parameter);

        if (ctx.COMMA() != null) {
            log.trace("attaching parameter " + parameter + " to parameter_list node.");

            List<Object> list = getDecorationList(ctx);
            List<Object> childList = getDecorationList(ctx.list_parameters());
            list.addAll(childList);
            log.trace("merging list with child list node.");

        } else {
            log.trace("attaching single parameter " + parameter + " to new parameter_list node.");
        }
    }

    public void exitPos_literal(LPPNParser.Pos_literalContext ctx) {

        Literal literal = new Literal();
        Atom predicate = atomNodes.get(ctx.predicate());
        literal.setFunctor(predicate);

        if (ctx.list_parameters() != null) {
            List<Object> parameter_list = getDecorationList(ctx.list_parameters());
            List<Parameter> parameters = new ArrayList<Parameter>();
            for (Object parameter: parameter_list) {
                parameters.add((Parameter) parameter);
            }
            literal.setParameters(parameters);
        }

        literalNodes.put(ctx, literal);
    }

    public void exitLiteral(LPPNParser.LiteralContext ctx) {
        ExtLiteral extLiteral = ExtLiteral.build(literalNodes.get(ctx.pos_literal()));

        if (ctx.MINUS() != null || ctx.NEG() != null)
            extLiteral.negate();

        extLiteralNodes.put(ctx, extLiteral);
    }

    public void exitExt_literal(LPPNParser.Ext_literalContext ctx) {
        ExtLiteral extLiteral;

        if (ctx.NOT() != null || ctx.TILDE() != null)
            extLiteral = ExtLiteral.buildNull(extLiteralNodes.get(ctx.literal()));
        else
            extLiteral = extLiteralNodes.get(ctx.literal());

        extLiteralNodes.put(ctx, extLiteral);
    }


    public void exitHead_situation(LPPNParser.Head_situationContext ctx) {
        Situation situation = Situation.build(extLiteralNodes.get(ctx.literal()));
        situationNodes.put(ctx, situation);
    }

    public void exitBody_situation(LPPNParser.Body_situationContext ctx) {
        Situation situation = Situation.build(extLiteralNodes.get(ctx.ext_literal()));
        situationNodes.put(ctx, situation);
    }

    public void exitBody_expression(LPPNParser.Body_expressionContext ctx) {
        Expression expression;

        if (ctx.body_situation() != null) {
            expression = Expression.build(situationNodes.get(ctx.body_situation()));
        } else if (ctx.body_constraint() != null) {
            log.error("to be implemented");
            return;
        } else if (ctx.WHEN() != null) {
            log.trace("operation: "+operationNodes.get(ctx.operation()));
            log.trace("expression: "+operationNodes.get(ctx.operation()).toExpression());
            expression = Expression.build(operationNodes.get(ctx.operation()).toExpression(), expressionNodes.get(ctx.body_expression(0)), Operator.OCCURS_IN);
        } else if (ctx.LPAR() != null) {
            expression = expressionNodes.get(ctx.body_expression(0));
        } else {
            Operator op;
            if (ctx.AND() != null) op = Operator.AND;
            else if (ctx.OR() != null) op = Operator.OR;
            else if (ctx.XOR() != null) op = Operator.XOR;
            else if (ctx.SEQ() != null) op = Operator.SEQ;
            else if (ctx.PAR() != null) op = Operator.PAR;
            else if (ctx.ALT() != null) op = Operator.ALT;
            else {
                throw new RuntimeException("Unknown operator in expression.");
            }

            expression = Expression.build(
                    expressionNodes.get(ctx.body_expression(0)),
                    expressionNodes.get(ctx.body_expression(1)),
                    op
            );
        }
        expressionNodes.put(ctx, expression);
    }

    public void exitHead_expression(LPPNParser.Head_expressionContext ctx) {
        Expression expression;

        if (ctx.head_situation() != null) {
            expression = Expression.build(situationNodes.get(ctx.head_situation()));
        } else if (ctx.WHEN() != null) {
            expression = Expression.build(operationNodes.get(ctx.operation()).toExpression(), expressionNodes.get(ctx.head_expression(0)), Operator.OCCURS_IN);
        } else if (ctx.LPAR() != null) {
            expression = expressionNodes.get(ctx.head_expression(0));
        } else {
            Operator op;
            if (ctx.AND() != null) op = Operator.AND;
            else if (ctx.OR() != null) op = Operator.OR;
            else if (ctx.XOR() != null) op = Operator.XOR;
            else if (ctx.SEQ() != null) op = Operator.SEQ;
            else if (ctx.PAR() != null) op = Operator.PAR;
            else if (ctx.ALT() != null) op = Operator.ALT;
            else {
                throw new RuntimeException("Unknown operator in expression.");
            }

            expression = Expression.build(
                    expressionNodes.get(ctx.head_expression(0)),
                    expressionNodes.get(ctx.head_expression(1)),
                    op
            );
        }
        expressionNodes.put(ctx, expression);
    }

    public void exitHead(LPPNParser.HeadContext ctx) {
        expressionNodes.put(ctx, expressionNodes.get(ctx.head_expression()));
    }

    public void exitBody(LPPNParser.BodyContext ctx) {
        expressionNodes.put(ctx, expressionNodes.get(ctx.body_expression()));
    }

    public void exitEvent(LPPNParser.EventContext ctx) {
        Event event = Event.build(extLiteralNodes.get(ctx.literal()));
        eventNodes.put(ctx, event);
    }

    public void exitCausalrule(LPPNParser.CausalruleContext ctx) {
        CausalRule rule = new CausalRule();
        rule.setCondition(expressionNodes.get(ctx.body_expression()));
        rule.setAction(operationNodes.get(ctx.operation()));
        causalRuleNodes.put(ctx, rule);
    }

    public void exitOperation(LPPNParser.OperationContext ctx) {

        Operation operation;

        if (ctx.event() != null) {
            operation = Operation.build(eventNodes.get(ctx.event()));
        } else if (ctx.LPAR() != null) {
            operation = operationNodes.get(ctx.operation(0));
        } else {
            Operator op;
            if (ctx.SEQ() != null) op = Operator.SEQ;
            else if (ctx.PAR() != null) op = Operator.PAR;
            else if (ctx.ALT() != null) op = Operator.ALT;
            else {
                throw new RuntimeException("Unknown operator in operation.");
            }

            operation = Operation.build(
                    operationNodes.get(ctx.operation(0)),
                    operationNodes.get(ctx.operation(1)),
                    op
            );
        }

        operationNodes.put(ctx, operation);
    }

    public void exitSituationfact(LPPNParser.SituationfactContext ctx) {
        LogicRule rule = new LogicRule();
        rule.setHead(expressionNodes.get(ctx.head()));
        logicRuleNodes.put(ctx, rule);
    }

    public void exitConstraint(LPPNParser.ConstraintContext ctx) {
        LogicRule rule = new LogicRule();
        rule.setBody(expressionNodes.get(ctx.body()));
        logicRuleNodes.put(ctx, rule);
    }

    public void exitNormrule(LPPNParser.NormruleContext ctx) {
        LogicRule rule = new LogicRule();

        if (ctx.IS_EQUIVALENT_TO() != null) { // double implication
            rule.setBiconditional(true);
        } else { // normal implication
            rule.setBiconditional(false);
        }

        rule.setHead(expressionNodes.get(ctx.head()));
        rule.setBody(expressionNodes.get(ctx.body()));
        logicRuleNodes.put(ctx, rule);
    }

    public void exitEventfact(LPPNParser.EventfactContext ctx) {
        CausalRule rule = new CausalRule();
        rule.setAction(operationNodes.get(ctx.operation()));
        causalRuleNodes.put(ctx, rule);
    }

    public void exitLogicrule(LPPNParser.LogicruleContext ctx) {

        if (ctx.constraint() != null) {
            logicRuleNodes.put(ctx, logicRuleNodes.get(ctx.constraint()));
        } else if (ctx.normrule() != null) {
            logicRuleNodes.put(ctx, logicRuleNodes.get(ctx.normrule()));
        }

    }

    public void exitProgram(LPPNParser.ProgramContext ctx) {
        program = new Program();

        for (LPPNParser.CausalruleContext childCtx: ctx.causalrule()) {
            program.getCausalRules().add(causalRuleNodes.get(childCtx));
        }

        for (LPPNParser.LogicruleContext childCtx: ctx.logicrule()) {
            program.getLogicRules().add(logicRuleNodes.get(childCtx));
        }

        for (LPPNParser.SituationfactContext childCtx: ctx.situationfact()) {
            program.getLogicRules().add(logicRuleNodes.get(childCtx));
        }

        for (LPPNParser.EventfactContext childCtx: ctx.eventfact()) {
            program.getCausalRules().add(causalRuleNodes.get(childCtx));
        }
    }

}

