package spreadsheet;

import spreadsheet.content.Content;

import java.util.HashSet;
import java.util.Set;

/** One cell of the spreadsheet: an address and, optionally, a content. */
public class Cell {

    private final Coordinate coordinate;
    private Content content;
    /** Cells this cell's formula uses. Filled in when cell references are implemented (step 6). */
    private final Set<Cell> dependsOn = new HashSet<>();

    public Cell(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public boolean isEmpty() {
        return content == null;
    }

    /** The content as text, or "" if the cell is empty. */
    public String getContentText() {
        return content == null ? "" : content.getText();
    }

    public Set<Cell> getDependsOn() {
        return dependsOn;
    }
}
