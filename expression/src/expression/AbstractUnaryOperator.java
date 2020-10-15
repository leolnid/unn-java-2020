package expression;

import exceptions.EvaluatingException;

public abstract class AbstractUnaryOperator implements TripleExpression {
    private final TripleExpression operand;

    public AbstractUnaryOperator(final TripleExpression operand) {
        this.operand = operand;
    }

    protected abstract void check(final int x) throws EvaluatingException;

    protected abstract int result(final int x) throws EvaluatingException;

    public int evaluate(int... a) throws EvaluatingException {
        return result(operand.evaluate(a));
    }
}
