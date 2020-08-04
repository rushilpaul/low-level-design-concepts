package assignment1.parser.lexical;

import assignment1.parser.TokenDetectionStrategy;
import assignment1.parser.exceptions.SyntaxException;
import assignment1.parser.operands.BooleanOp;
import assignment1.parser.operands.Expression;
import assignment1.parser.operands.IntegerOp;
import assignment1.parser.operands.Operand;
import assignment1.parser.operators.Operator;
import assignment1.parser.operators.OperatorBuilder;
import assignment1.parser.operators.logical.LogicalOperator;
import assignment1.parser.operators.relational.RelationalOperator;

import java.util.List;

public class ParseTreeGenerator {

    private int currentPos = 0;
    private List<Token> tokens;

    private TokenDetectionStrategy tokenDetectionStrategy = new TokenDetectionStrategy();

    public ParseTreeGenerator(List<Token> tokens) {
        this.tokens = tokens;
    }

    /**
     * Generates a parse tree using the given set of tokens
     * @throws SyntaxException for any syntax errors
     */
    public Operand generate() throws SyntaxException {
        return expression();
    }

    public Operand expression() {
        Operand operand = compositeBooleanExpr();
        if(getToken().tokenType() != TokenType.END_OF_INPUT)
            throw new SyntaxException("Unexpected end of input", currentPos);
        return operand;
    }

    public Operand compositeBooleanExpr() {

        Operand leftPart = simpleBooleanExpr();

        Token lookAhead = getToken();
        if(lookAhead.tokenType() == TokenType.WORD) {  // logical operator expected

            nextPosition();
            Operator operator = OperatorBuilder.createOperator(lookAhead);
            if(!(operator instanceof LogicalOperator))
                throw new SyntaxException("Logical operator expected", currentPos);
            Operand rightPart = compositeBooleanExpr();
            operator.addOperand(leftPart);
            operator.addOperand(rightPart);
            return operator.evaluate();
        }
        return leftPart;
    }

    /**
     * TODO: complete this method
     * @return
     */
    public Operand simpleBooleanExpr() {

        Token lookAhead = getToken();

        if(lookAhead.tokenType() == TokenType.PARENTHESIS_OPEN) {

            nextPosition();
            Operand middlePart = compositeBooleanExpr();

            Token closingBracket = getTokenAndNext();
            if(closingBracket.tokenType() != TokenType.PARENTHESIS_CLOSE)
                throw new SyntaxException("[ParseTreeGenerator] Closing bracket expected", currentPos);

            return middlePart;

        } else if(lookAhead.tokenType() == TokenType.WORD) {

            Operand leftPart = term();

            Token nextToken = getToken();
            if(tokenDetectionStrategy.isRelationalOperator(nextToken)) {
                RelationalOperator relationalOperator = relationalOperator();
                relationalOperator.addOperand(leftPart);
                relationalOperator.addOperand(compositeBooleanExpr());

                return relationalOperator.evaluate();
            }
            return leftPart;

        } else
            throw new SyntaxException("Unsupported shit encountered", currentPos);  // TODO: Implement the other operators like BETWEEN
    }

    public Operand term() {
        Token token = getTokenAndNext();
        if(tokenDetectionStrategy.isInteger(token))
            return IntegerOp.of(token.toString());

        else if(tokenDetectionStrategy.isBooleanConst(token))
            return BooleanOp.of(token.toString());

//        if(tokenDetectionStrategy.isVariable(token))
//            return token;   // TODO: fetch the variable's value
        throw new SyntaxException("Expected a term", currentPos);
    }

    private RelationalOperator relationalOperator() {
        Token token = getTokenAndNext();
        if(!tokenDetectionStrategy.isRelationalOperator(token))
            throw new SyntaxException("Relational operator expected instead of " + token, currentPos);
        return (RelationalOperator) OperatorBuilder.createOperator(token);
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
