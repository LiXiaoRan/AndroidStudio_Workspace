package com.inititute.main.ThreadTest;

/**
 * 这个类是想测试在两个线程枪一块资源时，被一个线程拿到了同步锁，另一个是跳过同代码块向下执行还是在这里等待，
 * 显然，通过运行结果可以看出，是在同步代码块的位置等待。然后等待获得锁的线程释放了锁，立马进入。
 * Created by LiRan on 2015-10-30.
 */
public class SyncFlowTest {


    public static void main(String[] args) {

        synctestfun mysynctestfun = new synctestfun();

        new mythread("线程1", mysynctestfun).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new mythread("线程2", mysynctestfun).start();


    }


}


class synctestfun {
    synchronized public void test() {
        System.out.println(Thread.currentThread().getName() + "获得了线程锁");

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int count = 0;
        while (++count < 60000) {
            if (count % 10000 == 0) {
                System.out.println("这里是" + Thread.currentThread().getName() + "count is " + count);
            }
        }

    }
}


class mythread extends Thread {

    private String name;
    private synctestfun mysynctestfun;

    public mythread(String name, com.inititute.main.ThreadTest.synctestfun synctestfun) {
        this.name = name;
        this.mysynctestfun = synctestfun;
    }

    public mythread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        super.run();

        setName(name);

        System.out.println(getName() + "线程锁之前");
        mysynctestfun.test();

        System.out.println(getName() + "线程锁之后的代码");

    }


}
