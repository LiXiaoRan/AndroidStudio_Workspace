package com.liran.main.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 测试Testable注解 用到了Testable 和 MyTest
 * Created by liran on 2015-10-13.
 */
public class ProcessorTest {
    public static void process(String clazz) throws ClassNotFoundException {
        int passed = 0;
        int failed = 0;

        //遍历clazz类里对应的所有方法
        for (Method m : Class.forName(clazz).getMethods()) {
            if (m.isAnnotationPresent(Testable.class)) {
                try {
                    m.invoke(null);
                    passed++;
                } catch (IllegalAccessException e) {
                    failed++;
                    System.out.println("method :" + m + " run failed error  Exception: " + e.getCause());
                } catch (InvocationTargetException e) {
                    failed++;
                    System.out.println("method :" + m + " run failed error  Exception: " + e.getCause());
                }
            }
        }

        //统计测试结果
        System.out.println("run method number is " + (passed + failed) + "   success: " + passed + "" +
                "  failed: " + failed);
    }

}
