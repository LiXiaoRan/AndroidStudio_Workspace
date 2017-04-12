package com.inititute.main.Stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by LiRan on 2015-11-01.
 */
public class BufferReaderTest {

    public static String read(String filename) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        String s = null;
        while ((s = bufferedReader.readLine()) != null) {
            stringBuilder.append(s);
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }

    public static void main(String[] args) {

        try {
            System.out.println(read("E:\\androidstudio_workespace\\MyApplication\\javaproject\\src\\main\\java\\com\\liran\\main\\Stream\\BufferReaderTest.java"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
