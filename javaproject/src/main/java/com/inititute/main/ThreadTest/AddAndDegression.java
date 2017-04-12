package com.inititute.main.ThreadTest;

/**
 * 那次上次老师让做的  关于实现两个线程其中一个累加其中一个累减，使数字保持在0--100之间
 * Created by liran on 2015-10-20.
 */
public class AddAndDegression {

    public static void main(String[] args) {
        new AddClass().start();
        new DegressionClass().start();
    }

}

class MyNumber {
    public static int i;


    synchronized public static void Deal(int type) {
        if (type == 0) {
            MyNumber.i++;
            if (MyNumber.i > 100) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println(Thread.currentThread().getName() + "   I的值是：" + MyNumber.i);
        } else {
            MyNumber.i--;
            if (MyNumber.i < 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println(Thread.currentThread().getName() + "   I的值是：" + MyNumber.i);
        }


    }

    public static void add() {

        while (true) {
            MyNumber.i++;
            if (MyNumber.i >= 100) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println(Thread.currentThread().getName() + "   I的值是：" + MyNumber.i);
        }

    }


    public static void Degression() {
        while (true) {
            MyNumber.i--;
            if (MyNumber.i <= 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println(Thread.currentThread().getName() + "   I的值是：" + MyNumber.i);
        }
    }

}


class AddClass extends Thread {

    @Override
    public void run() {

/*//        MyNumber.add();
        while (true) {
            MyNumber.Deal(0);
        }*/

        MyNumber.add();

    }
}

class DegressionClass extends Thread {

    @Override
    public void run() {

        MyNumber.Degression();

/*        while (true) {
            MyNumber.Deal(1);
        }*/
    }

}
