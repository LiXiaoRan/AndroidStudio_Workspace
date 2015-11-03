package com.liran.main.Stream;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 内存映射文件  可以修改和创建一些超大文件
 * Created by LiRan on 2015-11-03.
 */
public class LargeMappedFiles {

    static int length = 0x8FFFFFF;//128MB

    public static void main(String[] args) throws IOException {

        MappedByteBuffer mappedByteBuffer = new RandomAccessFile("test.dat", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
        for (int i = 0; i < length; i++) {
            mappedByteBuffer.put((byte) 'x');
        }

        System.out.println("写入完成");

        for (int i = length / 2; i < length / 2 + 6; i++) {
            System.out.printf(String.valueOf(" " + mappedByteBuffer.get(i) + " "));
        }

    }
}
