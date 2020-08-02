package assignment1.parser;

public class SyntaxException extends RuntimeException {

    public SyntaxException(String message, int position) {
        super(message + " at position " + position);
    }
}
