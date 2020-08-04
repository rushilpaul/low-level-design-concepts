package assignment1.parser.operators.relational;

import assignment1.parser.operands.DataType;
import assignment1.parser.operands.Operand;
import assignment1.parser.operators.Operator;

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

    protected Operand firstOperand() {
        return operands.get(0);
    }

    protected Operand secondOperand() {
        return operands.get(1);
    }

    @Override
    protected boolean canOperate() {
        return firstOperand().getDataType() == secondOperand().getDataType();
    }
}
