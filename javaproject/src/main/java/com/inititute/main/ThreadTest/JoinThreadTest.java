package com.inititute.main.ThreadTest;

/**
 * 测试线程的join方法
 * Created by liran on 2015-10-19.
 */
public class JoinThreadTest {

    public static void main(String[] args) {

        new JoinThread("新线程").start();

        for (int i = 0; i < 100; i++) {
            if (i == 20) {
                JoinThread jt = new JoinThread("被join的线程");
                jt.start();
                try {
                    jt.join();  //这一句式在main线程中执行的，所以这行代码执行之后，main线程阻塞，“新线程”和“被join的线程”并发执行，知道被join的线程执行完，然后main线程才继续执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "  " + i);
        }

    }

}


class JoinThread extends Thread {


    public JoinThread(String name) {
        super(name);
    }

    @Override
    public void run() {

        int N = 100;
        if (getName().equals("新线程")) {
            N = 200;
        }

        for (int i = 0; i < N; i++) {
            System.out.println(getName() + "  " + i);
        }

    }
}
