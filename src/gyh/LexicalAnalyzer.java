package gyh;

import java.util.*;

public class LexicalAnalyzer {
    private SourceBuffer sourceBuffer;
    private char currentChar;
    
    // Palavras-chave mapeadas para seu TokenType
    private static final Map<String, TokenType> KEYWORDS = new HashMap<>();
    
    static {
        KEYWORDS.put("DEC", TokenType.PCDec);
        KEYWORDS.put("PROG", TokenType.PCProg);
        KEYWORDS.put("INT", TokenType.PCInt);
        KEYWORDS.put("REAL", TokenType.PCReal);
        KEYWORDS.put("LER", TokenType.PCLer);
        KEYWORDS.put("IMPRIMIR", TokenType.PCImprimir);
        KEYWORDS.put("SE", TokenType.PCSe);
        KEYWORDS.put("ENTAO", TokenType.PCEntao);
        KEYWORDS.put("SENAO", TokenType.PCSenao);
        KEYWORDS.put("ENQTO", TokenType.PCEnqto);
        KEYWORDS.put("INI", TokenType.PCIni);
        KEYWORDS.put("FIM", TokenType.PCFim);
        KEYWORDS.put("E", TokenType.OpBoolE);
        KEYWORDS.put("OU", TokenType.OpBoolOu);
    }
    
    public LexicalAnalyzer(String filePath) {
        this.sourceBuffer = new SourceBuffer(filePath);
        this.currentChar = sourceBuffer.peek();
    }
    
    /**
     * Retorna o próximo token do arquivo
     */
    public Token getToken() {
        skipWhitespaceAndComments();
        
        int startLine = sourceBuffer.getLine();
        int startColumn = sourceBuffer.getColumn();

        // Fim do arquivo
        if (sourceBuffer.isEOF()) {
            return new Token(TokenType.EOF, "EOF", startLine, startColumn);
        }
        
        char c = currentChar;
        
        // Números (inteiro ou real)
        if (Character.isDigit(c) || c == '.') {
            return readNumber(startLine, startColumn);
        }
        
        // Variável (começa com letra minúscula)
        if (Character.isLowerCase(c)) {
            return readIdentifier(startLine, startColumn);
        }
        
        // Palavra-chave (começa com letra maiúscula)
        if (Character.isUpperCase(c)) {
            return readKeyword(startLine, startColumn);
        }
        
        // String entre aspas
        if (c == '"') {
            return readString(startLine, startColumn);
        }
        
        // Símbolos especiais
        return readSymbol(startLine, startColumn);
    }
    
    /**
     * Pula espaços, tabs, novas linhas e comentários (#)
     */
    private void skipWhitespaceAndComments() {
        while (!sourceBuffer.isEOF()) {
            if (currentChar == ' ' || currentChar == '\t' || currentChar == '\r' || currentChar == '\n') {
                advance();
            }
            else if (currentChar == '#') {
                // Comentário: ignora até o fim da linha
                while (!sourceBuffer.isEOF() && currentChar != '\n') {
                    advance();
                }
            }
            else {
                break;
            }
        }
    }
    
    /**
     * Lê um número (inteiro ou real)
     */
    private Token readNumber(int startLine, int startColumn) {
        StringBuilder number = new StringBuilder();
        boolean hasDot = false;

        if (currentChar == '.') {
            number.append('.');
            hasDot = true;
            advance();

            // Se o próximo caractere não for um dígito, é um erro
            if (!sourceBuffer.isEOF() && !Character.isDigit(currentChar)) {
                return new Token(null, number.toString(), startLine, startColumn);
            }
        }
        
        while (!sourceBuffer.isEOF() && (Character.isDigit(currentChar) || currentChar == '.')) {
            if (currentChar == '.') {
                if (hasDot) {
                    // Segundo ponto - erro
                    return new Token(null, number.toString() + currentChar, startLine, startColumn);
                }
                hasDot = true;
            }
            number.append(currentChar);
            advance();
        }
        
        String lexeme = number.toString();
        
        if (hasDot) {
            if (lexeme.equals(".")) {
                return new Token(null, ".", startLine, startColumn);
            }
            double value = Double.parseDouble(lexeme);
            return new Token(TokenType.NumReal, value, startLine, startColumn);
        } else {
            int value = Integer.parseInt(lexeme);
            return new Token(TokenType.NumInt, value, startLine, startColumn);
        }
    }
    
    /**
     * Lê um identificador (variável) - letra minúscula seguida de letras/números
     */
    private Token readIdentifier(int startLine, int startColumn) {
        StringBuilder identifier = new StringBuilder();
        
        while (!sourceBuffer.isEOF() && 
               (Character.isLetterOrDigit(currentChar) || currentChar == '_')) {
            identifier.append(currentChar);
            advance();
        }
        
        String lexeme = identifier.toString();
        
        // Verificar se tentou usar palavra-chave em minúsculo (erro)
        if (KEYWORDS.containsKey(lexeme.toUpperCase())) {
            return new Token(null, lexeme, startLine, startColumn);
        }
        
        return new Token(TokenType.Var, lexeme, startLine, startColumn);
    }
    
    /**
     * Lê uma palavra-chave (letras maiúsculas)
     */
    private Token readKeyword(int startLine, int startColumn) {
        StringBuilder keyword = new StringBuilder();
        
        while (!sourceBuffer.isEOF() && Character.isUpperCase(currentChar)) {
            keyword.append(currentChar);
            advance();
        }
        
        String lexeme = keyword.toString();
        TokenType type = KEYWORDS.get(lexeme);
        
        if (type != null) {
            return new Token(type, lexeme, startLine, startColumn);
        } else {
            return new Token(null, lexeme, startLine, startColumn);
        }
    }
    
    /**
     * Lê uma string entre aspas
     */
    private Token readString(int startLine, int startColumn) {
        StringBuilder str = new StringBuilder();
        advance(); // consome a aspa de abertura
        
        while (!sourceBuffer.isEOF() && currentChar != '"') {
            if (currentChar == '\n') {
                return new Token(null, str.toString(), startLine, startColumn);
            }
            str.append(currentChar);
            advance();
        }
        
        if (sourceBuffer.isEOF()) {
            return new Token(null, str.toString(), startLine, startColumn);
        }
        
        advance(); // consome a aspa de fechamento
        String lexeme = str.toString();
        return new Token(TokenType.Cadeia, lexeme, lexeme, startLine, startColumn);
    }
    
    /**
     * Lê símbolos especiais (operadores, delimitadores, etc)
     */
    private Token readSymbol(int startLine, int startColumn) {
        char c = currentChar;
        
        switch (c) {
            case ':':
                advance();
                if (currentChar == '=') {
                    advance();
                    return new Token(TokenType.Atrib, ":=", startLine, startColumn);
                }
                return new Token(TokenType.Delim, ":", startLine, startColumn);
                
            case '<':
                advance();
                if (currentChar == '=') {
                    advance();
                    return new Token(TokenType.OpRelMenorIgual, "<=", startLine, startColumn);
                }
                return new Token(TokenType.OpRelMenor, "<", startLine, startColumn);
                
            case '>':
                advance();
                if (currentChar == '=') {
                    advance();
                    return new Token(TokenType.OpRelMaiorIgual, ">=", startLine, startColumn);
                }
                return new Token(TokenType.OpRelMaior, ">", startLine, startColumn);
                
            case '=':
                advance();
                if (currentChar == '=') {
                    advance();
                    return new Token(TokenType.OpRelIgual, "==", startLine, startColumn);
                }
                return new Token(null, "=", startLine, startColumn);
                
            case '!':
                advance();
                if (currentChar == '=') {
                    advance();
                    return new Token(TokenType.OpRelDif, "!=", startLine, startColumn);
                }
                return new Token(null, "!", startLine, startColumn);
                
            case '*':
                advance();
                return new Token(TokenType.OpAritMult, "*", startLine, startColumn);
                
            case '/':
                advance();
                return new Token(TokenType.OpAritDiv, "/", startLine, startColumn);
                
            case '+':
                advance();
                return new Token(TokenType.OpAritSoma, "+", startLine, startColumn);
                
            case '-':
                advance();
                return new Token(TokenType.OpAritSub, "-", startLine, startColumn);
                
            case '(':
                advance();
                return new Token(TokenType.AbrePar, "(", startLine, startColumn);
                
            case ')':
                advance();
                return new Token(TokenType.FechaPar, ")", startLine, startColumn);
                
            default:
                advance();
                return new Token(null, String.valueOf(c), startLine, startColumn);
        }
    }
    
    /**
     * Avança para o próximo caractere
     */
    private void advance() {
        sourceBuffer.advance();
        currentChar = sourceBuffer.peek();
    }
    
    /**
     * Fecha o leitor de arquivo
     */
    public void close() {
        sourceBuffer.close();
    }
    
    /**
     * Método auxiliar para testar se um token é erro
     */
    public static boolean isErrorToken(Token token) {
        return token.type == null;
    }
    
    /**
     * Retorna todos os tokens do arquivo (útil para testes)
     */
    public List<Token> getAllTokens() {
        List<Token> tokens = new ArrayList<>();
        Token token;
        
        do {
            token = getToken();
            tokens.add(token);
        } while (token.type != TokenType.EOF);
        
        return tokens;
    }
}