package assignment1.parser.operators.logical;

import assignment1.parser.exceptions.SyntaxException;
import assignment1.parser.operands.BooleanOp;
import assignment1.parser.operands.DataType;
import assignment1.parser.operands.Operand;

import java.util.List;

public class OrOperator extends LogicalOperator {

    public OrOperator() {
    }

    public OrOperator(List<Operand> operands) {
        super(operands);
    }

    @Override
    public Operand compute() {
        if(!canOperate())
            throw new SyntaxException(String.format("Can't operate on operands %s and %s", firstOperand(), secondOperand()));
        Boolean result = BooleanOp.of(firstOperand()).getBasicValue() || BooleanOp.of(secondOperand()).getBasicValue();
        return BooleanOp.of(result);
    }

    @Override
    protected boolean canOperate() {
        return operands.get(0).getDataType() == DataType.BOOLEAN && operands.get(1).getDataType() == DataType.BOOLEAN;
    }

    @Override
    public String stringRepresentation() {
        return "or";
    }
}
