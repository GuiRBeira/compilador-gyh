package gyh;

import gyh.parser.GYHBaseVisitor;
import gyh.parser.GYHParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class GYHCodeGenerator extends GYHBaseVisitor<String> {
    private SymbolTable symbolTable;
    private int indentLevel = 1;

    public GYHCodeGenerator(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    private String getIndent() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentLevel; i++) {
            sb.append("    ");
        }
        return sb.toString();
    }

    private TokenType getExpressionType(GYHParser.ExpressionContext ctx) {
        return new GYHBaseVisitor<TokenType>() {
            @Override
            public TokenType visitFactor(GYHParser.FactorContext ctx) {
                if (ctx.Var() != null) {
                    return symbolTable.getType(ctx.Var().getText());
                } else if (ctx.NumInt() != null) {
                    return TokenType.PCInt;
                } else if (ctx.NumReal() != null) {
                    return TokenType.PCReal;
                } else {
                    return visit(ctx.expression());
                }
            }

            @Override
            protected TokenType defaultResult() {
                return TokenType.PCInt;
            }

            @Override
            protected TokenType aggregateResult(TokenType aggregate, TokenType nextResult) {
                if (aggregate == TokenType.PCReal || nextResult == TokenType.PCReal) {
                    return TokenType.PCReal;
                }
                return TokenType.PCInt;
            }
        }.visit(ctx);
    }

    @Override
    public String visitPrograma(GYHParser.ProgramaContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("#include <stdio.h>\n");
        sb.append("#include <stdlib.h>\n\n");
        sb.append("int main() {\n");
        
        sb.append(visit(ctx.declarations()));
        sb.append(visit(ctx.statementList()));
        
        sb.append("    return 0;\n");
        sb.append("}\n");
        return sb.toString();
    }

    @Override
    public String visitDeclarations(GYHParser.DeclarationsContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (GYHParser.VariableDeclarationContext varCtx : ctx.variableDeclaration()) {
            sb.append(visit(varCtx));
        }
        if (ctx.variableDeclaration().size() > 0) {
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String visitVariableDeclaration(GYHParser.VariableDeclarationContext ctx) {
        String varName = ctx.Var().getText();
        String typeStr = ctx.type().getText();
        String cType = typeStr.equals("INT") ? "int" : "double";
        return getIndent() + cType + " " + varName + ";\n";
    }

    @Override
    public String visitStatementList(GYHParser.StatementListContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (GYHParser.StatementContext stmtCtx : ctx.statement()) {
            sb.append(visit(stmtCtx));
        }
        return sb.toString();
    }

    @Override
    public String visitStatement(GYHParser.StatementContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public String visitAssignment(GYHParser.AssignmentContext ctx) {
        String name = ctx.Var().getText();
        String expr = visit(ctx.expression());
        return getIndent() + name + " = " + expr + ";\n";
    }

    @Override
    public String visitReadCommand(GYHParser.ReadCommandContext ctx) {
        String varName = ctx.Var().getText();
        TokenType type = symbolTable.getType(varName);
        if (type == TokenType.PCReal) {
            return getIndent() + "scanf(\"%lf\", &" + varName + ");\n";
        } else {
            return getIndent() + "scanf(\"%d\", &" + varName + ");\n";
        }
    }

    @Override
    public String visitPrintCommand(GYHParser.PrintCommandContext ctx) {
        if (ctx.Cadeia() != null) {
            return getIndent() + "printf(\"%s\\n\", " + ctx.Cadeia().getText() + ");\n";
        } else if (ctx.expression() != null) {
            String expr = visit(ctx.expression());
            TokenType type = getExpressionType(ctx.expression());
            if (type == TokenType.PCReal) {
                return getIndent() + "printf(\"%lf\\n\", " + expr + ");\n";
            } else {
                return getIndent() + "printf(\"%d\\n\", " + expr + ");\n";
            }
        }
        return "";
    }

    @Override
    public String visitIfCommand(GYHParser.IfCommandContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(getIndent()).append("if (").append(visit(ctx.expression())).append(") {\n");
        indentLevel++;
        sb.append(visit(ctx.statementList(0)));
        indentLevel--;
        sb.append(getIndent()).append("}");
        if (ctx.statementList().size() > 1) {
            sb.append(" else {\n");
            indentLevel++;
            sb.append(visit(ctx.statementList(1)));
            indentLevel--;
            sb.append(getIndent()).append("}\n");
        } else {
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String visitWhileCommand(GYHParser.WhileCommandContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(getIndent()).append("while (").append(visit(ctx.expression())).append(") ");
        sb.append(visit(ctx.subAlgorithm()));
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public String visitSubAlgorithm(GYHParser.SubAlgorithmContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        indentLevel++;
        sb.append(visit(ctx.statementList()));
        indentLevel--;
        sb.append(getIndent()).append("}");
        return sb.toString();
    }

    @Override
    public String visitExpression(GYHParser.ExpressionContext ctx) {
        return visit(ctx.logicalExpression());
    }

    @Override
    public String visitLogicalExpression(GYHParser.LogicalExpressionContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.relationalExpression(0)));
        for (int i = 1; i < ctx.relationalExpression().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            if (op.equals("E")) {
                op = "&&";
            } else if (op.equals("OU")) {
                op = "||";
            }
            sb.append(" ").append(op).append(" ").append(visit(ctx.relationalExpression(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitRelationalExpression(GYHParser.RelationalExpressionContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.arithmeticExpression(0)));
        for (int i = 1; i < ctx.arithmeticExpression().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            sb.append(" ").append(op).append(" ").append(visit(ctx.arithmeticExpression(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitArithmeticExpression(GYHParser.ArithmeticExpressionContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.term(0)));
        for (int i = 1; i < ctx.term().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            sb.append(" ").append(op).append(" ").append(visit(ctx.term(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitTerm(GYHParser.TermContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.factor(0)));
        for (int i = 1; i < ctx.factor().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            sb.append(" ").append(op).append(" ").append(visit(ctx.factor(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitFactor(GYHParser.FactorContext ctx) {
        if (ctx.Var() != null) {
            return ctx.Var().getText();
        } else if (ctx.NumInt() != null) {
            return ctx.NumInt().getText();
        } else if (ctx.NumReal() != null) {
            return ctx.NumReal().getText();
        } else if (ctx.expression() != null) {
            return "(" + visit(ctx.expression()) + ")";
        }
        return "";
    }
}
