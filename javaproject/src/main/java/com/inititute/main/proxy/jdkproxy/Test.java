package com.inititute.main.proxy.jdkproxy;

import com.inititute.main.proxy.Car;
import com.inititute.main.proxy.Moveable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理测试类
 * Created by liran on 2015-10-13.
 */
public class Test {

    public static void main(String[] args) {
        Car car = new Car();
        InvocationHandler invocationHandler = new TimeHandler(car);
        Class<Car> cls = Car.class;
        /**
         * 三个参数  第一个是类加载器
         * 第二个是 实现接口
         * 第三个是InvocationHandler
         *
         */
        Moveable moveable = (Moveable) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(),
                invocationHandler);
        moveable.move();

    }

}
