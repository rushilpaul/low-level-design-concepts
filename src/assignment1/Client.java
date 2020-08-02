package assignment1;

import java.time.Instant;
import java.time.ZoneOffset;

public class Client {

    public static void main(String args[]) throws Exception {

        FeatureGate featureGate = new FeatureGate();
        featureGate.isAllowed("(a1.b != \"b\" and age > 10)", "", null);
    }
}
