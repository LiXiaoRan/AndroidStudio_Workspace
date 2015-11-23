package com.liran.binderconnpool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.concurrent.CountDownLatch;

/**
 * Binder连接池
 * Created by LiRan on 2015-11-23.
 */
public class BinderPool {

    public static final String TAG = "BinderPool";

    public static final int BINDER_NONE = -1;
    public static final int BINDER_CPMPUTE = 0;
    public static final int BINDER_SECURITY_CENTER = 1;


    private Context mcontext;
    private IBinderPool mBinderPool;
    /**
     * volatile 变量不会像锁那样造成线程阻塞，因此也很少造成可伸缩性问题。
     * 在某些情况下，如果读操作远远大于写操作，volatile 变量还可以提供优于锁的性能优势。
     */
    private static volatile BinderPool sInstance;

    private CountDownLatch mConneectBinderPoolCountDownLatch;


    public BinderPool(Context context) {
        mcontext = context.getApplicationContext();
        connBinderService();
    }

    private synchronized void connBinderService() {

        mConneectBinderPoolCountDownLatch = new CountDownLatch(1);
        Intent service = new Intent(mcontext, BinderPoolService.class);
        mcontext.bindService(service, mBinderPoolConnection, Context.BIND_AUTO_CREATE);

        try {
            //这里让线程阻塞 知道mBinderPool赋值并绑定死亡代理才开始运行是为了防止高并发的时候过早的返回mInstance，导致接下来binderPool.queryBinder可能会出现空指针
            mConneectBinderPoolCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static BinderPool getsInstance(Context context) {
        synchronized (BinderPool.class) {
            if (sInstance == null) {
                sInstance = new BinderPool(context);
            }
        }
        return sInstance;

    }

    public IBinder queryBinder(int binderCode) {

        IBinder binder = null;
        if (mBinderPool != null) {
            try {
                //这里调用的是远成服务的方法，这就是为什么会有IBinderPool这个Binder，
                // 只有远程服务查询并new出来的ibinder才运行在远程服务的binder线程池中才
                // 可以和远程服务进行通信
                binder = mBinderPool.queryBinder(binderCode);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return binder;
    }


    private ServiceConnection mBinderPoolConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinderPool = IBinderPool.Stub.asInterface(service);
            try {
                //设置死亡代理
                mBinderPool.asBinder().linkToDeath(mbinderPoolDeathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            mConneectBinderPoolCountDownLatch.countDown();
        }


        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private IBinder.DeathRecipient mbinderPoolDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            //如果服务异常终止则重启服务
            com.orhanobut.logger.Logger.t(TAG).d("binder in died");
            mBinderPool.asBinder().unlinkToDeath(mbinderPoolDeathRecipient, 0);
            mBinderPool = null;
            connBinderService();
        }
    };


    public static class BinderPoolImpl extends IBinderPool.Stub {


        public BinderPoolImpl() {
            super();
        }

        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            IBinder binder = null;
            com.orhanobut.logger.Logger.d("queryBinder");

            switch (binderCode) {
                case BINDER_CPMPUTE:
                    binder = new ComputeImpl();
                    break;
                case BINDER_SECURITY_CENTER:
                    binder = new SecurityCenterImpl();
                    break;

            }


            return binder;
        }
    }

}
