package by.zvezdina.composite.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

public class Context {

    private Deque<Integer> contextValue = new ArrayDeque<>();

    public Integer pop() {
        return contextValue.pop();
    }

    public void push(Integer number) {
        contextValue.push(number);
    }
}
