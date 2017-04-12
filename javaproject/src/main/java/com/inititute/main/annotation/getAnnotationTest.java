package com.inititute.main.annotation;

import java.lang.annotation.Annotation;

/**
 * 获得一个类中的方法的所有注解 并遍历输出
 * <p/>
 * Created by liran on 2015-10-13.
 */
public class getAnnotationTest {


    public static void main(String[] args) {
        try {
            //注意方法的参数  权限必须是public
            Annotation[] array = Class.forName("com.liran.main.annotation.annotationTest1").
                    getMethod("testExecute", int.class).getAnnotations();

            for (Annotation anno : array) {
                System.out.println(anno);
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
