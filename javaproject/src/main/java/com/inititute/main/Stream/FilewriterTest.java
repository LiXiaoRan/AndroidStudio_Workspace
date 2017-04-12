package com.inititute.main.Stream;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by liran on 2015-10-16.
 */
public class FilewriterTest {
    public static void main(String[] args) {
        try (
                FileWriter fileWriter = new FileWriter("filewriter.txt")) {
            fileWriter.write("test try aaaaa");
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }


}
