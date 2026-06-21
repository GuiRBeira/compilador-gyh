package gyh;

import org.antlr.v4.runtime.*;
import gyh.parser.*;
import java.io.IOException;

public class SyntacticAnalyzer {
    private LexicalAnalyzer scanner;

    public SyntacticAnalyzer(LexicalAnalyzer scanner) {
        this.scanner = scanner;
    }

    public SymbolTable parse() {
        // First check for lexical errors detected by the scanner
        for (Token t : scanner.getAllTokens()) {
            if (t.type == null) {
                throw new GYHException("SEMÂNTICO", "Erro Léxico encontrado: '" + t.lexeme + "'", t.line, t.column);
            }
        }

        ListTokenSource tokenSource = new ListTokenSource(scanner.getAntlrTokens(), scanner.getFilePath());
        CommonTokenStream tokens = new CommonTokenStream(tokenSource);
        GYHParser parser = new GYHParser(tokens);
        
        // Handle syntax errors
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                     int line, int charPositionInLine, String msg,
                                     RecognitionException e) {
                throw new GYHException("SINTÁTICO", msg, line, charPositionInLine + 1);
            }
        });

        // Start rule
        GYHParser.ProgramaContext tree = parser.programa();

        // Print the tree
        GYHTreePrinter printer = new GYHTreePrinter();
        printer.visit(tree);

        // Run semantic analysis
        GYHSemanticAnalyzer semantic = new GYHSemanticAnalyzer();
        semantic.visit(tree);

        return semantic.getSymbolTable();
    }
}
