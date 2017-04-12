package com.inititute.main.DesignPattern.桥接模式;

/**
 * 桥接模式
 * Created by LiRan on 2016-03-03.
 */
public class Brtdge_pattern {

    public static void main(String[] args) {

        Abstraction abstraction=new RefinedAbstraction();
        abstraction.setImplementor(new ConcreteImplementorA());
        abstraction.Operation();

        abstraction.setImplementor(new ConcreteImplementorB());
        abstraction.Operation();

    }
}
