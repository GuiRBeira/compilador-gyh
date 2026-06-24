package gyh;

import gyh.parser.GYHParser;

/*
 * Trabalho de Compiladores - Compilador GYH
 * Nome: Guilherme R. Beira
 * RA: 2270080
 * Nome: Pedro Miguel Lorin
 * RA: 2478528
 * ************************
 */

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java gyh.Main <arquivo.gyh> [--tokens]");
            return;
        }

        String filePath = args[0];
        boolean showTokens = args.length > 1 && args[1].equals("--tokens");
        // Inicializa o analisador como nulo
        LexicalAnalyzer analyzer = null;

        try {
            // Cria o analisador léxico
            analyzer = new LexicalAnalyzer(filePath);

            if (showTokens) {
                Token token;
                do {
                    token = analyzer.getToken();
                    if (token.type != null) {
                        System.out.println(token.toDebugString());
                    } else {
                        System.err.println("ERRO LÉXICO: Caractere ou sequência inválida '" + token.lexeme + "'");
                    }
                } while (token.type != TokenType.EOF);
                return;
            }

            // 1. Checagem de erros léxicos antes de prosseguir
            for (Token t : analyzer.getAllTokens()) {
                if (t.type == null) {
                    throw new GYHException("LÉXICO", "Caractere ou sequência inválida '" + t.lexeme + "'", t.line, t.column);
                }
            }

            // Cria o analisador sintático
            SyntacticAnalyzer parser = new SyntacticAnalyzer(analyzer);

            System.out.println("Iniciando Compilação (GYH)...");
            System.out.println("================================");

            // 2. Chama o parser para iniciar a análise sintática e obter a ParseTree
            GYHParser.ProgramaContext tree = parser.parse();

            // 3. Imprime a árvore sintática
            GYHTreePrinter printer = new GYHTreePrinter();
            printer.visit(tree);

            // 4. Executa a análise semântica
            GYHSemanticAnalyzer semantic = new GYHSemanticAnalyzer();
            semantic.visit(tree);
            semantic.checkUnusedVariables();

            SymbolTable table = semantic.getSymbolTable();

            System.out.println("================================");
            System.out.println("Compilação concluída com sucesso!");
            // Imprime a tabela de símbolos
            System.out.println(table);

        } catch (GYHException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("[ERRO CRÍTICO]: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (analyzer != null) {
                analyzer.close();
            }
        }
    }
}
