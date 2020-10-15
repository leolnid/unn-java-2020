package expression;

import exceptions.EvaluatingException;

public class CheckedLog extends AbstractBinaryOperator {

    public CheckedLog(TripleExpression first, TripleExpression second) {
        super(first, second);
    }

    public void check(int x, int y) throws EvaluatingException {
        if (x <= 0 || y <= 1) {
            throw new EvaluatingException("Exception: logarithm arguments must be positive; logarithm base must not be less than 1");
        }
    }

    public int result(int x, int y) throws EvaluatingException{
        check(x, y);
        int result = -1;
        while (x > 0) {
            x /= y;
            result++;
        }
        return result;
    }
}