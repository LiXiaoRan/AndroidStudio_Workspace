package com.inititute.main.ThreadTest;

/**
 * 测试join
 * Created by liran on 2015-10-23.
 */


public class JoinThreadTest1 {

    public static void main(String[] args) {

        Sleeper sleeper1 = new Sleeper(1500, "sleeper1");
        Sleeper sleeper2 = new Sleeper(3000, "sleeper2");


        Joing joing1 = new Joing(sleeper1, "joing1"); //joing1必须等待sleeper1执行完成才可以执行
        Joing joing2 = new Joing(sleeper2, "joing2");//joing2必须等待sleeper2执行完成才可以执行

//        sleeper2.interrupt();

    }

}

class Sleeper extends Thread {

    private int duration;

    public Sleeper(int duration, String name) {
        super(name);
        this.duration = duration;
        start();
    }

    @Override
    public void run() {

        try {
            sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();

            System.out.println(getName() + "was interupted :" + isInterrupted());
            return;
        }
        System.out.println(getName() + "执行完成");
    }
}

class Joing extends Thread {


    private Sleeper sleeper;

    public Joing(Sleeper sleeper, String name) {
        super(name);
        this.sleeper = sleeper;
        start();
    }


    @Override
    public void run() {
        try {
            sleeper.join();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(getName() + "join 完成");
    }
}