package by.zvezdina.composite.service.impl;

import by.zvezdina.composite.entity.TextComponent;
import by.zvezdina.composite.service.CustomTextService;
import by.zvezdina.composite.service.comparator.WordLengthComparator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class CustomTextServiceImpl implements CustomTextService {
    private static final Logger logger = LogManager.getLogger();
    private static final String SPACE_DELIMITER = "\s";
    private static final String NONE_SENTENCE = "";
    private static final String VOWEL_SET = "(?iu)[aeiouаеиоуё]";
    private static final String CONSONANT_SET = "(?iu)[^aeiouаеиоуё\\p{Punct}\\s\\d]";

    @Override
    public String sortParagraphs(TextComponent text) {
        String sorted = text.getListComponents()
                .stream()
                .sorted(Comparator.comparingInt(TextComponent::getSize))
                .map(TextComponent::toString)
                .collect(Collectors.joining());

        return sorted;
    }

    @Override
    public String filterSentencesByWordsNumber(TextComponent text, int numberOfWords) {
        String filtered = text.getListComponents()
                .stream()
                .flatMap(c -> c.getListComponents().stream())
                .filter(c -> {
                    long count = c.getListComponents()
                            .stream()
                            .flatMap(e -> e.getListComponents().stream())
                            .count();
                    return count / 2 >= numberOfWords;
                })
                .map(TextComponent::toString)
                .collect(Collectors.joining());

        return filtered;
    }

    public String findSentenceHavingLongestWord(TextComponent text) {
        WordLengthComparator comparator = new WordLengthComparator();

        Optional<String> sentence = text.getListComponents().stream()
                .flatMap(c -> c.getListComponents().stream())
                .min(comparator)
                .map(TextComponent::toString);

        if (sentence.isEmpty()) {
            logger.log(Level.INFO, "Sentence with longest word was not found");
            return NONE_SENTENCE;
        }

        String optionalSentence = sentence.get();
        logger.log(Level.INFO, "Found sentence: " + optionalSentence);
        return optionalSentence;
    }

    @Override
    public Map<String, Integer> countWordsFrequency(TextComponent text) {
        Map<String, Integer> wordsFrequency = new HashMap<>();

        text.getListComponents()
                .stream()
                .flatMap(c -> c.getListComponents().stream())
                .flatMap(c -> c.getListComponents().stream())
                .flatMap(c -> c.getListComponents().stream())
                .map(TextComponent::toString)
                .map(String::toLowerCase)
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
        //Pattern pattern = Pattern.compile(VOWEL_SET);
        long count = sentence.getListComponents()
                .stream()
                .flatMap(c -> c.getListComponents().stream())
                .flatMap(c -> c.getListComponents().stream())
                .map(TextComponent::toString)
                .filter(c -> c.matches(VOWEL_SET))
                .count();

        logger.log(Level.INFO, "Count vowels: " + count + " for sentence: " + sentence);
        return count;
    }

    @Override
    public long countConsonants(TextComponent sentence) {
        long count = sentence.getListComponents()
                .stream()
                .flatMap(c -> c.getListComponents().stream())
                .flatMap(c -> c.getListComponents().stream())
                .map(TextComponent::toString)
                .filter(c -> c.matches(CONSONANT_SET))
                .count();

        logger.log(Level.INFO, "Count consonants: " + count + " for sentence: " + sentence);
        return count;
    }
}


