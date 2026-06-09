package gyh;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class SourceBuffer {
    private String filePath;
    private BufferedReader reader;
    private int currentChar;
    private int line;
    private int column;

    public SourceBuffer(String filePath) {
        // Inicializa linha como 1
        this.line = 1;
        // Inicializa coluna como 0
        this.column = 0;
        try {
            // Abre o leitor de arquivo
            this.reader = new BufferedReader(new FileReader(filePath));
            // Lê o primeiro caractere
            this.currentChar = reader.read();
            // Define coluna inicial como 1
            this.column = 1;
        } catch (IOException e) {
            // Lança erro se não conseguir abrir
            throw new RuntimeException("Erro ao abrir arquivo: " + filePath, e);
        }
    }

    /**
     * Retorna o caractere atual sem avançar.
     * Retorna -1 se chegou ao fim do arquivo.
     */
    // Retorna o caractere atual
    public char peek() {
        // Se for fim de arquivo retorna nulo
        if (isEOF()) {
            return '\0';
        }
        // Retorna o caractere convertido
        return (char) currentChar;
    }

    /**
     * Avança para o próximo caractere e retorna o caractere consumido.
     * Retorna '\0' se já estava no fim do arquivo.
     */
    // Avança para o próximo caractere
    public char advance() {
        // Se for fim de arquivo retorna nulo
        if (isEOF()) {
            return '\0';
        }
        // Salva o caractere atual
        char consumed = (char) currentChar;
        try {
            // Se for quebra de linha, incrementa linha e zera coluna
            if (consumed == '\n') {
                line++;
                column = 0;
            }
            // Lê o próximo caractere do buffer
            currentChar = reader.read();
            // Incrementa coluna
            column++;
        } catch (IOException e) {
            // Lança erro se falhar a leitura
            throw new RuntimeException("Erro ao ler arquivo na linha " + line, e);
        }
        // Retorna o caractere que foi consumido
        return consumed;
    }

    /**
     * Verifica se o fim do arquivo foi atingido.
     */
    public boolean isEOF() {
        return currentChar == -1;
    }

    /**
     * Retorna a linha atual do arquivo (1-indexed).
     */
    public int getLine() {
        return line;
    }

    /**
     * Retorna a coluna atual do arquivo (1-indexed).
     */
    public int getColumn() {
        return column;
    }

    /**
     * Fecha o leitor de arquivo.
     */
    public void close() {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao fechar arquivo", e);
        }
    }
}
