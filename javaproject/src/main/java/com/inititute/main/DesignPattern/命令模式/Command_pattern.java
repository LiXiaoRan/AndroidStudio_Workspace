package com.inititute.main.DesignPattern.命令模式;

/**
 * 命令模式
 * Created by LiRan on 2016-03-03.
 */
public class Command_pattern {

    public static void main(String[] args) {

        Invoker invoker=new Invoker();
        Receiver receiver=new Receiver();
        Command command=new ConcreteCommand(receiver);

        invoker.AddCommand(command);
        invoker.ExecuteAll();

    }

}
