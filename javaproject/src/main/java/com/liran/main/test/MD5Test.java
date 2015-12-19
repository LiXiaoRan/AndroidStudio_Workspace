package com.liran.main.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5
 * Created by LiRan on 2015-12-13.
 */
public class MD5Test {


    public static void main(String[] args) throws NoSuchAlgorithmException {


        String url="123";
        MessageDigest messageDigest=MessageDigest.getInstance("MD5");
//        messageDigest.update(url.getBytes());


        byte[] cipherData = messageDigest.digest(url.getBytes());

        StringBuilder builder = new StringBuilder();
        for(byte cipher : cipherData) {
            String toHexStr = Integer.toHexString(cipher & 0xff);
            builder.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr);

            System.out.println(cipher & 0xff);
            System.out.println(toHexStr);
            System.out.println("--------------------------------");


        }
//        System.out.println(builder.toString());

    }





}
