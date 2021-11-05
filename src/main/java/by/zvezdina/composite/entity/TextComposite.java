package by.zvezdina.composite.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private static final Logger logger = LogManager.getLogger();
    private List<TextComponent> components = new ArrayList<>();

    @Override
    public void add(TextComponent textComponent) {
        components.add(textComponent);
    }

    @Override
    public void remove(TextComponent textComponent) {
        components.remove(textComponent);
    }

    @Override
    public List<TextComponent> getListComponents() {
        return new ArrayList<>(components);
    }

    @Override
    public int getSize() {
        return components.size();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (TextComponent component : components) {
            builder.append(component.toString());
        }
        return builder.toString();
    }
}
