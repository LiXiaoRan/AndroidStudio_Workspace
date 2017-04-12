package com.inititute.main.ThreadTest;

class Res {

    int i = 0;
    boolean flag = false;
}

class mythread1 implements Runnable {

    private Res r;

    mythread1(Res r) {

        this.r = r;
    }

    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            synchronized (r) {
                if (r.i < 100) {
                    r.flag = false;
                    r.i++;

                    System.out.println(Thread.currentThread().getName()
                            + "--累加-->    " + r.i);
                } else {
                    r.flag = true;
                }

                if (r.flag)
                    try {
                        r.wait();
                    } catch (Exception e) {
                    }

                r.notify();

            }
        }

    }

}

class mythread2 implements Runnable {

    private Res r;

    mythread2(Res r) {

        this.r = r;
    }

    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            synchronized (r) {
                if (r.i > 0) {
                    r.flag = false;
                    r.i--;
                    System.out.println(Thread.currentThread().getName()
                            + "--累减-->    " + r.i);
                } else {
                    r.flag = true;
                }
                if (r.flag)
                    try {
                        r.wait();
                    } catch (Exception e) {
                    }

                r.notify();
            }
        }

    }

}

public class test {


    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Res r = new Res();
        new Thread(new mythread1(r)).start();
        new Thread(new mythread2(r)).start();
    }

}

