package com.liran.main.Stream;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * 通过实现Externalizable接口 进行自定义序列化
 * 通过这种方式来实现序列化那么被序列化的类的构造函数必须是public的，不然会出现恢复异常
 * 恢复对象的时候会调用默认的构造函数这一点和普通的序列化差别很大
 * 如果没有默认的构造函数那么在恢复的时候就会出现java.io.InvalidClassException异常
 * 通过这种方式依然没有解决序列化的问题
 * Created by liran on 2015-10-17.
 */
public class customSerizal {


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("data.out"));
        Persons persons1 = new Persons("你好a", 18);
        Persons persons2 = new Persons("你好b", 19);
        Persons persons3=new Persons("你好c",22);
        out.writeObject(persons1);
        out.writeObject(persons2);
        out.writeObject(persons3);
        persons3.setName("你不好啊");
        out.writeObject(persons3);
        out.close();

        System.out.println("-------------------下面是从序列化中恢复-------------------------------");

        ObjectInput in = new ObjectInputStream(new FileInputStream("data.out"));
        Persons inpersons = (Persons) in.readObject();
        Persons inpersons2 = (Persons) in.readObject();
        Persons inpersons3= (Persons) in.readObject();
        System.out.println(inpersons);
        System.out.println(inpersons2);
        System.out.println(inpersons3);
        in.close();
    }
}

class Persons implements Externalizable {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Persons() {
        System.out.println("Persons 默认的构造函数");
    }

    public Persons(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Persons 非默认的构造函数 name is " + name + " age is " + age);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(new StringBuffer(name).reverse());
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = ((StringBuffer) in.readObject()).reverse().toString();
        this.age = in.readInt();
    }


    @Override
    public String toString() {
        return "persion name is " + name + " age is " + age;
    }
}