package com.liran.main.Stream;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * Created by liran on 2015-10-18.
 */
public class CharsetTest {

    public static void main(String[] args) throws CharacterCodingException {
        //创建GBK编码的charset对象
        Charset cn = Charset.forName("GBK");

        //创建解码器
        CharsetEncoder cnEncoder = cn.newEncoder();
        //创建编码器
        CharsetDecoder cnDecoder = cn.newDecoder();

        CharBuffer cb = CharBuffer.allocate(10);
        cb.put('a');
        cb.put('冉');
//        cb.flip();


        //将字符序列转换为字节序列
        ByteBuffer byteBuffer = cnEncoder.encode(cb);
        //输出自己恶心并列的每一个字节
        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.printf(byteBuffer.get(i) + " ");
        }
        System.out.println("");
        //将bytebuffer的数据解码成字符序列
        System.out.println(cnDecoder.decode(byteBuffer).toString());
    }

}
