package com.inititute.main.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这是一个注解    Inherited 意味着被这个注解修饰的类的注解具有继承性
 * Created by liran on 2015-10-11.
 */
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@Inherited
public @interface Inheritable {
}
