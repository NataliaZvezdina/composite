package by.zvezdina.composite.parser;

import by.zvezdina.composite.entity.TextComponent;

public abstract class AbstractHandler {
    public AbstractHandler successor;

    public AbstractHandler(AbstractHandler successor) {
        this.successor = successor;
    }

    public abstract TextComponent handleRequest(String textElement);

    public TextComponent chain(String textElement) {
        return successor.handleRequest(textElement);
    }
}
