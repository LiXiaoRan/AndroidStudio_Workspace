package com.example;

/**
 *
 */
public class MyClass {

    public static void main(String[] args) {

        Parcel p = new Parcel();
        Contents c = p.contents();
        Destination d = p.destination("hello ");
        Parcel.PDestination PD = p.new PDestination("hello2");//内部类必须和外部类的对象想关联
        System.out.println(d.readLabel());
        System.out.println(PD.readLabel());
    }


}
