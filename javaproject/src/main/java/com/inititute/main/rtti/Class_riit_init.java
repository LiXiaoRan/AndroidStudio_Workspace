package com.inititute.main.rtti;

/**
 * 这个类主要是测试一下.class获取class对象和Class.forName获取class获取对象的区别
 * Created by liran on 2015-10-08.
 */
public class Class_riit_init {

    public static void main(String[] args) {
        Class initable = Initable.class;
        System.out.println("after creater initable done");
        System.out.println("Initable.satticfinal= " + Initable.satticfinal);
        System.out.println("Initable.satticfinal2= " + Initable.satticfinal2);

        System.out.println("");
        System.out.println("Initable2.satticfinal= " + Initable2.satticfinal);


        System.out.println("");
        try {
            Class initable3 = Class.forName("com.liran.main.rtti.Initable3");  //一定是包名+类名
            initable3.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}

class Initable {
    static final int satticfinal = 47;
    static final int satticfinal2 = (int) (Math.random() * 10);

    static {
        System.out.println("initializing Initable");
    }

    public Initable() {
        System.out.println("Initable constructor");
    }
}

class Initable2 {
    static int satticfinal = 147;

    static {
        System.out.println("initializing Initable222");
    }

    public Initable2() {
        System.out.println("Initable222 constructor");
    }
}

class Initable3 {
    static int satticfinal = 258;

    static {
        System.out.println("initializing Initable333");
    }

    public Initable3() {
        System.out.println("Initable333 constructor");
    }
}






