package com.liran.main.annotation;


@Inheritable
class InheritedTestBase {

}

/**
 * Inherited具有继承性
 * Created by liran on 2015-10-11.
 */
public class InheritedTest extends InheritedTestBase {

    public static void main(String[] args) {
        //打印当前类是否有Inheritable修饰
        System.out.println(InheritedTest.class.isAnnotationPresent(Inheritable.class));
    }


}

