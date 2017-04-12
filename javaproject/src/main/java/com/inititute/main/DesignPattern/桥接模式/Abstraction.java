package com.inititute.main.DesignPattern.桥接模式;

/**
 * 抽象
 * Created by LiRan on 2016-03-03.
 */
public class Abstraction {

    protected Implementor implementor;

    public void setImplementor(Implementor implementor) {
        this.implementor = implementor;
    }

    public void Operation(){
        implementor.Operation();
    }
}
