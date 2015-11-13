package com.liran.main.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

/**
 * java8反射新方法
 * Created by LiRan on 2015-11-13.
 */
public class MethodParamterTest {

    public static void main(String[] args) throws NoSuchMethodException {

        Class<mytest> clazz=mytest.class;
        Method method=clazz.getMethod("replace",String.class,List.class);
        System.out.println("replace方法的形参个数: "+method.getParameterCount());

        //获取replace方法的所有参数信息
        Parameter[] parameters=method.getParameters();

        int index=1;
        //遍历所有参数
        for (Parameter p:parameters) {
            System.out.println("---------------第"+(index++)+"个参数--------------");
            System.out.println("参数名："+p.getName());
            System.out.println("参数类型："+p.getType());
            System.out.println("参数泛型："+p.getParameterizedType());
        }

    }

}


class mytest{
    public void replace(String str,List<String> stringList){}
}
