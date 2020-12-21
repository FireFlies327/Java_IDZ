package main;

public enum Xml {

    DOM(1, "DOM"),
    SAX(2, "SAX"),
    StAX(3, "StAX");

    private int id;
    private String name;

    Xml(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
