package assignment1;

import assignment1.parser.TokenDetectionStrategy;
import assignment1.parser.data.AttributeExtractor;
import assignment1.parser.lexical.LexicalScanner;
import assignment1.parser.LanguageParser;
import assignment1.parser.lexical.Token;
import assignment1.parser.operands.BooleanOp;
import assignment1.parser.operands.DataType;
import assignment1.parser.operands.Operand;

import java.util.List;
import java.util.Map;

public class FeatureGate {

    public FeatureGate() {

    }

    public boolean isAllowed(String conditionalExpr, String featureName, Map<String, Object> userAttributes) throws Exception {

        LexicalScanner lexicalScanner = new LexicalScanner(conditionalExpr);
        List<Token> tokenList = lexicalScanner.tokenize();
        printTokenList(tokenList);  // only for debug statement

        TokenDetectionStrategy tokenDetectionStrategy = new TokenDetectionStrategy();

        AttributeExtractor attributeExtractor = new AttributeExtractor(userAttributes);

        LanguageParser parser = new LanguageParser(tokenList, tokenDetectionStrategy, attributeExtractor);

        Operand result = parser.generate();
        System.out.println("Result: " + result);

        boolean isFeatureAllowed = result.getDataType() == DataType.BOOLEAN && ((BooleanOp) result).getBasicValue();
        return isFeatureAllowed;
    }

    private void printTokenList(List<Token> tokens) {
        for(Object token : tokens) {
            System.out.print(token + " ");
        }
        System.out.println();
    }
}
