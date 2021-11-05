package by.zvezdina.composite.reader;

import by.zvezdina.composite.exception.TextHandlingException;

import java.util.List;

public interface CustomReader {

    String readLines(String filePath) throws TextHandlingException;
}
