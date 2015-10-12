package com.liran.main.proxy;

/**
 * 通过聚合的方式实现代理
 * Created by liran on 2015-10-12.
 */
public class Car3 extends Car {

    private Car car;

    public Car3(Car car) {
        this.car = car;
    }

    @Override
    public void move() {
        car.move();
    }
}
