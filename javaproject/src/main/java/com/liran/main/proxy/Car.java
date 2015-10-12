package com.liran.main.proxy;

/**
 * Created by liran on 2015-10-12.
 */
public class Car implements Moveable {


    @Override
    public void move() {
        long starttime = System.currentTimeMillis();
        System.out.println("car start");
        //实现开车
        try {
            System.out.println("car travel  ing...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endtime = System.currentTimeMillis();
        System.out.println("car end   driver time is " + (endtime - starttime) + "ms");

    }
}
