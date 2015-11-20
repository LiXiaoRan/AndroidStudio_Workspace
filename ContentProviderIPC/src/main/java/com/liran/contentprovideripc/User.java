package com.liran.contentprovideripc;

/**
 * Created by LiRan on 2015-11-20.
 */
public class User {
    private int id;
    private String name;
    private String sex;

    public User() {
    }

    public User(int id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "id : "+id+" 用户名："+name+" 性别："+sex;
    }
}
