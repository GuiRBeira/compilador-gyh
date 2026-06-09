// Generated from src/gyh/GYH.g4 by ANTLR 4.13.2

package gyh.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GYHParser}.
 */
public interface GYHListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GYHParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(GYHParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link GYHParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(GYHParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link GYHParser#declarations}.
	 * @param ctx the parse tree
	 */
	void enterDeclarations(GYHParser.DeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GYHParser#declarations}.
	 * @param ctx the parse tree
	 */
	void exitDeclarations(GYHParser.DeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GYHParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(GYHParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GYHParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(GYHParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GYHParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(GYHParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GYHParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(GYHParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GYHParser#statementList}.
	 * @param ctx the parse tree
	 */
	void enterStatementList(GYHParser.StatementListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GYHParser#statementList}.
	 * @param ctx the parse tree
	 */
	void exitStatementList(GYHParser.StatementListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GYHParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(GYHParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GYHParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(GYHParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GYHParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(GYHParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GYHParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(GYHParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link GYHParser#readCommand}.
	 * @param ctx the parse tree
	 */
	void enterReadCommand(GYHParser.ReadCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link GYHParser#readCommand}.
	 * @param ctx the parse tree
	 */
	void exitReadCommand(GYHParser.ReadCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link GYHParser#printCommand}.
	 * @param ctx the parse tree
	 */
	void enterPrintCommand(GYHParser.PrintCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link GYHParser#printCommand}.
	 * @param ctx the parse tree
	 */
	void exitPrintCommand(GYHParser.PrintCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link GYHParser#ifCommand}.
	 * @param ctx the parse tree
	 */
	void enterIfCommand(GYHParser.IfCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link GYHParser#ifCommand}.
	 * @param ctx the parse tree
	 */
	void exitIfCommand(GYHParser.IfCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link GYHParser#whileCommand}.
	 * @param ctx the parse tree
	 */
	void enterWhileCommand(GYHParser.WhileCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link GYHParser#whileCommand}.
	 * @param ctx the parse tree
	 */
	void exitWhileCommand(GYHParser.WhileCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link GYHParser#subAlgorithm}.
	 * @param ctx the parse tree
	 */
	void enterSubAlgorithm(GYHParser.SubAlgorithmContext ctx);
	/**
	 * Exit a parse tree produced by {@link GYHParser#subAlgorithm}.
	 * @param ctx the parse tree
	 */
	void exitSubAlgorithm(GYHParser.SubAlgorithmContext ctx);
	/**
	 * Enter a parse tree produced by {@link GYHParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(GYHParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GYHParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(GYHParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GYHParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalExpression(GYHParser.LogicalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GYHParser#logicalExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalExpression(GYHParser.LogicalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GYHParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(GYHParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GYHParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(GYHParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GYHParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpression(GYHParser.ArithmeticExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GYHParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpression(GYHParser.ArithmeticExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GYHParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(GYHParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link GYHParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(GYHParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link GYHParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(GYHParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GYHParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(GYHParser.FactorContext ctx);
}