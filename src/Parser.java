import expressions.BinaryExpression;
import expressions.BinaryOperator;
import expressions.Expression;
import expressions.operators.AddOperator;
import expressions.operators.DivideOperator;
import expressions.operators.MinusOperator;
import expressions.operators.TimesOperator;
import statements.PrintStatement;
import statements.Statement;
import tokens.Token;
import tokens.TokenType;
import tokens.UnexpectedTokenException;
import values.NumericValue;
import values.StringValue;

import java.util.*;

public class Parser {
    private final List<Token> tokens;
    private final List<Statement> statements;
    private int position = 0;

    private static final Map<String, BinaryOperator> operatorToClass = new HashMap<>();
    static {
        operatorToClass.put("+", new AddOperator());
        operatorToClass.put("-", new MinusOperator());
        operatorToClass.put("*", new TimesOperator());
        operatorToClass.put("/", new DivideOperator());
    }
    private final String[][] operations = {
            {"+", "-"},
            {"*", "/"}
    };
    public Parser(String src) {
        Lexer lexer = new Lexer(src);
        tokens = lexer.lex();
        statements = new ArrayList<>();
    }
    private void skipWhitespace() {
        while (position < tokens.size() && tokens.get(position).type() == TokenType.Whitespace) {
            position++;
        }
    }
    private Token curToken() {
        return tokens.get(position);
    }
    public void parse() {
        while (position < tokens.size()) {
            parseNextStatement();
        }
    }
    private void parseNextStatement() {
        Token firstToken = curToken();
        if (firstToken.type() == TokenType.Keyword) {
            if (firstToken.value().equals("print")) {
                parsePrintStatement();
            }
        }
    }
    private void parsePrintStatement() {
        if (!curToken().value().equals("print")) {
            throw new UnexpectedTokenException("Expected literal print in call to parsePrintStatement");
        }
        position++;
        skipWhitespace();
        statements.add(new PrintStatement(parseExpression(0)));
    }

    /**
     * parses expression and leaves position 1 token after end of expression.
     * @param level used to maintain order of operations
     * @return an expression
     */
    private Expression parseExpression(int level) {
        if (level == operations.length) { // no more operations to use
            if (curToken().type() == TokenType.Numeric) {
                return new NumericValue(tokens.get(position++).value());
            }
            else if (tokens.get(position).type() == TokenType.Text) {
                return new StringValue(tokens.get(position++).value());
            }
        }
        Expression lhs = parseExpression(level - 1);
        skipWhitespace();
        while (Arrays.asList(operations[level]).contains(curToken().value())) { // check if current level of operations contains operation
            String operator = curToken().value();
            position++;
            skipWhitespace();
            lhs = new BinaryExpression(lhs, parseExpression(level), operatorToClass.get(operator));
        }
        return lhs;
    }
}
