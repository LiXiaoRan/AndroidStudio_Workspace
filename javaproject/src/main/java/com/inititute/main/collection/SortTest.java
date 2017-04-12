package com.inititute.main.collection;

import com.inititute.main.Bean.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * 这里演示的排序方法是通过bean实现Comparable接口来实现排序的
 * Created by Lenovo on 2015-10-27.
 */
public class SortTest {


    public static void main(String[] args) {

        List<Person> listbean = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person(i, "我是" + i, new Random().nextDouble() * 100, "圣诞节卡号到家咯回到家咯" + i);
            listbean.add(person);
        }


        Collections.shuffle(listbean);  //随机一下顺序


        for (Iterator it = listbean.iterator(); it.hasNext(); ) {
            Person p = (Person) it.next();
            System.out.println(p);
        }


        System.out.println("-----------------排序后-----------------------");

        //如果调用这个方法listbean中的bean必须实现Comparable接口，并实现其中的方法，这一句自动调用那个方法进行比较
        Collections.sort(listbean);


        for (Iterator it = listbean.iterator(); it.hasNext(); ) {
            Person p = (Person) it.next();
            System.out.println(p);
        }


    }


}
