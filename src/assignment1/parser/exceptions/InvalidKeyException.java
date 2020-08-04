package assignment1.parser.exceptions;

public class InvalidKeyException extends Exception {

    public InvalidKeyException(String keyName) {
        super("Invalid key encountered: " + keyName);
    }
}
