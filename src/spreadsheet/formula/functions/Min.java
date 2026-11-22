package spreadsheet.formula.functions;

import spreadsheet.formula.Argument;
import spreadsheet.formula.operands.Function;

import java.util.List;

/** MIN(...): the minimum of its arguments. */
public class Min extends Function {

    public Min(List<Argument> arguments) {
        super(arguments);
    }
}
