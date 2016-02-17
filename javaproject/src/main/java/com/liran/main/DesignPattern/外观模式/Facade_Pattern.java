package com.liran.main.DesignPattern.外观模式;

/**
 * 外观模式
 * 为子系统中的一组接口提供一个统一的界面，此模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。
 * Created by LiRan on 2016-02-17.
 */
public class Facade_Pattern {


    public static void main(String[] args) {

        Client client = new Client(new Facade());
        client.doSomeThing();

    }

}


/**
 * 客户端
 */

class Client {

    private Facade facade;

    public Client(Facade facade) {
        this.facade = facade;
    }

    public void doSomeThing() {
        facade.MethodA();
        facade.MethodB();
    }

}


/**
 * 外观类
 */
class Facade {

    private SubSystemOne systemOne;
    private SubSystemTwo systemTwo;
    private SubSystemThree systemThree;
    private SubSystemFour systemFour;

    public Facade() {

        systemOne = new SubSystemOne();
        systemTwo = new SubSystemTwo();
        systemThree = new SubSystemThree();
        systemFour = new SubSystemFour();
    }

    public void MethodA() {

        System.out.println("方法组A---------");
        systemOne.MethodOne();
        systemThree.MethodThree();

    }

    public void MethodB() {

        System.out.println("方法组B-----------");
        systemTwo.MethodTwo();
        systemFour.MethodFour();

    }

}

/**
 * 子系统类
 */
class SubSystemOne {
    public void MethodOne() {
        System.out.println("子系统方法一");
    }
}

/**
 * 子系统类
 */

class SubSystemTwo {
    public void MethodTwo() {
        System.out.println("子系统方法二");
    }
}

/**
 * 子系统类
 */

class SubSystemThree {
    public void MethodThree() {
        System.out.println("子系统方法三");
    }
}

/**
 * 子系统类
 */

class SubSystemFour {
    public void MethodFour() {
        System.out.println("子系统方法四");
    }
}
