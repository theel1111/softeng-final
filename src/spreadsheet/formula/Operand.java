package spreadsheet.formula;

/**
 * Something a formula operates on: a number, a cell reference or a function.
 * It is an interface (not a class) because NumericValue, CellReference and Function
 * are both Operands and Arguments in the domain model, and a Java class can only extend one class.
 */
public interface Operand {
}
