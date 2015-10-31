package com.liran.main.ThreadTest;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用CountDownLatch控制任务的执行顺序
 * Created by LiRan on 2015-10-31.
 */
public class CountDownLatchTest {

    static final int SIZE = 100;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new WaitingTask(countDownLatch));
        }
        for (int i = 0; i < SIZE; i++) {
            executorService.execute(new TaskPersion(countDownLatch));
        }
        System.out.println("执行完所有的任务");
        executorService.shutdown();
    }
}


class TaskPersion implements Runnable {

    private final CountDownLatch countDownLatch;

    public static int count = 0;
    public final int id = count++;

    private static Random random = new Random(47);

    public TaskPersion(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        doWorks();
        countDownLatch.countDown();


    }

    private void doWorks() {
        try {
            Thread.sleep(random.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this + "completed");
    }

    @Override
    public String toString() {
        return "TaskPersion id " + id + " 工作完成 ";
    }
}

class WaitingTask implements Runnable {

    private static int count = 0;
    public final int id = count++;
    private final CountDownLatch countDownLatch;

    WaitingTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        try {
            countDownLatch.await();
            System.out.println(this + "WaitingTask await");
        } catch (InterruptedException e) {
            System.out.println(this + "WaitingTask await中断");
        }

    }

    @Override
    public String toString() {
        return "WaitingTask id " + id;
    }
}
