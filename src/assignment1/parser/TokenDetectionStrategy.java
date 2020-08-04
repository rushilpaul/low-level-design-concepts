package assignment1.parser;

import assignment1.parser.lexical.Token;
import assignment1.parser.lexical.TokenType;
import assignment1.parser.operators.Operator;
import assignment1.parser.operators.logical.AndOperator;
import assignment1.parser.operators.logical.OrOperator;
import assignment1.parser.operators.misc.BetweenOperator;
import assignment1.parser.operators.relational.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TokenDetectionStrategy {

    /**
     * This stores the string representations of all relational operators
     */
    private Set<Character> relationalOperatorsStringForm;

    /**
     * This stores the string representations of all logical operators
     */
    private Set<Character> logicalOperatorsStringForm;

    private BetweenOperator betweenOperator = new BetweenOperator();

    public TokenDetectionStrategy() {
        // Should either use better design or do this via reflection, otherwise adding new operators can be a pain
        Set<Operator> allRelationalOperators = new HashSet<>();
        allRelationalOperators.add(new EqualsOperator());
        allRelationalOperators.add(new LessThanOperator());
        allRelationalOperators.add(new GreaterThanOperator());
        allRelationalOperators.add(new GreaterThanEqualsOperator());

        Set<Operator> allLogicalOperators = new HashSet<>();
        allLogicalOperators.add(new AndOperator());
        allLogicalOperators.add(new OrOperator());

        relationalOperatorsStringForm = new HashSet(getStringRepresentations(allRelationalOperators));
        logicalOperatorsStringForm = new HashSet(getStringRepresentations(allLogicalOperators));
    }

    public boolean isBooleanConst(Token token) {

        if(token.tokenType() == TokenType.WORD) {
             String value = token.toString();
             return value.equals("true") || value.equals("false");
        }
        return false;
    }

    public boolean isInteger(Token token) {

        if(token.tokenType() == TokenType.NUMBER) {
            try {
                Integer.parseInt(token.toString()); // this check is needed if we support floating point numbers in future
                return true;
            } catch (NumberFormatException ex) {
                return false;
            }
        }
        return false;
    }

    public boolean isVariable(Token token) {
        return token.tokenType() == TokenType.WORD && !logicalOperatorsStringForm.contains(token) && !isBooleanConst(token);
    }

    public boolean isBetweenOperator(Token token) {
        return token.tokenType() == TokenType.WORD && token.toString().equals(betweenOperator.stringRepresentation());
    }

    public boolean isRelationalOperator(Token token) {
        return relationalOperatorsStringForm.contains(token.toString());
    }

    public boolean isLogicalOperator(Token token) {
        return logicalOperatorsStringForm.contains(token.toString());
    }

    private Set<String> getStringRepresentations(Set<Operator> operators) {
        return operators.stream()
                .map(Operator::stringRepresentation)
                .collect(Collectors.toSet());
    }
}
