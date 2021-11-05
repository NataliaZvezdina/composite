package by.zvezdina.composite.parser;

import by.zvezdina.composite.entity.SymbolLeaf;
import by.zvezdina.composite.entity.TextComponent;
import by.zvezdina.composite.entity.TextComposite;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeHandler extends AbstractHandler {
    private static final Logger logger = LogManager.getLogger();

    public LexemeHandler() {
        super(null);
    }

    @Override
    public TextComponent handleRequest(String textElement) {
        System.out.println("Start parse text element : " + textElement);

        TextComponent textComponent = new TextComposite();
        TextComponent component;
        char[] symbols = textElement.toCharArray();
        for (char symbol : symbols) {
            component = new SymbolLeaf(symbol);
            logger.log(Level.INFO, "Parsed symbol: " + component);
            textComponent.add(component);
        }
        logger.log(Level.INFO, "Text component: " + textComponent);
        return textComponent;
    }
}
