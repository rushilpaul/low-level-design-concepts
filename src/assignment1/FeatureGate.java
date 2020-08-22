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

    private LexicalScanner lexicalScanner;
    private TokenDetectionStrategy tokenDetectionStrategy;

    private FeatureGate() { }

    public static FeatureGate basicFeatureGate() {
        FeatureGate featureGate = new FeatureGate();
        featureGate.setLexicalScanner(new LexicalScanner());
        featureGate.setTokenDetectionStrategy(new TokenDetectionStrategy());
        return featureGate;
    }

    public boolean isAllowed(String conditionalExpr, String featureName, Map<String, Object> userAttributes) {

        List<Token> tokenList = lexicalScanner.tokenize(conditionalExpr);
        printTokenList(tokenList);  // only for debugging purposes

        LanguageEvaluator parser = new LanguageEvaluator(tokenList, tokenDetectionStrategy, userAttributes);

        Operand result;
        try {
            result = parser.evaluate();
        } catch (Exception ex) {
            System.err.println("[ERROR] Evaluation error: " + ex.getMessage());
            return false;
        }
        if(result.getDataType() != DataType.BOOLEAN)
            System.out.println("[WARN] Evaluation result is of type " + result.getDataType());
        else
            System.out.println("[DEBUG] Evaluation result: " + result);

        boolean isFeatureAllowed = result.getDataType() == DataType.BOOLEAN && ((BooleanOp) result).getBasicValue();
        return isFeatureAllowed;
    }

    private void printTokenList(List<Token> tokens) {

        String printableList = "[" + String.join(" ", tokens.stream().map(token -> token.toString()).collect(Collectors.toList())) + "]";
        System.out.println("[DEBUG] Token list generated: " + printableList);
    }

    public void setLexicalScanner(LexicalScanner lexicalScanner) {
        this.lexicalScanner = lexicalScanner;
    }

    public void setTokenDetectionStrategy(TokenDetectionStrategy tokenDetectionStrategy) {
        this.tokenDetectionStrategy = tokenDetectionStrategy;
    }
}
