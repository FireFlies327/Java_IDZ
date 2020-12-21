package main.parser;

import main.Currency;
import java.io.File;
import java.util.List;

public interface Parser {
    List<Currency> readDataXml(File file);
}

