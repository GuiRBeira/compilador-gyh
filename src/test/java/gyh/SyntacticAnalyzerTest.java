package gyh;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.*;

class SyntacticAnalyzerTest {
    
    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

    private void parseString(String source) {
        String tempFile = TEMP_DIR + "/temp_test_" + System.currentTimeMillis() + ".gyh";
        try {
            Files.writeString(Paths.get(tempFile), source);
            LexicalAnalyzer scanner = new LexicalAnalyzer(tempFile);
            
            // Verifica se há erro léxico antes do parser
            for (Token t : scanner.getAllTokens()) {
                if (t.type == null) {
                    throw new GYHException("LÉXICO", "Erro léxico no teste", t.line, t.column);
                }
            }

            SyntacticAnalyzer parser = new SyntacticAnalyzer(scanner);
            parser.parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Files.deleteIfExists(Paths.get(tempFile));
            } catch (IOException ignored) {}
        }
    }

    @Test
    @DisplayName("Deve analisar com sucesso um programa GYH mínimo válido")
    void testMinimalValidProgram() {
        String program = 
            ":DEC\n" +
            ":PROG\n";
        assertDoesNotThrow(() -> parseString(program));
    }

    @Test
    @DisplayName("Deve analisar com sucesso programa com atribuição simples")
    void testValidAssignment() {
        String program = 
            ":DEC\n" +
            "x:INT\n" +
            ":PROG\n" +
            "x := 10\n";
        assertDoesNotThrow(() -> parseString(program));
    }

    @Test
    @DisplayName("Deve analisar com sucesso programa com condicionais completas")
    void testValidConditional() {
        String program = 
            ":DEC\n" +
            "x:INT\n" +
            ":PROG\n" +
            "SE x > 0 ENTAO\n" +
            "    x := 1\n" +
            "SENAO\n" +
            "    x := 0\n" +
            "FIM\n";
        assertDoesNotThrow(() -> parseString(program));
    }

    @Test
    @DisplayName("Deve detectar erro sintático: falta de ENTAO no comando SE")
    void testMissingEntao() {
        String program = 
            ":DEC\n" +
            "x:INT\n" +
            ":PROG\n" +
            "SE x > 0\n" +
            "    x := 1\n" +
            "FIM\n";
        
        GYHException ex = assertThrows(GYHException.class, () -> parseString(program));
        assertEquals("SINTÁTICO", ex.getErrorType());
        assertTrue(ex.getMessage().contains("missing 'ENTAO'"));
    }

    @Test
    @DisplayName("Deve detectar erro sintático: falta de delimitação nos blocos")
    void testMissingProgDelimiter() {
        String program = 
            ":DEC\n" +
            "x:INT\n" +
            "PROG\n" + // Faltando o ":" antes do PROG
            "x := 1\n";
        
        GYHException ex = assertThrows(GYHException.class, () -> parseString(program));
        assertEquals("SINTÁTICO", ex.getErrorType());
    }

    @Test
    @DisplayName("Deve detectar erro sintático: parênteses desbalanceados")
    void testUnbalancedParenthesis() {
        String program = 
            ":DEC\n" +
            "x:INT\n" +
            ":PROG\n" +
            "x := (5 + 3\n";
        
        GYHException ex = assertThrows(GYHException.class, () -> parseString(program));
        assertEquals("SINTÁTICO", ex.getErrorType());
    }
}
