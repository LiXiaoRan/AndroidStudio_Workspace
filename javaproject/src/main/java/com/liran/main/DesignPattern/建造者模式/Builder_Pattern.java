package com.liran.main.DesignPattern.建造者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式
 * Created by LiRan on 2016-02-19.
 */
public class Builder_Pattern {

    public static void main(String[] args) {

        Director director = new Director();

        Builder builderA = new ConcreateBuilderA();
        director.Construct(builderA);
        Product productA = builderA.getResult();
        productA.show();

        Builder builderB = new ConcreateBuilderB();
        director.Construct(builderB);
        Product productB = builderB.getResult();
        productB.show();

    }


}


class Director {

    public void Construct(Builder builder) {
        builder.BuildPatrA();
        builder.BuildPatrB();
    }


}

/**
 * Builder是为创建一个Product
 * 对象的各个部件制定的抽象接口
 */
abstract class Builder {

    public Builder() {
    }

    public abstract void BuildPatrA();

    public abstract void BuildPatrB();

    public abstract Product getResult();

}


class ConcreateBuilderA extends Builder {

    private Product product = new Product();

    @Override
    public void BuildPatrA() {
        product.addPart(" 部件A ");
    }

    @Override
    public void BuildPatrB() {
        product.addPart(" 部件B ");
    }

    @Override
    public Product getResult() {
        return product;
    }
}


class ConcreateBuilderB extends Builder {

    private Product product = new Product();

    @Override
    public void BuildPatrA() {
        product.addPart("部件X");
    }

    @Override
    public void BuildPatrB() {
        product.addPart("部件Y");
    }

    @Override
    public Product getResult() {
        return product;
    }
}

/**
 * 产品
 */
class Product {
    private List<String> data = new ArrayList<>();

    public void addPart(String part) {
        data.add(part);
    }

    public void show() {
        for (String part : data) {
            System.out.println("产品：" + part);
        }
    }

}


