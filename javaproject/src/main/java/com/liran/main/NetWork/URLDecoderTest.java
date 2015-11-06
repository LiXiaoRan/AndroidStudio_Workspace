package com.liran.main.NetWork;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 测试URLDecoder , URLEncoder
 * Created by LiRan on 2015-11-06.
 */
public class URLDecoderTest {


    public static void main(String[] args) throws UnsupportedEncodingException {
        //将 application/x-www-form-urlencoded MIME 转换为  String  格式
        String mime = URLDecoder.decode("%E6%B5%8B%E8%AF%95%E4%B8%80%E4%B8%8B%E5%95%8A%7E%7E", "UTF-8");
        System.out.println("String is " + mime);

        //将 String 转换为 application/x-www-form-urlencoded MIME 格式
        String url = URLEncoder.encode("测试一下啊~~", "UTF-8");
        System.out.println("application/x-www-form-urlencoded MIME 格式: " + url);

    }

}
