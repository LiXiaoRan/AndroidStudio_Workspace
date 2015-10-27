package com.liran.main.collection;

/**
 * Created by Lenovo on 2015-10-27.
 */
public class HashCodeTest {

    private int number;

    public HashCodeTest(int number) {
        this.number = number;
    }

    public HashCodeTest() {
    }

    public static void main(String[] args) {

        HashCodeTest hashCodeTest = new HashCodeTest();
        //这里输出的是getClass().getName() + '@' + Integer.toHexString(hashCode())
        System.out.println("hashCodeTest is " + hashCodeTest);

        HashCodeTest hashCodeTest1 = new HashCodeTest(1);
        HashCodeTest hashCodeTest2 = new HashCodeTest(1);

        System.out.println("hashCodeTest1.equals(hashCodeTest2) : " + hashCodeTest1.equals(hashCodeTest2));

    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {

        return obj instanceof HashCodeTest && ((HashCodeTest) obj).number == number;

    }
}
