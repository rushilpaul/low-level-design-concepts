package assignment1.parser.operands;

import assignment1.parser.DataType;

public class IntegerOp extends Operand {

    public IntegerOp() {
    }

    public IntegerOp(Integer value) {
        super(value);
    }

    public static IntegerOp of(Integer value) {
        return new IntegerOp(value);
    }

    @Override
    public DataType getDataType() {
        return DataType.INTEGER;
    }
}
