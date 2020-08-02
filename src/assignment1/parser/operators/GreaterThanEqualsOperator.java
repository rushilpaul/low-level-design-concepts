package assignment1.parser.operators;

import assignment1.parser.operands.BooleanOp;
import assignment1.parser.operands.Operand;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GreaterThanEqualsOperator extends RelationalOperator {

    public GreaterThanEqualsOperator() {
    }

    public GreaterThanEqualsOperator(List<Operand> operands) {
        super(operands);
    }

    @Override
    public Operand compute() {
        GreaterThanOperator greaterThanOperator = new GreaterThanOperator(operands);
        EqualsOperator equalsOperator = new EqualsOperator(operands);
        return BooleanOp.of((Boolean) greaterThanOperator.evaluate().getValue() || (Boolean) equalsOperator.evaluate().getValue());
    }

    @Override
    protected boolean canOperate() {
        return true;    // TODO: implement
    }

    @Override
    public Set<Character> tokenStartsWith() {
        return new HashSet<>(Arrays.asList('>'));
    }
}
