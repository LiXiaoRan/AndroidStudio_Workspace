package com.inititute.flabbybird.bean;

import java.io.Serializable;

/**
 * 用户数据的bean
 * Created by LiRan on 2017-02-05.
 */

public class User implements Serializable{

    private int id;
    private String username;
    private String passwd;
    private int score;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User(String username, String passwd, int score) {
        this.username = username;
        this.passwd = passwd;
        this.score = score;
    }

    public User(int id, String username, String passwd, int score) {
        this.id = id;
        this.username = username;
        this.passwd = passwd;
        this.score = score;
    }

    public User() {
        score = 0;
        username = "xxx";
        passwd = "xxx";
    }
}
