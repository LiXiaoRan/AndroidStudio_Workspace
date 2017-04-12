package com.inititute.main.Stream;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试通过Files和Paths操作文件
 * Created by liran on 2015-10-19.
 */
public class NewIO2Test {

    public static void main(String[] args) throws Exception {

        System.out.println("啊啊啊啊啊");
        //复制文件
        Files.copy(Paths.get("E:\\androidstudio_workespace\\MyApplication\\javaproject\\src\\main\\java\\com\\liran\\main\\Stream\\NewIO2Test.java"), new FileOutputStream("NewIO2Test.txt"));

        //输出文件中的全部内容
        List<String> lines = Files.readAllLines(Paths.get("E:\\androidstudio_workespace\\MyApplication\\javaproject\\src\\main\\java\\com\\liran\\main\\Stream\\NewIO2Test.java"));
        System.out.println(lines);


        //向文件中写入内容

        List<String> poem = new ArrayList<>();
        poem.add("哈哈哈");
        poem.add("嘿嘿嘿");
        Files.write(Paths.get("a.txt"), poem);

    }


}
