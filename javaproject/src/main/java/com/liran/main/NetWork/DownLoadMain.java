package com.liran.main.NetWork;

import java.io.IOException;

/**
 * 多线程下载
 * Created by LiRan on 2015-11-07.
 */
public class DownLoadMain {


    public static void main(String[] args) throws IOException {
        final DownloadUtils downloadUtils = new DownloadUtils("http://www.crazyit.org/"
                + "attachments/month_1403/1403202355ff6cc9a4fbf6f14a.png", "ios.png", 4);
        //开始下载
        downloadUtils.downLoad();


        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("已经完成: " + downloadUtils.getCompleteRate());

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

          /* new Thread(()->{
            while ()

           }).start();*/
    }
}
