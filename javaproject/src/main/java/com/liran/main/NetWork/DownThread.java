package com.liran.main.NetWork;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 下载线程
 * Created by LiRan on 2015-11-06.
 */
public class DownThread extends Thread {

    //下载资源的路径
    private String path;
    //当前线程的下载位置
    private int statrPos;
    //当前线程下载文件的大小
    private int currentPartSize;
    //当前线程需要下载的文件
    private RandomAccessFile currentPart;
    //定义该线程已下载的字节数
    private int length;


    /**
     * @param path            下载资源的路径
     * @param statrPos        当前线程的下载位置
     * @param currentPartSize 当前线程下载文件的大小
     * @param currentPart     当前线程需要下载的文件块
     */
    public DownThread(String path, int statrPos, int currentPartSize, RandomAccessFile currentPart) {
        this.statrPos = statrPos;
        this.currentPartSize = currentPartSize;
        this.currentPart = currentPart;
        this.path = path;
    }

    @Override
    public void run() {

        try {
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept",
                    "image/gif, image/jpeg, image/pjpeg, image/pjpeg, "
                            + "application/x-shockwave-flash, application/xaml+xml, "
                            + "application/vnd.ms-xpsdocument, application/x-ms-xbap, "
                            + "application/x-ms-application, application/vnd.ms-excel, "
                            + "application/vnd.ms-powerpoint, application/msword, */*");
            httpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            InputStream inputStream = httpURLConnection.getInputStream();
            inputStream.skip(currentPartSize);
            byte[] buffer = new byte[1024];
            int hashRead = 0;
            while ((hashRead = inputStream.read(buffer)) != -1 && length < currentPartSize) {
                currentPart.write(buffer, 0, hashRead);
                length += hashRead;
            }

            currentPart.close();
            inputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
