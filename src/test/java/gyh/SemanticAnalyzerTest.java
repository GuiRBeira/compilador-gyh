package gyh;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.*;

class SemanticAnalyzerTest {
    
    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

    private void analyzeString(String source) {
        String tempFile = TEMP_DIR + "/temp_test_" + System.currentTimeMillis() + ".gyh";
        try {
            Files.writeString(Paths.get(tempFile), source);
            LexicalAnalyzer scanner = new LexicalAnalyzer(tempFile);
            
            // Lança se houver erro léxico
            for (Token t : scanner.getAllTokens()) {
                if (t.type == null) {
                    throw new GYHException("LÉXICO", "Erro léxico no teste", t.line, t.column);
                }
            }

            SyntacticAnalyzer parser = new SyntacticAnalyzer(scanner);
            gyh.parser.GYHParser.ProgramaContext tree = parser.parse();

            GYHSemanticAnalyzer semantic = new GYHSemanticAnalyzer();
            semantic.visit(tree);
            semantic.checkUnusedVariables();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.deleteIfExists(Paths.get(tempFile));
            } catch (IOException ignored) {}
        }
    }

    @Test
    @DisplayName("Deve analisar com sucesso um programa semântico válido")
    void testValidProgram() {
        String program = 
            ":DEC\n" +
            "a:INT\n" +
            "b:REAL\n" +
            ":PROG\n" +
            "a := 10\n" +
            "b := a * 2.5\n" +
            "IMPRIMIR b\n";
        assertDoesNotThrow(() -> analyzeString(program));
    }

    @Test
    @DisplayName("Deve detectar erro semântico: variável não declarada")
    void testUndeclaredVariable() {
        String program = 
            ":DEC\n" +
            "a:INT\n" +
            ":PROG\n" +
            "b := 10\n";
        
        GYHException ex = assertThrows(GYHException.class, () -> analyzeString(program));
        assertEquals("SEMÂNTICO", ex.getErrorType());
        assertTrue(ex.getMessage().contains("não declarada"));
    }

    @Test
    @DisplayName("Deve detectar erro semântico: atribuição de REAL em variável INT")
    void testTypeMismatchRealToInt() {
        String program = 
            ":DEC\n" +
            "a:INT\n" +
            ":PROG\n" +
            "a := 3.5\n";
        
        GYHException ex = assertThrows(GYHException.class, () -> analyzeString(program));
        assertEquals("SEMÂNTICO", ex.getErrorType());
        assertTrue(ex.getMessage().contains("Tipo incompatível"));
    }

    @Test
    @DisplayName("Deve detectar erro semântico: variável usada sem ser inicializada")
    void testUninitializedVariable() {
        String program = 
            ":DEC\n" +
            "a:INT\n" +
            "b:INT\n" +
            ":PROG\n" +
            "b := a + 1\n";
        
        GYHException ex = assertThrows(GYHException.class, () -> analyzeString(program));
        assertEquals("SEMÂNTICO", ex.getErrorType());
        assertTrue(ex.getMessage().contains("pode não ter sido inicializada"));
    }

    @Test
    @DisplayName("Deve detectar erro semântico: variável já declarada")
    void testVariableAlreadyDeclared() {
        String program = 
            ":DEC\n" +
            "x:INT\n" +
            "x:REAL\n" +
            ":PROG\n" +
            "x := 10\n";
        
        GYHException ex = assertThrows(GYHException.class, () -> analyzeString(program));
        assertEquals("SEMÂNTICO", ex.getErrorType());
        assertTrue(ex.getMessage().contains("já declarada"));
    }
}
