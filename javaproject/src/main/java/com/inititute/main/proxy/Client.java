package com.inititute.main.proxy;

/**
 * Created by liran on 2015-10-12.
 */
public class Client {
    public static void main(String[] args) {
        Car car = new Car();
//        car.move();

        //使用继承方式实现代理
//        Car2 car2=new Car2();
//        car2.move();

        //使用聚合方式实现代理
//        Moveable car3 = new Car3(car);
//        car3.move();


        //先记录日志 在记录时间
        CarTimeProxy ctp = new CarTimeProxy(car);
        CarLogProxy clp = new CarLogProxy(ctp);
        clp.move();

    }
}
