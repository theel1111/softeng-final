package spreadsheet;

import spreadsheet.content.Content;

import spreadsheet.content.FormulaContent;
import spreadsheet.content.NumericalContent;
import spreadsheet.content.TextContent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpreadsheetTest {

    @Test
    void columnLettersConvertToNumbersAndBack() {
        assertEquals(1, Coordinate.columnIndex("A"));
        assertEquals(26, Coordinate.columnIndex("Z"));
        assertEquals(27, Coordinate.columnIndex("AA"));
        assertEquals(28, Coordinate.columnIndex("AB"));
        assertEquals("AB", Coordinate.columnName(28));
        assertEquals("ZZ", Coordinate.columnName(702));
        assertEquals("AAA", Coordinate.columnName(703));
    }

    @Test
    void parsesCoordinates() {
        Coordinate c = Coordinate.parse("ab12");
        assertEquals("AB", c.getColumn());
        assertEquals(12, c.getRow());
        assertEquals(Coordinate.of(28, 12), c);
        assertThrows(IllegalArgumentException.class, () -> Coordinate.parse("1A"));
        assertThrows(IllegalArgumentException.class, () -> Coordinate.parse("A0"));
    }

    @Test
    void detectsTheContentType() {
        assertTrue(Content.fromText("4") instanceof NumericalContent);
        assertTrue(Content.fromText("1.2") instanceof NumericalContent);
        assertTrue(Content.fromText("TOTAL") instanceof TextContent);
        assertTrue(Content.fromText("1.2.3") instanceof TextContent);
        assertTrue(Content.fromText("=A1+B1") instanceof FormulaContent);
        assertNull(Content.fromText(""));
    }

    @Test
    void setsAndShowsContentNotValue() {
        Spreadsheet sheet = new Spreadsheet();
        sheet.setContent("A1", "=C1+C2");
        sheet.setContent("B1", "4");
        sheet.setContent("B3", "TOTAL");
        assertEquals("=C1+C2", sheet.getContentText("A1"));
        assertEquals("4", sheet.getContentText("B1"));
        assertEquals("TOTAL", sheet.getContentText("B3"));
        assertEquals("", sheet.getContentText("Z99"));

        sheet.setContent("B1", "");
        assertEquals("", sheet.getContentText("B1"));
        assertNull(sheet.getCell(Coordinate.parse("B1")));
    }
}
