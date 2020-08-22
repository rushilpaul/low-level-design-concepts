package assignment1.parser.data;

import assignment1.parser.exceptions.InvalidKeyException;
import assignment1.parser.exceptions.UnsupportedDataTypeException;

import java.util.Map;

/**
 * Extracts data out of a provided data map
 */
public class AttributeExtractor {

    public static final String KEY_SEPARATOR = "\\.";

    /**
     * @param dotSeparatedKey A (dot delimited) key (e.g. "user.location.city")
     * @return Value at the specified key, or null if no value exists
     * @throws InvalidKeyException if the dotted key is invalid (individual key parts should not be empty)
     * @throws UnsupportedDataTypeException if a data type apart from Integer, Boolean, String is encountered
     */
    public Object getPrimitiveValue(Map<String, Object> dataMap, String dotSeparatedKey) throws InvalidKeyException, UnsupportedDataTypeException {

        if(dataMap == null)
            throw new IllegalArgumentException("Data Map cannot be null");

        String keyParts[] = getKeyParts(dotSeparatedKey);
        Map currentNode = dataMap;
        StringBuilder keyString = new StringBuilder();

        for(int position = 0; position < keyParts.length; position++) {

            keyString.append(keyParts[position]);
            Object value = currentNode.get(keyParts[position]);

            if(value == null)
                return null;
            else if(isPrimitive(value))
                return value;
            else if(value instanceof Map) {
                currentNode = (Map) value;
            } else
                throw new UnsupportedDataTypeException(keyString.toString());
        }
        return null;
    }


    private boolean isPrimitive(Object object) {
        return object instanceof Integer || object instanceof Boolean || object instanceof String;
    }


    private String[] getKeyParts(String dottedKey) throws InvalidKeyException {

        String keyParts[];
        try {
            keyParts = dottedKey.split(KEY_SEPARATOR);
        } catch (Exception ex) {
            throw new InvalidKeyException(dottedKey);
        }
        for(int i = 0; i < keyParts.length; i++) {
            if(keyParts[i].length() == 0)
                throw new InvalidKeyException(dottedKey);
        }
        return keyParts;
    }
}
