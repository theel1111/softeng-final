package spreadsheet;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The address of a cell: a column made of letters (A, B, ..., Z, AA, AB, ...)
 * and a row number starting at 1. Example: "C3".
 */
public final class Coordinate implements Comparable<Coordinate> {

    private static final Pattern FORMAT = Pattern.compile("([A-Za-z]+)([1-9][0-9]*)");

    private final String column;
    private final int row;

    public Coordinate(String column, int row) {
        if (column == null || !column.matches("[A-Za-z]+")) {
            throw new IllegalArgumentException("Invalid column: " + column);
        }
        if (row < 1) {
            throw new IllegalArgumentException("Row must be 1 or more: " + row);
        }
        this.column = column.toUpperCase();
        this.row = row;
    }

    /** Builds a coordinate from text such as "A1" or "ab12". */
    public static Coordinate parse(String text) {
        Matcher m = FORMAT.matcher(text == null ? "" : text.trim());
        if (!m.matches()) {
            throw new IllegalArgumentException("Invalid cell coordinate: " + text);
        }
        return new Coordinate(m.group(1), Integer.parseInt(m.group(2)));
    }

    /** Builds a coordinate from a column number (A = 1) and a row number. */
    public static Coordinate of(int columnIndex, int row) {
        return new Coordinate(columnName(columnIndex), row);
    }

    /** "A" -> 1, "Z" -> 26, "AA" -> 27, "AB" -> 28 (base 26 with no zero digit). */
    public static int columnIndex(String column) {
        int index = 0;
        for (char c : column.toUpperCase().toCharArray()) {
            index = index * 26 + (c - 'A' + 1);
        }
        return index;
    }

    /** 1 -> "A", 26 -> "Z", 27 -> "AA". */
    public static String columnName(int columnIndex) {
        if (columnIndex < 1) {
            throw new IllegalArgumentException("Column number must be 1 or more: " + columnIndex);
        }
        StringBuilder name = new StringBuilder();
        int n = columnIndex;
        while (n > 0) {
            n--;
            name.append((char) ('A' + n % 26));
            n /= 26;
        }
        return name.reverse().toString();
    }

    public String getColumn() {
        return column;
    }

    public int getColumnIndex() {
        return columnIndex(column);
    }

    public int getRow() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate other = (Coordinate) o;
        return row == other.row && column.equals(other.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    /** Orders by row first, then by column (reading order). */
    @Override
    public int compareTo(Coordinate other) {
        if (row != other.row) return Integer.compare(row, other.row);
        return Integer.compare(getColumnIndex(), other.getColumnIndex());
    }

    @Override
    public String toString() {
        return column + row;
    }
}
