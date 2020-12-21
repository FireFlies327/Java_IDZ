package main.parser;

import main.Currency;
import main.Key;
import java.io.*;
import java.util.*;
import javax.xml.stream.*;
import javax.xml.stream.events.*;

public class StAXParser implements Parser {

    boolean bNumCode, bCharCode, bScale, bName, bRate;

    public List<Currency> readDataXml(File file) {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(file.getName()));

            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                switch(event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        if (qName.equalsIgnoreCase(Key.CURRENCY.toString())) {
                            //System.out.println("Start Element : "+Key.CURRENCY.toString());
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            String id = attributes.next().getValue();
                            System.out.println(Key.ID.toString()+" : " + id);
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
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if(bNumCode) {
                            System.out.println(Key.NUMCODE.toString()+": " + characters.getData());
                            bNumCode = false;
                        }
                        if(bCharCode) {
                            System.out.println(Key.CHARCODE.toString()+": " + characters.getData());
                            bCharCode = false;
                        }
                        if(bScale) {
                            System.out.println(Key.SCALE.toString()+": " + characters.getData());
                            bScale = false;
                        }
                        if(bName) {
                            System.out.println(Key.NAME.toString()+": " + characters.getData());
                            bName = false;
                        }
                        if(bRate) {
                            System.out.println(Key.RATE.toString()+": " + characters.getData());
                            bRate = false;
                        }
                        break;

                        case XMLStreamConstants.END_ELEMENT:
                            EndElement endElement = event.asEndElement();
                            if(endElement.getName().getLocalPart().equalsIgnoreCase(Key.CURRENCY.toString())) {
                                System.out.println();
                            }
                            break;
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }

        return new ArrayList<>();
    }
}
