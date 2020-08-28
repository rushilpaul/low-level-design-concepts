package assignment1.parser.operands;

public class BooleanOp extends Operand {

    public static BooleanOp TRUE = BooleanOp.of(true);

    public static BooleanOp FALSE = BooleanOp.of(false);

    public static BooleanOp of(Operand value) {
        if(value.getDataType() != DataType.BOOLEAN)
            throw new IllegalArgumentException("Operand is not a boolean!");
        return (BooleanOp) value;
    }

    public static BooleanOp of(String value) {
        if(!value.equals("true") && !value.equals("false"))
            throw new IllegalArgumentException("String " + value + " is not a valid boolean constant!");
        return of(Boolean.valueOf(value));
    }

    public static BooleanOp of(Boolean value) {
        BooleanOp booleanOp = new BooleanOp();
        booleanOp.setValue(value);
        return booleanOp;
    }

    public Boolean getBasicValue() {
        return (Boolean) getValue();
    }

    @Override
    public DataType getDataType() {
        return DataType.BOOLEAN;
    }
}
