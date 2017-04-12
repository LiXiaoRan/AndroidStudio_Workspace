package com.inititute.main.Stream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 以UTF8进行读写
 * Created by LiRan on 2015-11-01.
 */
public class DatOutPutStreamTest {

    public static void Write(String fileName) {
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(fileName));
            dataOutputStream.writeUTF("大家好啊");
            dataOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dataOutputStream != null)
                    dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static String read(String fileNmae) {
        DataInputStream dataInputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            dataInputStream = new DataInputStream(new FileInputStream(fileNmae));
            String s = null;
            while ((s = dataInputStream.readUTF()) != null) {
                stringBuilder.append(s);
            }
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
        } finally {

            try {
                if (dataInputStream != null)
                    dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        read("E:\\androidstudio_workespace\\MyApplication\\javaproject\\src\\main\\java\\com\\liran\\main\\Stream\\DatOutPutStreamTest.java");
        Write("test.txt");
    }
}
