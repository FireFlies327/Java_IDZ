package main;

import main.parser.*;
import java.io.*;
import java.util.*;
import java.lang.*;

class XmlParser1 {

    public static void main(String[] args) throws Exception {
        while(true) {
            List<Currency> currencyList = getResultList(readNumParser(), new File(getFileName()));
            currencyList.forEach(s -> System.out.println(
                    Key.ID.toString()+": "+s.getID()+"\n"+
                    Key.NUMCODE.toString() + ": " + s.getNumCode() + "\n" +
                    Key.CHARCODE.toString() + ": " + s.getCharCode() + "\n" +
                    Key.NAME.toString() +": " + s.getName() + "\n" +
                    Key.RATE.toString() + ": " + s.getRate() + "\n" +
                    Key.SCALE.toString() +": " + s.getScale() + "\n"));
        }
    }

    private static int readNumParser(){
        Scanner in = new Scanner(System.in);
        System.out.print("\n Выберите xml-парсер: \n" +
                Xml.DOM.getId()  + " - " + Xml.DOM.getName() +" "+
                Xml.SAX.getId()  + " - " + Xml.SAX.getName() +" "+
                Xml.StAX.getId() + " - " + Xml.StAX.getName()+" "+
                "0 - выход \n");
        int numParser = in.nextInt();
        if (numParser == 0) System.exit(0);
        return numParser;
    }

    private static List<Currency> getResultList(int num, File xmlFile){
        Parser parser = null;
        switch(num){
            case (1):
                parser = new DOMParser();
                break;
            case (2):
                parser = new SAXParser1();
                break;
            case (3):
                parser = new StAXParser();
                break;
        }
        return parser.readDataXml(xmlFile);
    }

    private static String getFileName() throws IOException {

        return "Currency.xml";
    }
}

