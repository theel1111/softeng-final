package spreadsheet.content;

/** A number stored in a cell, e.g. 4 or 1.2. */
public class NumericalContent extends Content {

    private final double num;

    public NumericalContent(double num) {
        this.num = num;
    }

    public double getNum() {
        return num;
    }

    /** Whole numbers are shown without ".0": 4.0 -> "4", 1.2 -> "1.2". */
    @Override
    public String getText() {
        if (num == Math.rint(num) && Math.abs(num) < 1e15) {
            return Long.toString((long) num);
        }
        return Double.toString(num);
    }
}
