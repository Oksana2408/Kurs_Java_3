package Lesson_3;

/**
 * 1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
 * Используйте wait/notify/notifyAll.
 * 2. На серверной стороне сетевого чата реализовать управление потоками через ExecutorService.
 * */

public class MainHW_3 {
    private static final Object monitor = new Object();
    private static String current = "A";

    public static void main(String[] args) {
        new Thread(()->{
            printA();
        }).start();
        new Thread(()->{
            printB();
        }).start(); new Thread(()->{
            printC();
        }).start();




    }

    public static void printA(){
        synchronized (monitor){
            try {
            for (int i = 0; i < 5; i++) {
                while (current != "A"){
                        monitor.wait();
                    }
                System.out.println("A");
                current = "B";
                monitor.notifyAll();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }public static void printB(){
        synchronized (monitor){
            try {
            for (int i = 0; i < 5; i++) {
                while (current != "B"){
                        monitor.wait();
                    }
                System.out.println("B");
                current = "C";
                monitor.notifyAll();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }public static void printC(){
        synchronized (monitor){
            try {
            for (int i = 0; i < 5; i++) {
                while (current != "C"){
                        monitor.wait();
                    }
                System.out.println("C");
                current = "A";
                monitor.notifyAll();
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
