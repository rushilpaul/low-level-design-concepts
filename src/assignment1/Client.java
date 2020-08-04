package assignment1;

import java.util.HashMap;
import java.util.Map;

public class Client {

    public static void main(String args[]) throws Exception {

        FeatureGate featureGate = new FeatureGate();

//        String condition = "(age > true and location.country == \"India\") or (true == true)";
        String condition = "age === true";
        Map<String, Object> attributeMap = new HashMap<>();
        fillMap(attributeMap);

        if(featureGate.isAllowed(condition, "some feature", attributeMap))
            System.out.println("Feature is allowed");
        else
            System.out.println("Feature is not allowed");
//        featureGate.isAllowed("(a1.b != \"b\" and age > 10)", "", null);
    }

    private static void fillMap(Map<String, Object> dataMap) {

        dataMap.put("age", 20);
        dataMap.put("isRetarded", true);
        Map<String, Object> locationMap = new HashMap<>();
        locationMap.put("country", "India");
        dataMap.put("location", locationMap);
    }
}
