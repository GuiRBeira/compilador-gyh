package gyh;

public class SyntacticAnalyzer {
    private LexicalAnalyzer scanner;
    private Token currentToken;
    private SymbolTable symbolTable;
    // Nível atual da árvore para indentação
    private int treeLevel = 0;

    public SyntacticAnalyzer(LexicalAnalyzer scanner) {
        // Atribui o scanner
        this.scanner = scanner;
        // Cria a tabela de símbolos
        this.symbolTable = new SymbolTable();
        // Inicializa o primeiro token
        this.advance(); 
    }

    private void advance() {
        // Pede o próximo token ao scanner
        currentToken = scanner.getToken();
        // Se for um erro léxico, lança erro semântico
        if (currentToken != null && currentToken.type == null) {
            semanticError("Erro Léxico encontrado: '" + currentToken.lexeme + "'");
        }
    }

    private void match(TokenType expected) {
        // Verifica se o tipo do token atual é o esperado
        if (currentToken.type == expected) {
            // Avança para o próximo
            advance();
        } else {
            // Caso contrário, erro sintático
            syntaxError("Esperado " + expected + " mas encontrado " + currentToken.type + " ('" + currentToken.lexeme + "')");
        }
    }

    private void syntaxError(String message) {
        throw new GYHException("SINTÁTICO", message, currentToken.line, currentToken.column);
    }

    private void semanticError(String message) {
        throw new GYHException("SEMÂNTICO", message, currentToken.line, currentToken.column);
    }

    // Método auxiliar para imprimir a árvore sintática
    private void printTree(String rule) {
        for (int i = 0; i < treeLevel; i++) {
            System.out.print("  ");
        }
        System.out.println("└─ " + rule + (currentToken != null ? " [token: " + currentToken.lexeme + "]" : ""));
    }

    public SymbolTable parse() {
        printTree("Programa");
        treeLevel++;
        
        // Deve começar com :DEC
        match(TokenType.Delim); // ':'
        match(TokenType.PCDec); // 'DEC'
        
        // Processa as declarações
        declarations();
        
        // Deve ter :PROG
        match(TokenType.Delim); // ':'
        match(TokenType.PCProg); // 'PROG'
        
        // Processa os comandos
        statementList();
        
        // Deve terminar com EOF
        match(TokenType.EOF);
        
        treeLevel--;
        // Retorna a tabela de símbolos preenchida
        return symbolTable;
    }

    private void declarations() {
        printTree("ListaDeclaracoes");
        treeLevel++;
        while (currentToken.type == TokenType.Var) {
            variableDeclaration();
        }
        treeLevel--;
    }

    private void variableDeclaration() {
        printTree("DeclaraçãoVariável");
        treeLevel++;
        String varName = currentToken.lexeme;
        match(TokenType.Var);
        match(TokenType.Delim); // ':'
        
        TokenType varType = currentToken.type;
        if (varType == TokenType.PCInt || varType == TokenType.PCReal) {
            // Verificação semântica desativada: apenas registra se for preciso, mas não valida duplicata
            symbolTable.add(varName, varType);
            advance();
        } else {
            syntaxError("Tipo inválido. Esperado INT ou REAL.");
        }
        treeLevel--;
    }

    private void statementList() {
        printTree("ListaComandos");
        treeLevel++;
        while (currentToken.type != TokenType.EOF && currentToken.type != TokenType.PCFim && currentToken.type != TokenType.PCSenao) {
            statement();
        }
        treeLevel--;
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
        } else if (currentToken.type == TokenType.PCIni) {
            // Se começar com INI, é um sub-algoritmo (bloco)
            subAlgorithm();
        } else {
            syntaxError("Comando inesperado: " + currentToken.lexeme);
        }
    }

    private void assignment() {
        printTree("Atribuição");
        treeLevel++;
        // Verificação semântica desativada: não checa se variável existe
        match(TokenType.Var);
        match(TokenType.Atrib); // ':='
        
        expression();
        treeLevel--;
    }

    private void readCommand() {
        match(TokenType.PCLer);
        // Verificação semântica desativada: não checa se variável existe
        match(TokenType.Var);
    }

    private void printCommand() {
        match(TokenType.PCImprimir);
        if (currentToken.type == TokenType.Var) {
            // Verificação semântica desativada
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
        printTree("ComandoRepeticao (ENQTO)");
        treeLevel++;
        match(TokenType.PCEnqto);
        expression();
        // O corpo do ENQTO é um sub-algoritmo
        subAlgorithm();
        treeLevel--;
    }

    private void subAlgorithm() {
        printTree("SubAlgoritmo (Bloco)");
        treeLevel++;
        // Início do bloco
        match(TokenType.PCIni);
        // Lista de comandos dentro do bloco
        statementList();
        // Fim do bloco
        match(TokenType.PCFim);
        treeLevel--;
    }

    private TokenType expression() {
        printTree("Expressão");
        treeLevel++;
        TokenType type = logicalExpression();
        treeLevel--;
        return type;
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
            // Verificação semântica desativada
            advance();
            return null; // Tipo ignorado na análise puramente sintática
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
