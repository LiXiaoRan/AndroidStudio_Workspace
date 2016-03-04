package com.liran.main.DesignPattern.享元模式;

/**
 * 享元模式
 * Created by LiRan on 2016-03-04.
 */
public class Flyweight_pattern {

    public static void main(String[] args) {



    }

}

/**
 * 所有享元类的超类或者接口，通过这个接
 * 口，Flyweight可以接受并作用于外部状态
 */
abstract class Flyweight {

    public abstract void Operation(int extrinsicstate);

}

/**
 * 继承Flyweight超类或实现Flyweight接口
 * ，并未内部状态增加存储空间。
 */
class ConcreteFlyweight extends Flyweight {

    @Override
    public void Operation(int extrinsicstate) {
        System.out.println("具体的Flyweight：" + extrinsicstate);

    }
}

/***
 * 指那些不需要共享的Flyweight子类。
 * 因为Flyweight接口共享成为可能，
 * 但它并不强制共享。
 */
class UnsharedConcreteFlyweight extends Flyweight {

    @Override
    public void Operation(int extrinsicstate) {
        System.out.println("不共享的Flyweight：" + extrinsicstate);
    }
}

class FlyweightFactory {


}