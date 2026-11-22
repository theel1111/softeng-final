package spreadsheet.content;

/**
 * What a cell holds: a number, a text or a formula.
 * For D2 we only store and show the content as typed; nothing is calculated.
 */
public abstract class Content {

    private static final String NUMBER = "[+-]?(\\d+(\\.\\d*)?|\\.\\d+)([eE][+-]?\\d+)?";

    /** The content as the user typed it, which is also how it is stored in an S2V file. */
    public abstract String getText();

    /**
     * Works out the type from the text:
     * starts with '=' -> formula, a valid number -> number, anything else -> text.
     * Returns null for an empty string (an empty cell).
     */
    public static Content fromText(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        if (text.startsWith("=")) {
            return new FormulaContent(text);
        }
        if (text.trim().matches(NUMBER)) {
            return new NumericalContent(Double.parseDouble(text.trim()));
        }
        return new TextContent(text);
    }

    @Override
    public String toString() {
        return getText();
    }
}
