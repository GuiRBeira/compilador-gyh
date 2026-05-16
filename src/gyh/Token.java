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
        this.type = type;
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
    }

    public Token(TokenType type, Integer intValue, int line, int column) {
        this.type = type;
        this.intValue = intValue;
        this.line = line;
        this.column = column;
    }

    public Token(TokenType type, Double realValue, int line, int column) {
        this.type = type;
        this.realValue = realValue;
        this.line = line;
        this.column = column;
    }

    public Token(TokenType type, String lexeme, String stringValue, int line, int column) {
        this.type = type;
        this.lexeme = lexeme;
        this.stringValue = stringValue;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return String.format("Token [tipo=%-12s, lexema='%s', linha=%d, col=%d]", 
                             type, lexeme, line, column);
    }
}
