package expression;

import java.util.Arrays;
import java.util.List;

public class Variable implements TripleExpression {

    private final String var;
    private final List<String> Variables = Arrays.asList("x", "y", "z", "tmp", "t1m2p3");

    public Variable(final String x) {
        this.var = x;
    }

    public int evaluate(int ... x) {
        return x[Variables.indexOf(var)];
    }
}
