package com.example.lenovo.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 通过插件完成了安卓上的序列化
 * Created by lr on 2015-08-21.
 */
public class info implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.desc);
    }

    protected info(Parcel in) {
        this.title = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<info> CREATOR = new Parcelable.Creator<info>() {
        public info createFromParcel(Parcel source) {
            return new info(source);
        }

        public info[] newArray(int size) {
            return new info[size];
        }
    };
}
