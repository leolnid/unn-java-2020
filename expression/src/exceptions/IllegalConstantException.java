package exceptions;

public class IllegalConstantException extends ParsingException {
    public IllegalConstantException(final String Const, final String s, final int ind) {
        super("Constant '" + Const + "' is not suitable for int at position: " + ind + ": " + s);
    }
}
