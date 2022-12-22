import tokens.Token;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String src = Files.readString(Paths.get("C:\\Users\\leifa\\IdeaProjects\\ember\\src\\sample.txt"));
        Lexer lexer = new Lexer(src);
        List<Token> tokens = lexer.lex();
        for (Token token : tokens) {
            System.out.printf("%s %s\n", token.type(), token.value());
        }
    }
}