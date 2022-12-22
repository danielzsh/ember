package expressions;

import values.Value;

public class BinaryExpression implements Expression {
    private final Expression lhs;
    private final Expression rhs;
    private final BinaryOperator operator;
    public BinaryExpression(Expression lhs, Expression rhs, BinaryOperator operator) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.operator = operator;
    }
    public Value<?> evaluate() {
        return operator.evaluate(lhs.evaluate(), rhs.evaluate());
    }

    @Override
    public String toString() {
        return "BinaryExpression{" +
                "lhs=" + lhs +
                ", rhs=" + rhs +
                ", operator=" + operator +
                '}';
    }
}
