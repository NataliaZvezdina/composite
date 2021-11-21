package by.zvezdina.composite.parser;

import by.zvezdina.composite.entity.TextComponent;
import by.zvezdina.composite.exception.TextHandlingException;
import by.zvezdina.composite.reader.CustomReader;
import by.zvezdina.composite.reader.impl.CustomReaderImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TextHandlerTest {

    private AbstractHandler textHandler;
    private CustomReader reader;
    private static final String TEST_FILE_PATH1 = "testdata/example1.txt";
    private String textExpected = "First line Second line Third line ";

    @BeforeClass
    public void setUp() {
        textHandler = new TextHandler();
        reader = new CustomReaderImpl();
    }

    @Test
    public void testHandleRequest() throws TextHandlingException {
        String fileContent = reader.readFileContent(TEST_FILE_PATH1);

        TextComponent textComponent = textHandler.handleRequest(fileContent);

        assertEquals(textComponent.toString(), textExpected);
    }
}