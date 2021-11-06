package by.zvezdina.composite.service.impl;

import by.zvezdina.composite.entity.TextComponent;
import by.zvezdina.composite.service.CustomTextService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CustomTextServiceImpl implements CustomTextService {
    private static final Logger logger = LogManager.getLogger();
    private static final String SPACE = "\\s";
    private static final String VOWEL_SET = "(?iu)[aeiouаеиоу]";
    private static final String CONSONANT_SET = "(?iu)[^aeiouаеиоу\\p{Punct}\\s\\d]";

    @Override
    public String sortParagraphs(TextComponent text) {
        String sorted = text.getListComponents().stream().sorted(Comparator.comparingInt(TextComponent::getSize))
                .map(TextComponent::toString).collect(Collectors.joining(" "));

        return sorted;
    }

    @Override
    public String filterSentencesByWordsNumber(TextComponent text, int numberOfWords) {
        String filtered = text.getListComponents().stream().flatMap(c -> c.getListComponents().stream())
                .filter(c -> (c.getSize() > numberOfWords))
                .map(TextComponent::toString)
                .collect(Collectors.joining(" "));

        return filtered;
    }

    public String findSentenceHavingLongestWord(TextComponent text) {
        List<String> senntences = text.getListComponents().stream()
                .flatMap(c -> c.getListComponents().stream())
                .map(TextComponent::toString)
                .collect(Collectors.toList());

        String longestWord = "";
        String sentenceToFind = "";
        for (String sentence : senntences) {
            String[] words = sentence.split(SPACE);
            Optional<String> longest = Arrays.stream(words).max(Comparator.comparingInt(String::length));
            if (longest.isPresent()) {
                String optionalLongest = longest.get();
                if (optionalLongest.length() > longestWord.length()) {
                    longestWord = optionalLongest;
                    sentenceToFind = sentence;
                }
            }
        }

        logger.log(Level.INFO, "Found sentence: " + sentenceToFind);
        return sentenceToFind;
    }

    @Override
    public Map<String, Integer> countWordsFrequency(TextComponent text) {
        Map<String, Integer> wordsFrequency = new HashMap<>();

        text.getListComponents()
                .stream()
                .flatMap(c -> c.getListComponents().stream())
                .flatMap(c -> c.getListComponents().stream())
                .map(TextComponent::toString)
                .forEach(c -> {
                    wordsFrequency.computeIfAbsent(c, (key) -> 0);
                    int count = wordsFrequency.get(c);
                    count++;
                    wordsFrequency.put(c, count);
                });


        return wordsFrequency;
    }

    @Override
    public long countVowels(TextComponent sentence) {
        long count = sentence.getListComponents()
                .stream()
                .flatMap(c -> c.getListComponents().stream())
                .map(TextComponent::toString)
                .filter(c -> Pattern.matches(VOWEL_SET, c))
                .count();

        logger.log(Level.INFO, "Count vowels: " + count + " for sentence: " + sentence);
        return count;
    }

    @Override
    public long countConsonants(TextComponent sentence) {
        long count = sentence.getListComponents()
                .stream()
                .flatMap(c -> c.getListComponents().stream())
                .map(TextComponent::toString)
                .filter(c -> Pattern.matches(CONSONANT_SET, c))
                .count();

        logger.log(Level.INFO, "Count consonants: " + count + " for sentence: " + sentence);
        return count;
    }
}


