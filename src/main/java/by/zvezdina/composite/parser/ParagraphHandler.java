package by.zvezdina.composite.parser;

import by.zvezdina.composite.entity.TextComponent;
import by.zvezdina.composite.entity.TextComposite;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphHandler extends AbstractHandler {
    private static final Logger logger = LogManager.getLogger();
    private static final String SENTENCE_DELIMITER = ".+?[.?!…](\\s|$)";

    public ParagraphHandler(){
        super(new SentenceHandler());
    }

    @Override
    public TextComponent handleRequest(String textElement) {
        logger.log(Level.INFO, "Start parse text element - paragraph: " + textElement);

        Pattern pattern = Pattern.compile(SENTENCE_DELIMITER);
        Matcher matcher = pattern.matcher(textElement);

        TextComponent textComponent = new TextComposite();
        TextComponent component;
        while (matcher.find()) {
            logger.log(Level.INFO, "Found matching sentence:" + matcher.group());
            component = chain(matcher.group());
            textComponent.add(component);
        }
        logger.log(Level.INFO, "Result text component - sentence: " + textComponent);
        return textComponent;
    }
}
