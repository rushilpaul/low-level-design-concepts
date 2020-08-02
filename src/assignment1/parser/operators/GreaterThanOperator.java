package assignment1.parser.operators;

import assignment1.parser.operands.BooleanOp;
import assignment1.parser.operands.Operand;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GreaterThanOperator extends RelationalOperator {

    public GreaterThanOperator() {
    }

    public GreaterThanOperator(List<Operand> operands) {
        super(operands);
    }

    @Override
    public Operand compute() {

        boolean result = (Float) operands.get(0).getValue() > (Float) operands.get(1).getValue();
        return BooleanOp.of(result);
    }

    @Override
    protected boolean canOperate() {
        return true;    // TODO: implement this
    }

    public Set<Character> tokenStartsWith() {
        return new HashSet<>(Arrays.asList('>'));
    }
}
