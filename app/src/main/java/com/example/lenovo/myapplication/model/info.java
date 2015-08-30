package com.example.lenovo.myapplication.model;

/**
 * Created by lr on 2015-08-21.
 */
public class info {
    String title;
    String desc;



    public info(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public info() {
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
