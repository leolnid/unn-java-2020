package expression;

public class Const implements TripleExpression {
    private final Number input;

    public Const(final Number input) {
        this.input = input;
    }

    public int evaluate(int ... x) {
        return input.intValue();
    }
}
