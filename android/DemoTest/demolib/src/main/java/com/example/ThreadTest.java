package com.example;

public class ThreadTest {


    public static void main(String[] args) {

        MyThread thread = new MyThread();
        Thread t1 = new Thread(thread, "t1");
        Thread t2 = new Thread(thread, "t2");
        Thread t3 = new Thread(thread, "t3");

        t1.start();
        t2.start();
        t3.start();
    }


    static class MyThread implements Runnable {

        private int counts = 10;

        @Override
        public void run() {

            while (true) {

                if (counts > 0) {
                    System.out.println(Thread.currentThread().getName() + ":" + counts--);
                } else {
                    System.out.println(Thread.currentThread().getName() + ":结束");
                    System.exit(0);
                }

            }

        }
    }

}
