package com.inititute.main.ThreadTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试lock方法加锁
 * Created by Lenovo on 2015-10-24.
 */
public class MyLockTest {

    private ReentrantLock lock = new ReentrantLock();

    public void unTime() {
        boolean capture = lock.tryLock(); //加锁  成功返回true

        try {

            System.out.println("trylock : " + capture);
        } finally {
            if (capture)
                lock.unlock();
        }

    }


    public void timeed() throws InterruptedException {
        boolean capture = lock.tryLock(2, TimeUnit.SECONDS);//如果两秒后仍然没有获得锁，则返回false

        try {
            System.out.println("tryLock(2, TimeUnit.SECONDS) : " + capture);
        } finally {
            if (capture)
                lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {


        final MyLockTest myLockTest = new MyLockTest();
        myLockTest.unTime();
        myLockTest.timeed();
        Thread.currentThread().setPriority(Thread.NORM_PRIORITY);

//        final CountDownLatch latch = new CountDownLatch(1);//1.增加一个"屏障"
        new Thread() {
            {
                setDaemon(true);
            }

            @Override
            public void run() {
                Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
                boolean islock = myLockTest.lock.tryLock();
                System.out.println("这里在另一个新开的线程中调用lock  没有释放  islock= " + islock);
//                latch.countDown();//2.屏障解除
            }
        }.start();

        Thread.sleep(100);  //切换一下线程
//        Thread.yield();

        System.out.println("---------------------------------");
        myLockTest.unTime();  //由于lock被占用着  所以无法获取锁 返回false
        myLockTest.timeed();  //由于lock被占用着  所以无法获取锁 返回false

    }


}
