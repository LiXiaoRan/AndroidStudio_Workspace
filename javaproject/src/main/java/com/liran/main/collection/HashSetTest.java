package com.liran.main.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Lenovo on 2015-10-26.
 */
public class HashSetTest {

    public static void main(String[] args) {
        Set<String> myset = new HashSet<>();
        myset.add("ccc");
        myset.add("aaa");
        myset.add("bbb");
        myset.add("aaa");//set不会存储重复数据
        for (Iterator set = myset.iterator(); set.hasNext(); ) {
            String str = (String) set.next();
            System.out.println("set is :" + str);
        }

        System.out.println("----------------------------------------------------------------");

        Set<String> intSet = new TreeSet<>();
        intSet.add("g");
        intSet.add("a");
        intSet.add("z");
        intSet.add("c");//TreeSet会对数据进行排序
        for (Iterator intset = intSet.iterator(); intset.hasNext(); ) {
            String str = (String) intset.next();
            System.out.println("intSet is " + str);
        }

    }

}
