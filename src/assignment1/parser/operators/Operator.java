package assignment1.parser.operators;

import assignment1.parser.operands.Operand;

import java.util.*;

public abstract class Operator {

    protected List<Operand> operands;

    public Operator() {
        operands = new ArrayList<>();
    }

    public Operator(List<Operand> operands) {
        operands = new ArrayList<>(operands);
    }

    public void addOperand(Operand operand) {
        this.operands.add(operand);
    }

    public Operand evaluate() {

        checkNumberOfOperands();
        if(!canOperate()) {
            System.out.println("Can't operate on the operands: " + operands);
            return null;
        }
        return compute();
    }

    abstract protected Operand compute();

    abstract public Set<Character> tokenStartsWith();

    abstract protected boolean canOperate();

    /**
     * Abort if number of operands are not as expected
     */
    abstract protected void checkNumberOfOperands();
}
