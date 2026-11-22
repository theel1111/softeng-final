package spreadsheet.content;

import spreadsheet.formula.Operand;
import spreadsheet.formula.Operator;

import java.util.ArrayList;
import java.util.List;

/**
 * A formula stored in a cell, e.g. "=A1+B1".
 * For D2 only the expression text is kept. The operands and operators
 * (see the domain model) are filled in when formulas are parsed and evaluated (steps 3 and 5).
 */
public class FormulaContent extends Content {

    private final String expression;
    private final List<Operand> operands = new ArrayList<>();
    private final List<Operator> operators = new ArrayList<>();

    public FormulaContent(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public List<Operand> getOperands() {
        return operands;
    }

    public List<Operator> getOperators() {
        return operators;
    }

    @Override
    public String getText() {
        return expression;
    }
}
