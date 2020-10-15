package exceptions;

public class MissingClosingParenthesisException extends ParsingException {
    public MissingClosingParenthesisException(final String s){
        super("Missing closing parenthesis: " + s);
    }
}
