package com.liran.main.NetWork;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * udp的服务端
 * Created by LiRan on 2015-11-09.
 */
public class UdpServer {


    public static final int PORT = 30000;
    /**
     * 定义每个数据报的大小为4KB
     */
    public static final int DATA_LEN = 4096;

    /**
     * 定义接收数据的字节数组
     */
    byte[] inBuff = new byte[DATA_LEN];

    /**
     * 定义一个接收数据的DatagramPacket
     */
    private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
    /**
     * 定义一个发送数据的DatagramPacket
     */
    private DatagramPacket outPacket;


    private void init() throws IOException {


        DatagramSocket socket = new DatagramSocket(PORT);

        for (int i = 0; i < 1000; i++) {
            socket.receive(inPacket);

            System.out.println("inBuff==inPacket.getData(): " + (inBuff == inPacket.getData()));

            System.out.println("收到的内容：" + new String(inBuff, 0, inPacket.getLength()));

            byte[] sendData = ("反馈：" + inBuff.toString()).getBytes();

            outPacket = new DatagramPacket(sendData, sendData.length, inPacket.getSocketAddress());

            //发送数据
            socket.send(outPacket);
        }


    }


    public static void main(String[] args) {

        try {
            new UdpServer().init();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
