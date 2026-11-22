package ui;

import spreadsheet.Coordinate;
import spreadsheet.Spreadsheet;
import spreadsheet.io.S2VStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Text menu for the use cases. It only talks to the user; all the logic
 * lives in Spreadsheet and S2VStorage, so a GUI could replace this class.
 * For D2 it shows cell contents (as typed), not computed values.
 */
public class ConsoleMenu {

    private final Scanner in = new Scanner(System.in);
    private Spreadsheet sheet = new Spreadsheet();

    public static void main(String[] args) {
        new ConsoleMenu().run();
    }

    public void run() {
        while (true) {
            System.out.println();
            System.out.println("1. Create new spreadsheet  2. Edit cell  3. Show spreadsheet");
            System.out.println("4. Save (S2V)  5. Load (S2V)  6. Exit");
            String option = ask("> ");
            try {
                switch (option) {
                    case "1": sheet = new Spreadsheet(); System.out.println("New spreadsheet created."); break;
                    case "2": editCell(); break;
                    case "3": show(); break;
                    case "4": S2VStorage.save(sheet, Path.of(ask("File path: "))); System.out.println("Saved."); break;
                    case "5": sheet = S2VStorage.load(Path.of(ask("File path: "))); System.out.println("Loaded."); break;
                    case "6": return;
                    default: System.out.println("Unknown option.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("File error: " + e.getMessage());
            }
        }
    }

    private void editCell() {
        Coordinate coordinate = Coordinate.parse(ask("Cell (e.g. A1): "));
        sheet.setContent(coordinate, ask("Content (empty to clear): "));
        System.out.println(coordinate + " = " + sheet.getContentText(coordinate));
    }

    private void show() {
        if (sheet.isEmpty()) {
            System.out.println("(empty spreadsheet)");
            return;
        }
        int width = 12;
        StringBuilder header = new StringBuilder(String.format("%4s", ""));
        for (int col = 1; col <= sheet.getMaxColumn(); col++) {
            header.append(String.format(" %-" + width + "s", Coordinate.columnName(col)));
        }
        System.out.println(header);
        for (int row = 1; row <= sheet.getMaxRow(); row++) {
            StringBuilder line = new StringBuilder(String.format("%4d", row));
            for (int col = 1; col <= sheet.getMaxColumn(); col++) {
                String text = sheet.getContentText(Coordinate.of(col, row));
                if (text.length() > width) text = text.substring(0, width - 1) + "…";
                line.append(String.format(" %-" + width + "s", text));
            }
            System.out.println(line);
        }
    }

    private String ask(String prompt) {
        System.out.print(prompt);
        return in.hasNextLine() ? in.nextLine() : "6";
    }
}
