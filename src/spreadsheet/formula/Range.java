package spreadsheet.formula;

import spreadsheet.Coordinate;

/** A rectangle of cells inside a function, e.g. A1:B3. Only valid as a function argument. */
public class Range implements Argument {

    private final Coordinate topLeft;
    private final Coordinate bottomRight;

    public Range(Coordinate topLeft, Coordinate bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Coordinate getTopLeft() {
        return topLeft;
    }

    public Coordinate getBottomRight() {
        return bottomRight;
    }
}
