package com.inititute.main.reflect;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 测试通过Class对象来获取详细信息
 * Created by LiRan on 2015-11-12.
 */

//定义可重复注解
@Repeatable(Annos.class)
@interface Anno {
}

@Retention(value = RetentionPolicy.RUNTIME)
@interface Annos {
    Anno[] value();
}


//使用可重复的注解
@Anno
@Anno
public class ClassTest {


    public ClassTest(String name) {
        System.out.println("有参数的构造函数: " + name);
    }

    public ClassTest() {
        System.out.println("没有参数的构造函数");
    }

    public void info() {
        System.out.println("有参数的info函数");
    }

    public void info(String info) {
        System.out.println("无参数的info函数 info: " + info);
    }

    class Inner {

    }

    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {

        //获取ClassTest类的class对象
        Class<ClassTest> clazz = ClassTest.class;

        //获取所有的构造器
        Constructor<?>[] constructorlist = clazz.getDeclaredConstructors();

        System.out.println("ClassTest类的全部构造函数如下：");
        for (Constructor c : constructorlist) {
            System.out.println(c);
        }

        //获取所有的public构造器
        Constructor<?>[] publicconstructorlist = clazz.getDeclaredConstructors();
        System.out.println("ClassTest类的public构造函数如下：");
        for (Constructor pubc : constructorlist) {
            System.out.println(pubc);
        }


        //获取所有的public方法
        Method[] methods = clazz.getDeclaredMethods();
        System.out.println("ClassTest类的全部public函数如下");
        for (Method pubm : methods) {
            System.out.println(pubm);
        }


        //获取指定方法
        System.out.println("ClassTest里带一个字符串参数的info方法: " + clazz.getMethod("info", String.class));


        //注解
        Annotation[] annotations = clazz.getAnnotations();
        System.out.println("所有的注解：");
        for (Annotation ann : annotations) {
            System.out.println(ann);
        }


        //内部类
        Class<?>[] classes=clazz.getDeclaredClasses();
        System.out.println("所有的内部类：");
        for (Class<?> cll : classes) {
            System.out.println(cll);
        }

        Class<?> inclazz=Class.forName("com.liran.main.reflect.ClassTest$Inner");
        System.out.println("ClassTest$Inner对应的外部类是："+inclazz.getDeclaredClasses());

    }

}
