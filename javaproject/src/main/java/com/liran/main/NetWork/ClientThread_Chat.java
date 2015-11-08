package com.liran.main.NetWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 聊天客户端线程
 * Created by LiRan on 2015-11-08.
 */
public class ClientThread_Chat implements Runnable {

    private Socket socket;

    private BufferedReader bufferedReader;

    public ClientThread_Chat(Socket socket) {
        this.socket = socket;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("----------客户端进行监听线程----------");
        String str = null;
        try {
            while ((str = bufferedReader.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
