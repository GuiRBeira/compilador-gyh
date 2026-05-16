package gyh;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

class LexicalAnalyzerTest {
    
    private static final String TEST_DIR = "tests/programs/";
    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
    
    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(Paths.get(TEST_DIR));
    }
    
    // ==================== TESTES DE PALAVRAS-CHAVE ====================
    
    @Test
    @DisplayName("Deve reconhecer palavra-chave DEC")
    void testKeywordDEC() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("DEC");
        Token token = analyzer.getToken();
        assertEquals(TokenType.PCDec, token.type);
        assertEquals("DEC", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer palavra-chave PROG")
    void testKeywordPROG() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("PROG");
        Token token = analyzer.getToken();
        assertEquals(TokenType.PCProg, token.type);
        assertEquals("PROG", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer palavra-chave INT")
    void testKeywordINT() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("INT");
        Token token = analyzer.getToken();
        assertEquals(TokenType.PCInt, token.type);
        assertEquals("INT", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer palavra-chave REAL")
    void testKeywordREAL() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("REAL");
        Token token = analyzer.getToken();
        assertEquals(TokenType.PCReal, token.type);
        assertEquals("REAL", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer palavra-chave LER")
    void testKeywordLER() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("LER");
        Token token = analyzer.getToken();
        assertEquals(TokenType.PCLer, token.type);
        assertEquals("LER", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer palavra-chave IMPRIMIR")
    void testKeywordIMPRIMIR() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("IMPRIMIR");
        Token token = analyzer.getToken();
        assertEquals(TokenType.PCImprimir, token.type);
        assertEquals("IMPRIMIR", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer palavra-chave SE")
    void testKeywordSE() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("SE");
        Token token = analyzer.getToken();
        assertEquals(TokenType.PCSe, token.type);
        assertEquals("SE", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer palavra-chave ENTAO")
    void testKeywordENTAO() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("ENTAO");
        Token token = analyzer.getToken();
        assertEquals(TokenType.PCEntao, token.type);
        assertEquals("ENTAO", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer palavra-chave SENAO")
    void testKeywordSENAO() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("SENAO");
        Token token = analyzer.getToken();
        assertEquals(TokenType.PCSenao, token.type);
        assertEquals("SENAO", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer palavra-chave ENQTO")
    void testKeywordENQTO() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("ENQTO");
        Token token = analyzer.getToken();
        assertEquals(TokenType.PCEnqto, token.type);
        assertEquals("ENQTO", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer palavra-chave INI")
    void testKeywordINI() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("INI");
        Token token = analyzer.getToken();
        assertEquals(TokenType.PCIni, token.type);
        assertEquals("INI", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer palavra-chave FIM")
    void testKeywordFIM() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("FIM");
        Token token = analyzer.getToken();
        assertEquals(TokenType.PCFim, token.type);
        assertEquals("FIM", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer operador booleano E")
    void testKeywordE() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("E");
        Token token = analyzer.getToken();
        assertEquals(TokenType.OpBoolE, token.type);
        assertEquals("E", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer operador booleano OU")
    void testKeywordOU() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("OU");
        Token token = analyzer.getToken();
        assertEquals(TokenType.OpBoolOu, token.type);
        assertEquals("OU", token.lexeme);
        analyzer.close();
    }
    
    // ==================== TESTES DE VARIÁVEIS ====================
    
    @Test
    @DisplayName("Deve reconhecer variável com letra minúscula")
    void testVariable() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("variavel");
        Token token = analyzer.getToken();
        assertEquals(TokenType.Var, token.type);
        assertEquals("variavel", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer variável com números")
    void testVariableWithNumbers() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("variavel123");
        Token token = analyzer.getToken();
        assertEquals(TokenType.Var, token.type);
        assertEquals("variavel123", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer variável com underscore")
    void testVariableWithUnderscore() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("variavel_teste");
        Token token = analyzer.getToken();
        assertEquals(TokenType.Var, token.type);
        assertEquals("variavel_teste", token.lexeme);
        analyzer.close();
    }
    
    // ==================== TESTES DE NÚMEROS ====================
    
    @Test
    @DisplayName("Deve reconhecer número inteiro")
    void testIntegerNumber() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("42");
        Token token = analyzer.getToken();
        assertEquals(TokenType.NumInt, token.type);
        assertEquals("42", token.lexeme);
        assertEquals(42, token.intValue);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer número real")
    void testRealNumber() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("3.14");
        Token token = analyzer.getToken();
        assertEquals(TokenType.NumReal, token.type);
        assertEquals("3.14", token.lexeme);
        assertEquals(3.14, token.realValue, 0.001);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer número real sem parte inteira")
    void testRealNumberWithoutIntegerPart() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString(".5");
        Token token = analyzer.getToken();
        assertEquals(TokenType.NumReal, token.type);
        assertEquals(".5", token.lexeme);
        assertEquals(0.5, token.realValue, 0.001);
        analyzer.close();
    }
    
    // ==================== TESTES DE STRINGS ====================
    
    @Test
    @DisplayName("Deve reconhecer string entre aspas")
    void testString() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("\"Hello World\"");
        Token token = analyzer.getToken();
        assertEquals(TokenType.Cadeia, token.type);
        assertEquals("Hello World", token.stringValue);
        analyzer.close();
    }
    
    // ==================== TESTES DE OPERADORES ARITMÉTICOS ====================
    
    @Test
    @DisplayName("Deve reconhecer operador +")
    void testOperatorPlus() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("+");
        Token token = analyzer.getToken();
        assertEquals(TokenType.OpAritSoma, token.type);
        assertEquals("+", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer operador -")
    void testOperatorMinus() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("-");
        Token token = analyzer.getToken();
        assertEquals(TokenType.OpAritSub, token.type);
        assertEquals("-", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer operador *")
    void testOperatorMultiply() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("*");
        Token token = analyzer.getToken();
        assertEquals(TokenType.OpAritMult, token.type);
        assertEquals("*", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer operador /")
    void testOperatorDivide() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("/");
        Token token = analyzer.getToken();
        assertEquals(TokenType.OpAritDiv, token.type);
        assertEquals("/", token.lexeme);
        analyzer.close();
    }
    
    // ==================== TESTES DE OPERADORES RELACIONAIS ====================
    
    @Test
    @DisplayName("Deve reconhecer operador <")
    void testRelLess() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("<");
        Token token = analyzer.getToken();
        assertEquals(TokenType.OpRelMenor, token.type);
        assertEquals("<", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer operador <=")
    void testRelLessEqual() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("<=");
        Token token = analyzer.getToken();
        assertEquals(TokenType.OpRelMenorIgual, token.type);
        assertEquals("<=", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer operador >")
    void testRelGreater() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString(">");
        Token token = analyzer.getToken();
        assertEquals(TokenType.OpRelMaior, token.type);
        assertEquals(">", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer operador >=")
    void testRelGreaterEqual() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString(">=");
        Token token = analyzer.getToken();
        assertEquals(TokenType.OpRelMaiorIgual, token.type);
        assertEquals(">=", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer operador ==")
    void testRelEqual() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("==");
        Token token = analyzer.getToken();
        assertEquals(TokenType.OpRelIgual, token.type);
        assertEquals("==", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer operador !=")
    void testRelNotEqual() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("!=");
        Token token = analyzer.getToken();
        assertEquals(TokenType.OpRelDif, token.type);
        assertEquals("!=", token.lexeme);
        analyzer.close();
    }
    
    // ==================== TESTES DE DELIMITADORES ====================
    
    @Test
    @DisplayName("Deve reconhecer delimitador :")
    void testDelimiter() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString(":");
        Token token = analyzer.getToken();
        assertEquals(TokenType.Delim, token.type);
        assertEquals(":", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer atribuição :=")
    void testAssignment() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString(":=");
        Token token = analyzer.getToken();
        assertEquals(TokenType.Atrib, token.type);
        assertEquals(":=", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer (")
    void testOpenParenthesis() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("(");
        Token token = analyzer.getToken();
        assertEquals(TokenType.AbrePar, token.type);
        assertEquals("(", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve reconhecer )")
    void testCloseParenthesis() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString(")");
        Token token = analyzer.getToken();
        assertEquals(TokenType.FechaPar, token.type);
        assertEquals(")", token.lexeme);
        analyzer.close();
    }
    
    // ==================== TESTES DE COMENTÁRIOS ====================
    
    @Test
    @DisplayName("Deve ignorar comentário com #")
    void testComment() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("# Isto é um comentário\nvar");
        Token token = analyzer.getToken();
        assertEquals(TokenType.Var, token.type);
        assertEquals("var", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve ignorar múltiplos comentários")
    void testMultipleComments() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("# comentário 1\nDEC\n# comentário 2\nPROG");
        Token t1 = analyzer.getToken();
        Token t2 = analyzer.getToken();
        
        assertEquals(TokenType.PCDec, t1.type);
        assertEquals(TokenType.PCProg, t2.type);
        analyzer.close();
    }
    
    // ==================== TESTES DE ERROS ====================
    
    @Test
    @DisplayName("Deve detectar erro: = sozinho")
    void testErrorSingleEqual() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("=");
        Token token = analyzer.getToken();
        assertNull(token.type);
        assertEquals("=", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve detectar erro: ! sozinho")
    void testErrorSingleExclamation() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("!");
        Token token = analyzer.getToken();
        assertNull(token.type);
        assertEquals("!", token.lexeme);
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve detectar erro: caractere inválido")
    void testErrorInvalidChar() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("@");
        Token token = analyzer.getToken();
        assertNull(token.type);
        assertEquals("@", token.lexeme);
        analyzer.close();
    }
    
    // ==================== TESTES DE SEQUÊNCIAS ====================
    
    @Test
    @DisplayName("Deve analisar declaração de variável completa")
    void testVariableDeclaration() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("parametro:INT");
        
        Token t1 = analyzer.getToken(); // parametro
        Token t2 = analyzer.getToken(); // :
        Token t3 = analyzer.getToken(); // INT
        
        assertEquals(TokenType.Var, t1.type);
        assertEquals("parametro", t1.lexeme);
        
        assertEquals(TokenType.Delim, t2.type);
        assertEquals(":", t2.lexeme);
        
        assertEquals(TokenType.PCInt, t3.type);
        assertEquals("INT", t3.lexeme);
        
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve analisar expressão aritmética")
    void testArithmeticExpression() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("x := a + b * 2");
        
        Token t1 = analyzer.getToken(); // x
        Token t2 = analyzer.getToken(); // :=
        Token t3 = analyzer.getToken(); // a
        Token t4 = analyzer.getToken(); // +
        Token t5 = analyzer.getToken(); // b
        Token t6 = analyzer.getToken(); // *
        Token t7 = analyzer.getToken(); // 2
        
        assertEquals(TokenType.Var, t1.type);
        assertEquals(TokenType.Atrib, t2.type);
        assertEquals(TokenType.Var, t3.type);
        assertEquals(TokenType.OpAritSoma, t4.type);
        assertEquals(TokenType.Var, t5.type);
        assertEquals(TokenType.OpAritMult, t6.type);
        assertEquals(TokenType.NumInt, t7.type);
        assertEquals(2, t7.intValue);
        
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve analisar expressão relacional")
    void testRelationalExpression() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("parametro > 1");
        
        Token t1 = analyzer.getToken();
        Token t2 = analyzer.getToken();
        Token t3 = analyzer.getToken();
        
        assertEquals(TokenType.Var, t1.type);
        assertEquals(TokenType.OpRelMaior, t2.type);
        assertEquals(TokenType.NumInt, t3.type);
        
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve analisar estrutura condicional SE")
    void testConditional() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("SE x == 0 ENTAO y := 1");
        
        Token t1 = analyzer.getToken(); // SE
        Token t2 = analyzer.getToken(); // x
        Token t3 = analyzer.getToken(); // ==
        Token t4 = analyzer.getToken(); // 0
        Token t5 = analyzer.getToken(); // ENTAO
        Token t6 = analyzer.getToken(); // y
        Token t7 = analyzer.getToken(); // :=
        Token t8 = analyzer.getToken(); // 1
        
        assertEquals(TokenType.PCSe, t1.type);
        assertEquals(TokenType.Var, t2.type);
        assertEquals(TokenType.OpRelIgual, t3.type);
        assertEquals(TokenType.NumInt, t4.type);
        assertEquals(TokenType.PCEntao, t5.type);
        assertEquals(TokenType.Var, t6.type);
        assertEquals(TokenType.Atrib, t7.type);
        assertEquals(TokenType.NumInt, t8.type);
        
        analyzer.close();
    }
    
    @Test
    @DisplayName("Deve analisar estrutura de repetição ENQTO")
    void testLoop() throws IOException {
        LexicalAnalyzer analyzer = createAnalyzerFromString("ENQTO x > 0 INI x := x - 1 FIM");
        
        List<TokenType> expected = Arrays.asList(
            TokenType.PCEnqto, TokenType.Var, TokenType.OpRelMaior, TokenType.NumInt,
            TokenType.PCIni, TokenType.Var, TokenType.Atrib, TokenType.Var,
            TokenType.OpAritSub, TokenType.NumInt, TokenType.PCFim
        );
        
        for (TokenType expectedType : expected) {
            Token token = analyzer.getToken();
            assertEquals(expectedType, token.type);
        }
        
        analyzer.close();
    }
    
    // ==================== TESTES DE PROGRAMA COMPLETO ====================
    
    @Test
    @DisplayName("Deve analisar programa fatorial completo")
    void testFullFactorialProgram() throws IOException {
        String program = 
            ":DEC\n" +
            "parametro:INT\n" +
            "fatorial:INT\n" +
            ":PROG\n" +
            "# Calcula o fatorial\n" +
            "LER parametro\n" +
            "fatorial := parametro\n" +
            "SE parametro == 0 ENTAO\n" +
            "    fatorial := 1\n" +
            "ENQTO parametro > 1\n" +
            "INI\n" +
            "    fatorial := fatorial * (parametro - 1)\n" +
            "    parametro := parametro - 1\n" +
            "FIM\n" +
            "IMPRIMIR fatorial";
        
        LexicalAnalyzer analyzer = createAnalyzerFromString(program);
        List<Token> tokens = analyzer.getAllTokens();
        
        // Verificar alguns tokens específicos
        boolean foundPCDec = false;
        boolean foundPCProg = false;
        boolean foundPCLer = false;
        boolean foundPCSe = false;
        boolean foundPCEnqto = false;
        boolean foundPCImprimir = false;
        
        for (Token t : tokens) {
            if (t.type == TokenType.PCDec) foundPCDec = true;
            if (t.type == TokenType.PCProg) foundPCProg = true;
            if (t.type == TokenType.PCLer) foundPCLer = true;
            if (t.type == TokenType.PCSe) foundPCSe = true;
            if (t.type == TokenType.PCEnqto) foundPCEnqto = true;
            if (t.type == TokenType.PCImprimir) foundPCImprimir = true;
        }
        
        assertTrue(foundPCDec, "Deveria ter token DEC");
        assertTrue(foundPCProg, "Deveria ter token PROG");
        assertTrue(foundPCLer, "Deveria ter token LER");
        assertTrue(foundPCSe, "Deveria ter token SE");
        assertTrue(foundPCEnqto, "Deveria ter token ENQTO");
        assertTrue(foundPCImprimir, "Deveria ter token IMPRIMIR");
        
        analyzer.close();
    }
    
    // ==================== MÉTODOS AUXILIARES ====================
    
    private LexicalAnalyzer createAnalyzerFromString(String source) throws IOException {
        String tempFile = TEMP_DIR + "/temp_test_" + System.currentTimeMillis() + ".gyh";
        Files.writeString(Paths.get(tempFile), source);
        return new LexicalAnalyzer(tempFile);
    }
}