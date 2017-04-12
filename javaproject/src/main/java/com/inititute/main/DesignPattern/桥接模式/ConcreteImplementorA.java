package com.inititute.main.DesignPattern.桥接模式;

/**
 * Created by LiRan on 2016-03-03.
 */
public class ConcreteImplementorA extends  Implementor {

    @Override
    public void Operation() {
        System.out.println("具体实现A的方法执行");
    }
}
