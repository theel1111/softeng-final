package spreadsheet.content;

/** A text stored in a cell, e.g. a header like "TOTAL". */
public class TextContent extends Content {

    private final String text;

    public TextContent(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }
}
