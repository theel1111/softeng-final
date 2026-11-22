package spreadsheet.formula.functions;

import spreadsheet.formula.Argument;
import spreadsheet.formula.operands.Function;

import java.util.List;

/** MAX(...): the maximum of its arguments. */
public class Max extends Function {

    public Max(List<Argument> arguments) {
        super(arguments);
    }
}
