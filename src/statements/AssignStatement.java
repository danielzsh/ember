package statements;

import expressions.Expression;
import runtime.ScopeStack;

public class AssignStatement implements Statement {
    private final String varName;
    private final Expression rhs;

    public AssignStatement(String varName, Expression rhs) {
        this.varName = varName;
        this.rhs = rhs;
    }

    /**
     * Details the execution of the statement.
     */
    @Override
    public void execute() {
        ScopeStack.peek().put(varName, rhs.evaluate());
    }

    @Override
    public String toString() {
        return "AssignStatement{" +
                "varName='" + varName + '\'' +
                ", rhs=" + rhs +
                '}';
    }
}
