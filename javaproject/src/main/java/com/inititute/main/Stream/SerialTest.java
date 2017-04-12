package com.inititute.main.Stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 序列化的一个问题
 * Created by liran on 2015-10-17.
 */
public class SerialTest {


    public static void main(String[] args) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("SerialTest.txt"));
             ObjectInputStream ois = new ObjectInputStream(new FileInputStream("SerialTest.txt"))
        ) {

            Person person = new Person(18, "God");
            //这里写入文件会对person对象进行序列化
            oos.writeObject(person);
            Person person1 = (Person) ois.readObject();
            person.setName("ghost");
            //这次再次写入文件就不会进行序列化了，所以修改的内容应该是没有被存入，可以看接下来的输出
            oos.writeObject(person);

            Person person2 = (Person) ois.readObject();


            System.out.println("person1==person2 ---> " + (person1 == person2));//输出true
            System.out.println("person2 name is : " + person2.getName());//输出的名字并没有变

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}


class Person implements Serializable {

    int age;
    String name;

    public Person() {
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
