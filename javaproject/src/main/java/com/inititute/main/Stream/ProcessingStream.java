package com.inititute.main.Stream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 测试处理流
 * Created by liran on 2015-10-16.
 */
public class ProcessingStream {

    public static void main(String[] args) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("ProcessingStreamTest.txt");
             PrintStream printStream = new PrintStream(fileOutputStream);
        ) {

            printStream.println("---------------------- test ProcessingStream ------------------- ");
            printStream.println(new ProcessingStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
