package com.example;

/**
 * 内部类的继承测试
 * Created by liran on 2015-10-05.
 */
public class ExtendInnerClass extends WithInner.Inner {

    public ExtendInnerClass(WithInner withInner) {
        withInner.super();// 必须调用被继承的那个内部类的外部类的构造方法

        System.out.println("called");
    }

    public static void main(String[] args) {
        WithInner wi = new WithInner();
        ExtendInnerClass extendInnerClass = new ExtendInnerClass(wi);
    }
}

class WithInner {
    class Inner {
    }
}
