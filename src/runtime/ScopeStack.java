package runtime;

import java.util.ArrayList;
import java.util.Stack;

public class ScopeStack {
    private static final Stack<Scope> stack = new Stack<>();
    private static void push(Scope scope) {
        stack.push(scope);
    }
    private static Scope peek() {
        return stack.peek();
    }
}
