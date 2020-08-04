package assignment1.parser.operators.relational;

import assignment1.parser.operands.BooleanOp;
import assignment1.parser.operands.IntegerOp;
import assignment1.parser.operands.Operand;

import java.util.List;

public class GreaterThanOperator extends RelationalOperator {

    public GreaterThanOperator() {
    }

    public GreaterThanOperator(List<Operand> operands) {
        super(operands);
    }

    @Override
    public Operand compute() {

        boolean result = (Integer) operands.get(0).getValue() > (Integer) operands.get(1).getValue();
        return BooleanOp.of(result);
    }

    @Override
    protected boolean canOperate() {
        return true;    // TODO: implement this
    }

    @Override
    public String stringRepresentation() {
        return ">";
    }
}
