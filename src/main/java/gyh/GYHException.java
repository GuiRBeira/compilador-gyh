package gyh;

public class GYHException extends RuntimeException {
    private int line;
    private int column;
    private String errorType;

    public GYHException(String type, String message, int line, int column) {
        super(String.format("[%s] Erro na linha %d, coluna %d: %s", type, line, column, message));
        this.errorType = type;
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getErrorType() {
        return errorType;
    }
}
