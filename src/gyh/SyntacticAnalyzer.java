package gyh;

import java.util.List;

public class SyntacticAnalyzer {
    private LexicalAnalyzer scanner;
    private Token currentToken;
    private SymbolTable symbolTable;

    public SyntacticAnalyzer(LexicalAnalyzer scanner) {
        this.scanner = scanner;
        this.symbolTable = new SymbolTable();
        this.advance(); // Inicializa o primeiro token
    }

    private void advance() {
        currentToken = scanner.getToken();
        if (currentToken != null && currentToken.type == null) {
            semanticError("Erro Léxico encontrado: '" + currentToken.lexeme + "'");
        }
    }

    private void match(TokenType expected) {
        if (currentToken.type == expected) {
            advance();
        } else {
            syntaxError("Esperado " + expected + " mas encontrado " + currentToken.type + " ('" + currentToken.lexeme + "')");
        }
    }

    private void syntaxError(String message) {
        throw new GYHException("SINTÁTICO", message, currentToken.line, currentToken.column);
    }

    private void semanticError(String message) {
        throw new GYHException("SEMÂNTICO", message, currentToken.line, currentToken.column);
    }

    public SymbolTable parse() {
        match(TokenType.Delim); // ':'
        match(TokenType.PCDec); // 'DEC'
        
        declarations();
        
        match(TokenType.Delim); // ':'
        match(TokenType.PCProg); // 'PROG'
        
        statementList();
        
        match(TokenType.EOF);
        return symbolTable;
    }

    private void declarations() {
        while (currentToken.type == TokenType.Var) {
            variableDeclaration();
        }
    }

    private void variableDeclaration() {
        String varName = currentToken.lexeme;
        match(TokenType.Var);
        match(TokenType.Delim); // ':'
        
        TokenType varType = currentToken.type;
        if (varType == TokenType.PCInt || varType == TokenType.PCReal) {
            if (!symbolTable.add(varName, varType)) {
                semanticError("Variável '" + varName + "' já declarada.");
            }
            advance();
        } else {
            syntaxError("Tipo inválido. Esperado INT ou REAL.");
        }
    }

    private void statementList() {
        while (currentToken.type != TokenType.EOF && currentToken.type != TokenType.PCFim && currentToken.type != TokenType.PCSenao) {
            statement();
        }
    }

    private void statement() {
        if (currentToken.type == TokenType.Var) {
            assignment();
        } else if (currentToken.type == TokenType.PCLer) {
            readCommand();
        } else if (currentToken.type == TokenType.PCImprimir) {
            printCommand();
        } else if (currentToken.type == TokenType.PCSe) {
            ifCommand();
        } else if (currentToken.type == TokenType.PCEnqto) {
            whileCommand();
        } else {
            syntaxError("Comando inesperado: " + currentToken.lexeme);
        }
    }

    private void assignment() {
        String varName = currentToken.lexeme;
        if (!symbolTable.exists(varName)) {
            semanticError("Variável '" + varName + "' não declarada.");
        }
        TokenType varType = symbolTable.getType(varName);
        
        match(TokenType.Var);
        match(TokenType.Atrib); // ':='
        
        TokenType exprType = expression();
        
        if (varType == TokenType.PCInt && exprType == TokenType.PCReal) {
            semanticError("Tipo incompatível: não é possível atribuir REAL para a variável INT '" + varName + "'.");
        }
    }

    private void readCommand() {
        match(TokenType.PCLer);
        String varName = currentToken.lexeme;
        if (!symbolTable.exists(varName)) {
            semanticError("Variável '" + varName + "' não declarada.");
        }
        match(TokenType.Var);
    }

    private void printCommand() {
        match(TokenType.PCImprimir);
        if (currentToken.type == TokenType.Var) {
            if (!symbolTable.exists(currentToken.lexeme)) {
                semanticError("Variável '" + currentToken.lexeme + "' não declarada.");
            }
            advance();
        } else if (currentToken.type == TokenType.Cadeia) {
            advance();
        } else {
            expression();
        }
    }

    private void ifCommand() {
        match(TokenType.PCSe);
        expression();
        match(TokenType.PCEntao);
        statementList();
        if (currentToken.type == TokenType.PCSenao) {
            match(TokenType.PCSenao);
            statementList();
        }
        match(TokenType.PCFim);
    }

    private void whileCommand() {
        match(TokenType.PCEnqto);
        expression();
        match(TokenType.PCIni);
        statementList();
        match(TokenType.PCFim);
    }

    private TokenType expression() {
        return logicalExpression();
    }

    private TokenType logicalExpression() {
        TokenType type = relationalExpression();
        while (currentToken.type == TokenType.OpBoolE || currentToken.type == TokenType.OpBoolOu) {
            advance();
            relationalExpression();
            type = TokenType.PCInt;
        }
        return type;
    }

    private TokenType relationalExpression() {
        TokenType type = arithmeticExpression();
        while (currentToken.type == TokenType.OpRelMaior || currentToken.type == TokenType.OpRelMaiorIgual ||
               currentToken.type == TokenType.OpRelMenor || currentToken.type == TokenType.OpRelMenorIgual ||
               currentToken.type == TokenType.OpRelIgual || currentToken.type == TokenType.OpRelDif) {
            advance();
            arithmeticExpression();
            type = TokenType.PCInt;
        }
        return type;
    }

    private TokenType arithmeticExpression() {
        TokenType type = term();
        while (currentToken.type == TokenType.OpAritSoma || currentToken.type == TokenType.OpAritSub) {
            advance();
            TokenType rightType = term();
            type = promote(type, rightType);
        }
        return type;
    }

    private TokenType term() {
        TokenType type = factor();
        while (currentToken.type == TokenType.OpAritMult || currentToken.type == TokenType.OpAritDiv) {
            advance();
            TokenType rightType = factor();
            type = promote(type, rightType);
        }
        return type;
    }

    private TokenType factor() {
        if (currentToken.type == TokenType.Var) {
            String varName = currentToken.lexeme;
            if (!symbolTable.exists(varName)) {
                semanticError("Variável '" + varName + "' não declarada.");
            }
            TokenType type = symbolTable.getType(varName);
            advance();
            return type;
        } else if (currentToken.type == TokenType.NumInt) {
            advance();
            return TokenType.PCInt;
        } else if (currentToken.type == TokenType.NumReal) {
            advance();
            return TokenType.PCReal;
        } else if (currentToken.type == TokenType.AbrePar) {
            match(TokenType.AbrePar);
            TokenType type = expression();
            match(TokenType.FechaPar);
            return type;
        } else {
            syntaxError("Fator esperado (identificador, número ou parênteses). Encontrado: " + currentToken.type);
            return null;
        }
    }

    private TokenType promote(TokenType t1, TokenType t2) {
        if (t1 == TokenType.PCReal || t2 == TokenType.PCReal) {
            return TokenType.PCReal;
        }
        return TokenType.PCInt;
    }
}
