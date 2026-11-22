package spreadsheet.formula.functions;

import spreadsheet.formula.Argument;
import spreadsheet.formula.operands.Function;

import java.util.List;

/** SUMA(...): the sum of its arguments. */
public class Suma extends Function {

    public Suma(List<Argument> arguments) {
        super(arguments);
    }
}
