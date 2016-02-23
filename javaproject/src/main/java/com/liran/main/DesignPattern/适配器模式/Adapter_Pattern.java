package com.liran.main.DesignPattern.适配器模式;

/**
 * 适配器模式
 * Created by LiRan on 2016-02-23.
 */
public class Adapter_Pattern {

    public static void main(String[] args) {

        Target target=new Adapter();
        target.Request();//对客户端来说调用的就是target的Request方法。

    }

}


abstract class Target{

    public abstract void Request();
}

class Adapter extends Target{

    //建立一个私有的adaptee对象
    private Adaptee adaptee=new Adaptee();

    @Override
    public void Request() {

        //这样就可以把表面上调用Request方法变成实际调用SpecificRequest
        adaptee.SpecificRequest();
    }
}


class Adaptee{

    public void SpecificRequest(){
        System.out.println("特殊请求");
    }

}