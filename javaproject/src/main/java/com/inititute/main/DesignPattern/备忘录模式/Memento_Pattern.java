package com.inititute.main.DesignPattern.备忘录模式;

/**
 * 备忘录模式
 * Created by LiRan on 2016-02-27.
 */
public class Memento_Pattern {

    public static void main(String[] args) {


        Originator originator=new Originator();
        originator.setState("存档前");
        originator.show();
        System.out.println("---------------存档-------------");

        CareTaker careTaker=new CareTaker();
        careTaker.setMemento(originator.createMemento());//存档

        System.out.println("-------------修改数据------------");
        originator.setState("修改数据成功了");
        originator.show();

        System.out.println("---------------还原--------------");
        originator.setMemento(careTaker.getMemento());
        originator.show();

    }
}


/**
 * 发起人，就是需要保存状态的类
 */
class Originator {

    /**
     * 代表发起人中所有要被存储的数据
     */
    private String state;


    /**
     * 保存数据到备忘录中
     * @return 需要保存的数据的备忘录
     */
    public Memento createMemento(){
        return new Memento(state);
    }

    /**
     * 根据传入的备忘录重置状态。
     * @param memento 传入的备忘录
     */
    public void setMemento(Memento memento){
            state=memento.getState();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void show(){
        System.out.println("state 数据是："+state);
    }
}

/**
 * 备忘录
 */
class Memento {

    /**
     * 代表发起人中所有要被存储的数据
     */
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}


/**
 * 管理者，用来管理备忘录
 */
class CareTaker{


    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}