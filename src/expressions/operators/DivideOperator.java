package expressions.operators;

import expressions.BinaryOperator;
import expressions.IncompatibleException;
import values.NumericValue;
import values.StringValue;
import values.Value;

public class DivideOperator implements BinaryOperator {
    private boolean isInt(NumericValue numVal) {
        return numVal.getValue() % 1 == 0;
    }
    /**
     * Divides two values.
     * @param lhs left-hand side
     * @param rhs right-hand side
     * @return result of division
     * @throws IncompatibleException if lhs and rhs are not compatible types
     */
    @Override
    public Value<?> evaluate(Value<?> lhs, Value<?> rhs) {
        if (lhs instanceof NumericValue lhsNumVal) {
            if (rhs instanceof NumericValue rhsNumVal) {
                if (isInt(lhsNumVal) && isInt(rhsNumVal)) {
                    return new NumericValue(lhsNumVal.getValue().intValue() / rhsNumVal.getValue().intValue());
                }
                else return new NumericValue(lhsNumVal.getValue() / rhsNumVal.getValue());
            }
        }
        throw new IncompatibleException(String.format("Could not multiply %s and %s", lhs.toString(), rhs.toString()));
    }

    @Override
    public String toString() {
        return "DivideOperator";
    }
}
