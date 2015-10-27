package com.liran.main.Bean;

/**
 * Created by Lenovo on 2015-10-27.
 */
public class Person implements Comparable<Person> {

    int id;
    /**
     * 姓名
     */
    String name;
    /**
     * 成绩
     */
    double performance;
    /**
     * 地址
     */
    String Addr;

    public Person() {
    }

    public Person(int id, String name, double performance, String addr) {
        this.id = id;
        this.name = name;
        this.performance = performance;
        Addr = addr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    @Override
    public int compareTo(Person o) {

        if (performance == o.performance) {
            return 0;
        } else if (performance > o.performance) {
            return 1;
        } else if (performance < o.performance) {
            return -1;
        } else {
            return (int) (performance - o.performance);
        }


    }

    @Override
    public String toString() {
        return "id is " + id + " name is " + name + "  performance is " + performance + " addr is " + Addr;
    }
}
