package com.liran.main.Stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 这里测试了一个很神奇的方法，就是通过implements Serializable也可以自定义序列化，
 * 就是在实现了这个接口的类中定义writeObject和readObject方法，必须严格按照我的范例中的格式,
 * 然后ObjectOutputStream的writeObject方法和ObjectInputStream的readObject会检查这个Serializable
 * 是否有自己的方法，如果有就调用它自己的。
 * Created by LiRan on 2015-11-04.
 */
public class SerializableReplaceTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Pig pig = new Pig(1, 2);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("data.txt"));
        outputStream.writeObject(pig);
        outputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("data.txt"));
        Pig pig1 = (Pig) objectInputStream.readObject();
        System.out.println(pig1);
        objectInputStream.close();
    }


}

class Pig implements Serializable {
    int weight;
    int heigh;


    private void writeObject(ObjectOutputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultWriteObject();//这里为了省事直接调用了默认的序列化方法，当然也可以自己实现这个方法
        System.out.println("自定义的writeObject");
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();//这里为了省事直接调用了默认的序列化方法，当然也可以自己实现这个方法
        System.out.println("自定义的readObject");
    }

    public Pig() {
        System.out.println("Pig的默认构造函数");
    }

    public Pig(int weight, int heigh) {
        this.weight = weight;
        this.heigh = heigh;
    }

    @Override
    public String toString() {
        return "pig weight is " + weight + " heigh is " + heigh;
    }
}
