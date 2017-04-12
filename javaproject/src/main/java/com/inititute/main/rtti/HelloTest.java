package com.inititute.main.rtti;

/**
 * Created by liran on 2015-10-06.
 */
public class HelloTest {

    public static void main(String[] args) {

        Shape s = new Circle();
        s.draw();

    }

}


abstract class Shape {
    public void draw() {
        System.out.println("shape.draw");
    }

    abstract public String toString();

}

class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Circle.draw");
    }

    @Override
    public String toString() {
        return "Circle";
    }
}

class Square extends Shape {
    @Override
    public void draw() {
        System.out.println("Square.draw");
    }

    @Override
    public String toString() {
        return "Square";
    }
}

class Trangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Trangle.draw");
    }

    @Override
    public String toString() {
        return "Trangle";
    }
}

