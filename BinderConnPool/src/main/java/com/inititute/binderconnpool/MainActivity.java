package com.inititute.binderconnpool;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;

/**
 *
 * Binder连接池的思路是这样的：首先创建一些AIDL文件，然后在本地写出实现stub的类，然后其中一个AIDL文件中要提供
 * queryBinder方法，远程服务中返回的binder也就是这个有queryBinder方法的binder，然后建一个线程池类，单例模式，
 * 有queryBinder方法的binder实现类要作为线程池类内部字段，并且实现queryBinder方法，在这个类中要进行与远程
 * 服务链接等等多种工作，与远程服务链接陈恭侯就可以通过XXXasInterface方法获取远程服务返回的Binder实例，这个binder
 * 实例就是拥有queryBinder方法binder，这时候对这个字段进行赋值，接下来姐可以通过queryBinder方法来获取其他的
 * binder实例，从而调用不同的远程方法，相当于把之前服务器的功能都交给了binder连接池类代理完成。
 *
 */
public class MainActivity extends AppCompatActivity {

    private ISecurityCenter mSecurityCenter;
    private ICompute mCompute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {

                doWork();

            }
        }).start();

    }

    private void doWork() {
        BinderPool binderPool=BinderPool.getsInstance(MainActivity.this);
        IBinder securityBinder=binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);
        mSecurityCenter= (ISecurityCenter) SecurityCenterImpl.asInterface(securityBinder);
        String msg="ABCDE";
        System.out.println("content is "+msg);
        try {
            String passwd=mSecurityCenter.encyrpt(msg);
            System.out.println("encyrpt is "+passwd);
            System.out.println("decrypt is "+mSecurityCenter.dencyrpt(passwd));
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        IBinder compute=binderPool.queryBinder(BinderPool.BINDER_CPMPUTE);
        mCompute=ComputeImpl.asInterface(compute);
        try {
            System.out.println("mCompute.add(1,3) is "+mCompute.add(1,3));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

       /*这样写根本就是彻彻底底的错误的，因为这样调用的是本地方法，而不是远程服务的方法
       IBinder compute=new ComputeImpl();
        mCompute=ComputeImpl.asInterface(compute);
        try {
            System.out.println("add is " + mCompute.add(1, 2));
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/
    }
}
