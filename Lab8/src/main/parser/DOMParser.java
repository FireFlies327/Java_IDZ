package main.parser;

import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import main.Currency;
import main.Key;

public class DOMParser implements Parser {

    public List<main.Currency> readDataXml(File file) {
        List<Currency> currencyList = new ArrayList<Currency>();
        DocumentBuilderFactory dbFactory = null;
        DocumentBuilder dBuilder = null;
        Document doc = null;
        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(Key.CURRENCY.toString()); //список элементов <Currency> .. </Currency>

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nNode;
                    int ID = Integer.parseInt(el.getAttribute(Key.ID.toString()));
                    String numCode = el.getElementsByTagName(Key.NUMCODE.toString()).item(0).getTextContent();
                    String charCode = el.getElementsByTagName(Key.CHARCODE.toString()).item(0).getTextContent();
                    String name = el.getElementsByTagName(Key.NAME.toString()).item(0).getTextContent();
                    int scale = Integer.parseInt(el.getElementsByTagName(Key.SCALE.toString()).item(0).getTextContent());
                    double rate = Double.parseDouble(el.getElementsByTagName(Key.RATE.toString()).item(0).getTextContent());
                    Currency c = new Currency(ID, numCode, charCode, scale, name, rate);
                    currencyList.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currencyList;
    }

}
