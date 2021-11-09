package by.zvezdina.composite.service.comparator;

import by.zvezdina.composite.entity.TextComponent;

import java.util.Comparator;
import java.util.Optional;

public class WordLengthComparator implements Comparator<TextComponent> {

    @Override
    public int compare(TextComponent sentence1, TextComponent sentence2) {
        Optional<String> maxWord1 = sentence1.getListComponents()
                .stream()
                .flatMap(c -> c.getListComponents().stream())
                .map(TextComponent::toString)
                .max(Comparator.comparingInt(String::length));

        Optional<String> maxWord2 = sentence2.getListComponents()
                .stream()
                .flatMap(c -> c.getListComponents().stream())
                .map(TextComponent::toString)
                .max(Comparator.comparingInt(String::length));

        String maxWordOptional1 = "";
        String maxWordOptional2 = "";

        if (maxWord1.isPresent()) {
            maxWordOptional1 = maxWord1.get();
        }

        if (maxWord1.isPresent()) {
            maxWordOptional2 = maxWord2.get();
        }

        return maxWordOptional1.compareTo(maxWordOptional2);
    }
}
