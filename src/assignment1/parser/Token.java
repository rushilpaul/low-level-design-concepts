package assignment1.parser;

public class Token {

    private Object value;

    public Token(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public String toString() {
        if(value == null)
            return null;
        return value.toString();
    }
}
