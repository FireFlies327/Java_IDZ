package main;

import com.google.gson.Gson;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherService {
    public static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=Berlin&appid=72f4bfbdfd9cb863eae4359caacedf3c";

    public static void main(String[] args) {
        URL url = createUrl(WEATHER_URL);

        String json = parseUrl(url);

        Gson gson = new Gson();
        Info info = gson.fromJson(json, Info.class);

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

        table.append(header);
        table.append("<tr><td>Name</td><td>").append(info.name).append("</td></tr>");
        table.append("<tr><td>Code</td><td>").append(info.cod).append("</td></tr>");
        table.append("<tr><td>Weather</td><td>").append(info.weather[0].main + ", " + info.weather[0].description).append("<img src='http://openweathermap.org/img/w/" + info.weather[0].icon + ".png' alt='img'>").append("</td></tr>");
        table.append("<tr><td>Temperature</td><td>").append(info.main.temp).append("</td></tr>");
        table.append("<tr><td>Min temperature</td><td>").append(info.main.temp_min).append("</td></tr>");
        table.append("<tr><td>Max temperature</td><td>").append(info.main.temp_max).append("</td></tr>");
        table.append("<tr><td>Pressure</td><td>").append(info.main.pressure).append("</td></tr>");
        table.append("<tr><td>Humidity</td><td>").append(info.main.humidity).append("</td></tr>");
        table.append("<tr><td>Wind speed</td><td>").append(info.wind.speed).append("</td></tr>");
        table.append("<tr><td>Cloud</td><td>").append(info.clouds.all).append("</td></tr>");
        table.append(footer);

        File file = new File("out.html");
        file.delete();

        try (FileWriter writer = new FileWriter("out.html", true)) {
            writer.write(table.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String parseUrl(URL url) {
        if (url == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static URL createUrl(String link) {
        try {
            return new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
