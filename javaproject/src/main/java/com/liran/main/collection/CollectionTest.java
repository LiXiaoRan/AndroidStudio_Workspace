package com.liran.main.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by liran on 2015-10-05.
 */
public class CollectionTest {

    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        print(collection);
        Integer[] moreints = {6, 7, 8, 9, 10};
        collection.addAll(Arrays.asList(moreints));
        print(collection);
        Collections.addAll(collection, 100, 101, 102, 103);
        print(collection);
        Collections.addAll(collection, moreints);
        print(collection);


        List<Integer> list = Arrays.asList(16, 17, 18, 19, 20);
        list.set(1, 99);  //把1号位置的元素替换为99
//        list.add(21);//直接通过Arrays.asList输出将其当作list，底层表示是数组 不能改变尺寸 因此add或者delete都会报错
        print(list);
    }

    public static void print(Collection<Integer> collection) {
        for (Integer i : collection) {
            System.out.printf(" " + i + " ");
        }
        System.out.println("");
    }
}
