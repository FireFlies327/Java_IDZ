package main.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import main.Key;

class UserHandler extends DefaultHandler {

    boolean bNumCode, bCharCode, bScale, bName, bRate;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase(Key.CURRENCY.toString())) {
            System.out.println(Key.ID.toString()+" "+attributes.getValue(Key.ID.toString()));
        } else if (qName.equalsIgnoreCase(Key.NUMCODE.toString())) {
            bNumCode = true;
        } else if (qName.equalsIgnoreCase(Key.CHARCODE.toString())) {
            bCharCode = true;
        } else if (qName.equalsIgnoreCase(Key.SCALE.toString())) {
            bScale = true;
        } else if (qName.equalsIgnoreCase(Key.NAME.toString())) {
            bName = true;
        } else if (qName.equalsIgnoreCase(Key.RATE.toString())) {
            bRate = true;
        }
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase(Key.CURRENCY.toString())) {

            System.out.println();
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (bNumCode) {
            System.out.println(Key.NUMCODE.toString()+ ": " + new String(ch, start, length));
            bNumCode = false;
        } else if (bCharCode) {
            System.out.println(Key.CHARCODE.toString()+ ": " + new String(ch, start, length));
            bCharCode = false;
        } else if (bScale) {
            System.out.println(Key.SCALE.toString()+ ": " + new String(ch, start, length));
            bScale = false;
        } else if (bName) {
            System.out.println(Key.NAME.toString()+" : " + new String(ch, start, length));
            bName = false;
        } else if (bRate) {
            System.out.println(Key.RATE.toString()+" : " + new String(ch, start, length));
            bRate = false;
        }
    }
}