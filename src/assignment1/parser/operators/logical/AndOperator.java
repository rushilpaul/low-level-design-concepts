package assignment1.parser.operators.logical;

import assignment1.parser.exceptions.SyntaxException;
import assignment1.parser.operands.BooleanOp;
import assignment1.parser.operands.DataType;
import assignment1.parser.operands.Operand;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AndOperator extends LogicalOperator {

    public AndOperator() {
    }

    public AndOperator(List<Operand> operands) {
        super(operands);
    }

    @Override
    public Operand compute() {
        if(!canOperate())
            throw new SyntaxException(String.format("Can't operate on operands %s and %s", firstOperand(), secondOperand()));
        Boolean result = BooleanOp.of(firstOperand()).getBasicValue() && BooleanOp.of(secondOperand()).getBasicValue();
        return BooleanOp.of(result);
    }

    @Override
    public String stringRepresentation() {
        return "and";
    }
}
