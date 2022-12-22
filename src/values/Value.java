package values;

import expressions.Expression;

public class Value<T> implements Expression {
    private final T value;

    public Value(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public Value<?> evaluate() {
        return this;
    }

    public T getValue() {
        return value;
    }
}
