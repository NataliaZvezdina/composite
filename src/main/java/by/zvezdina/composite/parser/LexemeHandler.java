package by.zvezdina.composite.parser;

import by.zvezdina.composite.entity.TextComponent;
import by.zvezdina.composite.entity.TextComposite;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeHandler extends AbstractHandler {
    private static final Logger logger = LogManager.getLogger();
    private static final String WORD_DELIMITER = "(?iu)[a-zа-яё]+";
    private static final String SPACE_JOINER = "\s";

    public LexemeHandler() {
        super(new WordHandler());
    }

    @Override
    public TextComponent handleRequest(String textElement) {
        logger.log(Level.INFO, "Start parse text element - lexeme: " + textElement);

        Pattern pattern = Pattern.compile(WORD_DELIMITER);
        Matcher matcher = pattern.matcher(textElement);

        TextComponent textComponent = new TextComposite();
        TextComponent component;

        while (matcher.find()) {
            int startIndex = matcher.start();
            int endIndex = matcher.end();

            component = chain(textElement.substring(startIndex, endIndex));
            textComponent.add(component);
            logger.log(Level.INFO, "Found matching word:" + matcher.group());

            component = super.chain(SPACE_JOINER);
            textComponent.add(component);
        }

        logger.log(Level.INFO, "Result text component - lexeme: " + textComponent);
        return textComponent;
    }
}
