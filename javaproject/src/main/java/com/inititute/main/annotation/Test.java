package com.inititute.main.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这是一个的注解 第一个自己写的注解
 * @interface 是用来定义注解的  nterface是用来定义接口的
 * *
 * Created by liran on 2015-10-04.
 */

@Target(ElementType.METHOD)    //意思是在方法中应用
@Retention(RetentionPolicy.RUNTIME)  //在运行时可以应用
public @interface Test {
    public int id() default 0;  //定义了变量  名字是id  默认值是0

}
