package com.liran.main.reflect;

import com.liran.main.Bean.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过construct创建对象 执行私有和共有的Method方法 修改private变量
 * Created by LiRan on 2015-11-15.
 */
public class ReflectTest {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        Class<?> Personclass=Person.class;

        //获取指定的构造函数
        Constructor percon=Personclass.getConstructor(int.class,String.class,double.class,String.class);

        //通过construct创建对象
        Person person= (Person) percon.newInstance(1, "哈哈", 3.0, "随便填的");

        System.out.println(person);

        //获取指定的Method并执行
        Method method=Personclass.getMethod("getName",null);
        System.out.println("getname is " + method.invoke(person, null));

        Method method2=Personclass.getMethod("setName",String.class);
        method2.invoke(person, "小明");
        System.out.println("----------改名字之后------------");
        System.out.println(person);

        //调用类的私有方法  通过getDeclaredMethod
        Method method3=Personclass.getDeclaredMethod("test",String.class);
        method3.setAccessible(true);//跳过对这个方法权限检查，执行私有方法
        method3.invoke(person,"Hello");


        //修改private变量  获取Addr这个私有的变量
        Field field=Personclass.getDeclaredField("Addr");
        field.setAccessible(true);//跳过对这个变量的权限检查
        field.set(person, "南阳理工啊");
        System.out.println("----------改私有变量之后------------");
        System.out.println(person);

    }


}
