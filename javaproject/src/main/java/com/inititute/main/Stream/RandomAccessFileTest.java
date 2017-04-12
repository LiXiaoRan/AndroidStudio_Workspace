package com.inititute.main.Stream;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 测试随机访问文件
 * Created by liran on 2015-10-16.
 */
public class RandomAccessFileTest {

    public static void main(String[] args) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\androidstudio_workespace\\MyApplication\\javaproject\\src\\main\\java\\com\\liran\\main\\Stream\\RandomAccessFileTest.java", "rw")) {
            byte[] buff = new byte[1024];

            //从第300字节开始读取
            randomAccessFile.seek(300);

            int hasRead = 0;

            while ((hasRead = randomAccessFile.read(buff)) > 0) {
                System.out.printf(new String(buff, 0, hasRead));
            }


            //将指针移动到文件的最后
            randomAccessFile.seek(randomAccessFile.length());
            //追加一块内容
            randomAccessFile.write("//add to content".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
