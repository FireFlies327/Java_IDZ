package main;

public class Report {
    private int id;
    private int rate;
    private int time;

    public Report() {
    }

    public Report(int rate, int time) {
        this.rate = rate;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Rate=" + rate +
                ", time=" + time;
    }
}
