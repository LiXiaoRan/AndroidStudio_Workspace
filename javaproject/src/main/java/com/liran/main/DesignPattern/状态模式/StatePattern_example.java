package com.liran.main.DesignPattern.状态模式;

/**
 * 演示例子
 * Created by LiRan on 2016-02-22.
 */
public class StatePattern_example {

    public static void main(String[] args) {

        Work work = new Work();
        work.hour = 9;
        work.writeProgram();
        work.hour = 11;
        work.writeProgram();
        work.hour = 12;
        work.writeProgram();
        work.hour = 15;
        work.writeProgram();

        work.isFinish=true;

        work.hour = 19;
        work.writeProgram();

        work.hour = 21;
        work.writeProgram();

        work.hour = 22;
        work.writeProgram();

        work.hour = 23;
        work.writeProgram();


    }

}

class Work {
    public int hour;
    public boolean isFinish;
    private WorkState current;

    public Work() {
        current = new MorinigState();
    }

    public WorkState getState() {
        return current;
    }

    public void setState(WorkState current) {
        this.current = current;
    }

    public void writeProgram() {
        current.writeProgram(this);
    }
}

/**
 * 抽象工作状态类
 */
abstract class WorkState {
    abstract public void writeProgram(Work w);
}

/**
 * 上午工作状态
 */
class MorinigState extends WorkState {


    @Override
    public void writeProgram(Work w) {
        if (w.hour < 12) {

            System.out.println("当前时间是：" + w.hour + " 精神百倍");
        } else {
            w.setState(new noonState());
            w.writeProgram();
        }

    }
}


/**
 * 中午工作状态
 */
class noonState extends WorkState {


    @Override
    public void writeProgram(Work w) {

        if (w.hour < 13) {

            System.out.println("当前时间是：" + w.hour + " 吃午饭  困  午休 ");
        } else {
            w.setState(new afternoonState());
            w.writeProgram();
        }


    }
}


/**
 * 下午工作状态
 */
class afternoonState extends WorkState {


    @Override
    public void writeProgram(Work w) {

        if (w.hour < 17) {

            System.out.println("当前时间是：" + w.hour + " 感觉状态还不错 ");
        } else {
            w.setState(new eveningState());
            w.writeProgram();
        }


    }
}

/**
 * 晚上工作状态
 */
class eveningState extends WorkState {

    @Override
    public void writeProgram(Work w) {

        if (w.isFinish) {

            w.setState(new restState());
            w.writeProgram();
        } else {
            if (w.hour < 21) {

                System.out.println("当前时间是：" + w.hour + " 加班啊  ");
            } else {
                w.setState(new SleepingState());
                w.writeProgram();
            }

        }


    }
}

class SleepingState extends WorkState {

    @Override
    public void writeProgram(Work w) {

        System.out.println("当前时间是：" + w.hour + " 不行了 睡着了 ");

    }
}

class restState extends WorkState {

    @Override
    public void writeProgram(Work w) {
        System.out.println("当前时间是：" + w.hour + " 下班回家了 ");
    }
}