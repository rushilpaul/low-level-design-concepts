package assignment1.parser.operators;

import assignment1.parser.operands.BooleanOp;
import assignment1.parser.operands.Operand;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EqualsOperator extends RelationalOperator {

    public EqualsOperator() {
    }

    public EqualsOperator(List<Operand> operands) {
        super(operands);
    }

    @Override
    public Operand compute() {
        if(!canOperate())
            return null;
        return BooleanOp.of(operands.get(0).equals(operands.get(1)));
    }

    @Override
    public Set<Character> tokenStartsWith() {
        return new HashSet<>(Arrays.asList('='));
    }

    @Override
    protected boolean canOperate() {
        return true;    // TODO: implement
    }
}
