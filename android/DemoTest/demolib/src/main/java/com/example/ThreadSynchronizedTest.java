package com.example;

/**
 * Created by hh on 16/11/29.
 */

public class ThreadSynchronizedTest {


    public static void main(String[] args) {

//        Test test = new Test();

        MyThreadA mytA1 = new MyThreadA(new Test());
        mytA1.setName("mytA1");
        MyThreadA mytA2 = new MyThreadA(new Test());
        mytA2.setName("mytA2");
        MyThreadB mytB1 = new MyThreadB(new Test());
        mytB1.setName("mytB1");
        MyThreadB mytB2 = new MyThreadB(new Test());
        mytB2.setName("mytB2");

        mytA1.start();
        mytA2.start();
        mytB1.start();
        mytB2.start();

    }
}

class MyThreadA extends Thread {

    Test test;
    boolean flag = true;

    public MyThreadA(Test test) {
        this.test = test;
    }

    @Override
    public void run() {
        while (flag) {
            test.inputA();
            if (test.countsA == 0) {
                flag = false;
            }
        }
    }
}

class MyThreadB extends Thread {

    Test test;
    boolean flag = true;

    public MyThreadB(Test test) {
        this.test = test;
    }

    @Override
    public void run() {
        while (flag) {
            test.inputB();
            if (test.countsA == 0) {
                flag = false;
            }
        }
    }
}

class Test {

    public Test() {
    }

    public static int countsA = 100;
//    public static int countsB = 100;

    public synchronized void inputA() {
        if (countsA > 0) {
            System.out.println(Thread.currentThread().getName() + ":A" + countsA--);
        } else {
            System.out.println(Thread.currentThread().getName() + ":A结束");
        }
    }

    public synchronized void inputB() {
        if (countsA > 0) {
            System.out.println(Thread.currentThread().getName() + ":B" + countsA--);
        } else {
            System.out.println(Thread.currentThread().getName() + ":B结束");
        }
    }
}