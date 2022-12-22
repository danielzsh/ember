package tokens;

public enum TokenType {
    Whitespace("[\\s\\t\\n\\r]"),
    Keyword("(input|print|for|in)"),
    GroupDivider("(\\[|\\])"),
    Logical("true|false"),
    Numeric("[0-9]+"),
    Text("\"([^\"]*)\""),
    Variable("[a-zA-Z_]+[a-zA-Z0-9_]*"),
    Operator("(\\+|\\-|\\>|\\<|\\={1,2}|\\!|\\:{2})"); // +, -, >, <, =, ==, !, ::
    private final String regex;
    TokenType(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}