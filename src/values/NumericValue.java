package values;

public class NumericValue extends Value<Double> {
    public NumericValue(String value) {
        super(Double.parseDouble(value));
    }
    public NumericValue(double value) {
        super(value);
    }
    public NumericValue(int value) {
        super((double) value);
    }

    @Override
    public String toString() {
        if (getValue() % 1 < 1e-9) return Integer.toString(getValue().intValue()); // TODO: fix error where integers are displayed as decimals
        else return getValue().toString();
    }
}
