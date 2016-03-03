package com.liran.main.DesignPattern.桥接模式;

/**
 * Created by LiRan on 2016-03-03.
 */
public class RefinedAbstraction extends Abstraction {

    @Override
    public void Operation() {
        implementor.Operation();
    }
}
