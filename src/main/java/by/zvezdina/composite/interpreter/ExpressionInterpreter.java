package by.zvezdina.composite.interpreter;

import java.util.List;

public class ExpressionInterpreter {
    private Context context = new Context();

    public Integer handleExpression(List<MathExpression> expression) {
        for (MathExpression terminal : expression) {
            terminal.interpret(context);
        }
        return context.pop();
    }

}
