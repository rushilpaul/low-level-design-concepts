package assignment1.parser.operands;

public class StringOp extends Operand {

    public static StringOp of(String value) {
        StringOp stringOp = new StringOp();
        stringOp.setValue(value);
        return stringOp;
    }

    public String getBasicValue() {
        return (String) getValue();
    }

    @Override
    public DataType getDataType() {
        return DataType.STRING;
    }
}
