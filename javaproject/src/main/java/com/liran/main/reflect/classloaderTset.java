package com.liran.main.reflect;

/**
 * 类加载器
 * Created by LiRan on 2015-11-10.
 */
public class classloaderTset {


    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        classLoader.loadClass("com.liran.main.reflect.test");//并没有加载类
        System.out.println("----------上面调用了类加载器-----------------");
        Class.forName("com.liran.main.reflect.test");//这一句才会加载类

    }

}

class test {
    static {
        System.out.println("加载了test类");
    }
}
