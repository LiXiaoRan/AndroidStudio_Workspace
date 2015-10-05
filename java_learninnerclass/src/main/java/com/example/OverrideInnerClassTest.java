package com.example;

/**
 * 内部类被继承时，子类不能覆盖父类的内部类,除非是子类的内部类明确的继承某个父类的内部类
 * Created by liran on 2015-10-05.
 */
public class OverrideInnerClassTest extends Egg {

    //这里尝试覆盖父类的内部类
    protected class Yolk {
        public Yolk() {
            System.out.println("OverrideInnerClassTest.yolk");
        }
    }

    public static void main(String[] args) {
        new OverrideInnerClassTest();

    }


}


class Egg {
    protected class Yolk {
        public Yolk() {
            System.out.println("egg.yolk");
        }
    }

    private Yolk y;


    public Egg() {
        System.out.println("new Egg");
        y = new Yolk();
    }
}