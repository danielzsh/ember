package expressions.operators;

import expressions.BinaryOperator;
import expressions.IncompatibleException;
import values.NumericValue;
import values.Value;

public class MinusOperator implements BinaryOperator {
    /**
     * Subtracts two values.
     * @param lhs left-hand side
     * @param rhs right-hand side
     * @return result of subtraction
     * @throws IncompatibleException if lhs and rhs are not compatible types
     */
    @Override
    public Value<?> evaluate(Value<?> lhs, Value<?> rhs) {
        if (lhs instanceof NumericValue lhsNumVal) {
            if (rhs instanceof NumericValue rhsNumVal)
                return new NumericValue(lhsNumVal.getValue() - rhsNumVal.getValue());
        }
        throw new IncompatibleException(String.format("Could not subtract %s and %s", lhs.toString(), rhs.toString()));
    }
}
