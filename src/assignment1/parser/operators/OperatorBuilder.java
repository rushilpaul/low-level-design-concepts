package assignment1.parser.operators;

import assignment1.parser.exceptions.SyntaxException;
import assignment1.parser.lexical.Token;
import assignment1.parser.operators.logical.AndOperator;
import assignment1.parser.operators.logical.OrOperator;
import assignment1.parser.operators.relational.*;

public class OperatorBuilder {

    public static Operator createOperator(Token token) {

        String value = token.toString();
        if(value.equals("<"))
            return new LessThanOperator();

        else if(value.equals("<="))
            return new LessThanEqualsOperator();

        else if(value.equals(">"))
            return new GreaterThanOperator();

        else if(value.equals(">="))
            return new GreaterThanEqualsOperator();

        else if(value.equals("=="))
            return new EqualsOperator();

        else if(value.equals("and")) {
            return new AndOperator();
        }
        else if(value.equals("or")) {
            return new OrOperator();
        }

        throw new SyntaxException("Unsupported operator " + token.getValue());
    }
}
