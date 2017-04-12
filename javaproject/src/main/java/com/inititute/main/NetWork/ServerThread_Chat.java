package com.inititute.main.NetWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 聊天服务器线程
 * Created by LiRan on 2015-11-08.
 */
public class ServerThread_Chat implements Runnable {

    private Socket socket;
    private BufferedReader bufferedReader;

    public ServerThread_Chat(Socket socket) throws IOException {
        this.socket = socket;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
    }


    @Override
    public void run() {

        String str = null;
        while ((str = readFromClient()) != null) {

            for (Socket s : ChatOnlineServer.socklist) {
                try {
                    PrintWriter printWriter = new PrintWriter(s.getOutputStream(), true);
                    printWriter.println(str);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    /**
     * 从客户端读取数据
     */
    private String readFromClient() {

        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            ChatOnlineServer.socklist.remove(socket);
        }
        return null;

    }


}
