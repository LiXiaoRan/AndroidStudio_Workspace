package com.inititute.main.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * AOP代理
 * Created by LiRan on 2015-11-15.
 */
public class denymicProxy {

    public static void main(String[] args) {

        Dog target=new GunDog();
        Dog dog= (Dog) MyProxyFactory.getProxyu(target);
        //此处的dog其实是一个动态代理对象，动态代理对象无论执行任何方法都是执行handler中的invoke方法
        dog.info();
        dog.run();
    }
}

class MyProxyFactory {

    /**
     * 为指定的target生成动态代理对象
     * @param target
     * @return
     */
    public static Object getProxyu(Object target) {
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.setTarget(target);
        //创建并返回一个动态代理
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
    }

}


class MyInvocationHandler implements InvocationHandler {

    /**
     * 需要被代理的对象
     */
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        DogUtil du = new DogUtil();
        du.method1();
        Object result = method.invoke(target, args);
        du.method2();
        return result;
    }
}

interface Dog {
    void info();

    void run();
}


class GunDog implements Dog {


    @Override
    public void info() {
        System.out.println("猎狗");
    }

    @Override
    public void run() {
        System.out.println("善于奔跑啊");
    }
}


class DogUtil {

    //第一个拦截器方法
    public void method1() {
        System.out.println("================模拟第一个通用方法==============");
    }

    //第二个拦截器方法
    public void method2() {
        System.out.println("================模拟第二个通用方法================");
    }


}