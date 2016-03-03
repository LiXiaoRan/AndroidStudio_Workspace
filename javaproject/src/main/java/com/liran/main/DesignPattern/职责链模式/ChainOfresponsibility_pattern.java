package com.liran.main.DesignPattern.职责链模式;

/**
 * 职责链模式
 * Created by LiRan on 2016-03-03.
 */
public class ChainOfresponsibility_pattern {

    public static void main(String[] args) {

    }

}

/**
 * Handler定义一个处理请示的接口
 */
abstract class Handler{

    protected Handler successor;

    /**
     * 设置继任者
     * @param successor
     */
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    /**
     * 处理请求的抽象方法
     * @param request
     */
    public abstract void HandlerRequest(int request);

}




