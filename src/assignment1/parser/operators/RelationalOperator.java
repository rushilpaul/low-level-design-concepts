package assignment1.parser.operators;

import assignment1.parser.operands.Operand;

import java.util.List;

public abstract class RelationalOperator extends Operator {

    public RelationalOperator() {
    }

    public RelationalOperator(List<Operand> operands) {
        super(operands);
    }

    @Override
    protected void checkNumberOfOperands() {
        if(operands.size() != 2) {
            throw new RuntimeException("Incorrect number of operands for " + getClass().getName());
        }
    }
}
