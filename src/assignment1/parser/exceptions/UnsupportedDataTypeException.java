package assignment1.parser.exceptions;

public class UnsupportedDataTypeException extends Exception {

    public UnsupportedDataTypeException(String key) {
        super("Value at key " + key + " has unsupported type of data");
    }
}
