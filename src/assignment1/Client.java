package assignment1;

public class Client {

    public static void main(String args[]) throws Exception {

        FeatureGate featureGate = new FeatureGate();
        featureGate.isAllowed("(true == false or false == \"false\") and false == true", "", null);
//        featureGate.isAllowed("(a1.b != \"b\" and age > 10)", "", null);
    }
}
