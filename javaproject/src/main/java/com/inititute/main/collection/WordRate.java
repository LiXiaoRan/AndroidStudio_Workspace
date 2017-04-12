package com.inititute.main.collection;

import java.util.TreeMap;

/**
 * 给定一篇英文文章，找出其中使用频率最高的英语单词
 * Created by LiRan on 2015-11-21.
 */
public class WordRate {


    public static void main(String[] args) {

        String text = "hello aa aa aa word";
        System.out.println("这篇文章是："+text);
        Wordfind(text);
    }

    private static void Wordfind(String text) {

        TreeMap<String, Integer> treeData = new TreeMap<>();

        String[] texts = text.split(" ");

        for (int i = 0; i < texts.length; i++) {
//            System.out.println(texts[i]);

            if (treeData.containsKey(texts[i])) {

                treeData.put(texts[i], treeData.get(texts[i]) + 1);
            } else {
                treeData.put(texts[i], 1);
            }
//            System.out.println(texts[i]+"  "+treeData.get(texts[i]));
        }


        System.out.println("出现最多的单词是： "+treeData.firstKey());

    }


}
