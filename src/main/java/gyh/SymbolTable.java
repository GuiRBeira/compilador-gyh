package gyh;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, TokenType> symbols;

    public SymbolTable() {
        this.symbols = new HashMap<>();
    }

    public Map<String, TokenType> getSymbols() {
        return symbols;
    }

    /**
     * Adiciona uma variável à tabela.
     * @return true se adicionado com sucesso, false se já existia.
     */
    public boolean add(String name, TokenType type) {
        if (symbols.containsKey(name)) {
            return false;
        }
        symbols.put(name, type);
        return true;
    }

    /**
     * Verifica se uma variável existe.
     */
    public boolean exists(String name) {
        return symbols.containsKey(name);
    }

    /**
     * Retorna o tipo da variável.
     */
    public TokenType getType(String name) {
        return symbols.get(name);
    }

    @Override
    public String toString() {
        return "Tabela de Símbolos: " + symbols.toString();
    }
}
