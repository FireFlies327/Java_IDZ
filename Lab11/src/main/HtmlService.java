package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlService {
    public void makeHtml(List<String> list, String name) {
        String header = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "    <head>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <title>City</title>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "\t  <table border=\"1\" width=\"100%\" cellpadding=\"5\">";

        String footer = "\t </table>\n" +
                "    </body>\n" +
                "</html>";

        StringBuilder table = new StringBuilder();

        for (String s: list) {
            table.append("<tr><td>").append(s).append("</td></tr>");
        }

        File file = new File(name);
        file.delete();

        try (FileWriter writer = new FileWriter(name, true)) {
            writer.write(header + table + footer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
