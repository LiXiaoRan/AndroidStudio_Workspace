package com.liran.main.ThreadTest;

/**
 * Created by liran on 2015-10-22.
 */
public class Liftoff implements Runnable {

    protected int CountDown = 10;
    private String name;

    public Liftoff() {
    }

    public Liftoff(String name) {
        Thread.currentThread().setName("aaaa");
        this.name = name;

    }

    public Liftoff(int countDown) {
        CountDown = countDown;
    }


    @Override
    public void run() {
        while (CountDown-- > 0) {
            System.out.println(status());
            Thread.yield();
        }
    }

    public String status() {

        Thread.currentThread().setName(name);
        return "# " + Thread.currentThread().getName() + " (" + (CountDown > 0 ? CountDown : " Liftoff! ") + ")";
    }
}
