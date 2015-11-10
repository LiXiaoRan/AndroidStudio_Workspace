package com.liran.main.reflect;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * 自定义类加载器
 * Created by LiRan on 2015-11-10.
 */
public class CompileClassLoader extends ClassLoader {

    private byte[] getBytes(String fileName) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
            StringBuilder stringBuilder = null;
            String str = null;
            while ((str = br.readLine()) != null) {
                stringBuilder.append(str);
            }
            return stringBuilder.toString().getBytes();

        } catch (UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncodingException");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("找不到这个文件啊");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void main(String[] args) {
        System.out.println();
    }

}
