package com.inititute.main.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by liran on 2015-10-09.
 */
public class MainTest {

    /***
     * 将src的集合元素拷贝到dest中并且返回最后一个拷贝的元素
     *
     * @param dest
     * @param src
     * @param <T>
     * @return
     */
    public static <T> T copy(Collection<T> dest, Collection<? extends T> src) {

        T last = null;
        for (T t : src) {
            last = t;
            dest.add(t);
        }
        return last;
    }


    /***
     * 将src的集合元素拷贝到dest中并且返回最后一个拷贝的元素
     *
     * @param dest
     * @param src
     * @param <T>
     * @return
     */
    public static <T> T copy2(Collection<? super T> dest, Collection<T> src) {

        T last = null;
        for (T t : src) {
            last = t;
            dest.add(t);
        }
        return last;
    }


    public static void main(String[] args) {

        List<Number> ln = new ArrayList<>();
        List<Integer> li = new ArrayList<>();
        //设置泛型上限
//        Integer last =  copy(ln, li);   //由于dest必须是number类型，所以T是number，返回值也是number，而不是integer，所以此句报错。
        //设置泛型下限
        Integer last = copy2(ln, li);
    }


}
