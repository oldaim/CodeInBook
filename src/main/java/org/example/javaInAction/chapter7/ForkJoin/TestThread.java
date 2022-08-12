package org.example.javaInAction.chapter7.ForkJoin;

public class TestThread {
    static int sum = 0;
    static Runnable task1 = () -> {

        for (int i = 0; i < 10 ; i++) {
            sum += i;
            System.out.println("Thread1 의 합계는?:" + sum);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    };

    static Runnable task2 = () -> {

        for (int i = 0; i < 10 ; i++) {
            sum += i;
            System.out.println("Thread2 의 합계는?:" + sum);
        }


    };

    static Thread threadFirst = new Thread(task1);
    static Thread threadSecond = new Thread(task2);

    public static void main(String[] args) {
        threadFirst.start();
        threadSecond.start();
    }
}
