package assignment1.parser.lexical;

import assignment1.parser.exceptions.SyntaxException;

import java.util.ArrayList;
import java.util.List;

import static assignment1.parser.lexical.TokenType.*;

public class LexicalScanner {

    private int currentPos;
    private char expression[];
    private String[] validOperators = { "<", ">", "<=", ">=", "AND", "OR", "BETWEEN" };
    private static final Character EOF = '$';

    public LexicalScanner(String expression) {
        this.expression = (expression + EOF).toCharArray();
        currentPos = 0;
    }

    public List<Token> tokenize() {

        List<Token> tokens = new ArrayList<>();

        // start parsing
        while(true) {

            Character currentChar = getChar();
            Token token;

            if(Character.isWhitespace(currentChar)) {   // skip whitespaces
                getWhitespaces();
                continue;
            }

            if(currentChar == EOF) {
                tokens.add(new Token("", END_OF_INPUT));
                break;

            } else if(Character.isDigit(currentChar)) {
                token = new Token(getInteger(), NUMBER);

            } else if(currentChar == '"') {
                token = new Token(getStringConstant(), STRING_CONST);

            } else if(Character.isLetter(currentChar)) {
                token = new Token(getVariableOrKeyword(), WORD);

            } else if(currentChar == '>') {
                if(isNextString(">=")) {
                    token = new Token(">=", GREATER_THAN_EQUALS);
                    nextPosition();
                } else
                    token = new Token(">", GREATER_THAN);
                nextPosition();

            } else if(currentChar == '<') {
                if(isNextString("<=")) {
                    token = new Token("<=", LESS_THAN_EQUALS);
                    nextPosition();
                } else
                    token = new Token("<", LESS_THAN);
                nextPosition();

            } else if(currentChar == '=') {
                if(isNextString("==")) {
                    token = new Token("==", EQUALS);
                    nextPosition(2);
                } else
                    throw new SyntaxException(String.format("Invalid character '%c' found encountered", currentChar), currentPos);

            } else if(currentChar == '!') {
                if(isNextString("!=")) {
                    token = new Token("!=", NOT_EQUALS);
                    nextPosition(2);
                } else
                    throw new SyntaxException(String.format("Invalid character '%c' found encountered", currentChar), currentPos);

            } else if(isParenthesis(currentChar)) {
                token = new Token(currentChar, currentChar == '(' ? PARENTHESIS_OPEN : PARENTHESIS_CLOSE);
                nextPosition();
            }

            else if(currentChar == ',') {
                token = new Token(currentChar, COMMA);
                nextPosition();

            } else
                throw new SyntaxException(String.format("Invalid character '%c' encountered", currentChar), currentPos);
            tokens.add(token);
        }
        return tokens;
    }

    private boolean isParenthesis(char ch) {
        return ch == '(' || ch == ')';
    }

    /**
     * Returns a string constant in double quotes
     * @return
     */
    private String getStringConstant() {

        StringBuilder stringConstant = new StringBuilder();
        nextPosition();
        char ch;
        while((ch = getAndNext()) != '"') {
            stringConstant.append(ch);
        }
        return stringConstant.toString();
    }

    private String getSpecialChar() {
        char ch = getChar();
        nextPosition();
        return ch + "";
    }

    /**
     *
     * @return dot separated variables or keywords like true, false, AND, OR, BETWEEN, ALLOF, NONEOF
     */
    private String getVariableOrKeyword() {

        StringBuilder builder = new StringBuilder();
        builder.append(getLetter());

        if(Character.isLetterOrDigit(getChar()))
            builder.append(getAlphaNumericString());

        if(getChar() == '.') {
            builder.append(getAndNext());
            builder.append(getVariableOrKeyword());
        }

        return builder.toString();
    }

    private String getAlphaNumericString() {

        StringBuilder builder = new StringBuilder();
        if(!Character.isLetterOrDigit(getChar()))
            throw new SyntaxException("Letter or digit was expected", currentPos);
        builder.append(getAndNext());

        if(Character.isLetterOrDigit(getChar()))
            builder.append(getAlphaNumericString());

        return builder.toString();
    }

    /**
     * Assumes the current character is a digit
     * @return an integer
     */
    private Integer getInteger() {

        Integer number = getDigit();
        // Keep accumulating more digits only if the current char is a digit
        if(Character.isDigit(getChar()))
            number = Integer.parseInt(number + String.valueOf(getInteger()));
        return number;
    }

    private Character getLetter() {
        if(!Character.isLetter(getChar()))
            throw new SyntaxException("Letter was expected", currentPos);
        return getAndNext();
    }

    private Integer getDigit() {
        if(!Character.isDigit(getChar()))
            throw new SyntaxException("A digit was expected", currentPos);
        return getAndNext() - '0';
    }

    /**
     * Assumes the current character is a whitespace
     */
    private void getWhitespaces() {
        if(!Character.isWhitespace(getChar()))
            throw new SyntaxException("A space was expected", currentPos);
        do
            nextPosition();
        while(Character.isWhitespace(getChar()));
    }

    private void nextPosition(int times) {
        currentPos += times;
    }

    private Character getChar() {

        if(currentPos >= expression.length) {
            throw new SyntaxException("Syntax error", currentPos);
        }
        return expression[currentPos];
    }

    private char getAndNext() {

        char ch = getChar();
        nextPosition();
        return ch;
    }

    /**
     * Skip to the next position
     */
    private void nextPosition() {
        currentPos++;
    }

    /**
     * @param lookAheadString
     * @return true / false depending on whether the next string is equal to the given lookAheadString
     */
    private boolean isNextString(String lookAheadString) {

        if(currentPos + lookAheadString.length() > expression.length)
            return false;
        for(int i = 0; i < lookAheadString.length(); i++)
            if(expression[currentPos + i] != lookAheadString.charAt(i))
                return false;
        return true;
    }
}
