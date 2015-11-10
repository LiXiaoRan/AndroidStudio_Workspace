package com.liran.main.reflect;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * 访问jvm类加载器
 * Created by LiRan on 2015-11-10.
 */
public class ClassLoaderPropTest {

    public static void main(String[] args) throws IOException {

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器：" + systemClassLoader);
        /*系统类加载器的路径通常有CLASSPATH指定，如果没有指定，默认当前路径。*/
        Enumeration<URL> eml = systemClassLoader.getResources("");
        while (eml.hasMoreElements()) {
            System.out.println(eml.nextElement());
        }

        //获取系统类加载器的父类  ---》扩展类加载器
        ClassLoader extendsLoader = systemClassLoader.getParent();
        System.out.println("扩展类加载器：" + extendsLoader);
        System.out.println("扩展类加载器的加载路径：" + System.getProperty("java.ext.dirs"));
        //扩展类加载器的父类应该是根类加载器但是这里返回NULL 那是因为根类加载器不是java实现的而且是没有继承classloader的抽象类
        System.out.println("扩展类加载器的父类：" + extendsLoader.getParent());
    }


}
