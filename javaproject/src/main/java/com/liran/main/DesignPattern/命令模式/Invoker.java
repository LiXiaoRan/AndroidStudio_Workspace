package com.liran.main.DesignPattern.命令模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 要求该命令执
 * 行这个请求
 * Created by LiRan on 2016-03-03.
 */
public class Invoker {

    private List<Command> commands = new ArrayList<>();


    public void AddCommand(Command command){
        commands.add(command);
    }

    public void RemoveCommand(Command command){
        commands.remove(command);
    }

    public void ExecuteAll() {
        for (Command c : commands) {
            c.Execute();
        }

    }

}
