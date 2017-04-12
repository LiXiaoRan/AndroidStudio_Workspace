package com.inititute.main.DesignPattern.迭代器模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 迭代器模式
 * Created by LiRan on 2016-03-01.
 */
public class Iterator_pattern {

    public static void main(String[] args) {

        ConcreteAggregate aggregate=new ConcreteAggregate();
        aggregate.set(0,"哈哈0");
        aggregate.set(1,"哈哈1");
        aggregate.set(2,"哈哈2");
        aggregate.set(3,"哈哈3");
        aggregate.set(4,"哈哈4");
        aggregate.set(5,"哈哈5");

        Iterator i=new ConcreteIterator(aggregate);

        Object item=i.First();
        while (!i.IsDone()){
            System.out.println(i.CurrentItem()+"请买票");
            i.Next();
        }

    }

}


/**
 * Iterator迭代器抽象类
 */
abstract class Iterator {

    public abstract Object First();

    public abstract Object Next();

    public abstract boolean IsDone();

    public abstract Object CurrentItem();

}

/**
 * Aggregate抽象聚集类
 */
abstract class Aggregate {

    public abstract Iterator CreateIterator();

}

/**
 * ConcreteIterator具体迭代器类，集成Iterator
 */
class ConcreteIterator extends Iterator {

    private ConcreteAggregate aggregate;
    private int current = 0;

    public ConcreteIterator(ConcreteAggregate aggregate) {
        this.aggregate = aggregate;
    }

    @Override
    public Object First() {
        return aggregate.get(0);
    }

    @Override
    public Object Next() {
        Object rect = null;
        current++;
        if (current < aggregate.count()) {
            rect = aggregate.get(current);
        }
        return rect;
    }

    @Override
    public boolean IsDone() {
        return current >= aggregate.count() ? true : false;
    }

    @Override
    public Object CurrentItem() {
        return aggregate.get(current);
    }
}

/**
 * 具体聚集类
 */
class ConcreteAggregate extends Aggregate {

    private List<Object> items = new ArrayList<>();

    @Override
    public Iterator CreateIterator() {
        return new ConcreteIterator(this);
    }

    public int count() {
        return items.size();
    }

    public Object get(int idex) {
        return items.get(idex);
    }

    public void set(int idex, Object o) {
        items.add(idex,o);
    }

}
