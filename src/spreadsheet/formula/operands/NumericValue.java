package spreadsheet.formula.operands;

import spreadsheet.formula.Argument;
import spreadsheet.formula.Operand;

/** A number typed inside a formula, e.g. the 27 in =SUMA(A1;27). */
public class NumericValue implements Operand, Argument {

    private final double num;

    public NumericValue(double num) {
        this.num = num;
    }

    public double getNum() {
        return num;
    }
}
