package com.inititute.main.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by liran on 2015-10-13.
 */
public class TimeHandler implements InvocationHandler {

    private Object target;

    public TimeHandler(Object object) {
        this.target = object;

    }


    /**
     * @param proxy  被代理的对象
     * @param method 被代理的对象方法
     * @param args   被代理的对象的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long starttime = System.currentTimeMillis();
        System.out.println("car start");
        method.invoke(target);
        long endtime = System.currentTimeMillis();
        System.out.println("car end   driver time is " + (endtime - starttime) + "ms");
        return null;
    }
}
