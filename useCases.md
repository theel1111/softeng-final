# Use cases: Spreadsheet

Actor: **User** (interacts through the console menu)

---

## UC1: Create new spreadsheet
**Actor:** User
**Precondition:**
**Main flow:**
1.
**Alternative / error flows:**
-
**Postcondition:**

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
**Precondition:**
**Main flow:**
1.
**Alternative / error flows:**
-
**Postcondition:**

---

## UC4: Save spreadsheet to S2V file
**Actor:** User
**Precondition:**
**Main flow:**
1.
**Alternative / error flows:**
-
**Postcondition:**

---

## UC5: Load spreadsheet from S2V file
**Actor:** User
**Precondition:**
**Main flow:**
1.
**Alternative / error flows:**
-
**Postcondition:**

---

## UC6: Exit program
**Actor:** User
**Precondition:**
**Main flow:**
1.
**Alternative / error flows:**
-
**Postcondition:**