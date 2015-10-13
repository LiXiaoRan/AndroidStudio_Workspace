package com.liran.main.annotation;

/**
 * Created by liran on 2015-10-13.
 */
public class MyTest {

    @Testable
    public static void m1() {

    }

    public static void m2() {

    }

    @Testable
    public static void m3() {
        throw new RuntimeException("args is error m333 ^-^ ");
    }

    public static void m4() {

    }

    @Testable
    public static void m5() {

    }

    public static void m6() {

    }

    @Testable
    public static void m7() {
        throw new RuntimeException("progream error m77777 ");
    }

    public static void m8() {

    }


}
