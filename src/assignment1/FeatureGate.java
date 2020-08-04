package assignment1;

import assignment1.parser.lexical.LexicalScanner;
import assignment1.parser.lexical.ParseTreeGenerator;
import assignment1.parser.lexical.Token;

import java.util.List;
import java.util.Map;

public class FeatureGate {

    public boolean isAllowed(String conditionalExpr, String featureName, Map<String, Object> userAttributes) throws Exception {

        LexicalScanner lexicalScanner = new LexicalScanner(conditionalExpr);
        List<Token> tokenList = lexicalScanner.tokenize();
        for(Object token : tokenList) {
            System.out.print(token + " ");
        }
        System.out.println();
        ParseTreeGenerator treeGenerator = new ParseTreeGenerator(tokenList);
        System.out.println("Result: " + treeGenerator.generate());

        return false;
    }
}
