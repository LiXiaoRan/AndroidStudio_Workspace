package com.inititute.main.ThreadTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * Created by liran on 2015-10-22.
 */
public class CachedThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            //我里面没有在构造方法中修改线程名字，因为那样相当于修改了主线程的名字 没有意义
            executorService.execute(new Liftoff("线程" + i));
        }
        executorService.shutdown();


        System.out.println("main name is " + Thread.currentThread().getName());
    }


}
