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

        System.out.println("----------Component final -->");
        System.out.println(component.toString());

        System.out.println("-------------------------------------------");
        CustomTextService service = new CustomTextServiceImpl();

        System.out.println("---------Sort paragraphs by number of sentences -->");
        System.out.println(service.sortParagraphs(component));

        System.out.println("---------Filter sentences --->");
        String result = service.filterSentencesByWordsNumber(component, 2);
        System.out.println("Resulting filtered text: " + result);

        System.out.println("--------Sentence with the longest word -->");
        System.out.println(service.findSentenceHavingLongestWord(component));


        System.out.println("--------Count vowels in sentences --->");
        component.getListComponents()
                .stream().flatMap(c -> c.getListComponents().stream())
                .forEach(c -> System.out.println(service.countVowels(c) + " for sentence: " + c));

//        System.out.println("--------Count consonants in sentences --->");
//        component.getListComponents()
//                .stream().flatMap(c -> c.getListComponents().stream())
//                .forEach(c -> System.out.println(service.countConsonants(c) + " for sentence: " + c));
//
//        System.out.println("--------Count words frequency -->");
//        service.countWordsFrequency(component)
//                .forEach((key, value) -> System.out.println(key + " - found times " + value));

    }
}
