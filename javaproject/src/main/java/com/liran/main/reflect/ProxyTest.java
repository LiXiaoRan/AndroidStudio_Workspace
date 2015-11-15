package com.liran.main.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 这种代理方式只能用于接口
 * Created by LiRan on 2015-11-15.
 */
public class ProxyTest {

    public static void main(String[] args) {

        InvocationHandler invocationHandler = new InvocationHandler() {

            /**
             * 执行动态代理对象所有方法时，都会被替换成执行如下invoke方法，其中：
             * @param proxy 动态代理的对象
             * @param method 正在执行的方法
             * @param args 调用目标方法时传入的参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("正在执行的方法：" + method);
                if (args != null) {
                    System.out.println("参数是：");
                    for (Object obj : args) {
                        System.out.println(obj);
                    }

                } else {
                    System.out.println("调用该方法没有实参");
                }

                return null;
            }
        };


        /*Person person = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, invocationHandler);

        person.getAddr();
        person.setId(1);*/


        Myinterface myinterface= (Myinterface) Proxy.newProxyInstance(Myinterface.class.getClassLoader(),new Class[]{Myinterface.class},invocationHandler);

        myinterface.walk();
        myinterface.eat("羊肉串");
    }


}

interface Myinterface {
    void walk();
    void eat(String name);
}