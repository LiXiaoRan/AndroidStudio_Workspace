package com.inititute.main.annotation;

/**
 * Created by liran on 2015-10-13.
 */
public class RunTest {

    public static void main(String[] args) {
        try {
            ProcessorTest.process("com.liran.main.annotation.MyTest");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
