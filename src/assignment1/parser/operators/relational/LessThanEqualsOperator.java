package assignment1.parser.operators.relational;

import assignment1.parser.operands.BooleanOp;
import assignment1.parser.operands.Operand;

import java.util.List;

public class LessThanEqualsOperator extends RelationalOperator {

    public LessThanEqualsOperator() {
    }

    public LessThanEqualsOperator(List<Operand> operands) {
        super(operands);
    }

    @Override
    public Operand compute() {
        LessThanOperator lessThanOperator = new LessThanOperator(operands);
        EqualsOperator equalsOperator = new EqualsOperator(operands);
        return BooleanOp.of((Boolean) lessThanOperator.evaluate().getValue() || (Boolean) equalsOperator.evaluate().getValue());
    }

    @Override
    public String stringRepresentation() {
        return "<=";
    }
}
