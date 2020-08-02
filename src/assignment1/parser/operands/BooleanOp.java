package assignment1.parser.operands;

import assignment1.parser.DataType;

public class BooleanOp extends Operand {

    public BooleanOp() {
    }

    public BooleanOp(Boolean value) {
        super(value);
    }

    public static BooleanOp of(boolean value) {
        return new BooleanOp(Boolean.valueOf(value));
    }

    public static BooleanOp TRUE() {
        return new BooleanOp(Boolean.TRUE);
    }

    public static BooleanOp FALSE() {
        return new BooleanOp(Boolean.FALSE);
    }

    @Override
    public DataType getDataType() {
        return DataType.BOOLEAN;
    }

}
