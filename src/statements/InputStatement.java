package statements;

import tokens.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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
    }
    public static void main(String[] args) throws IOException {
        InputStatement inputStatement = new InputStatement("test");
        inputStatement.execute();
    }
}
