package com.inititute.main.NetWork;

import java.io.IOException;
import java.io.RandomAccessFile;
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
    private int threadNum;
    //定义下载的线程对象
    private DownThread[] downThreads;
    //定义下载文件的总大小
    private int fileSize;


    public DownloadUtils(String path, String targetFilePath, int threadNum) {
        this.path = path;
        this.targetFilePath = targetFilePath;
        this.threadNum = threadNum;
        //初始化threads数组
        downThreads = new DownThread[threadNum];
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
        conn.disconnect();
        //这是用文件大小除于线程数量，给每个线程分配相同的大小进行下载。。。。
        int currentPartSize = fileSize / threadNum + 1;

        RandomAccessFile file = new RandomAccessFile(targetFilePath, "rw");
        file.setLength(fileSize);
        file.close();
        for (int i = 0; i < threadNum; i++) {
            //计算每个线程下载的开始位置
            int startpos = i * currentPartSize;
            //每一个线程使用一个RandomAccessFile进行下载
            RandomAccessFile currentPart = new RandomAccessFile(targetFilePath, "rw");
            //定义该线程的下载位置
            currentPart.seek(startpos);
            //创建下载线程
            downThreads[i] = new DownThread(targetFilePath, startpos, currentPartSize, currentPart);
            //启动下载线程
            downThreads[i].start();

        }

    }

    //获取下载完成的百分比
    public double getCompleteRate() {

        // 统计多条线程已经下载的总大小
        int sumSize = 0;
        for (int i = 0; i < threadNum; i++) {
            sumSize += downThreads[i].length;
        }
        // 返回已经完成的百分比
        return sumSize * 1.0 / fileSize;

    }



}
