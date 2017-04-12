package com.inititute.main.ThreadTest;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程的本地存储
 * <p/>
 * ThreadLocal 对象通常当作静态域存储 每个线程都被分配了自己的存储，即便只有一个ThreadLocalVariableHolder
 * <p/>
 * Created by LiRan on 2015-10-27.
 */
class Access implements Runnable {

    private final int id;


    public Access(int id) {
        this.id = id;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {//线程处于非阻断状态

            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }


    }

    @Override
    public String toString() {
        return "#" + id + ": " + ThreadLocalVariableHolder.get();
    }
}


public class ThreadLocalVariableHolder {


    private static ThreadLocal<Integer> value = new InheritableThreadLocal<Integer>() {
        private Random rand = new Random(47);

        protected synchronized Integer initialValue() {
            return rand.nextInt(10000);
        }
    };

    public static int get() {
        return value.get();
    }


    public static void increment() {
        value.set(value.get() + 1);
    }


    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Access(i));
        }

        TimeUnit.SECONDS.sleep(3);
        executorService.shutdown();

    }

}