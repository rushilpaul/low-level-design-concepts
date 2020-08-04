package assignment1.parser.operands;

import assignment1.parser.data.AttributeExtractor;
import assignment1.parser.exceptions.EvaluationException;
import assignment1.parser.exceptions.InvalidKeyException;
import assignment1.parser.exceptions.UnsupportedDataTypeException;

public class UserVariableOp extends Operand {

    private DataType dataType;

    public UserVariableOp(AttributeExtractor attributeExtractor, String attributeKey) throws InvalidKeyException, UnsupportedDataTypeException {

        Object value = attributeExtractor.getPrimitiveValue(attributeKey);
        if(value == null) {
            throw new EvaluationException("Variable " + attributeKey + " not found in the Data Map");
        }
        if(value instanceof Integer)
            dataType = DataType.INTEGER;
        else if(value instanceof Boolean)
            dataType = DataType.BOOLEAN;
        else if(value instanceof String)
            dataType = DataType.STRING;

        setValue(value);
    }

    @Override
    public DataType getDataType() {
        return dataType;
    }
}
