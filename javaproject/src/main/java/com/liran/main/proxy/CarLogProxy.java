package com.liran.main.proxy;

/**
 * 对日志进行代理
 * Created by liran on 2015-10-13.
 */
public class CarLogProxy implements Moveable {

    private Moveable moveable;

    public CarLogProxy(Moveable moveable) {
        this.moveable = moveable;
    }


    @Override
    public void move() {
        System.out.println("LOG start");
        moveable.move();
        System.out.println("LOG end");
    }
}
