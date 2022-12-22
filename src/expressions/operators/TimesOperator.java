package expressions.operators;

import expressions.BinaryOperator;
import expressions.IncompatibleException;
import values.NumericValue;
import values.StringValue;
import values.Value;

public class TimesOperator implements BinaryOperator {
    /**
     * Multiplies two values.
     * @param lhs left-hand side
     * @param rhs right-hand side
     * @return result of multiplication
     * @throws IncompatibleException if lhs and rhs are not compatible types
     */
    @Override
    public Value<?> evaluate(Value<?> lhs, Value<?> rhs) {
        if (lhs instanceof NumericValue lhsNumVal) {
            if (rhs instanceof NumericValue rhsNumVal)
                return new NumericValue(lhsNumVal.getValue() * rhsNumVal.getValue());
            else if (rhs instanceof StringValue rhsStringVal && lhsNumVal.getValue() % 1 == 0) {
                return new StringValue(rhsStringVal.getValue().repeat(lhsNumVal.getValue().intValue()));
            }
        }
        else if (lhs instanceof StringValue lhsStringVal) {
            if (rhs instanceof NumericValue rhsNumVal && rhsNumVal.getValue() % 1 == 0) {
                return new StringValue(lhsStringVal.getValue().repeat(rhsNumVal.getValue().intValue()));
            }
        }
        throw new IncompatibleException(String.format("Could not multiply %s and %s", lhs.toString(), rhs.toString()));
    }

    @Override
    public String toString() {
        return "TimesOperator";
    }
}
