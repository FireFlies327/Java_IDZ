package main;

public class Info {
    String name;
    int cod;
    Weather[] weather;
    Main main;
    Wind wind;
    Clouds clouds;

    class Weather {
        String main;
        String description;
        String icon;
    }

    class Main {
        Float temp;
        Float temp_min;
        Float temp_max;
        int pressure;
        int humidity;
    }

    class Wind {
        float speed;
    }

    class Clouds {
        int all;
    }
}
