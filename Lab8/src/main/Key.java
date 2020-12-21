package main;

public enum Key {

    CURRENCY("Currency"),
    ID("Id"),
    NUMCODE("NumCode"),
    CHARCODE("CharCode"),
    SCALE("Scale"),
    RATE("Rate"),
    NAME("Name");

    private String identifier;

    Key(String identifier) {
        this.identifier = identifier;
    }

    public String toString() {
        return identifier;
    }

}
