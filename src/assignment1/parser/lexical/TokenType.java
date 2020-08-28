package assignment1.parser.lexical;

/**
 * This is merely used to pass on information about the type of token to the next layer of the parsing engine
 */
public enum TokenType {

    WORD,       // represents variable name, or a keyword (like BETWEEN, OR)
    NUMBER,     // represents integers or floating point numbers (in future)
    STRING_CONST,     // represents string constants
    PARENTHESIS_OPEN,
    PARENTHESIS_CLOSE,
    COMMA,
    GREATER_THAN,
    GREATER_THAN_EQUALS,
    LESS_THAN,
    LESS_THAN_EQUALS,
    EQUALS,
    NOT_EQUALS,
    END_OF_INPUT
}