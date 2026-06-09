// Generated from src/gyh/GYH.g4 by ANTLR 4.13.2

package gyh.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GYHParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GYHVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GYHParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(GYHParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link GYHParser#declarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarations(GYHParser.DeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GYHParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(GYHParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GYHParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(GYHParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GYHParser#statementList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementList(GYHParser.StatementListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GYHParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(GYHParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GYHParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(GYHParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link GYHParser#readCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadCommand(GYHParser.ReadCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link GYHParser#printCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintCommand(GYHParser.PrintCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link GYHParser#ifCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfCommand(GYHParser.IfCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link GYHParser#whileCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileCommand(GYHParser.WhileCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link GYHParser#subAlgorithm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubAlgorithm(GYHParser.SubAlgorithmContext ctx);
	/**
	 * Visit a parse tree produced by {@link GYHParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(GYHParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GYHParser#logicalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalExpression(GYHParser.LogicalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GYHParser#relationalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpression(GYHParser.RelationalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GYHParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticExpression(GYHParser.ArithmeticExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GYHParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(GYHParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link GYHParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(GYHParser.FactorContext ctx);
}