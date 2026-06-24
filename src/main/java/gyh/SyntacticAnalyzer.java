package gyh;

import org.antlr.v4.runtime.*;
import gyh.parser.*;

public class SyntacticAnalyzer {
    private LexicalAnalyzer scanner;

    public SyntacticAnalyzer(LexicalAnalyzer scanner) {
        this.scanner = scanner;
    }

    public GYHParser.ProgramaContext parse() {
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
        return parser.programa();
    }
}
