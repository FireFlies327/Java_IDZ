package main;

import java.util.ArrayList;
import java.util.List;

public class CalculatorThread extends Thread {
    private List<Integer> list;
    private List<Integer> outList;

    public List<Integer> getOutList() {
        return outList;
    }

    public CalculatorThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        outList = new ArrayList<>();
        for (Integer integer: list) {
            if ((integer * 4) % 10 == 0) outList.add(integer);
        }
    }
}
