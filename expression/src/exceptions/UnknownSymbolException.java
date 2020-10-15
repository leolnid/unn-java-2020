package exceptions;

public class UnknownSymbolException extends ParsingException {
    public UnknownSymbolException(final String s, final int pos) {
        super("Unknown symbol '" + s.charAt(pos) + "' " + "at position: " + pos + ": " + s);
    }
}
