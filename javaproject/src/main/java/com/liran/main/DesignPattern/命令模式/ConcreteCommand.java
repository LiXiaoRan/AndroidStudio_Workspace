package com.liran.main.DesignPattern.命令模式;

/**
 * 具体的命令
 * Created by LiRan on 2016-03-03.
 */
public class ConcreteCommand implements Command {


    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void Execute() {
        receiver.Action();
    }
}
