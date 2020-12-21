package main;


public class Currency {

    private int ID;
    private String numCode, charCode, name;
    private int scale;
    private double rate;

    public Currency(int id, String numCode, String charCode, int scale, String name, double rate){
        this.ID = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.scale = scale;
        this.name = name;
        this.rate = rate;
    }

    public Currency(){}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

}
