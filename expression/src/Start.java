import expression.parser.ExpressionParser;
import expression.parser.Parser;

public class Start {
    public static void main(String[] args) {
        Parser parser = new ExpressionParser();
        try {
            System.out.print(parser.parse(
                    "7 * (z + y) + x")
                    .evaluate(1, -1, 2));
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}