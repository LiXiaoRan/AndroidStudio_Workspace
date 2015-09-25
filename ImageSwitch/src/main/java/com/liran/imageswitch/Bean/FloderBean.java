package com.liran.imageswitch.Bean;

/**
 * Created by lr on 2015-09-24.
 */
public class FloderBean {

    /**
     * 当前文件夹的路径
     */
    private String dir;
    /**
     * 当前文件夹第一张图片的路径
     */
    private String firstImagePath;
    /**
     * 当前文件夹的名称
     */
    private String name;
    /**
     * 当前文件夹图片的数量
     */
    private int count;

    public FloderBean() {
    }

    public FloderBean(String dir, String firstImagePath, String name, int count) {
        this.dir = dir;
        this.firstImagePath = firstImagePath;
        this.name = name;
        this.count = count;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;

        int lastIndexOf=this.dir.indexOf("/");
        this.name=this.dir.substring(lastIndexOf);
    }

    public String getFirstImagePath() {
        return firstImagePath;
    }

    public void setFirstImagePath(String firstImagePath) {
        this.firstImagePath = firstImagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
