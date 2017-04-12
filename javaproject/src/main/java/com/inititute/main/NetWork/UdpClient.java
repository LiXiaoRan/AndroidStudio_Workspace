package com.inititute.main.NetWork;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * udp 客户端
 * Created by LiRan on 2015-11-09.
 */
public class UdpClient {

    /**
     * 定义发送数据的目的地
     */
    public static final int PORT = 30000;
    public static final String DEST_IP = "127.0.0.1";


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


    public void init() throws IOException {

        //使用随机端口创建一个DatagramSocket
        DatagramSocket socket = new DatagramSocket();

        //初始化一个向固定端口和ip发送数据的DatagramPacket
        outPacket = new DatagramPacket(new byte[0], 0, InetAddress.getByName(DEST_IP), PORT);

        //创建键盘输入流
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            //将键盘输入的一行字符串转换成字节数组
            byte[] buff = scanner.nextLine().getBytes();
            //设置发送用的DatagramPacket
            outPacket.setData(buff);
            socket.send(outPacket);
            socket.receive(inPacket);
            System.out.println("客户端读取的信息：" + new String(inBuff, 0, inPacket.getLength()));

        }


    }


    public static void main(String[] args) {
        try {
            new UdpClient().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
