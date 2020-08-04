package assignment1.parser.exceptions;

public class SyntaxException extends RuntimeException {

    public SyntaxException(String message, int position) {
        super(message + " at position " + position);
    }

    public SyntaxException(String message) {
        super(message);
    }
}
