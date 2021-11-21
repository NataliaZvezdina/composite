package by.zvezdina.composite.reader.impl;

import by.zvezdina.composite.exception.TextHandlingException;
import by.zvezdina.composite.reader.CustomReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CustomReaderImplTest {

    private CustomReader reader;
    private static final String TEST_FILE_PATH1 = "testdata/example1.txt";
    private String expected = "    First line.     Second line.     Third line.";

    @BeforeClass
    public void setUp() {
        reader = new CustomReaderImpl();
    }

    @Test
    public void testReadFileContent() throws TextHandlingException {

        String actual = reader.readFileContent(TEST_FILE_PATH1);

        assertEquals(actual, expected);
    }
}