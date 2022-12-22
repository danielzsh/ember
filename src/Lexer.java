import tokens.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Divides source string into lexemes.
 */
public class Lexer {
    private final String source;
    private final List<Token> tokens;
    /**
     *
     * @param source Source file to be processed.
     */
    public Lexer(String source) {
        this.source = source;
        tokens = new ArrayList<>();
    }

    /**
     * Parses source file into lexemes.
     * @return List of all tokens from source file
     */
    public List<Token> lex() {
        int position = 0;
        while (position < source.length()) {
            position += nextToken(position);
        }
        return tokens;
    }

    /**
     * Returns next token after certain position.
     * @param position position to start searching for next token
     * @return length of token found
     */
    private int nextToken(int position) {
        String searchSpace = source.substring(position);
        for (var tokenType : TokenType.values()) {
            Pattern pattern = Pattern.compile("^" + tokenType.getRegex()); // add caret to search from beginning
            Matcher matcher = pattern.matcher(searchSpace);
            if (matcher.find()) {
                String content = matcher.groupCount() > 0 ? matcher.group(1) : matcher.group(); // string is captured in separate group
                Token token = new Token(tokenType, content);
                tokens.add(token);
                return matcher.group().length();
            }
        }
        throw new TokenException(String.format("Invalid expression: %s", searchSpace));
    }
}
