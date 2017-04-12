package com.inititute.main.ThreadTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by liran on 2015-10-23.
 */
public class ThreadFactoryDemo {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService= Executors.newCachedThreadPool(new myThreadFac());
        for (int i = 0; i < 10; i++) {
            executorService.execute(new BackGroundThread());

        }
//        executorService.shutdown();
        Thread.sleep(175);

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
