package com.liran.main.reflect;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 测试通过Class对象来获取详细信息
 * Created by LiRan on 2015-11-12.
 */

//定义可重复注解
@Repeatable(Annos.class)
@interface Anno{}

@Retention(value = RetentionPolicy.RUNTIME)
@interface Annos{
    Anno[] value();
}


//使用可重复的注解
@Anno
@Anno
public class ClassTest {




    public static void main(String[] args) {

    }

}
