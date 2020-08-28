package assignment1.parser.operators.relational;

import assignment1.parser.operands.BooleanOp;
import assignment1.parser.operands.Operand;

import java.util.List;

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
        return BooleanOp.of(firstOperand().equals(secondOperand()));
    }

    @Override
    public String stringRepresentation() {
        return "==";
    }
}
