package by.zvezdina.composite.interpreter;

import by.zvezdina.composite.exception.TextHandlingException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ExpressionHandler {

    private static final String RIGHT_ANGLE_BRACE = ">";
    private static final String LEFT_ANGLE_BRACE = "<";
    private static final String SINGLE_SPACE = " ";

//    public String parseMathExpression(String expression) {
//        return
//    }

    public String convertToPolishNotation(String expression) throws TextHandlingException {
        StringBuilder polishNotation = new StringBuilder();

        Deque<String> queue = new ArrayDeque<>();
        String[] allSymbols = splitExpression(expression);
        boolean isInverted = false;

        for (String symbol : allSymbols) {
            if (!symbol.isBlank()) {
                if (Character.isDigit(symbol.charAt(0))) {
                    polishNotation.append(symbol).append(SINGLE_SPACE);
                    if (isInverted) {
                        polishNotation.append(MathOperation.INVERT).append(SINGLE_SPACE);
                        isInverted = false;
                    }
                } else if (MathOperation.INVERT.contentEquals(symbol)) {
                    isInverted = true;
                } else if (MathOperation.getOperationSet().contains(symbol)) {
                    queue.add(symbol);
                } else if (MathOperation.CLOSED_BRACE.contentEquals(symbol)) {
                    String queueSymbol = queue.pollLast();
                    while (!MathOperation.OPEN_BRACE.contentEquals(queueSymbol)) {
                        polishNotation.append(queueSymbol).append(SINGLE_SPACE);
                        queueSymbol = queue.pollLast();
                    }
                } else {
                    throw new TextHandlingException("Incorrect expression: " + expression);
                }
            }
        }

        String queueSymbol = queue.pollLast();
        while (queueSymbol != null) {
            polishNotation.append(queueSymbol).append(SINGLE_SPACE);
            queueSymbol = queue.pollLast();
        }

        return polishNotation.toString();
    }

    public String[] splitExpression(String expression) {
        List<String> strings = new ArrayList<>();
        StringBuilder digitBuilder = new StringBuilder();
        StringBuilder symbolBuilder = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            symbolBuilder.append(expression.charAt(i));

            if (RIGHT_ANGLE_BRACE.contentEquals(symbolBuilder) || LEFT_ANGLE_BRACE.contentEquals(symbolBuilder)) {
                continue;
            }

            if (MathOperation.getOperationSet().contains(symbolBuilder.toString())
                    || MathOperation.CLOSED_BRACE.contentEquals(symbolBuilder)) {
                if (!digitBuilder.isEmpty()) {
                    strings.add(digitBuilder.toString());
                    digitBuilder.delete(0, digitBuilder.length());
                }
                strings.add(symbolBuilder.toString());
            } else if (i == expression.length() - 1) {
                if (!symbolBuilder.isEmpty() && Character.isDigit(symbolBuilder.charAt(0))) {
                    digitBuilder.append(symbolBuilder);
                }
                strings.add(digitBuilder.toString());
            } else if (Character.isDigit(symbolBuilder.charAt(0))) {
                digitBuilder.append(symbolBuilder);
            }
            symbolBuilder.delete(0, symbolBuilder.length());
        }
        System.out.println(strings);
        return strings.toArray(String[]::new);
    }
}
