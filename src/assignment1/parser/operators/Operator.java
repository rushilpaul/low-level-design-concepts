package assignment1.parser.operators;

import assignment1.parser.exceptions.EvaluationException;
import assignment1.parser.exceptions.SyntaxException;
import assignment1.parser.operands.Expression;
import assignment1.parser.operands.Operand;

import java.util.*;

public abstract class Operator implements Expression {

    protected List<Operand> operands;

    public Operator() {
        operands = new ArrayList<>();
    }

    public Operator(List<Operand> operands) {
        this.operands = new ArrayList<>(operands);
    }

    public void addOperand(Operand operand) {
        this.operands.add(operand);
    }

    public Operand evaluate() {

        checkNumberOfOperands();
        if(!canOperate()) {
            throw new EvaluationException(String.format("Can't apply operator '%s' on the operands: %s", stringRepresentation(), operands));
        }
        return compute();
    }

    abstract protected Operand compute();

    /**
     * @return true if this operator can operate on the operands provided, false otherwise
     */
    abstract protected boolean canOperate();

    /**
     * Abort if number of operands are not as expected
     */
    abstract protected void checkNumberOfOperands();

    abstract public String stringRepresentation();
}
