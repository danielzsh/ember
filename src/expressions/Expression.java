package expressions;
import values.Value;

public interface Expression {
    public Value<?> evaluate();
    public String toString();
}
