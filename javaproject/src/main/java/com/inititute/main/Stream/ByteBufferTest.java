package com.inititute.main.Stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 管道和bytebuffer
 * <p/>
 * <p/>
 * FileChannel只可以被FileOutputStream FileInputStream还有RandomAccessFile构造
 * <p/>
 * Created by LiRan on 2015-11-01.
 */
public class ByteBufferTest {

    public static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {

        //通过FileOutputStream获取data.txt文件的通道
        FileChannel fileChannel = new FileOutputStream("data.txt").getChannel();
        //将字节序列从给定的缓冲区写入此通道。
        fileChannel.write(ByteBuffer.wrap("aaaa".getBytes()));
        fileChannel.close();

        //通过 RandomAccessFile 获取data.txt文件的通道
        fileChannel = new RandomAccessFile("data.txt", "rw").getChannel();
        fileChannel.position(fileChannel.size());
        fileChannel.write(ByteBuffer.wrap("  bbb".getBytes()));
        fileChannel.close();

        //通过 FileInputStream 获取data.txt文件的通道
        fileChannel = new FileInputStream("data.txt").getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(BSIZE);
        fileChannel.read(byteBuffer);
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            System.out.print((char) byteBuffer.get());
        }

    }


}
