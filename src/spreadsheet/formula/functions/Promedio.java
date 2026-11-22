package spreadsheet.formula.functions;

import spreadsheet.formula.Argument;
import spreadsheet.formula.operands.Function;

import java.util.List;

/** PROMEDIO(...): the average of its arguments. */
public class Promedio extends Function {

    public Promedio(List<Argument> arguments) {
        super(arguments);
    }
}
