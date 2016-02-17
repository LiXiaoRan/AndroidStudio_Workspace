package com.liran.main.DesignPattern.原型模式;

/**
 * 原型模式
 * Created by LiRan on 2016-01-19.
 */
public class Prototype_Test {




    public static void main(String[] args) {

        myPersionPrototype xiaoming=new myPersionPrototype("小明",18);
        myPersionPrototype xiaohong= (myPersionPrototype) xiaoming.Clone();
        xiaohong.setName("小红");
        System.out.println(xiaoming.toString());
        System.out.println(xiaohong.toString());

        if(xiaohong!=xiaoming){
            System.out.println("xiaohong!=xiaoming");
        }

        if(xiaohong.getClass()==xiaoming.getClass()){
            System.out.println("xiaohong.getClass()==xiaoming.getClass()");
        }

        System.out.println(xiaohong.hashCode()+"     "+xiaoming.hashCode());

    }

}


class myPersionPrototype implements Prototype ,Cloneable{


    private String name;
    private int age;

    @Override
    public Prototype Clone() {


        try {
            return (Prototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public myPersionPrototype(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "姓名："+name+" 年龄："+age;
    }
}


interface Prototype  {
    public Object Clone();
}


