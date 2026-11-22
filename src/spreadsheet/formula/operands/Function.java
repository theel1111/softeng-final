package spreadsheet.formula.operands;

import spreadsheet.formula.Argument;
import spreadsheet.formula.Operand;

import java.util.ArrayList;
import java.util.List;

/**
 * A function such as SUMA(A1:B2;3). It has one or more arguments, and it is itself
 * both an Operand (usable in a formula) and an Argument (usable inside another function).
 * Calculation is added in step 8.
 */
public abstract class Function implements Operand, Argument {

    private final List<Argument> arguments;

    protected Function(List<Argument> arguments) {
        if (arguments == null || arguments.isEmpty()) {
            throw new IllegalArgumentException(getClass().getSimpleName() + " needs at least one argument");
        }
        this.arguments = new ArrayList<>(arguments);
    }

    public List<Argument> getArguments() {
        return arguments;
    }
}
