package com.liran.main.DesignPattern.享元模式;

import java.util.HashMap;

/**
 * 享元模式
 * Created by LiRan on 2016-03-04.
 */
public class Flyweight_pattern {

    public static void main(String[] args) {


        FlyweightFactory f = new FlyweightFactory();

        Flyweight flyweight1 = f.getFlyweight("X");
        flyweight1.Operation(1);

        Flyweight flyweight2 = f.getFlyweight("y");
        flyweight2.Operation(2);


        Flyweight flyweight3 = f.getFlyweight("z");
        flyweight3.Operation(3);

        UnsharedConcreteFlyweight uf = new UnsharedConcreteFlyweight();
        uf.Operation(44);

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

/**
 * 一个享元工厂，用来创建并管理Flyweight对象。它主要用
 * 来确保合理的共享flyweight，当用户请求一个Flyweight
 * 时，FlyweightFactory对象提供一个已创建的实例或者创建
 * 一个（如果不存在的话）。
 */

class FlyweightFactory {

    private HashMap<String, Flyweight> flyweights = new HashMap<>();


    public FlyweightFactory() {
        flyweights.put("X", new ConcreteFlyweight());
        flyweights.put("y", new ConcreteFlyweight());

    }

    public Flyweight getFlyweight(String key) {

        if(!flyweights.containsKey(key)){
            flyweights.put(key,new ConcreteFlyweight());
        }

        return flyweights.get(key);
    }

}