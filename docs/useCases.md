# Use cases: Spreadsheet

Actor: **User** (interacts through the console menu)

---

## UC1: Create new spreadsheet
**Actor:** User
**Precondition:** The program is running and showing the menu.
**Main flow:**
1. User selects "Create new spreadsheet" from the menu.
2. System creates a new, empty spreadsheet (all cells empty) and makes it the current spreadsheet.
3. System confirms the creation and returns to the menu.

**Alternative / error flows:**
- 2a. A spreadsheet with unsaved changes is already open: System asks whether to save it first. If the user says yes, UC4 (Save) runs, then the flow continues at step 2. If the user says no, the changes are discarded and the flow continues at step 2.

**Postcondition:** An empty spreadsheet is open and ready to be edited.

---

## UC2: Edit cell
**Actor:** User
**Precondition:** A spreadsheet is open (created with UC1 or loaded with UC5).
**Main flow:**
1. User selects "Edit cell" from the menu.
2. System asks for the cell coordinate.
3. User enters a coordinate (e.g. `C3`).
4. System validates the coordinate and asks for the new content.
5. User enters the content (e.g. `4`, `TOTAL` or `=A1+B1`).
6. System determines the content type: a formula if it starts with `=`, a number if it is a valid numeric value, otherwise text.
7. If the content is a formula, System checks its syntax and that it does not create a circular dependency.
8. System stores the content in the cell, replacing any previous content.
9. System recomputes the value of the cell and of every cell that depends on it, in dependency order (only the affected cells, not the whole spreadsheet).
10. System confirms the change and returns to the menu.

**Alternative / error flows:**
- 4a. The coordinate is invalid (e.g. `1A`, `A0`, `$$`): System shows an error message and asks for the coordinate again.
- 5a. The user enters an empty content: System clears the cell (it becomes empty) and continues at step 9.
- 7a. The formula has a syntax error (e.g. `=1++2`, `=SUMA(A1:`): System shows an error message; the cell keeps its previous content. Use case ends.
- 7b. The formula would create a circular dependency (e.g. A1 = `=A2+A3` while A2 = `=A1*2`): System shows an error message and rejects the change; the cell keeps its previous content. Use case ends.
- 9a. A formula uses a cell whose text content cannot be converted to a number (e.g. `TOTAL`): System shows an error for that cell's value; the content is still stored.

**Postcondition:** The cell holds the new content, and the values of the cell and of all cells depending on it are up to date. The spreadsheet has no circular dependencies.

---

## UC3: Show spreadsheet
**Actor:** User
**Precondition:** A spreadsheet is open.
**Main flow:**
1. User selects "Show spreadsheet" from the menu.
2. System prints the spreadsheet as a grid: column letters as headers, row numbers on the left, and each non-empty cell showing its value (the computed result for formulas).
3. System returns to the menu.

**Alternative / error flows:**
- 2a. The spreadsheet is completely empty: System shows a message saying so.
- 2b. A cell's value cannot be computed (e.g. its formula uses text that is not a number): System shows an error marker in that cell instead of a value.

**Postcondition:** The spreadsheet is unchanged.

---

## UC4: Save spreadsheet to S2V file
**Actor:** User
**Precondition:** A spreadsheet is open.
**Main flow:**
1. User selects "Save spreadsheet" from the menu.
2. System asks for the file path.
3. User enters a path (e.g. `data/budget.s2v`).
4. System writes the spreadsheet to the file in S2V format: one line per row, cell contents separated by `;`, empty cells left empty, formulas stored as their text (e.g. `=A1+B1`), and the `;` between function arguments stored as `,`.
5. System confirms the save and returns to the menu.

**Alternative / error flows:**
- 3a. The file already exists: System asks whether to overwrite it. If the user says no, System asks for another path (back to step 2).
- 4a. The file cannot be written (invalid path, no permission): System shows an error message; the spreadsheet is unchanged. Use case ends.

**Postcondition:** The file contains the spreadsheet in S2V format, and loading it (UC5) gives back the same spreadsheet.

---

## UC5: Load spreadsheet from S2V file
**Actor:** User
**Precondition:** The program is running and showing the menu.
**Main flow:**
1. User selects "Load spreadsheet" from the menu.
2. System asks for the file path.
3. User enters a path.
4. System reads the file line by line: line *n* becomes row *n*, and each `;`-separated part becomes the content of the next column (A, B, C…). Inside formulas, the `,` between function arguments is converted back to `;`.
5. System determines each content's type (as in UC2, step 6) and stores it in the matching cell.
6. System computes the values of all non-empty cells.
7. System replaces the current spreadsheet with the loaded one, confirms, and returns to the menu.

**Alternative / error flows:**
- 1a. The current spreadsheet has unsaved changes: System asks whether to save it first (as in UC1, 2a).
- 4a. The file does not exist or cannot be read: System shows an error message; the current spreadsheet is kept. Use case ends.
- 5a. A formula in the file has a syntax error or the file contains a circular dependency: System shows an error message saying which cell is wrong and does not load the file; the current spreadsheet is kept. Use case ends.

**Postcondition:** The loaded spreadsheet is open, and all its cell values are computed.

---

## UC6: Exit program
**Actor:** User
**Precondition:** The program is running and showing the menu.
**Main flow:**
1. User selects "Exit" from the menu.
2. System ends the program.

**Alternative / error flows:**
- 2a. The current spreadsheet has unsaved changes: System asks whether to save it first. If yes, UC4 (Save) runs before the program ends. If no, the changes are discarded.

**Postcondition:** The program has ended.