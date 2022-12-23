import expressions.BinaryExpression;
import expressions.BinaryOperator;
import expressions.Expression;
import expressions.VarExpression;
import expressions.operators.AddOperator;
import expressions.operators.DivideOperator;
import expressions.operators.MinusOperator;
import expressions.operators.TimesOperator;
import statements.AssignStatement;
import statements.InputStatement;
import statements.PrintStatement;
import statements.Statement;
import tokens.Token;
import tokens.TokenType;
import tokens.UnexpectedTokenException;
import values.NumericValue;
import values.StringValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public List<Statement> parse() {
        while (position < tokens.size()) {
            skipWhitespace();
            parseNextStatement();
        }
        return statements;
    }
    private void parseNextStatement() {
        Token firstToken = curToken();
        if (firstToken.type() == TokenType.Keyword) {
            if (firstToken.value().equals("print")) {
                parsePrintStatement();
            }
            else if (firstToken.value().equals("input")) {
                parseInputStatement();
            }
        }
        else if (firstToken.type() == TokenType.Variable) {
            parseAssignStatement();
        }
    }

    /**
     * Parses print statement and advances position to end of print statement
     */
    private void parsePrintStatement() {
        position++;
        skipWhitespace();
        statements.add(new PrintStatement(parseExpression(0)));
    }

    /**
     * Parses input statement and advances position to end of input statement
     */
    private void parseInputStatement() {
        position++;
        skipWhitespace();
        if (curToken().type() != TokenType.Variable) {
            throw new UnexpectedTokenException(String.format("Expected variable token in input statement, got %s instead", curToken().value()));
        }
        statements.add(new InputStatement(curToken().value()));
        position++;
    }
    private void parseAssignStatement() {
        String varName = curToken().value();
        position++;
        skipWhitespace();
        if (!curToken().value().equals("=")) {
            throw new UnexpectedTokenException(String.format("Expected =, got %s", curToken().value()));
        }
        position++;
        skipWhitespace();
        statements.add(new AssignStatement(varName, parseExpression(0)));
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
            else if (curToken().type() == TokenType.Text) {
                return new StringValue(tokens.get(position++).value());
            }
            else if (curToken().type() == TokenType.Variable) {
                return new VarExpression(tokens.get(position++).value());
            }
            else throw new UnexpectedTokenException(String.format("Did not expect %s when parsing level %d expression", curToken().value(), level));
        }
        Expression lhs = parseExpression(level + 1);
        skipWhitespace();
        while (position < tokens.size() && Arrays.asList(operations[level]).contains(curToken().value())) { // check if current level of operations contains operation
            String operator = curToken().value();
            position++;
            skipWhitespace();
            lhs = new BinaryExpression(lhs, parseExpression(level), operatorToClass.get(operator));
            skipWhitespace();
        }
        return lhs;
    }
    public static void main(String[] args) throws IOException {
        String src = Files.readString(Paths.get("C:\\Users\\leifa\\IdeaProjects\\ember\\src\\sample.txt"));
        Parser parser = new Parser(src);
        List<Token> tokens = parser.tokens;
        for (Token token : tokens) {
            System.out.println(token);
        } // TODO: remove
        parser.parse();
        for (Statement statement : parser.statements) {
            System.out.println(statement);
        }
    }
}
