public class LexicalAnalyzer {
    public FR fileReader;
    public LexicalAnalyzer(String name) {
        fileReader = new FR(name);
    }

    public Token getToken() {
        char character = fileReader.peek();
        while (character >= 0) {
            char c = (char) character;
            if (c == ' ' || c == '\t' || c == '\n' || c == '\r') {
                fileReader.advance();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Entry point — use the class here
    }
}
