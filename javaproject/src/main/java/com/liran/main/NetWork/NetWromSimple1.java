package com.liran.main.NetWork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络爬虫原理
 * Created by LiRan on 2015-11-07.
 */
public class NetWromSimple1 {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.baidu.com");

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("baidu.html"), "UTF-8"));

        String str = null;
        while ((str = (bufferedReader.readLine())) != null) {

            bufferedWriter.append(str);
            bufferedWriter.newLine();

        }

        bufferedReader.close();
        bufferedWriter.flush();
        bufferedWriter.close();

    }


}
