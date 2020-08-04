package assignment1.parser.evaluator;

import assignment1.parser.TokenDetectionStrategy;
import assignment1.parser.data.AttributeExtractor;
import assignment1.parser.exceptions.InvalidKeyException;
import assignment1.parser.exceptions.SyntaxException;
import assignment1.parser.exceptions.UnsupportedDataTypeException;
import assignment1.parser.lexical.Token;
import assignment1.parser.lexical.TokenType;
import assignment1.parser.operands.*;
import assignment1.parser.operators.OperatorBuilder;
import assignment1.parser.operators.logical.LogicalOperator;
import assignment1.parser.operators.relational.RelationalOperator;

import java.util.List;

public class LanguageEvaluator {

    private int currentPos = 0;
    private List<Token> tokens;

    private TokenDetectionStrategy tokenDetectionStrategy;
    private AttributeExtractor attributeExtractor;

    public LanguageEvaluator(List<Token> tokens, TokenDetectionStrategy tokenDetectionStrategy, AttributeExtractor attributeExtractor) {
        this.tokens = tokens;
        this.tokenDetectionStrategy = tokenDetectionStrategy;
        this.attributeExtractor = attributeExtractor;
    }

    /**
     * Generates a parse tree using the given set of tokens
     * @throws SyntaxException for any syntax errors
     */
    public Operand evaluate() throws SyntaxException, InvalidKeyException, UnsupportedDataTypeException {
        return expression();
    }

    public Operand expression() throws InvalidKeyException, UnsupportedDataTypeException {

        Operand operand = compositeBooleanExpr();
        if(getToken().tokenType() != TokenType.END_OF_INPUT)
            throw new SyntaxException(String.format("Unexpected token '%s'", getToken()), currentPos);
        return operand;
    }

    public Operand compositeBooleanExpr() throws InvalidKeyException, UnsupportedDataTypeException {

        Operand result = simpleBooleanExpr();

        while(tokenDetectionStrategy.isLogicalOperator(getToken())) {
            LogicalOperator operator = logicalOperator();
            Operand nextBooleanExpr = simpleBooleanExpr();

            operator.addOperand(result);
            operator.addOperand(nextBooleanExpr);
            result = operator.evaluate();
        }
        return result;
    }

    public Operand simpleBooleanExpr() throws InvalidKeyException, UnsupportedDataTypeException {

        Operand leftTerm = term();
        if(tokenDetectionStrategy.isRelationalOperator(getToken())) {

            RelationalOperator relationalOperator = relationalOperator();
            Operand rightTerm = term();
            relationalOperator.addOperand(leftTerm);
            relationalOperator.addOperand(rightTerm);
            return relationalOperator.evaluate();
        }
        return leftTerm;
    }

    public Operand term() throws InvalidKeyException, UnsupportedDataTypeException {

        Token lookAhead = getToken();
        if(tokenDetectionStrategy.isInteger(lookAhead))
            return IntegerOp.of(getTokenAndNext().toString());

        else if(tokenDetectionStrategy.isBooleanConst(lookAhead))
            return BooleanOp.of(getTokenAndNext().toString());

        else if(tokenDetectionStrategy.isVariable(lookAhead))
            return new UserVariableOp(attributeExtractor, (String) getTokenAndNext().getValue());

        else if(lookAhead.tokenType() == TokenType.STRING_CONST) {
            return StringOp.of(getTokenAndNext().toString());
        }

        else if(lookAhead.tokenType() == TokenType.PARENTHESIS_OPEN) {

            nextPosition(); // consume the bracket open
            Operand middleExpression = compositeBooleanExpr();
            Token closingBracket = getTokenAndNext();

            if(closingBracket.tokenType() != TokenType.PARENTHESIS_CLOSE)
                throw new SyntaxException("Expected a closing bracket", currentPos);

            return middleExpression;

        } else if(tokenDetectionStrategy.isBetweenOperator(lookAhead)) {
            throw new SyntaxException("Not implemented between yet");

        }
        throw new SyntaxException("Expected a variable, boolean, integer or string", currentPos);
    }

    private RelationalOperator relationalOperator() {
        if(!tokenDetectionStrategy.isRelationalOperator(getToken()))
            throw new SyntaxException("Relational operator expected instead of " + getToken(), currentPos);
        return (RelationalOperator) OperatorBuilder.createOperator(getTokenAndNext());
    }

    private LogicalOperator logicalOperator() {
        if(!tokenDetectionStrategy.isLogicalOperator(getToken()))
            throw new SyntaxException("Logical operator expected instead of " + getToken(), currentPos);
        return (LogicalOperator) OperatorBuilder.createOperator(getTokenAndNext());
    }

    private IntegerOp integerOp() {
        Token token = getTokenAndNext();
        if(!tokenDetectionStrategy.isInteger(token))
            throw new SyntaxException("Integer expected instead of " + token, currentPos);
        return IntegerOp.of(token.toString());
    }

    private BooleanOp booleanConst() {
        return BooleanOp.of(getTokenAndNext().toString());
    }

    private Token getTokenAndNext() {
        return tokens.get(currentPos++);
    }

    private Token getToken() {
        return tokens.get(currentPos);
    }

    private void nextPosition() {
        currentPos++;
    }
}
