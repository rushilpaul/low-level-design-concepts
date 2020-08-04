package assignment1;

import assignment1.parser.TokenDetectionStrategy;
import assignment1.parser.data.AttributeExtractor;
import assignment1.parser.lexical.LexicalScanner;
import assignment1.parser.evaluator.LanguageEvaluator;
import assignment1.parser.lexical.Token;
import assignment1.parser.operands.BooleanOp;
import assignment1.parser.operands.DataType;
import assignment1.parser.operands.Operand;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FeatureGate {

    public FeatureGate() {

    }

    public boolean isAllowed(String conditionalExpr, String featureName, Map<String, Object> userAttributes) {

        LexicalScanner lexicalScanner = new LexicalScanner(conditionalExpr);
        List<Token> tokenList = lexicalScanner.tokenize();
        printTokenList(tokenList);  // only for debugging purposes

        TokenDetectionStrategy tokenDetectionStrategy = new TokenDetectionStrategy();
        AttributeExtractor attributeExtractor = new AttributeExtractor(userAttributes);
        LanguageEvaluator parser = new LanguageEvaluator(tokenList, tokenDetectionStrategy, attributeExtractor);

        Operand result;
        try {
            result = parser.evaluate();
        } catch (Exception ex) {
            System.err.println("Evaluation error: " + ex.getMessage());
            return false;
        }
        if(result.getDataType() != DataType.BOOLEAN)
            System.out.println("WARN: Evaluation result is of type " + result.getDataType());
        else
            System.out.println("Evaluation result: " + result);

        boolean isFeatureAllowed = result.getDataType() == DataType.BOOLEAN && ((BooleanOp) result).getBasicValue();
        return isFeatureAllowed;
    }

    private void printTokenList(List<Token> tokens) {

        String printableList = "[" + String.join(" ", tokens.stream().map(token -> token.toString()).collect(Collectors.toList())) + "]";
        System.out.println(printableList);
    }
}
