package com.liran.main.proxy;

/**
 * 对时间进行代理
 * Created by liran on 2015-10-13.
 */
public class CarTimeProxy implements Moveable {

    private Moveable moveable;

    public CarTimeProxy(Moveable moveable) {
        this.moveable = moveable;
    }


    @Override
    public void move() {
        long starttime = System.currentTimeMillis();
        System.out.println("car start");

        moveable.move();

        long endtime = System.currentTimeMillis();
        System.out.println("car end   driver time is " + (endtime - starttime) + "ms");
    }
}
