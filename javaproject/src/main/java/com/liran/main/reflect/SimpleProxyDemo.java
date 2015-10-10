package com.liran.main.reflect;

/**
 * 静态代理
 * Created by liran on 2015-10-10.
 */
public class SimpleProxyDemo {

    public static void consumer(Interface Interface) {
        Interface.doSomething();
        Interface.somethingElse("ha ha ha ha ha");
    }

    public static void main(String[] args) {
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }

}

interface Interface {
    void doSomething();

    void somethingElse(String string);
}


class RealObject implements Interface {

    public RealObject() {
        System.out.println("RealObject construct");
    }

    @Override
    public void doSomething() {
        System.out.println("RealObject ---> doSomething");
    }

    @Override
    public void somethingElse(String string) {
        System.out.println("RealObject--->  somethingElse  " + string);
    }
}

class SimpleProxy implements Interface {

    private Interface proxied;

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
        System.out.println("SimpleProxy construct ");
    }

    @Override
    public void doSomething() {
        System.out.println("SimpleProxy ---> doSomething");
    }

    @Override
    public void somethingElse(String string) {
        System.out.println("SimpleProxy--->  somethingElse  " + string);
    }
}




