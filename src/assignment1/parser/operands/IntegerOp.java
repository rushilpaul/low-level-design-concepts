package assignment1.parser.operands;

public class IntegerOp extends Operand {

    public IntegerOp() {
    }

    public static IntegerOp of(Integer value) {
        IntegerOp integerOp = new IntegerOp();
        integerOp.setValue(value);
        return integerOp;
    }

    public static IntegerOp of(Operand value) {
        if(value.getDataType() != DataType.INTEGER)
            throw new IllegalArgumentException("Operand was not of type Integer");
        return (IntegerOp) value;
    }

    public static IntegerOp of(String value) {
        return IntegerOp.of(Integer.parseInt(value));
    }

    @Override
    public DataType getDataType() {
        return DataType.INTEGER;
    }
}
