package com.liran.main.smallfind;

/**
 * 发现了可以定义形参个数可变的方法
 * Created by liran on 2015-10-11.
 */
public class parameterChangeableMethod {


    public static void method(String... names) {
        for (String name : names) {
            System.out.println("name= " + name);
        }
        System.out.println("---------------------------------------");
    }


    public static void methodArray(String[] names) {
        for (String name : names) {
            System.out.println("name= " + name);
        }
        System.out.println("---------------------------------------");

    }


    public static void main(String[] args) {
        System.out.println("parmeter changeable method --------------------");
        String[] names = {"aaa", "bbb", "ccc"};
        method("aaa", "bbb", "ccc");
        method(names);

        System.out.println("parmeter not changeable method --------------------");
        methodArray(names);

    }


}
