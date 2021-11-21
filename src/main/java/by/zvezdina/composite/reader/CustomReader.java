package by.zvezdina.composite.reader;

import by.zvezdina.composite.exception.TextHandlingException;


public interface CustomReader {

    String readFileContent(String filePath) throws TextHandlingException;
}
