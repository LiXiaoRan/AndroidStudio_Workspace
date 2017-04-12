package com.inititute.main.proxy;

/**
 * Created by liran on 2015-10-12.
 */
public class Car implements Moveable {


    @Override
    public void move() {

        //实现开车
        try {
            System.out.println("car travel  ing...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
