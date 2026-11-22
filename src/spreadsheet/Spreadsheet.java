package spreadsheet;

import spreadsheet.content.Content;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A spreadsheet: the set of its non-empty cells, looked up by coordinate.
 * A map is used instead of a 2D array because the grid has no fixed size
 * (columns go A..Z, AA, AB, ...) and most cells are empty.
 */
public class Spreadsheet {

    private final Map<Coordinate, Cell> cells = new HashMap<>();

    /** Sets the content of a cell from the text the user typed. Empty text clears the cell. */
    public void setContent(Coordinate coordinate, String text) {
        Content content = Content.fromText(text);
        if (content == null) {
            cells.remove(coordinate);
            return;
        }
        cells.computeIfAbsent(coordinate, Cell::new).setContent(content);
    }

    public void setContent(String coordinate, String text) {
        setContent(Coordinate.parse(coordinate), text);
    }

    /** The cell at this coordinate, or null if it is empty. */
    public Cell getCell(Coordinate coordinate) {
        return cells.get(coordinate);
    }

    /** The content of a cell as text (e.g. "=A1+B1", not its value), or "" if empty. */
    public String getContentText(Coordinate coordinate) {
        Cell cell = cells.get(coordinate);
        return cell == null ? "" : cell.getContentText();
    }

    public String getContentText(String coordinate) {
        return getContentText(Coordinate.parse(coordinate));
    }

    public Collection<Cell> getCells() {
        return cells.values();
    }

    public boolean isEmpty() {
        return cells.isEmpty();
    }

    /** The highest row number that has a non-empty cell (0 if the sheet is empty). */
    public int getMaxRow() {
        int max = 0;
        for (Coordinate c : cells.keySet()) max = Math.max(max, c.getRow());
        return max;
    }

    /** The highest column number that has a non-empty cell (0 if the sheet is empty). */
    public int getMaxColumn() {
        int max = 0;
        for (Coordinate c : cells.keySet()) max = Math.max(max, c.getColumnIndex());
        return max;
    }
}
