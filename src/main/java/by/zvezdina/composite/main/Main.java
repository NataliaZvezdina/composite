package by.zvezdina.composite.main;

import by.zvezdina.composite.entity.TextComponent;
import by.zvezdina.composite.exception.TextHandlingException;
import by.zvezdina.composite.parser.AbstractHandler;
import by.zvezdina.composite.parser.TextHandler;
import by.zvezdina.composite.reader.CustomReader;
import by.zvezdina.composite.reader.impl.CustomReaderImpl;
import by.zvezdina.composite.service.CustomTextService;
import by.zvezdina.composite.service.impl.CustomTextServiceImpl;

public class Main {
    private static final String FILE_PATH = "data/text.txt";

    public static void main(String[] args) throws TextHandlingException {

        CustomReader reader = new CustomReaderImpl();
        String text = reader.readLines(FILE_PATH);
        System.out.println(text);
        System.out.println("------------------------------------------");

        AbstractHandler handler = new TextHandler();
        TextComponent component = handler.handleRequest(text);

        System.out.println("----------Component final");
        System.out.println(component.toString());

        System.out.println("-------------------------------------------");
        CustomTextService service = new CustomTextServiceImpl();
        System.out.println(service.sortParagraphs(text));

    }
}
