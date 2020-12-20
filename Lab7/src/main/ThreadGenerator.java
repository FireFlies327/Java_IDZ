package main;

import java.util.ArrayList;
import java.util.List;

public class ThreadGenerator {
    private Action action;
    private int threadCount;
    private int count;
    private long result;

    public ThreadGenerator(Action action, int threadCount, int count) {
        this.action = action;
        this.threadCount = threadCount;
        this.count = count;
    }

    public void execute() throws InterruptedException {
        // массив наборов чисел (размер соответствует количеству потоков)
        // набор чисел разделяется примерно поровну для обработки потоками
        List<Integer>[] inputListArray = new List[threadCount];

        for (int i = 0; i < threadCount; i++) {
            inputListArray[i] = new ArrayList<>();
        }

        // разбивка числового ряда на наборы
        int j = 0;
        for (int i = 1; i <= count; i++) {
            inputListArray[j].add(i);
            j++;
            if (j >= inputListArray.length) j = 0;
        }

        List<CalculatorThread> threadList = new ArrayList<>();
        for (List<Integer> list: inputListArray) {
            threadList.add(new CalculatorThread(list));
        }
        // запускаем обработку потоками
        for (Thread thread: threadList) {
            thread.start();
        }
        // ждем пока все отработают
        for (Thread thread: threadList) {
            thread.join();
        }

        // соединяем ответы в один набор
        List<Integer> allOutLists = new ArrayList<>();
        for (CalculatorThread thread: threadList) {
            allOutLists.addAll(thread.getOutList());
        }

        // вывод набора
        StringBuilder sb = new StringBuilder();
        if (allOutLists.size() > 0) {
            sb.append(allOutLists.get(0));
            for (int i = 1; i < allOutLists.size(); i++) {
                sb.append(", " + allOutLists.get(i));
            }
            System.out.println(sb);

            // действие над набором
            if (action == Action.ADDITION)
                result = allOutLists.stream().mapToInt(Integer::intValue).sum();
            if (action == Action.MULTIPLICATION) {
                result = 1;
                allOutLists.forEach(x -> result = result * x);
            }
            if (action == Action.SUBTRUCTION) {
                // todo неизвестно, как делать вычитание
            }
        }
    }

    public long getResult() {
        return result;
    }
}
