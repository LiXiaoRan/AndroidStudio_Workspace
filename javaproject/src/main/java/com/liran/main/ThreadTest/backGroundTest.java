package com.liran.main.ThreadTest;

import java.util.concurrent.TimeUnit;

/**
 * 测试后台线程
 * Created by liran on 2015-10-23.
 */
public class backGroundTest {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread damen=new Thread(new BackGroundThread());
            damen.setDaemon(true);//在start之前设置这个属性可以让线程变成后台线程
            damen.start();
        }

        System.out.println("所有线程已经执行完毕");
        TimeUnit.MILLISECONDS.sleep(175);  //一般情况下  main函数一旦结束所有的后台线程就也停止了，所以要暂定才能看到效果
    }




}


class BackGroundThread implements Runnable{

    @Override
    public void run() {
        System.out.println("线程："+Thread.currentThread().getName());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
