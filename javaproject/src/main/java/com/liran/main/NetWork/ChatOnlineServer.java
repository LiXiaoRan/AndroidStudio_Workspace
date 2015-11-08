package com.liran.main.NetWork;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 聊天程序的服务器
 * Created by LiRan on 2015-11-08.
 */
public class ChatOnlineServer {

    //保存socket
    public static List<Socket> socklist = new ArrayList<>();

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(42000);
            System.out.println("------------服务器已经启动-------------");
            while (true) {
                Socket s = serverSocket.accept();
                System.out.println("有客户端连接");
                socklist.add(s);
                new Thread(new ServerThread_Chat(s)).start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
