package by.zvezdina.composite.service;

import by.zvezdina.composite.entity.TextComponent;

public interface CustomTextService {

    String sortParagraphs(TextComponent text);

    String filterSentencesByWordsNumber(TextComponent text, int numberOfWords);

    String findSentenceHavingLongestWord(TextComponent text);

    long countVowels(TextComponent sentence);

    long countConsonants(TextComponent sentence);
}
