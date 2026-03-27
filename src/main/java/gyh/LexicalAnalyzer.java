package gyh;

import java.util.*;

public class LexicalAnalyzer {
    private FR fileReader;
    private char currentChar;
    private int currentLine;
    private int currentColumn;
    
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
        this.fileReader = new FR(filePath);
        this.currentLine = 1;
        this.currentColumn = 1;
        this.currentChar = fileReader.peek();
    }
    
    /**
     * Retorna o próximo token do arquivo
     */
    public Token getToken() {
        skipWhitespaceAndComments();
        
        // Fim do arquivo
        if (fileReader.isEOF()) {
            return new Token(TokenType.EOF, "EOF");
        }
        
        int startLine = currentLine;
        int startColumn = currentColumn;
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
        while (!fileReader.isEOF()) {
            if (currentChar == ' ' || currentChar == '\t' || currentChar == '\r') {
                currentColumn++;
                advance();
            } 
            else if (currentChar == '\n') {
                currentLine++;
                currentColumn = 1;
                advance();
            }
            else if (currentChar == '#') {
                // Comentário: ignora até o fim da linha
                while (!fileReader.isEOF() && currentChar != '\n') {
                    advance();
                }
                // Após o comentário, não avançamos a linha ainda
                // O próximo loop vai tratar o \n
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
        
        while (!fileReader.isEOF() && (Character.isDigit(currentChar) || currentChar == '.')) {
            if (currentChar == '.') {
                if (hasDot) {
                    // Segundo ponto - erro
                    Token errorToken = new Token(null, number.toString() + currentChar);
                    errorToken.lexeme = number.toString() + currentChar;
                    return errorToken;
                }
                hasDot = true;
            }
            number.append(currentChar);
            advance();
        }
        
        String lexeme = number.toString();
        
        if (hasDot) {
            // Caso especial: apenas "." não é um número válido
            if (lexeme.equals(".")) {
                return new Token(null, ".");
            }
            // Número real
            double value = Double.parseDouble(lexeme);
            Token token = new Token(TokenType.NumReal, value);
            token.lexeme = lexeme;
            return token;
        } else {
            // Número inteiro
            int value = Integer.parseInt(lexeme);
            Token token = new Token(TokenType.NumInt, value);
            token.lexeme = lexeme;
            return token;
        }
    }
    
    /**
     * Lê um identificador (variável) - letra minúscula seguida de letras/números
     */
    private Token readIdentifier(int startLine, int startColumn) {
        StringBuilder identifier = new StringBuilder();
        
        while (!fileReader.isEOF() && 
               (Character.isLetterOrDigit(currentChar) || currentChar == '_')) {
            identifier.append(currentChar);
            advance();
        }
        
        String lexeme = identifier.toString();
        
        // Verificar se tentou usar palavra-chave em minúsculo (erro)
        if (KEYWORDS.containsKey(lexeme.toUpperCase())) {
            Token errorToken = new Token(null, lexeme);
            return errorToken;
        }
        
        Token token = new Token(TokenType.Var, lexeme);
        return token;
    }
    
    /**
     * Lê uma palavra-chave (letras maiúsculas)
     */
    private Token readKeyword(int startLine, int startColumn) {
        StringBuilder keyword = new StringBuilder();
        
        while (!fileReader.isEOF() && Character.isUpperCase(currentChar)) {
            keyword.append(currentChar);
            advance();
        }
        
        String lexeme = keyword.toString();
        TokenType type = KEYWORDS.get(lexeme);
        
        if (type != null) {
            return new Token(type, lexeme);
        } else {
            // Palavra maiúscula desconhecida - erro
            Token errorToken = new Token(null, lexeme);
            return errorToken;
        }
    }
    
    /**
     * Lê uma string entre aspas
     */
    private Token readString(int startLine, int startColumn) {
        StringBuilder str = new StringBuilder();
        advance(); // consome a aspa de abertura
        
        while (!fileReader.isEOF() && currentChar != '"') {
            if (currentChar == '\n') {
                // String não fechada na mesma linha
                Token errorToken = new Token(null, str.toString());
                return errorToken;
            }
            str.append(currentChar);
            advance();
        }
        
        if (fileReader.isEOF()) {
            // String não fechada
            Token errorToken = new Token(null, str.toString());
            return errorToken;
        }
        
        advance(); // consome a aspa de fechamento
        String lexeme = str.toString();
        Token token = new Token(TokenType.Cadeia, lexeme, lexeme);
        return token;
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
                    return new Token(TokenType.Atrib, ":=");
                }
                return new Token(TokenType.Delim, ":");
                
            case '<':
                advance();
                if (currentChar == '=') {
                    advance();
                    return new Token(TokenType.OpRelMenorIgual, "<=");
                }
                return new Token(TokenType.OpRelMenor, "<");
                
            case '>':
                advance();
                if (currentChar == '=') {
                    advance();
                    return new Token(TokenType.OpRelMaiorIgual, ">=");
                }
                return new Token(TokenType.OpRelMaior, ">");
                
            case '=':
                advance();
                if (currentChar == '=') {
                    advance();
                    return new Token(TokenType.OpRelIgual, "==");
                }
                // '=' sozinho é erro na GYH (deveria ser := ou ==)
                return new Token(null, "=");
                
            case '!':
                advance();
                if (currentChar == '=') {
                    advance();
                    return new Token(TokenType.OpRelDif, "!=");
                }
                return new Token(null, "!");
                
            case '*':
                advance();
                return new Token(TokenType.OpAritMult, "*");
                
            case '/':
                advance();
                return new Token(TokenType.OpAritDiv, "/");
                
            case '+':
                advance();
                return new Token(TokenType.OpAritSoma, "+");
                
            case '-':
                advance();
                return new Token(TokenType.OpAritSub, "-");
                
            case '(':
                advance();
                return new Token(TokenType.AbrePar, "(");
                
            case ')':
                advance();
                return new Token(TokenType.FechaPar, ")");
                
            default:
                // Caractere inválido
                advance();
                Token errorToken = new Token(null, String.valueOf(c));
                return errorToken;
        }
    }
    
    /**
     * Avança para o próximo caractere
     */
    private void advance() {
        fileReader.advance();
        currentChar = fileReader.peek();
        if (currentChar != -1 && currentChar != '\n') {
            currentColumn++;
        }
    }
    
    /**
     * Fecha o leitor de arquivo
     */
    public void close() {
        fileReader.close();
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
    
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java LexicalAnalyzer <arquivo.gyh>");
            return;
        }
        
        LexicalAnalyzer analyzer = new LexicalAnalyzer(args[0]);
        Token token;
        
        System.out.println("Análise Léxica - Linguagem GYH");
        System.out.println("================================");
        
        do {
            token = analyzer.getToken();
            if (token.type != null) {
                System.out.println(token);
            } else {
                System.err.println("ERRO LÉXICO: '" + token.lexeme + "'");
            }
        } while (token.type != TokenType.EOF);
        
        analyzer.close();
    }
}