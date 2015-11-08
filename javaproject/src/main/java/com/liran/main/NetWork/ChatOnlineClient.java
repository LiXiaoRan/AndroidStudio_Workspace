package com.liran.main.NetWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 聊天程序的客户端
 * Created by LiRan on 2015-11-08.
 */
public class ChatOnlineClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 42000);

            new Thread(new ClientThread_Chat(socket)).start();

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            String line = null;

            //从键盘输入
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while ((line = br.readLine()) != null) {
                printWriter.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
