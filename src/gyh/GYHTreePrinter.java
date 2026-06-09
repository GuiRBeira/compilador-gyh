package gyh;

import gyh.parser.GYHBaseVisitor;
import gyh.parser.GYHParser;
import org.antlr.v4.runtime.ParserRuleContext;

public class GYHTreePrinter extends GYHBaseVisitor<Void> {
    private int treeLevel = 0;

    private void printNode(String ruleName, ParserRuleContext ctx) {
        for (int i = 0; i < treeLevel; i++) {
            System.out.print("  ");
        }
        String tokenText = ctx.getStart() != null ? ctx.getStart().getText() : "";
        System.out.println("└─ " + ruleName + " [token: " + tokenText + "]");
    }

    @Override
    public Void visitPrograma(GYHParser.ProgramaContext ctx) {
        printNode("Programa", ctx);
        treeLevel++;
        if (ctx.declarations() != null) {
            visit(ctx.declarations());
        }
        if (ctx.statementList() != null) {
            visit(ctx.statementList());
        }
        treeLevel--;
        return null;
    }

    @Override
    public Void visitDeclarations(GYHParser.DeclarationsContext ctx) {
        printNode("ListaDeclaracoes", ctx);
        treeLevel++;
        for (GYHParser.VariableDeclarationContext varCtx : ctx.variableDeclaration()) {
            visit(varCtx);
        }
        treeLevel--;
        return null;
    }

    @Override
    public Void visitVariableDeclaration(GYHParser.VariableDeclarationContext ctx) {
        printNode("DeclaraçãoVariável", ctx);
        return null;
    }

    @Override
    public Void visitStatementList(GYHParser.StatementListContext ctx) {
        printNode("ListaComandos", ctx);
        treeLevel++;
        for (GYHParser.StatementContext stmtCtx : ctx.statement()) {
            visit(stmtCtx);
        }
        treeLevel--;
        return null;
    }

    @Override
    public Void visitStatement(GYHParser.StatementContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Void visitAssignment(GYHParser.AssignmentContext ctx) {
        printNode("Atribuição", ctx);
        treeLevel++;
        if (ctx.expression() != null) {
            visit(ctx.expression());
        }
        treeLevel--;
        return null;
    }

    @Override
    public Void visitWhileCommand(GYHParser.WhileCommandContext ctx) {
        printNode("ComandoRepeticao (ENQTO)", ctx);
        treeLevel++;
        if (ctx.expression() != null) {
            visit(ctx.expression());
        }
        if (ctx.subAlgorithm() != null) {
            visit(ctx.subAlgorithm());
        }
        treeLevel--;
        return null;
    }

    @Override
    public Void visitSubAlgorithm(GYHParser.SubAlgorithmContext ctx) {
        printNode("SubAlgoritmo (Bloco)", ctx);
        treeLevel++;
        if (ctx.statementList() != null) {
            visit(ctx.statementList());
        }
        treeLevel--;
        return null;
    }

    @Override
    public Void visitExpression(GYHParser.ExpressionContext ctx) {
        printNode("Expressão", ctx);
        return null;
    }
}
// 
