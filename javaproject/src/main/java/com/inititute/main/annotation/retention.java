package com.inititute.main.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * java8中的retention注解
 * Created by liran on 2015-10-11.
 */
public class retention {

    //注解将被保留到运行时
    @Retention(RetentionPolicy.RUNTIME)
    public @interface test1 {
    }

    ;

    //注解将被编译器直接丢弃
    @Retention(RetentionPolicy.SOURCE)
    public @interface test2 {
    }

    ;

    //注解的默认状态弃
    @Retention(value = RetentionPolicy.CLASS)   //通过value赋值或者不通过都可以
    public @interface test3 {
    }

    ;


}
