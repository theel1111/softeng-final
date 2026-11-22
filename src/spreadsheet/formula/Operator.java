package spreadsheet.formula;

/** One of + - * / between two operands. */
public class Operator {

    private final char symbol;

    public Operator(char symbol) {
        if ("+-*/".indexOf(symbol) < 0) {
            throw new IllegalArgumentException("Unknown operator: " + symbol);
        }
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
