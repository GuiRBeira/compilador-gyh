package gyh;

import gyh.parser.GYHParser;

/*
 * Trabalho de Compiladores - Compilador GYH
 * Nome: Guilherme R. Beira
 * RA: 2270080
 * Nome: Pedro Miguel Lorin
 * RA: 2478528
 * ************************
 * Link do vídeo de apresentação: [Inserir o link do YouTube/Drive aqui]
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

            // 5. Geração de Código em C
            GYHCodeGenerator generator = new GYHCodeGenerator(table);
            String cCode = generator.visit(tree);

            // Determina arquivo de saída (.c) na pasta 'output'
            String inputFileName = java.nio.file.Paths.get(filePath).getFileName().toString();
            String baseName;
            if (inputFileName.endsWith(".gyh")) {
                baseName = inputFileName.substring(0, inputFileName.length() - 4);
            } else {
                baseName = inputFileName;
            }

            String outputDir = "output";
            java.nio.file.Files.createDirectories(java.nio.file.Paths.get(outputDir));
            String outputFilePath = outputDir + "/" + baseName + ".c";

            // Escreve o código C no arquivo
            java.nio.file.Files.writeString(java.nio.file.Paths.get(outputFilePath), cCode);

            System.out.println("================================");
            System.out.println("Compilação concluída com sucesso!");
            // Imprime a tabela de símbolos
            System.out.println(table);
            System.out.println("Código C gerado com sucesso em: " + outputFilePath);

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
