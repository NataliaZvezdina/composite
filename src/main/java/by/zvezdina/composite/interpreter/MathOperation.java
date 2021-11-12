package by.zvezdina.composite.interpreter;

import java.util.HashSet;
import java.util.Set;

public class MathOperation {

    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";
    public static final String INVERT = "~";
    public static final String AND = "&";
    public static final String OR = "|";
    public static final String XOR = "^";
    public static final String RIGHT_SHIFT = ">>";
    public static final String LEFT_SHIFT = "<<";
    public static final String OPEN_BRACE = "(";
    public static final String CLOSED_BRACE = ")";

    private static Set<String> operationSet = new HashSet<>();

    public MathOperation() {
        operationSet.add(PLUS);
        operationSet.add(MINUS);
        operationSet.add(MULTIPLY);
        operationSet.add(DIVIDE);
        operationSet.add(INVERT);
        operationSet.add(AND);
        operationSet.add(OR);
        operationSet.add(XOR);
        operationSet.add(RIGHT_SHIFT);
        operationSet.add(LEFT_SHIFT);
        operationSet.add(OPEN_BRACE);
    }

    public static Set<String> getOperationSet() {
        return operationSet;
    }
}
