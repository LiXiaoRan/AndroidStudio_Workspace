package com.inititute.main.proxy;

/**
 * 通过继承的方式来实现代理
 * Created by liran on 2015-10-12.
 */
public class Car2 extends Car {

    @Override
    public void move() {
        long starttime = System.currentTimeMillis();
        System.out.println("car start");
        super.move();
        long endtime = System.currentTimeMillis();
        System.out.println("car end   driver time is " + (endtime - starttime) + "ms");

    }
}
