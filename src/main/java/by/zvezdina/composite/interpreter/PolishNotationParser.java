package by.zvezdina.composite.interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PolishNotationParser {
    public static final String SPACE_FOR_SPLIT = " ";

    public List<MathExpression> parse(String polishNotation) {
        List<MathExpression> expression = new ArrayList<>();
        Arrays.asList(polishNotation.split(SPACE_FOR_SPLIT)).forEach(token -> {
            if (!token.isBlank()) {
                switch (token) {
                    case MathOperation.PLUS -> expression.add(c -> c.push(c.pop() + c.pop()));
                    case MathOperation.MINUS -> expression.add(c -> c.push(c.pop() - c.pop()));
                    case MathOperation.INVERT -> expression.add(c -> c.push(~c.pop()));
                    case MathOperation.OR -> expression.add(c -> c.push(c.pop() | c.pop()));
                    case MathOperation.AND -> expression.add(c -> c.push(c.pop() & c.pop()));
                    case MathOperation.XOR -> expression.add(c -> c.push(c.pop() ^ c.pop()));
                    case MathOperation.RIGHT_SHIFT -> expression.add(c -> c.push(c.pop() >> c.pop()));
                    case MathOperation.LEFT_SHIFT -> expression.add(c -> c.push(c.pop() << c.pop()));
                    case MathOperation.MULTIPLY -> expression.add(c -> c.push(c.pop() * c.pop()));
                    case MathOperation.DIVIDE -> expression.add(c -> c.push(c.pop() / c.pop()));
                    default -> expression.add(c -> c.push(Integer.parseInt(token)));
                }
            }
        });
        return expression;
    }
}
