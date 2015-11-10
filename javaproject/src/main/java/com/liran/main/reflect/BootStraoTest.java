package com.liran.main.reflect;

import java.net.URL;


/**
 * Created by LiRan on 2015-11-10.
 */
public class BootStraoTest {

    public static void main(String[] args) {

        //获取根类加载器
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();

        //遍历输出根类加载器的加载的全部URL
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }

    }

}
