package com.liran.main.Stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * 测试和通道相关的方法还有与Buffer相关的编码和解码
 * <p/>
 * 缓冲期容纳的是普通的字节，为了把他们转化成字符，我们要么在输入他们的时候对其进行编码
 * 这样他们的输出才会有意义
 * Created by LiRan on 2015-11-03.
 */
public class BufferToText {

    public static final int BSIZE = 1024;

    public static void main(String[] args) {

        try {
            FileChannel fileChannel = new FileOutputStream("data.txt").getChannel();
            fileChannel.write(ByteBuffer.wrap("大家好啊".getBytes()));
            fileChannel.close();

            fileChannel = new FileInputStream("data.txt").getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(BSIZE);
            fileChannel.read(byteBuffer);

            byteBuffer.flip();
            //这个地方读取出来是乱码
            System.out.println("这个地方读取出来是乱码: " + byteBuffer.asCharBuffer());


            //为重新读取缓存区的数据做好准备
            byteBuffer.rewind();
            //这里识别出字符的编码并且解码
            String encoding = System.getProperty("file.encoding");
            System.out.println("Decode using " + encoding + " :" + Charset.forName(encoding).decode(byteBuffer));
            fileChannel.close();

            //按照一种编码格式写入
            fileChannel = new FileOutputStream("data.txt").getChannel();
            fileChannel.write(ByteBuffer.wrap("固定的编码格式存储".getBytes("UTF-16BE")));
            fileChannel.close();

            //现在尝试读取
            fileChannel = new FileInputStream("data.txt").getChannel();
            byteBuffer.clear();
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            System.out.println(byteBuffer.asCharBuffer());
            fileChannel.close();

            //直接用CharBuffer的话就不用进行转码了
            fileChannel = new FileOutputStream("data.txt").getChannel();
            byteBuffer = ByteBuffer.allocate(24);
            byteBuffer.asCharBuffer().put("大家都很好啊");
            fileChannel.write(byteBuffer);
            fileChannel.close();
            fileChannel = new FileInputStream("data.txt").getChannel();
            byteBuffer.clear();
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            System.out.println(byteBuffer.asCharBuffer());
            fileChannel.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
