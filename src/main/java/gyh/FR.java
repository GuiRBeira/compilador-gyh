package gyh;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FR {
    private BufferedReader reader;
    private int currentChar;
    private int line;
    private int column;

    public FR(String filePath) {
        this.line = 1;
        this.column = 0;
        try {
            this.reader = new BufferedReader(new FileReader(filePath));
            this.currentChar = reader.read();
            this.column = 1;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao abrir arquivo: " + filePath, e);
        }
    }

    /**
     * Retorna o caractere atual sem avançar.
     * Retorna -1 se chegou ao fim do arquivo.
     */
    public char peek() {
        if (isEOF()) {
            return '\0';
        }
        return (char) currentChar;
    }

    /**
     * Avança para o próximo caractere e retorna o caractere consumido.
     * Retorna '\0' se já estava no fim do arquivo.
     */
    public char advance() {
        if (isEOF()) {
            return '\0';
        }
        char consumed = (char) currentChar;
        try {
            if (consumed == '\n') {
                line++;
                column = 0;
            }
            currentChar = reader.read();
            column++;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler arquivo na linha " + line, e);
        }
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
