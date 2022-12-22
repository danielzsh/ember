package expressions;

import runtime.ScopeStack;
import values.Value;

public class VarExpression implements Expression {
    private final String name;

    public VarExpression(String name) {
        this.name = name;
    }

    /**
     * @return Value of variable
     */
    @Override
    public Value<?> evaluate() {
        return ScopeStack.peek().get(name);
    }

    @Override
    public String toString() {
        return "VarExpression{" +
                "name='" + name + '\'' +
                '}';
    }
}
