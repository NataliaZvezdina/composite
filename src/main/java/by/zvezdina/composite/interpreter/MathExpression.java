package by.zvezdina.composite.interpreter;

@FunctionalInterface
public interface MathExpression {

    void interpret(Context context);
}
