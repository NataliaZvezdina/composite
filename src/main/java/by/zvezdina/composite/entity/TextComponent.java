package by.zvezdina.composite.entity;

import java.util.List;

public interface TextComponent {

    void add(TextComponent textComponent);

    void remove(TextComponent textComponent);

    List<TextComponent> getListComponents();

    int getSize();

    String toString();
}
