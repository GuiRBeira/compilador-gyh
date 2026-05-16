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
            SyntacticAnalyzer parser = new SyntacticAnalyzer(analyzer);

            System.out.println("Iniciando Compilação (GYH)...");
            System.out.println("================================");

            SymbolTable table = parser.parse();

            System.out.println("================================");
            System.out.println("Compilação concluída com sucesso!");
            System.out.println(table);

        } catch (GYHException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("[ERRO CRÍTICO]: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (analyzer != null) {
                analyzer.close();
            }
        }
    }
}
