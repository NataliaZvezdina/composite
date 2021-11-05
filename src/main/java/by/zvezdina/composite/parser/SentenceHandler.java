package by.zvezdina.composite.parser;

import by.zvezdina.composite.entity.TextComponent;
import by.zvezdina.composite.entity.TextComposite;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceHandler extends AbstractHandler {
    private static final Logger logger = LogManager.getLogger();
    private static final String LEXEME_DELIMITER = ".+?(\\s|$)" ;                     //  [\\.?!]\\s[\\.?!] [.|?|!|...]\\s


    public SentenceHandler() {
        super(new LexemeHandler());
    }

    @Override
    public TextComponent handleRequest(String textElement) {
        System.out.println(" Start parse text element : " + textElement);
        Pattern pattern = Pattern.compile(LEXEME_DELIMITER);
        Matcher matcher = pattern.matcher(textElement);

        TextComponent textComponent = new TextComposite();
        TextComponent component;

        while (matcher.find()) {

            logger.log(Level.INFO, "Found matching lexeme :" + matcher.group());
            component = super.chain(matcher.group());
            textComponent.add(component);
        }
        logger.log(Level.INFO, "Result text component: " + textComponent);
        return textComponent;
    }
}
