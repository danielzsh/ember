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
}
