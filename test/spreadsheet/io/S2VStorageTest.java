package spreadsheet.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import spreadsheet.Spreadsheet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class S2VStorageTest {

    @Test
    void savesInS2VFormat(@TempDir Path dir) throws IOException {
        Spreadsheet sheet = pdfExample();
        Path file = dir.resolve("sheet.s2v");
        S2VStorage.save(sheet, file);
        assertEquals(List.of("=C1+C2;4;1", ";;2", ";TOTAL;=A1+B1"),
                Files.readAllLines(file, StandardCharsets.UTF_8));
    }

    @Test
    void functionArgumentSeparatorBecomesCommaInFile(@TempDir Path dir) throws IOException {
        Spreadsheet sheet = new Spreadsheet();
        sheet.setContent("A1", "=SUMA(B1;B2;3)");
        Path file = dir.resolve("f.s2v");
        S2VStorage.save(sheet, file);
        assertEquals(List.of("=SUMA(B1,B2,3)"), Files.readAllLines(file, StandardCharsets.UTF_8));
        assertEquals("=SUMA(B1;B2;3)", S2VStorage.load(file).getContentText("A1"));
    }

    @Test
    void loadGivesBackWhatWasSaved(@TempDir Path dir) throws IOException {
        Spreadsheet original = pdfExample();
        Path file = dir.resolve("sheet.s2v");
        S2VStorage.save(original, file);
        Spreadsheet loaded = S2VStorage.load(file);
        for (String c : List.of("A1", "B1", "C1", "C2", "B3", "C3", "A2", "A3")) {
            assertEquals(original.getContentText(c), loaded.getContentText(c), c);
        }
    }

    /** The example spreadsheet from the project PDF. */
    private static Spreadsheet pdfExample() {
        Spreadsheet sheet = new Spreadsheet();
        sheet.setContent("A1", "=C1+C2");
        sheet.setContent("B1", "4");
        sheet.setContent("C1", "1");
        sheet.setContent("C2", "2");
        sheet.setContent("B3", "TOTAL");
        sheet.setContent("C3", "=A1+B1");
        return sheet;
    }
}
