package assignment1;

import java.util.HashMap;
import java.util.Map;

public class Client {

    public static void main(String args[]) {

        FeatureGate featureGate = new FeatureGate();

        String condition = "( (between (age, minimumAge, max_age) and country.name == \"India\") and isGovernmentEmployee == true) or country.population >= 100";
        Map<String, Object> attributeMap = new HashMap<>();
        fillMap(attributeMap);

        if(featureGate.isAllowed(condition, "some feature", attributeMap))
            System.out.println("Feature is allowed");
        else
            System.out.println("Feature is not allowed");
    }

    private static void fillMap(Map<String, Object> dataMap) {

        dataMap.put("age", 25);
        dataMap.put("minimumAge", 40);
        dataMap.put("max_age", 60);
        dataMap.put("isGovernmentEmployee", true);
        Map<String, Object> locationMap = new HashMap<>();
        locationMap.put("name", "India");
        locationMap.put("population", 10);
        dataMap.put("country", locationMap);
    }
}
