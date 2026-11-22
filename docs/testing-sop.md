# Testing SOP

How we write and run tests in this project.

## When to add a test

Not for every class or method, but for every **behaviour**, meaning anything that can be right or wrong.

| Write a test when… | Example in this project |
|---|---|
| A method makes a decision or computes something | `Coordinate.columnIndex("AB")` returns 28 |
| There is a rule from the project PDF | Formulas start with `=`; `;` inside a function is saved as `,` |
| Input can be invalid | `Coordinate.parse("1A")` throws an error |
| You fix a bug | First write a test that fails because of the bug, then fix it |
| Two parts must agree | Saving and then loading gives back the same spreadsheet |

**Skip** tests for code with no logic: plain getters and setters, empty shell classes (e.g. `Suma` before step 8), and `toString` used only for debugging.

## The loop, for every step

1. **Pick one behaviour** from the step (e.g. "a column name becomes a number").
2. **Write the test first** (or right after the method, before moving on): known input, expected output.
3. **Run it**: it should fail, which proves it checks something.
4. **Write or fix the code** until it passes.
5. **Run all tests**, not just the new one, so you notice if you broke something older.
6. **Commit** code and its test together. Never commit with red tests.

## Where tests go

- One test class per class under test, in the **same package**, under `test/` instead of `src/`:
  - `src/spreadsheet/Coordinate.java` → `test/spreadsheet/SpreadsheetTest.java` (core classes)
  - `src/spreadsheet/io/S2VStorage.java` → `test/spreadsheet/io/S2VStorageTest.java`
- Name the test class `<ClassName>Test`.
- Name each test method after the behaviour, as a sentence: `loadGivesBackWhatWasSaved`, not `test3`.

## What a good test looks like

```java
@Test
void columnLettersConvertToNumbers() {
    // Arrange: set up the input
    String column = "AB";
    // Act: call the code
    int index = Coordinate.columnIndex(column);
    // Assert: check the result
    assertEquals(28, index);
}
```

- **One behaviour per test**, so a failing test name tells you what broke.
- **Small, hand-checked examples.** Use the PDF's examples where possible (e.g. its S2V sample).
- **Test edge cases**: empty input, first and last values (`A`, `Z`, `AA`), invalid input.
- For files, use JUnit's `@TempDir` so tests never touch real files.

## Running tests (IntelliJ)

- All tests: right-click `test` → **Run 'All Tests'**.
- One class: click the green ▶ next to the class name.
- Green = pass, red = fail; click a red test to see expected vs actual.

## Note on the JUnit version

The jars in `lib/` are JUnit **5.7**. Newer helpers such as `assertInstanceOf` (5.8+) don't exist; use `assertTrue(x instanceof Y)` instead.
