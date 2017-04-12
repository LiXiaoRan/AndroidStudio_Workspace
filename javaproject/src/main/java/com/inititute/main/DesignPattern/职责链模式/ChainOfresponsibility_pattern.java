package com.inititute.main.DesignPattern.职责链模式;

/**
 * 职责链模式
 * Created by LiRan on 2016-03-03.
 */
public class ChainOfresponsibility_pattern {

    public static void main(String[] args) {

        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();

        handler1.setSuccessor(handler2);
        handler2.setSuccessor(handler3);

        handler1.HandlerRequest(10);
        handler1.HandlerRequest(9);
        handler1.HandlerRequest(25);
    }

}

/**
 * Handler定义一个处理请示的接口
 */
abstract class Handler {

    protected Handler successor;

    /**
     * 设置继任者
     *
     * @param successor
     */
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    /**
     * 处理请求的抽象方法
     *
     * @param request
     */
    public abstract void HandlerRequest(int request);

}

/**
 * 具体的处理者类，在10--20之间有权处理 否则交给后继者
 */
class ConcreteHandler1 extends Handler {


    @Override
    public void HandlerRequest(int request) {

        //如果request是0~~10之间就处理
        if (request >= 0 && request < 10) {
            System.out.println(getClass().getName().toString() + "  处理请求  " + request);

        } else if (successor != null) {
            //转到下一位
            successor.HandlerRequest(request);
        }

    }
}

class ConcreteHandler2 extends Handler {


    @Override
    public void HandlerRequest(int request) {

        //如果request是0~~10之间就处理
        if (request >= 10 && request < 20) {
            System.out.println(getClass().getName().toString() + "  处理请求  " + request);

        } else if (successor != null) {
            //转到下一位
            successor.HandlerRequest(request);
        }

    }
}

class ConcreteHandler3 extends Handler {


    @Override
    public void HandlerRequest(int request) {

        //如果request是0~~10之间就处理
        if (request >= 20 && request < 30) {
            System.out.println(getClass().getName().toString() + "  处理请求  " + request);

        } else if (successor != null) {
            //转到下一位
            successor.HandlerRequest(request);
        }

    }
}





