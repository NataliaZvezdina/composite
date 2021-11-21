package by.zvezdina.composite.service.impl;

import by.zvezdina.composite.entity.TextComponent;
import by.zvezdina.composite.exception.TextHandlingException;
import by.zvezdina.composite.parser.AbstractHandler;
import by.zvezdina.composite.parser.TextHandler;
import by.zvezdina.composite.reader.CustomReader;
import by.zvezdina.composite.reader.impl.CustomReaderImpl;
import by.zvezdina.composite.service.CustomTextService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class CustomTextServiceImplTest {

    private CustomTextService service;
    private CustomReader reader;

    private static final String TEST_FILE_PATH2 = "testdata/example2.txt";
    private TextComponent text;
    private TextComponent sentence;

    private String expectedSorted = "The sentence with theLongestWord The second paragraph The second sentence of the second " +
            "paragraph The first paragraph The second sentence The third sentence ";
    private String expectedFiltered = "The second sentence of the second paragraph The sentence with theLongestWord ";
    private String expectedSentence = "The sentence with theLongestWord ";

    private static final int NUMBER_OF_WORDS_TO_FILTER_SENTENCES = 4;
    private long expectedVowelsNumber = 5;
    private long expectedConsonantsNumber = 12;
    private Map<String, Integer> expectedWordFrequency;

    @BeforeClass
    public void initialiseTextComponent() throws TextHandlingException {
        service = new CustomTextServiceImpl();
        reader = new CustomReaderImpl();

        String content = reader.readFileContent(TEST_FILE_PATH2);
        AbstractHandler handler = new TextHandler();
        text = handler.handleRequest(content);
        sentence = text.getListComponents()
                .stream()
                .flatMap(c -> c.getListComponents().stream())
                .findFirst().get();

        expectedWordFrequency = new HashMap<>();
        expectedWordFrequency.put("the", 7);
        expectedWordFrequency.put("first", 1);
        expectedWordFrequency.put("paragraph", 3);
        expectedWordFrequency.put("second", 4);
        expectedWordFrequency.put("sentence", 4);
        expectedWordFrequency.put("third", 1);
        expectedWordFrequency.put("of", 1);
        expectedWordFrequency.put("with", 1);
        expectedWordFrequency.put(" ", 23);
        expectedWordFrequency.put("thelongestword", 1);
    }

    @Test
    public void testSortParagraphs() {

        String actual = service.sortParagraphs(text);

        assertEquals(actual, expectedSorted);
    }

    @Test
    public void testFilterSentencesByWordsNumber() {

        String actual = service.filterSentencesByWordsNumber(text, NUMBER_OF_WORDS_TO_FILTER_SENTENCES);

        assertEquals(actual, expectedFiltered);
    }

    @Test
    public void testFindSentenceHavingLongestWord() {

        String actual = service.findSentenceHavingLongestWord(text);

        assertEquals(actual, expectedSentence);
    }

    @Test
    public void testCountWordsFrequency() {

        Map<String, Integer> actual = service.countWordsFrequency(text);

        assertEqualsDeep(actual, expectedWordFrequency);
    }

    @Test
    public void testCountVowels() {

        long actual = service.countVowels(sentence);

        assertEquals(actual, expectedVowelsNumber);
    }

    @Test
    public void testCountConsonants() {

        long actual = service.countConsonants(sentence);

        assertEquals(actual, expectedConsonantsNumber);
    }
}