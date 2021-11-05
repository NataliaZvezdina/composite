package by.zvezdina.composite.parser;

import by.zvezdina.composite.entity.TextComponent;
import by.zvezdina.composite.entity.TextComposite;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextHandler extends AbstractHandler {
    private static final Logger logger = LogManager.getLogger();
    private static final String PARAGRAPH_DELIMITER = "(\\s{4}.+?)(\\s{4}|$)";

    public TextHandler() {
        super(new ParagraphHandler());
    }

    @Override
    public TextComponent handleRequest(String textElement) {
        System.out.println("Start parse text element: " + textElement);
        Pattern pattern = Pattern.compile(PARAGRAPH_DELIMITER);
        Matcher matcher = pattern.matcher(textElement);

        TextComponent textComponent = new TextComposite();
        TextComponent component;

        int endIndex = 0;
        while (matcher.find(endIndex)) {
            int beginIndex = matcher.start(1);
            endIndex = matcher.start(2);
            String paragraph = textElement.substring(beginIndex, endIndex);
            logger.log(Level.INFO, "Found matching paragraph:" + paragraph);

            component = chain(paragraph);
            textComponent.add(component);
        }
        logger.log(Level.INFO, "Result text component: " + textComponent);
        return textComponent;
    }
}
