package expression;

import exceptions.EvaluatingException;

public interface TripleExpression {
    int evaluate(int ... a) throws EvaluatingException;
}
