package assignment1;

import assignment1.parser.LexicalScanner;
import assignment1.parser.Token;

import java.util.List;
import java.util.Map;

public class FeatureGate {

    public boolean isAllowed(String conditionalExpr, String featureName, Map<String, Object> userAttributes) throws Exception {

        LexicalScanner lexicalScanner = new LexicalScanner(conditionalExpr);
        List<Token> tokenList = lexicalScanner.tokenize();
        for(Object token : tokenList) {
            System.out.println(token);
        }
        return false;
    }
}
