package com.inititute.main.DesignPattern.组合模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式
 * Created by LiRan on 2016-02-27.
 */
public class Composition_Pattern {

    public static void main(String[] args) {

        //生成树根root，树根上长出了两片树叶
        Composite root = new Composite("root");
        root.Add(new Leaf("Leaf A"));
        root.Add(new Leaf("Leaf B"));

        //树根上生出了树枝Composite X，树枝Composite X上又长出了树叶
        Composite com = new Composite("Composite X");
        com.Add(new Leaf("Leaf XA"));
        com.Add(new Leaf("Leaf XB"));
        root.Add(com);

        //树枝Composite X上又生出了树枝Composite XY，这个树枝上也长出了树叶
        Composite com2 = new Composite("Composite XY");
        com2.Add(new Leaf("Leaf XYA"));
        com2.Add(new Leaf("Leaf XYB"));
        com.Add(com2);

        //树根上又长出了Leaf C和Leaf D ，Leaf D又脱落了
        root.Add(new Leaf("Leaf C"));
        Leaf leaf = new Leaf("Leaf D");
        root.Add(leaf);
        root.Remove(leaf);

        root.Display(1);

    }


}


abstract class Compenent {

    protected String name;


    public Compenent(String name) {
        this.name = name;
    }

    public abstract void Add(Compenent c);

    public abstract void Remove(Compenent c);

    public abstract void Display(int depth);

}


class Leaf extends Compenent {


    public Leaf(String name) {
        super(name);
    }

    @Override
    public void Add(Compenent c) {
        System.out.println("不能添加叶子");
    }

    @Override
    public void Remove(Compenent c) {
        System.out.println("不能删除叶子");
    }

    @Override
    public void Display(int depth) {
        System.out.println("-" + depth + "  "+name);

    }
}

class Composite extends Compenent {

    /**
     * 一个对象的集合，用来存储其下属的枝节点也叶节点
     */
    private List<Compenent> children = new ArrayList<>();

    public Composite(String name) {
        super(name);
    }

    @Override
    public void Add(Compenent c) {
        children.add(c);
    }

    @Override
    public void Remove(Compenent c) {
        children.remove(c);
    }

    @Override
    public void Display(int depth) {

        //显示其枝节点名称，并对夏季进行遍历
        System.out.println("-" + depth + "  "+name);

        for (Compenent c : children) {
            c.Display(depth + 1);
        }
    }
}


