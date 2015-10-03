package com.example;

/**
 * 内部类在方法中
 * Created by liran on 2015-10-03.
 */
public class Parcel5 {

    public Destination destination(String string) {
        class PDestination implements Destination {
            private String label;

            public PDestination(String label) {
                this.label = label;
            }

            @Override
            public String readLabel() {
                return label;
            }
        }
        return new PDestination(string);
    }


    public static void main(String[] args) {
        System.out.println(new Parcel5().destination("hello").readLabel());
    }


}
