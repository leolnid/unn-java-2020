package exceptions;

public class MissingOpeningParenthesisException extends ParsingException {
    public MissingOpeningParenthesisException(final String s, final int pos){
        super("Closing parenthesis at position "  + pos + " has not opening parenthesis: " + s);
    }
}
