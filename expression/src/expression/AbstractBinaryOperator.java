package expression;

import exceptions.EvaluatingException;

public abstract class AbstractBinaryOperator implements TripleExpression {
    private final TripleExpression left;
    private final TripleExpression right;

    public AbstractBinaryOperator(final TripleExpression left, final TripleExpression right) {
        this.left = left;
        this.right = right;
    }

    protected abstract void check(final int x, final int y) throws EvaluatingException;

    protected abstract int result(final int x, final int y) throws EvaluatingException;

    public int evaluate(int ... a) throws EvaluatingException{
        return result(left.evaluate(a), right.evaluate(a));
    }
}