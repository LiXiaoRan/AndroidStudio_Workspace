package com.liran.main.Stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 拷贝一个文件<br>
 * Created by LiRan on 2015-11-03.
 */
public class ChannelCopy2 {

    public static void main(String[] args) throws IOException {
        FileChannel fromChannel = new FileInputStream("E:\\androidstudio_workespace\\MyApplication\\javaproject\\src\\main\\java\\com\\liran\\main\\Stream\\ChannelCopy2.java").getChannel();
        FileChannel toChannel = new FileOutputStream("a.txt").getChannel();

        toChannel.transferFrom(fromChannel, 0, fromChannel.size());


    }


}
