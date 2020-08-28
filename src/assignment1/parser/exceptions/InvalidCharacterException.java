package assignment1.parser.exceptions;

public class InvalidCharacterException extends SyntaxException {

    public InvalidCharacterException(char character, int position) {
        super(String.format("Invalid character '%c' encountered", character), position);
    }
}
