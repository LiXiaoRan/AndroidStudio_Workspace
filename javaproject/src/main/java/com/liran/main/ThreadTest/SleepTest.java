package com.liran.main.ThreadTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by LiRan on 2015-10-29.
 */
public class SleepTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new SleepThread("线程1"));
        executorService.execute(new SleepThread("线程2"));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();

    }


}


class WaitThread implements Runnable {

    private String name;

    public WaitThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {

    }
}


class SleepThread implements Runnable {


    private String name;

    public SleepThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        Thread.currentThread().setName(name);
        System.out.println("当前线程：" + Thread.currentThread().getName() + " 调用sleep前");

        try {
            System.out.println("调用Sleep");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("当前线程：" + Thread.currentThread().getName() + " 调用sleep后");


    }
}
