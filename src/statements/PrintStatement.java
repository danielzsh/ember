package statements;

import expressions.Expression;

public class PrintStatement implements Statement {
    private final Expression expression; // expression to be printed

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute() {
        System.out.println(expression.evaluate());
    }
}
