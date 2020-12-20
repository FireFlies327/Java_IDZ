package main;

import java.util.Scanner;

public class Main {
    public static void main(String ... args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        // todo можно добавить проверку вводимых данных
        System.out.println("Введите количество потоков");
        int threadCount = sc.nextInt();
        System.out.println("Введите количество чисел");
        int count = sc.nextInt();

        System.out.println("Выберите действие");
        System.out.println("1. Addition");
        System.out.println("2. Multiplication");
        System.out.println("3. Subtraction");

        Action action = null;
        int actionNumber = sc.nextInt();
        switch (actionNumber) {
            case 1:
                action = Action.ADDITION;
                break;
            case 2:
                action = Action.MULTIPLICATION;
                break;
//            case 3:
//                action = Action.SUBTRUCTION;
//                break;
        }

        ThreadGenerator threadGenerator = new ThreadGenerator(action, threadCount, count);
        threadGenerator.execute();
        System.out.println(threadGenerator.getResult());
    }
}
