public class Token {
    public TokenType type;
    public String lexeme;
    public Integer intValue;
    public Double realValue;
    public String stringValue;

    public Token(TokenType type, String lexeme) {
        this.type = type;
        this.lexeme = lexeme;
    }

    public Token(TokenType type, Integer intValue) {
        this.type = type;
        this.intValue = intValue;
    }

    public Token(TokenType type, Double realValue) {
        this.type = type;
        this.realValue = realValue;
    }

    public Token(TokenType type, String lexeme, String stringValue) {
        this.type = type;
        this.lexeme = lexeme;
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return "Token [type=" + type + ", lexeme=" + lexeme + ", intValue=" + intValue + ", realValue=" + realValue
                + ", stringValue=" + stringValue + "]";
    }

}
