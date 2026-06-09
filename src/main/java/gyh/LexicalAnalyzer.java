package gyh;

import java.io.IOException;
import java.util.*;
import org.antlr.v4.runtime.*;
import gyh.parser.GYHLexer;

public class LexicalAnalyzer {
    private String filePath;
    private List<Token> tokensList;
    private int currentTokenIndex;

    public String getFilePath() {
        return filePath;
    }

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
        this.filePath = filePath;
        try {
            CharStream input = CharStreams.fromFileName(filePath);
            GYHLexer lexer = new GYHLexer(input);
            lexer.removeErrorListeners();
            
            this.tokensList = new ArrayList<>();
            this.currentTokenIndex = 0;

            org.antlr.v4.runtime.Token antlrToken;
            while ((antlrToken = lexer.nextToken()).getType() != org.antlr.v4.runtime.Token.EOF) {
                Token gyhToken = convertToken(antlrToken);
                tokensList.add(gyhToken);
            }
            // Add EOF token at the end
            int lastLine = antlrToken.getLine();
            int lastCol = antlrToken.getCharPositionInLine() + 1;
            tokensList.add(new Token(TokenType.EOF, "EOF", lastLine, lastCol));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao abrir arquivo: " + filePath, e);
        }
    }

    private Token convertToken(org.antlr.v4.runtime.Token t) {
        int typeCode = t.getType();
        String lexeme = t.getText();
        int line = t.getLine();
        int col = t.getCharPositionInLine() + 1;

        TokenType type = null;
        Integer intVal = null;
        Double realVal = null;

        switch (typeCode) {
            case GYHLexer.PCDec: type = TokenType.PCDec; break;
            case GYHLexer.PCProg: type = TokenType.PCProg; break;
            case GYHLexer.PCInt: type = TokenType.PCInt; break;
            case GYHLexer.PCReal: type = TokenType.PCReal; break;
            case GYHLexer.PCLer: type = TokenType.PCLer; break;
            case GYHLexer.PCImprimir: type = TokenType.PCImprimir; break;
            case GYHLexer.PCSe: type = TokenType.PCSe; break;
            case GYHLexer.PCEntao: type = TokenType.PCEntao; break;
            case GYHLexer.PCSenao: type = TokenType.PCSenao; break;
            case GYHLexer.PCEnqto: type = TokenType.PCEnqto; break;
            case GYHLexer.PCIni: type = TokenType.PCIni; break;
            case GYHLexer.PCFim: type = TokenType.PCFim; break;
            case GYHLexer.OpBoolE: type = TokenType.OpBoolE; break;
            case GYHLexer.OpBoolOu: type = TokenType.OpBoolOu; break;

            case GYHLexer.OpAritMult: type = TokenType.OpAritMult; break;
            case GYHLexer.OpAritDiv: type = TokenType.OpAritDiv; break;
            case GYHLexer.OpAritSoma: type = TokenType.OpAritSoma; break;
            case GYHLexer.OpAritSub: type = TokenType.OpAritSub; break;

            case GYHLexer.OpRelMenorIgual: type = TokenType.OpRelMenorIgual; break;
            case GYHLexer.OpRelMenor: type = TokenType.OpRelMenor; break;
            case GYHLexer.OpRelMaiorIgual: type = TokenType.OpRelMaiorIgual; break;
            case GYHLexer.OpRelMaior: type = TokenType.OpRelMaior; break;
            case GYHLexer.OpRelIgual: type = TokenType.OpRelIgual; break;
            case GYHLexer.OpRelDif: type = TokenType.OpRelDif; break;

            case GYHLexer.Delim: type = TokenType.Delim; break;
            case GYHLexer.Atrib: type = TokenType.Atrib; break;
            case GYHLexer.AbrePar: type = TokenType.AbrePar; break;
            case GYHLexer.FechaPar: type = TokenType.FechaPar; break;

            case GYHLexer.Var:
                if (KEYWORDS.containsKey(lexeme.toUpperCase())) {
                    type = null;
                } else {
                    type = TokenType.Var;
                }
                break;

            case GYHLexer.NumInt:
                type = TokenType.NumInt;
                intVal = Integer.parseInt(lexeme);
                return new Token(type, lexeme, intVal, line, col);

            case GYHLexer.NumReal:
                type = TokenType.NumReal;
                String parseLexeme = lexeme;
                if (lexeme.startsWith(".")) {
                    parseLexeme = "0" + lexeme;
                }
                realVal = Double.parseDouble(parseLexeme);
                return new Token(type, lexeme, realVal, line, col);

            case GYHLexer.Cadeia:
                type = TokenType.Cadeia;
                String content = lexeme;
                if (lexeme.startsWith("\"") && lexeme.endsWith("\"") && lexeme.length() >= 2) {
                    content = lexeme.substring(1, lexeme.length() - 1);
                }
                return new Token(type, content, content, line, col);

            case GYHLexer.INVALID_KEYWORD:
            case GYHLexer.UNKNOWN:
            default:
                type = null;
                break;
        }

        return new Token(type, lexeme, line, col);
    }

    public Token getToken() {
        if (currentTokenIndex >= tokensList.size()) {
            return tokensList.get(tokensList.size() - 1);
        }
        return tokensList.get(currentTokenIndex++);
    }

    public List<Token> getAllTokens() {
        return new ArrayList<>(tokensList);
    }

    public void close() {
        // Nothing to close
    }

    public static boolean isErrorToken(Token token) {
        return token.type == null;
    }
}