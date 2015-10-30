package com.liran.main.ThreadTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 生产者与消费者
 * Created by LiRan on 2015-10-30.
 */
public class Restaurant {

    Meal meal;
    Chef chef = new Chef(this);
    WaitPersion waitPersion = new WaitPersion(this);
    ExecutorService exec = Executors.newCachedThreadPool();

    public Restaurant() {
        exec.execute(chef);
        exec.execute(waitPersion);

    }

    public static void main(String[] args) {

        new Restaurant();
    }

}


/**
 * 食物
 */
class Meal {

    public final int orderNum;

    Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {

        return "Meal " + orderNum;
    }
}

/**
 * 服务员
 */
class WaitPersion implements Runnable {

    private Restaurant restaurant;

    public WaitPersion(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {

        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null) {
                        wait();
                    }
                }

                System.out.println("WaitPerson got " + restaurant.meal);

                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
            }

        } catch (InterruptedException e) {
            System.out.println("WaitPersion 中断");
        }

    }
}

/**
 * 厨师
 */
class Chef implements Runnable {

    private Restaurant restaurant;

    int count = 0;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {

        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null) {
                        wait();
                    }
                }


                if (++count == 10) {
                    System.out.println("Out of food , closing");
                    restaurant.exec.shutdownNow();
                }

                System.out.println("order up");

                synchronized (restaurant.waitPersion) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPersion.notifyAll();
                }

                TimeUnit.MILLISECONDS.sleep(100);

            }

        } catch (InterruptedException e) {
            System.out.println("Chef 中断");
        }


    }
}



