package com.inititute.main.NetWork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 测试客户端socket的简单时例子
 * Created by LiRan on 2015-11-05.
 */
public class SimpleClientSocket1 {

    public static final int PORT = 8080;

    private static Socket socket;

    public static void main(String[] args) {

        try {
            InetAddress inetAddress = InetAddress.getByName(null);
            System.out.println("addr is =" + inetAddress);
            socket = new Socket(inetAddress, PORT);

            System.out.println("客户端启动 socket is ：" + socket);


            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            PrintWriter printWriter=new PrintWriter(socket.getOutputStream());
//            printWriter.println(ByteBuffer.wrap("我是客户端呀~~~\n".getBytes("UTF-8")));
            printWriter.println("我是客户端呀~~~~~~");
            System.out.println("sout我是客户端呀~~~");

            String Str = null;
            /*StringBuilder sb = new StringBuilder();
            while ((Str = bufferedReader.readLine()) != null) {
                sb.append(Str);
                System.out.printf("str : "+Str);
            }

            System.out.println("服务器对客户端说：" + sb);*/


           /* while (true) {
                Str = bufferedReader.readLine();
                if (Str.equals("END")) break;
                System.out.println("服务器说： " + Str.toString());
                printWriter.println("客户端说：" + Str.toString());

            }*/

            for (int i = 0; i < 10; i++) {
                printWriter.println("你好呀~ " + i);
                Str = bufferedReader.readLine();
                System.out.println(Str.toString());
            }


            printWriter.println("END");

        } catch (UnknownHostException e) {
            System.out.println("异常：找不到主机");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null)
                try {
                    System.out.println("-------------客户端关闭-------------");
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }


    }


}
