package by.zvezdina.composite.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SymbolLeaf implements TextComponent {
    private static final Logger logger = LogManager.getLogger();
    private char symbol;

    public SymbolLeaf(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void add(TextComponent textComponent) {
        logger.log(Level.ERROR, "Error while attempting execute method on symbol " + symbol);
        throw new UnsupportedOperationException("Error while attempting execute method on symbol " + symbol);
    }

    @Override
    public void remove(TextComponent textComponent) {
        logger.log(Level.ERROR, "Error while attempting execute method on symbol " + symbol);
        throw new UnsupportedOperationException("Error while attempting execute method on symbol " + symbol);
    }

    @Override
    public List<TextComponent> getListComponents() {
        logger.log(Level.ERROR, "Error while attempting execute method on symbol " + symbol);
        throw new UnsupportedOperationException("Error while attempting execute method on symbol " + symbol);
    }

    @Override
    public int getSize() {
        logger.log(Level.ERROR, "Error while attempting execute method on symbol " + symbol);
        throw new UnsupportedOperationException("Error while attempting execute method on symbol " + symbol);
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
