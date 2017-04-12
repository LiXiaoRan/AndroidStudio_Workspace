package com.inititute.flabbybird.bean;

import java.io.Serializable;

/**
 * 存储排行榜信息的bean
 * Created by LiRan on 2017-02-16.
 */

public class Info_score implements Comparable<Info_score> ,Serializable{

    private int id;
    private String username;
    private String time;
    private int score;


    public Info_score() {
        this.username = "xxx";
        this.time = "1970-01-01";
        this.score = 100;

    }

    public Info_score(String username, String time, int score) {
        this.username = username;
        this.time = time;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Info_score another) {


        return this.score - another.getScore();
    }
}
