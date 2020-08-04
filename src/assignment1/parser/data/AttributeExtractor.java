package assignment1.parser.data;

import assignment1.parser.exceptions.InvalidKeyException;
import assignment1.parser.exceptions.UnsupportedDataTypeException;

import java.util.HashMap;
import java.util.Map;

/**
 * Extracts data out of a provided data map
 */
public class AttributeExtractor {

    private Map<String, Object> dataMap;
    public static final String KEY_SEPARATOR = ".";

    public AttributeExtractor(Map<String, Object> dataMap) {
        this.dataMap = new HashMap<>(dataMap);
    }

    /**
     * @param dottedKey A dot separated key (like "user.location.city")
     * @return Value at the specified key, or null if no value exists
     * @throws InvalidKeyException if the dotted key is invalid (individual key parts should not be empty)
     * @throws UnsupportedDataTypeException if a data type apart from Integer, Boolean, String is encountered
     */
    public Object getPrimitiveValue(String dottedKey) throws InvalidKeyException, UnsupportedDataTypeException {

        String keyParts[] = getKeyParts(dottedKey);
        Map currentNode = dataMap;
        StringBuilder keyString = new StringBuilder();

        for(int position = 0; position < keyParts.length; position++) {

            keyString.append(keyParts[position]);
            Object value = currentNode.get(keyParts[position]);

            if(isPrimitive(value))
                return value;
            else if(value instanceof Map) {
                currentNode = (Map) value;
            } else
                throw new UnsupportedDataTypeException(keyString.toString());
        }
        throw new InvalidKeyException(dottedKey);
    }


    private boolean isPrimitive(Object object) {
        return object instanceof Integer || object instanceof Boolean || object instanceof String;
    }


    private String[] getKeyParts(String dottedKey) throws InvalidKeyException {

        String keyParts[] = null;
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
