package com.inititute.main.Stream;

import java.nio.CharBuffer;

/**
 * Created by liran on 2015-10-18.
 */
public class BufferTest {

    public static void main(String[] args) {
        //创建buffer
        CharBuffer buffer = CharBuffer.allocate(8);
        System.out.println("capacity is " + buffer.capacity());
        System.out.println("limit is " + buffer.limit());
        System.out.println("position is " + buffer.position());
        //放入元素
        buffer.put('a');
        buffer.put('b');
        buffer.put('c');
        System.out.println("after add 3 element :--->positon :" + buffer.position());
        System.out.println("after add 3 element :--->limit is " + buffer.limit());

        //调用filp方法 是为了取出数据而做的准备

        buffer.flip();
        System.out.println("after execution filp method:--->positon :" + buffer.position());
        System.out.println("after execution filp method:--->limit is " + buffer.limit());


        //取出第一个元素
        System.out.println("first element is " + buffer.get());
        System.out.println("after get first element position is " + buffer.position());

        //调用clear方法   为了重新装入数据 但是并没有删除之前的数据
        buffer.clear();
        System.out.println("after execution clear method:---->position: " + buffer.position());
        System.out.println("after execution clear method:---->limit is : " + buffer.limit());

        //输出之前的数据
        System.out.println(" element2 is " + buffer.get(2));
        //执行绝对读取后的position
        System.out.println();
        System.out.println("position is " + buffer.position());


    }

}
