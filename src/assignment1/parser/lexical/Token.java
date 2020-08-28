package assignment1.parser.lexical;

public class Token {

    private Object value;
    private TokenType tokenType;

    public Token(Object value, TokenType tokenType) {
        this.value = value;
        this.tokenType = tokenType;
    }

    public Object getValue() {
        return value;
    }

    public TokenType tokenType() {
        return tokenType;
    }

    public String toString() {
        if(value == null)
            return null;
        return value.toString();
    }

    public String getReadableString() {
        if(value == null)
            return null;
        return String.format("[%s : %s]", value.toString(), tokenType.name());
    }
}
