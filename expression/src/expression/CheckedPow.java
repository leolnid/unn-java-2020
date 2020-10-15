package expression;

import exceptions.IllegalOperationException;
import exceptions.OverflowException;

public class CheckedPow extends AbstractBinaryOperator {

    public CheckedPow(final TripleExpression x, final TripleExpression y) {
        super(x, y);
    }

    protected void check(int x, int y) throws IllegalOperationException {
        if (y < 0){
            throw new IllegalOperationException("Pow has negative argument: " + y);
        }
        if (x == y && y == 0){
            throw new IllegalOperationException("Pow has illegal arguments: " + x + " " + y);
        }
    }

    protected int result(int x, int y) throws IllegalOperationException , OverflowException{
        check(x, y);
        CheckedMultiply mul = new CheckedMultiply(new Const(0), new Const(0));
        int res = 1;
//        while (y != 0) {
//            if ((y & 1) == 1)
//                res = mul.result(res, x);
//            x = mul.result(x, x);
//            y >>= 1;
//        }
        for (int i = 0; i < y; i++) {
            res = mul.result(res, x);
        }
        return res;
    }
}
