package assignment1.parser.operators.logical;

import assignment1.parser.exceptions.SyntaxException;
import assignment1.parser.operands.DataType;
import assignment1.parser.operands.Operand;
import assignment1.parser.operators.Operator;

import java.util.List;

public abstract class LogicalOperator extends Operator {

    public LogicalOperator() {
    }

    public LogicalOperator(List<Operand> operands) {
        super(operands);
    }

    @Override
    protected void checkNumberOfOperands() {
        if(operands.size() != 2) {
            throw new SyntaxException("Incorrect number of operands for " + getClass().getName());
        }
    }

    @Override
    protected boolean canOperate() {
        return operands.get(0).getDataType() == DataType.BOOLEAN && operands.get(1).getDataType() == DataType.BOOLEAN;
    }

    protected Operand firstOperand() {
        return operands.get(0);
    }

    protected Operand secondOperand() {
        return operands.get(1);
    }
}
