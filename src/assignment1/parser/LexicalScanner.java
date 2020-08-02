package assignment1.parser;

import java.util.ArrayList;
import java.util.List;

public class LexicalScanner {

    private int currentPos;
    private char expression[];
    private String[] validOperators = { "<", ">", "<=", ">=", "AND", "OR", "BETWEEN" };
    private static final Character EOF = '$';

    public LexicalScanner(String expression) {
        this.expression = (expression + EOF).toCharArray();
        currentPos = 0;
    }

    public List<Token> tokenize() throws Exception {

        List<Token> tokens = new ArrayList<>();

        // start parsing
        while(currentPos < expression.length) {

            char currentChar = getChar();
            if(currentChar == EOF)
                break;

            Token token;

            if(Character.isWhitespace(currentChar)) {   // skip whitespaces
                getWhitespaces();
                continue;
            }
            if(Character.isDigit(currentChar)) {
                token = new Token(getInteger());
            }
            else if(currentChar == '"') {
                token = new Token(getString());

            } else if(Character.isLetter(currentChar)) {
                token = new Token(getVariableOrKeyword());

            } else if(currentChar == '<') {
                if(isNextString("<=")) {
                    token = new Token("<=");
                    nextPosition();
                } else
                    token = new Token("<");
                nextPosition();

            } else if(currentChar == '>') {
                if(isNextString(">=")) {
                    token = new Token(">=");
                    nextPosition();
                } else
                    token = new Token(">");
                nextPosition();

            } else if(isParenthesis(currentChar))
                token = new Token(getSpecialChar());
            else if(currentChar == ',') {
                token = new Token(Character.valueOf(currentChar));
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
    private String getString() {

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

        if(getChar() == '.') {
            builder.append(getChar());
            nextPosition();
            builder.append(getVariableOrKeyword());

        } else if(Character.isLetterOrDigit(getChar()))
            builder.append(getVariableOrKeyword());

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
     * @return
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
