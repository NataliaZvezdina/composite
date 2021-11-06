package by.zvezdina.composite.service;

import by.zvezdina.composite.entity.TextComponent;

import java.util.Map;

public interface CustomTextService {

    String sortParagraphs(TextComponent text);

    String filterSentencesByWordsNumber(TextComponent text, int numberOfWords);

    String findSentenceHavingLongestWord(TextComponent text);

    Map<String, Integer> countWordsFrequency(TextComponent text);

    long countVowels(TextComponent sentence);

    long countConsonants(TextComponent sentence);
}
