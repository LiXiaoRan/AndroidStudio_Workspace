package com.liran.main.NetWork;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 下载工具类
 * Created by LiRan on 2015-11-06.
 */
public class DownloadUtils {
    //定义下载资源的路径
    private String path;
    //定义下载文件的保存位置
    private String targetFilePath;
    //定义需要多少线程下载
    private DownThread[] downThreads;
    //定义下载文件的总大小
    private int fileSize;


    public DownloadUtils(String path, String targetFilePath, DownThread[] downThreads) {
        this.path = path;
        this.targetFilePath = targetFilePath;
        this.downThreads = downThreads;
    }

    public void downLoad() throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty(
                "Accept",
                "image/gif, image/jpeg, image/pjpeg, image/pjpeg, "
                        + "application/x-shockwave-flash, application/xaml+xml, "
                        + "application/vnd.ms-xpsdocument, application/x-ms-xbap, "
                        + "application/x-ms-application, application/vnd.ms-excel, "
                        + "application/vnd.ms-powerpoint, application/msword, */*");
        conn.setRequestProperty("Accept-Language", "zh-CN");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Connection", "Keep-Alive");
        //获取下载的文件的大小
        fileSize = conn.getContentLength();


    }


}
