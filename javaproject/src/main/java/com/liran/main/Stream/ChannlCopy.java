package com.liran.main.Stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 拷贝一个文件<br>
 * Created by LiRan on 2015-11-02.
 */
public class ChannlCopy {

    public static void main(String[] args) throws IOException {
        FileChannel in = new FileInputStream("E:\\androidstudio_workespace\\MyApplication\\javaproject\\src\\main\\java\\com\\liran\\main\\Stream\\ChannlCopy.java").getChannel();
        FileChannel out = new FileOutputStream("ChannlCopy.txt").getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while ((in.read(byteBuffer)) != -1) {
            byteBuffer.flip();
            out.write(byteBuffer);
            byteBuffer.clear();
        }


    }

}
