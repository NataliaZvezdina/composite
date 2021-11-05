package by.zvezdina.composite.service.impl;

import by.zvezdina.composite.entity.TextComponent;
import by.zvezdina.composite.parser.AbstractHandler;
import by.zvezdina.composite.parser.ParagraphHandler;
import by.zvezdina.composite.service.CustomTextService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CustomTextServiceImpl implements CustomTextService {

    @Override
    public String sortParagraphs(String text) {
        AbstractHandler handler = new ParagraphHandler();
        TextComponent component = handler.handleRequest(text);
        List<TextComponent> sorted = component.getListComponents().stream().sorted(Comparator.comparingInt(TextComponent::getSize))
                .collect(Collectors.toList());
        return sorted.toString();
    }
}
//List<TextComponent>