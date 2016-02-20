package com.liran.main.DesignPattern.观察者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式<br><br/>
 * 观察者模式一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象，这
 * 个主题对象状态发生变化会通知所有的观察者对象，使他们能够自己更新自己。
 * Created by LiRan on 2016-02-20.
 */
public class Observer_pattern {

    public static void main(String[] args) {

        ContreteSubject Subject = new ContreteSubject();


        Subject.Attach(new ContreteObserver(Subject, "路人甲"));
        Subject.Attach(new ContreteObserver(Subject, "路人乙"));

        Subject.SubjectState = " 开始干活了 ";
        Subject.Notify();

        Subject.Attach(new ContreteObserver(Subject, "路人丙"));

        Subject.SubjectState = " 开始吃饭了 ";
        Subject.Notify();

    }


}

/**
 * 抽象主题类
 */
abstract class Subject {

    protected List<Observerr> observerList = new ArrayList<>();

    public void Attach(Observerr Observerr) {
        observerList.add(Observerr);
    }

    public void Detach(Observerr Observerr) {
        observerList.remove(Observerr);
    }

    public void Notify() {

        if (observerList.size() != 0 && observerList != null) {
            for (Observerr o : observerList) {
                o.Update();
            }
        }
    }


}

/**
 * 具体的主题
 */
class ContreteSubject extends Subject {

    public String SubjectState;

}

/**
 * 抽象观察者类
 */
abstract class Observerr {
    public abstract void Update();
}

/**
 * 具体的观察者类
 */
class ContreteObserver extends Observerr {

    private String name;
    private String observerState;
    private ContreteSubject subject;

    public ContreteObserver(ContreteSubject subject, String name) {
        this.subject = subject;
        this.name = name;
    }

    public ContreteSubject getSubject() {
        return subject;
    }

    public void setSubject(ContreteSubject subject) {
        this.subject = subject;
    }

    @Override
    public void Update() {
        observerState = this.subject.SubjectState;
        System.out.println("观察者: " + name + " 的新状态是：" + observerState);
    }

}

