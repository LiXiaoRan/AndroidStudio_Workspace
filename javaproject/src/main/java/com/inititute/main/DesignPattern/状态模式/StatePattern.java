package com.inititute.main.DesignPattern.状态模式;

/**
 * 状态模式
 * Created by LiRan on 2016-02-22.
 */
public class StatePattern {

    public static void main(String[] args) {

        Context context = new Context(new ConcreteStateA());

        //切换状态
        context.requst();
        context.requst();
        context.requst();

    }

}


abstract class State {

    public abstract void Handle(Context context);
}


class ConcreteStateA extends State {

    @Override
    public void Handle(Context context) {
        context.setState(new ConcreteStateB());
    }

    @Override
    public String toString() {
        return "ConcreteStateA";
    }
}

class ConcreteStateB extends State {

    @Override
    public void Handle(Context context) {
        context.setState(new ConcreteStateA());

    }

    @Override
    public String toString() {
        return "ConcreteStateB";
    }

}

class Context {

    private State state;

    public Context(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        System.out.println("现在的状态是：" + state.toString());
    }

    public void requst() {
        state.Handle(this);
    }
}