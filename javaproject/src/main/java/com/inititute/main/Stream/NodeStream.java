package com.inititute.main.Stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 测试节点流
 * Created by liran on 2015-10-16.
 * java的默认工作目录是 E:\androidstudio_workespace\MyApplication\
 */
public class NodeStream {

    public static void main(String[] args) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("test.txt");
             FileInputStream fileInputStream = new FileInputStream("E:\\androidstudio_workespace\\MyApplication\\javaproject\\src\\main\\java\\com\\liran\\main\\Stream\\FilewriterTest.java");
        ) {

            byte[] buff = new byte[32];
            int hasread = 0;


            while ((hasread = fileInputStream.read(buff)) > 0) {
                System.out.printf(new String(buff, 0, hasread));
                fileOutputStream.write(buff, 0, hasread);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
