package statements;
import java.util.List;
public class MainStatement implements Statement {
    private final List<Statement> statements;
    public MainStatement(List<Statement> statements) {

        this.statements = statements;
    }
    @Override
    public void execute() {
        for (Statement statement : statements) {
            statement.execute();
        }
    }
}
