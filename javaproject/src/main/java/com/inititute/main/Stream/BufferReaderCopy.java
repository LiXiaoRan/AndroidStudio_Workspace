package com.inititute.main.Stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 完成指定文件的复制和剪切；使用BufferedReader和BufferedWriter完成。
 * Created by LiRan on 2015-11-21.
 */
public class BufferReaderCopy {

    public static void main(String[] args) throws IOException {

        String fileName = "E:\\androidstudio_workespace\\MyApplication\\javaproject\\src\\main\\java\\com\\liran\\main\\Stream\\BufferReaderCopy.java";

        copy(fileName);
        cut("copy.txt");

    }

    /**
     * 复制文件
     *
     * @param fileName
     */
    private static void copy(String fileName) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("copy.txt"));

        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            bufferedWriter.write(str, 0, str.length());
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }

    /***
     * 剪切文件
     *
     * @param fileName
     */
    private static void cut(String fileName) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("cut.txt"));

        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            bufferedWriter.write(str, 0, str.length());
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
        File file=new File(fileName);
        file.delete();
    }


}
