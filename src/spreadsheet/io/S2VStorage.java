package spreadsheet.io;

import spreadsheet.Coordinate;
import spreadsheet.Spreadsheet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Saves and loads spreadsheets in S2V (Semicolon Separated Values) format:
 * one line per row, cell contents separated by ';', empty cells left empty.
 * Inside formulas, the ';' between function arguments is stored as ','.
 */
public final class S2VStorage {

    private S2VStorage() {
    }

    public static void save(Spreadsheet sheet, Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        for (int row = 1; row <= sheet.getMaxRow(); row++) {
            lines.add(rowToLine(sheet, row));
        }
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    public static Spreadsheet load(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        Spreadsheet sheet = new Spreadsheet();
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(";", -1);
            for (int j = 0; j < parts.length; j++) {
                String text = parts[j];
                if (text.isEmpty()) continue;
                if (text.startsWith("=")) {
                    text = text.replace(',', ';');
                }
                sheet.setContent(Coordinate.of(j + 1, i + 1), text);
            }
        }
        return sheet;
    }

    /** A row ends at its last non-empty cell, as the S2V format requires. */
    private static String rowToLine(Spreadsheet sheet, int row) {
        int lastColumn = 0;
        for (int col = 1; col <= sheet.getMaxColumn(); col++) {
            if (!sheet.getContentText(Coordinate.of(col, row)).isEmpty()) lastColumn = col;
        }
        StringBuilder line = new StringBuilder();
        for (int col = 1; col <= lastColumn; col++) {
            if (col > 1) line.append(';');
            String text = sheet.getContentText(Coordinate.of(col, row));
            if (text.startsWith("=")) {
                text = text.replace(';', ',');
            }
            line.append(text);
        }
        return line.toString();
    }
}
