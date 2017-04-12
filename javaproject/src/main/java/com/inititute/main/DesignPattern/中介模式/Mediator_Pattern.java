package com.inititute.main.DesignPattern.中介模式;

/**
 * 中介者模式
 * Created by LiRan on 2016-03-04.
 */
public class Mediator_Pattern {

    public static void main(String[] args) {


        ConcreteMediator mediator = new ConcreteMediator();

        ConcreteColleague1 colleague1 = new ConcreteColleague1(mediator);
        ConcreteColleague2 colleague2 = new ConcreteColleague2(mediator);

        mediator.setColleague1(colleague1);
        mediator.setColleague2(colleague2);

        colleague1.send("你吃饭了吗？");
        colleague2.send("你猜猜");

    }

}

/**
 * 抽象中介者类
 */
abstract class Mediator {

    /***
     * 定义一个抽象的发送消息的方法
     *
     * @param message   消息
     * @param colleague 同事对象
     */
    abstract public void send(String message, Colleague colleague);

}

/**
 * 抽象同事类
 */
abstract class Colleague {

    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }


}

/**
 * 具体的中介者
 */
class ConcreteMediator extends Mediator {

    private ConcreteColleague1 colleague1;
    private ConcreteColleague2 colleague2;

    public void setColleague1(ConcreteColleague1 colleague1) {
        this.colleague1 = colleague1;
    }

    public void setColleague2(ConcreteColleague2 colleague2) {
        this.colleague2 = colleague2;
    }

    @Override
    public void send(String message, Colleague colleague) {

        if (colleague == colleague1) {
            colleague1.Notify(message);
        } else {
            colleague2.Notify(message);
        }

    }
}


/**
 * 具体的同事类
 */
class ConcreteColleague1 extends Colleague {

    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    public void Notify(String msg) {
        System.out.println("同事1得到消息：" + msg);
    }

    public void send(String msg) {
        mediator.send(msg, this);
    }

}

/**
 * 具体的同事类
 */
class ConcreteColleague2 extends Colleague {

    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }

    public void Notify(String msg) {
        System.out.println("同事2得到消息：" + msg);
    }

    public void send(String msg) {
        mediator.send(msg, this);
    }
}

