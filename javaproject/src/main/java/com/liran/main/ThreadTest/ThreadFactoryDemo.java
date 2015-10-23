package com.liran.main.ThreadTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by liran on 2015-10-23.
 */
public class ThreadFactoryDemo {

    public static void main(String[] args) {

        ExecutorService executorService= Executors.newCachedThreadPool(new myThreadFac());


    }


}


class myThreadFac implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        Thread t=new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
