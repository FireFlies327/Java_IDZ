package main.parser;

import main.Currency;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.*;

public class SAXParser1 implements Parser {
    public List<Currency> readDataXml(File file) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler();
            saxParser.parse(file, userhandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
