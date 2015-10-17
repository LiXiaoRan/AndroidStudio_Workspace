package com.liran.main.Stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 测试单例类的序列化  必须实现readResolve
 * Created by liran on 2015-10-17.
 */
public class SingLetonSerialTest {


    public static void main(String[] args) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("SingLetonSerialTest.txt"));
             ObjectInputStream ois = new ObjectInputStream(new FileInputStream("SingLetonSerialTest.txt"));
        ) {
            oos.writeObject(Orientation.HORIZONTAL);
            Orientation ori = (Orientation) ois.readObject();
            System.out.println("ori==Orientation.HORIZONTAL : ----> " + (ori == Orientation.HORIZONTAL));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}


/**
 * 这种枚举类或者单例类 正确序列化必须实现
 */
class Orientation implements Serializable {


    public static final Orientation HORIZONTAL = new Orientation(1);

    public static final Orientation VERTICAL = new Orientation(2);

    private int value;

    private Orientation(int value) {
        this.value = value;
    }

    private Object readResolve() throws ObjectStreamException {
        if (value == 1) {
            return HORIZONTAL;
        }
        if (value == 2) {
            return VERTICAL;
        }
        return null;
    }
}
