package com.liran.main.annotation;

/**
 * Created by liran on 2015-10-04.
 */
public class annotationTest1 {
    @Test(id = 100)
    public void execute() {
        System.out.println("execute is run ");
    }

    public static void main(String[] args) {
        annotationTest1 annotationTest1 = new annotationTest1();
        annotationTest1.testExecute(101);
    }


    @Test(id = 100)
    public void testExecute(int id) {
        execute();
        System.out.println("id=" + id);
    }


}
