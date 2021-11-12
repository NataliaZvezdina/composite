package by.zvezdina.composite.reader.impl;

import by.zvezdina.composite.exception.TextHandlingException;
import by.zvezdina.composite.reader.CustomReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomReaderImpl implements CustomReader {
    private static final Logger logger = LogManager.getLogger();

    public String readLines(String filePath) throws TextHandlingException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(filePath);
        File file = new File(resource.getFile());
        Path path = file.toPath();

        logger.log(Level.INFO, "Read file " + filePath);
//        List<String> lines = new ArrayList<>();
        String text = "";
        try (Stream<String> streamLines = Files.lines(path)){
            text = streamLines.collect(Collectors.joining(" "));
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while reading file " + filePath);
            throw new TextHandlingException("Error while reading file " + filePath, e);
        }
        return text;
    }
}
