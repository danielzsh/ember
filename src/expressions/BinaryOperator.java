package expressions;

import values.Value;

public interface BinaryOperator {
    Value<?> evaluate(Value<?> lhs, Value<?> rhs);
}
