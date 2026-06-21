package gyh;

public class Token {
    public TokenType type;
    public String lexeme;
    public Integer intValue;
    public Double realValue;
    public String stringValue;
    public int line;
    public int column;

    public Token(TokenType type, String lexeme, int line, int column) {
        // Define o tipo
        this.type = type;
        // Define o lexema
        this.lexeme = lexeme;
        // Define a linha
        this.line = line;
        // Define a coluna
        this.column = column;
    }

    public Token(TokenType type, String lexeme, Integer intValue, int line, int column) {
        // Define o tipo
        this.type = type;
        // Define o lexema
        this.lexeme = lexeme;
        // Define o valor inteiro
        this.intValue = intValue;
        // Define a linha
        this.line = line;
        // Define a coluna
        this.column = column;
    }

    public Token(TokenType type, String lexeme, Double realValue, int line, int column) {
        // Define o tipo
        this.type = type;
        // Define o lexema
        this.lexeme = lexeme;
        // Define o valor real
        this.realValue = realValue;
        // Define a linha
        this.line = line;
        // Define a coluna
        this.column = column;
    }

    public Token(TokenType type, String lexeme, String stringValue, int line, int column) {
        // Define o tipo
        this.type = type;
        // Define o lexema
        this.lexeme = lexeme;
        // Define o valor da string
        this.stringValue = stringValue;
        // Define a linha
        this.line = line;
        // Define a coluna
        this.column = column;
    }

    @Override
    public String toString() {
        return String.format("Token [tipo=%-12s, lexema='%s', linha=%d, col=%d]", 
                             type, lexeme, line, column);
    }

    public String toDebugString() {
        return "Token [type=" + type + ", lexeme=" + lexeme + ", intValue=" + intValue 
                + ", realValue=" + realValue + ", stringValue=" + stringValue + "]";
    }
}
