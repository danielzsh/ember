package statements;
import runtime.ScopeStack;
import tokens.TokenType;
import values.NumericValue;
import values.StringValue;

import java.util.Scanner;

public class InputStatement implements Statement {
    private final String varName;

    public InputStatement(String varName) {
        this.varName = varName;
    }

    /**
     * Details the execution of the statement.
     */
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.printf("Input %s>>>:", varName);
        String input = in.nextLine();
        if (input.matches(TokenType.Numeric.getRegex())) {
            ScopeStack.peek().put(varName, new NumericValue(Double.parseDouble(input)));
            // System.out.println("numeric"); TODO: remove
        }
        else {
            ScopeStack.peek().put(varName, new StringValue(input));
            // System.out.println("text"); TODO: remove
        }
        // System.out.println(input);
    }
    public static void main(String[] args) { // main function to test input functionality
        InputStatement inputStatement = new InputStatement("test");
        inputStatement.execute();
    }

    @Override
    public String toString() {
        return "InputStatement{" +
                "varName='" + varName + '\'' +
                '}';
    }
}
