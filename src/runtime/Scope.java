package runtime;

import values.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * Scope to store current variables.
 */
public class Scope {
    private final Map<String, Value<?>> variables;
    public Scope() {
        variables = new HashMap<>();
    }

    /**
     * Insert variable into scope.
     * @param varName name of inserted variable
     * @param value value of inserted variable
     */
    public void put(String varName, Value<?> value) {
        variables.put(varName, value);
    }
    public Value<?> get(String varName) {
        return variables.get(varName);
    }

}
