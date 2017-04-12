package com.inititute.main.NetWork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 测试一下服务器端的socket 简单的实例
 * Created by LiRan on 2015-11-05.
 */
public class SimpleServerSocket1 {

    public static final int PORT = 8080;

    public static Socket clientSocket;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            System.out.println("--------------服务器启动---------------");
            serverSocket = new ServerSocket(PORT);
            System.out.println("服务器端的socket：" + serverSocket);


            clientSocket = serverSocket.accept();
            System.out.println("获取客户端的socket：" + clientSocket);
            //服务器端用来读取客户端信息的缓冲流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //服务器端用来向客户端发送消息的缓冲流  true参数是自动刷新  println、printf 或 format 方法将刷新输出缓冲区
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

            /*printWriter.println("我是服务器呀~~~");
            System.out.println("sout我是服务器呀~~~");*/

            String clientStr = null;


            /**这是一个教训，这里前往不能写readline作为判断条件，因为他们没有判断换行
             StringBuilder sb = new StringBuilder();
             while ((clientStr = bufferedReader.readLine()) != null) {
             sb.append(clientStr);
             System.out.printf("clientStr: "+clientStr);
             }*/

            while (true) {
                clientStr = bufferedReader.readLine();
                if (clientStr.equals("END")) break;
                System.out.println("客户端说： " + clientStr.toString());
                printWriter.println("服务器说：" + clientStr.toString());

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("--------------服务器关闭---------------");
            if (serverSocket != null)
                serverSocket.close();
            if (clientSocket != null)
                clientSocket.close();
        }


    }

}
