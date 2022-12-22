import runtime.Scope;
import runtime.ScopeStack;
import statements.Statement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Runner {
    private final String src;
    private final List<Statement> statements;
    public Runner(String src) {
        this.src = src;
        Parser parser = new Parser(src);
        statements = parser.parse();
        ScopeStack.push(new Scope());
    }
    public void run() {
        for (Statement statement : statements) {
            statement.execute();
        }
    }
    public static void main(String[] args) throws IOException {
        String src = Files.readString(Paths.get("C:\\Users\\leifa\\IdeaProjects\\ember\\src\\sample.txt"));
        Runner runner = new Runner(src);
        runner.run();
    }
}
