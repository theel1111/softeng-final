package spreadsheet.formula.operands;

import spreadsheet.formula.Argument;
import spreadsheet.formula.Operand;

import spreadsheet.Coordinate;

/** A cell named inside a formula, e.g. the A1 in =A1+B1. Holds only the address. */
public class CellReference implements Operand, Argument {

    private final Coordinate coordinate;

    public CellReference(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
