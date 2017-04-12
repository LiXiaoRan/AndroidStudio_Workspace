package com.inititute.main.reflect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义类加载器
 * Created by LiRan on 2015-11-10.
 */
public class CompileClassLoader extends ClassLoader {

    /**
     * 读取一个文件的内容
     *
     * @param fileName 文件名
     * @return
     */
    private byte[] getBytes(String fileName) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            StringBuilder stringBuilder = new StringBuilder();
            String str = null;
            String s = null;
            while ((s = br.readLine()) != null) {
                stringBuilder.append(s);

            }
            br.close();
            return stringBuilder.toString().getBytes();

        } catch (UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncodingException");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("找不到这个文件啊");
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 编译java文件
     *
     * @param javaFile
     * @return
     */
    private boolean compile(String javaFile) {
        System.out.println("CompileClassLoader 正在编译：" + javaFile + "....");
        try {
            //调用系统的javac命令
            Process p = Runtime.getRuntime().exec("javac " + javaFile);
            //其它线程都等待这个线程完成
            p.waitFor();
            //获取javac线程的推出值  0表示正常
            int ret = p.exitValue();
            //反汇编译是否成功
            return ret == 0;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 重写classloader的findClass方法
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("执行findclass：" + name);
        Class clazz = null;
        //将包中的.替换成/
        String fileStub = name.replace(".", "/");
        String javaFilename = fileStub + ".java";
        String classFilename = fileStub + ".class";

        File javafile = new File(javaFilename);
        File classfile = new File(classFilename);
        //当指定的java文件存在且class文件不存在 或者java源文件的修改时间比class文件修改的时间更晚时，重新编译
        if ((javafile.exists() && !classfile.exists()) || javafile.lastModified() > classfile.lastModified()) {
            //如果编译失败或者class不存在
            if (!compile(javaFilename) || !classfile.exists()) {
                try {
                    System.out.println("编译失败");
                    throw new Exception("编译失败" + javaFilename);
                } catch (Exception e) {
                    System.out.println("编译失败" + javaFilename);
                    e.printStackTrace();
                }
            }
        }
        //如果class文件存在，系统将负责将该文件转换成class对象
        if (classfile.exists()) {
            byte[] raw = getBytes(classFilename);
            clazz = defineClass(name, raw, 0, raw.length);
            System.out.println("classfile存在");

        } else {
            System.out.println("classfile不存在");

        }


        if (clazz == null) {
            throw new ClassNotFoundException(name);
        }


        return clazz;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {


        // 如果运行该程序时没有参数，即没有目标类
        if (args.length < 1) {
            System.out.println("缺少目标类，请按如下格式运行Java源文件：");
            System.out.println("java CompileClassLoader ClassName");
        }
        // 第一个参数是需要运行的类
        String progClass = args[0];
        // 剩下的参数将作为运行目标类时的参数，
        // 将这些参数复制到一个新数组中
        String[] progArgs = new String[args.length - 1];
        System.arraycopy(args, 1, progArgs
                , 0, progArgs.length);
        CompileClassLoader ccl = new CompileClassLoader();
        // 加载需要运行的类
        Class<?> clazz = ccl.loadClass(progClass);
        // 获取需要运行的类的主方法
        Method main = clazz.getMethod("main", (new String[0]).getClass());
        Object[] argsArray = {progArgs};
        main.invoke(null, argsArray);

       /* byte[] bytes = getBytes("E:\\androidstudio_workespace\\MyApplication\\javaproject\\src\\main\\java\\com\\liran\\main\\reflect\\CompileClassLoader.java");
        System.out.printf(new String(bytes, 0, bytes.length));*/
       /* String progclass="com.liran.main.reflect.classloaderTset.java";
        CompileClassLoader compileClassLoader=new CompileClassLoader();
        try {
            Class<?> clazz=compileClassLoader.loadClass(progclass);

            //获取运行的类的主要方法
            Method method=clazz.getMethod("main",(new String[0]).getClass());

            System.out.println(method);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }*/


    }

}
