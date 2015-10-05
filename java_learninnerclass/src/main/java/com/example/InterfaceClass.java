package com.example;

/**
 * 写在接口中的内部类 可以实现其外部的接口
 * Created by liran on 2015-10-05.
 */
public interface InterfaceClass {
    void fun();

    class innerClass implements InterfaceClass {

        @Override
        public void fun() {
            System.out.println("fun called");
        }

        public static void main(String[] args) {
            new InterfaceClass.innerClass().fun();  //由于写在接口内的都是public static的，所以内部类也不例外
        }
    }
}
