package gyh;

import gyh.parser.GYHBaseVisitor;
import gyh.parser.GYHParser;
import org.antlr.v4.runtime.Token;

public class GYHSemanticAnalyzer extends GYHBaseVisitor<TokenType> {
    private SymbolTable symbolTable;

    public GYHSemanticAnalyzer() {
        this.symbolTable = new SymbolTable();
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    private void semanticError(String message, Token token) {
        throw new GYHException("SEMÂNTICO", message, token.getLine(), token.getCharPositionInLine() + 1);
    }

    private TokenType promote(TokenType t1, TokenType t2) {
        if (t1 == TokenType.PCReal || t2 == TokenType.PCReal) {
            return TokenType.PCReal;
        }
        return TokenType.PCInt;
    }

    @Override
    public TokenType visitVariableDeclaration(GYHParser.VariableDeclarationContext ctx) {
        String varName = ctx.Var().getText();
        String typeStr = ctx.type().getText();
        TokenType type = typeStr.equals("INT") ? TokenType.PCInt : TokenType.PCReal;
        
        if (!symbolTable.add(varName, type)) {
            semanticError("Variável '" + varName + "' já declarada.", ctx.Var().getSymbol());
        }
        return null;
    }

    @Override
    public TokenType visitAssignment(GYHParser.AssignmentContext ctx) {
        String varName = ctx.Var().getText();
        if (!symbolTable.exists(varName)) {
            semanticError("Variável '" + varName + "' não declarada.", ctx.Var().getSymbol());
        }
        TokenType varType = symbolTable.getType(varName);
        
        TokenType exprType = visit(ctx.expression());
        
        if (varType == TokenType.PCInt && exprType == TokenType.PCReal) {
            semanticError("Tipo incompatível: não é possível atribuir REAL para a variável INT '" + varName + "'.", ctx.Var().getSymbol());
        }
        return null;
    }

    @Override
    public TokenType visitReadCommand(GYHParser.ReadCommandContext ctx) {
        String varName = ctx.Var().getText();
        if (!symbolTable.exists(varName)) {
            semanticError("Variável '" + varName + "' não declarada.", ctx.Var().getSymbol());
        }
        return null;
    }

    @Override
    public TokenType visitPrintCommand(GYHParser.PrintCommandContext ctx) {
        if (ctx.expression() != null) {
            visit(ctx.expression());
        }
        return null;
    }

    @Override
    public TokenType visitExpression(GYHParser.ExpressionContext ctx) {
        return visit(ctx.logicalExpression());
    }

    @Override
    public TokenType visitLogicalExpression(GYHParser.LogicalExpressionContext ctx) {
        TokenType type = visit(ctx.relationalExpression(0));
        for (int i = 1; i < ctx.relationalExpression().size(); i++) {
            visit(ctx.relationalExpression(i));
            type = TokenType.PCInt;
        }
        return type;
    }

    @Override
    public TokenType visitRelationalExpression(GYHParser.RelationalExpressionContext ctx) {
        TokenType type = visit(ctx.arithmeticExpression(0));
        for (int i = 1; i < ctx.arithmeticExpression().size(); i++) {
            visit(ctx.arithmeticExpression(i));
            type = TokenType.PCInt;
        }
        return type;
    }

    @Override
    public TokenType visitArithmeticExpression(GYHParser.ArithmeticExpressionContext ctx) {
        TokenType type = visit(ctx.term(0));
        for (int i = 1; i < ctx.term().size(); i++) {
            TokenType rightType = visit(ctx.term(i));
            type = promote(type, rightType);
        }
        return type;
    }

    @Override
    public TokenType visitTerm(GYHParser.TermContext ctx) {
        TokenType type = visit(ctx.factor(0));
        for (int i = 1; i < ctx.factor().size(); i++) {
            TokenType rightType = visit(ctx.factor(i));
            type = promote(type, rightType);
        }
        return type;
    }

    @Override
    public TokenType visitFactor(GYHParser.FactorContext ctx) {
        if (ctx.Var() != null) {
            String varName = ctx.Var().getText();
            if (!symbolTable.exists(varName)) {
                semanticError("Variável '" + varName + "' não declarada.", ctx.Var().getSymbol());
            }
            return symbolTable.getType(varName);
        } else if (ctx.NumInt() != null) {
            return TokenType.PCInt;
        } else if (ctx.NumReal() != null) {
            return TokenType.PCReal;
        } else if (ctx.expression() != null) {
            return visit(ctx.expression());
        }
        return null;
    }
}
