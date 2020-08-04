package assignment1.parser.operators.misc;

import assignment1.parser.exceptions.SyntaxException;
import assignment1.parser.operands.*;
import assignment1.parser.operators.Operator;

import java.util.List;

public class BetweenOperator extends Operator {

    public BetweenOperator() {
    }

    public BetweenOperator(List<Operand> operands) {
        super(operands);
    }

    @Override
    public Operand compute() {
        if(!canOperate())
            return null;

        Operand primaryOp = operands.get(0);
        Operand startOp = operands.get(1);
        Operand endOp = operands.get(2);
        // Check if x >= a and x <= b
        int primaryOpValue = (Integer) primaryOp.getValue();
        return BooleanOp.of(primaryOpValue >= ((Integer) startOp.getValue()) && primaryOpValue <= ((Integer) endOp.getValue()));
    }

    @Override
    protected boolean canOperate() {
        return operands.get(0).getDataType() == DataType.INTEGER &&
                operands.get(1).getDataType() == DataType.INTEGER &&
                operands.get(1).getDataType() == DataType.INTEGER;
    }

    @Override
    public String stringRepresentation() {
        return "between";
    }

    @Override
    protected void checkNumberOfOperands() {
        if(operands.size() != 3) {
            throw new SyntaxException("Incorrect number of operands for " + getClass().getName());
        }
    }
}
