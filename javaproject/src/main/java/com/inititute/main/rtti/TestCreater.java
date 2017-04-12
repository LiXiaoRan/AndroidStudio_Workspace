package com.inititute.main.rtti;

/**
 * Created by liran on 2015-10-10.
 */
public class TestCreater {

    public static void main(String[] args) {

        TestCreater testCreater = new TestCreater();
        Class<TestCreater> testCreaterClass = TestCreater.class;


        Class<dog> dogClass = dog.class;
        dog doginstance = null;
        try {
            doginstance = dogClass.newInstance();  //想要执行这一句就必须在dog类中有默认的无参构造函数
        } catch (InstantiationException e) {
            e.printStackTrace();//如果没有默认的构造函数就会引发这个异常
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("testCreaterClass.isInstance(dogClass) : " + testCreaterClass.isInstance(dogClass));
//        System.out.println("doginstance instanceof TestCreater : "+(doginstance != null);

        doginstance.setAge(18);
        doginstance.setName("aaaa");
        System.out.println("dog is " + doginstance);
        if (doginstance instanceof TestCreater) {
            System.out.println("doginstance instanceof TestCreater  yes");
        } else {
            System.out.println("doginstance instanceof TestCreater  no");
        }


//        System.out.println("dogClass==testCreaterClass"+());

        if (testCreaterClass.equals(dogClass)) {
            System.out.println("testCreaterClass.equals(dogClass)");
        } else {
            System.out.println("testCreaterClass.equals(dogClass)---not");
        }


    }
}


class dog extends TestCreater {
    int age;
    String name;


    @Override
    public String toString() {
        return "[name is " + name + " age is " + age + " ]";
    }

    public dog() {
    }

    public dog(int age, String name) {
        this.age = age;
        this.name = name;
    }


    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
