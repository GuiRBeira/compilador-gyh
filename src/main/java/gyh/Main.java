/*
 * Compilador Linguagem GYH
 * Autor: Guilherme Ricardo Beira RA: 2270080
 * Disciplina: Compiladores
 */

package gyh;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java gyh.Main <arquivo.gyh>");
            return;
        }

        String filePath = args[0];
        LexicalAnalyzer analyzer = null;

        try {
            analyzer = new LexicalAnalyzer(filePath);
            Token token;

            System.out.println("Iniciando Análise Léxica...");
            System.out.println("================================");

            do {
                token = analyzer.getToken();
                
                if (token.type != null) {
                    // Saída padronizada para o relatório e para o script de demonstração
                    System.out.println(token.toString());
                } else {
                    // Caso o token seja null, reportamos como erro léxico
                    System.err.println("ERRO LÉXICO: Caractere ou sequência inválida '" + token.lexeme + "'");
                }

            } while (token.type != TokenType.EOF);

            System.out.println("================================");
            System.out.println("Análise concluída com sucesso.");

        } catch (RuntimeException e) {
            System.err.println("Erro durante a execução: " + e.getMessage());
        } finally {
            if (analyzer != null) {
                analyzer.close();
            }
        }
    }
}
