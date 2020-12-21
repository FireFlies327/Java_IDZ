package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseService databaseService = new DatabaseService();
            databaseService.generateStructure();
            HtmlService htmlService = new HtmlService();

            String htmlStr = new String(Files.readAllBytes(Paths.get("1.html")));
            Document html = Jsoup.parse(htmlStr);
            String name = html.getElementById("name").text();
            String language = html.getElementById("language").text();
            List<String> list = databaseService.getInfoByCityAndLanguage(name, language);
            htmlService.makeHtml(list, "out1.html");
            list.forEach(System.out::println);
            System.out.println("------------------------------------------------");

            htmlStr = new String(Files.readAllBytes(Paths.get("2.html")));
            html = Jsoup.parse(htmlStr);
            String type = html.getElementById("type").text();
            list = databaseService.getInfoByType(type);
            htmlService.makeHtml(list, "out2.html");
            list.forEach(System.out::println);
            System.out.println("------------------------------------------------");

            htmlStr = new String(Files.readAllBytes(Paths.get("3.html")));
            html = Jsoup.parse(htmlStr);
            Long population = Long.parseLong(html.getElementById("population").text());
            list = databaseService.getInfoByPopulation(population);
            htmlService.makeHtml(list, "out3.html");
            list.forEach(System.out::println);
            System.out.println("------------------------------------------------");

            list = databaseService.getInfoOldest();
            htmlService.makeHtml(list, "out4.html");
            list.forEach(System.out::println);
            System.out.println("------------------------------------------------");

            ConnectionService.getConnection().close();
        } catch(SQLException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
