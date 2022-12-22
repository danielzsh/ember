package expressions.operators;

import expressions.BinaryOperator;
import expressions.IncompatibleException;
import values.NumericValue;
import values.StringValue;
import values.Value;
public class AddOperator implements BinaryOperator {

    /**
     * Adds two values.
     * @param lhs left-hand side
     * @param rhs right-hand side
     * @return result of addition
     * @throws IncompatibleException if lhs and rhs are not compatible types
     */
    @Override
    public Value<?> evaluate(Value<?> lhs, Value<?> rhs) {
        if (lhs instanceof NumericValue lhsNumVal) {
            if (rhs instanceof NumericValue rhsNumVal) return new NumericValue(lhsNumVal.getValue() + rhsNumVal.getValue());
            else if (rhs instanceof StringValue rhsStringVal) return new StringValue(lhsNumVal.getValue() + rhsStringVal.getValue());
        }
        else if (lhs instanceof StringValue lhsStringVal) {
            if (rhs instanceof NumericValue rhsNumVal) return new StringValue(lhsStringVal.getValue() + rhsNumVal.getValue());
            else if (rhs instanceof StringValue rhsStringVal) return new StringValue(lhsStringVal.getValue() + rhsStringVal.getValue());
        }
        throw new IncompatibleException(String.format("Could not add %s and %s", lhs.toString(), rhs.toString()));
    }
}
