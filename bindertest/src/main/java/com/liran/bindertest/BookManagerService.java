package com.liran.bindertest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class BookManagerService extends Service {

    private String TAG = "BookManagerService";

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();

//    private CopyOnWriteArrayList<IOnNewBookArriveListener> mlisteners = new CopyOnWriteArrayList<>();

    /***
     * 用这个类来存储listener可以全自动的实现跨进程的删除listener接口的操作和线程同步
     */
    private RemoteCallbackList<IOnNewBookArriveListener> remoteCallbackListenerList = new RemoteCallbackList<>();

    private AtomicBoolean isServiceDestorted = new AtomicBoolean(false);

    public BookManagerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    private Binder mBinder = new IBookManager.Stub() {


        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArriveListener listener) throws RemoteException {
            /*if (!mlisteners.contains(listener)) {
                mlisteners.add(listener);
                Log.d(TAG, "registerListener: "+listener);
            } else {
                Logger.t(TAG).d("已经注册过了");
            }*/
            remoteCallbackListenerList.register(listener);
            int N = remoteCallbackListenerList.beginBroadcast();
            Logger.t(TAG).d("registerListener size is " + N);
            remoteCallbackListenerList.finishBroadcast();
        }

        @Override
        public void unregisterListener(IOnNewBookArriveListener listener) throws RemoteException {
           /* if (mlisteners.contains(listener)) {
                mlisteners.remove(listener);
            } else {
                Logger.t(TAG).d("删除失败 未找到");
            }*/
            remoteCallbackListenerList.unregister(listener);
            int N = remoteCallbackListenerList.beginBroadcast();
            Logger.t(TAG).d("unregisterListener size is " + N);
            remoteCallbackListenerList.finishBroadcast();
        }


    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        isServiceDestorted.set(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "安卓啊"));
        mBookList.add(new Book(2, "ios啊"));
        new Thread(new ServiceWork()).start();

    }

    private void OnNewNotify(Book book) throws RemoteException {
        mBookList.add(book);
        Logger.t(TAG).d("OnNewNotify LIST SIZE IS " + mBookList.size());
        /*for (IOnNewBookArriveListener listener : mlisteners) {
            Log.d(TAG, "OnNewNotify: listener size is " + mlisteners.size() + "  " + mlisteners);
            listener.onNewBookArriver(book);
        }*/
        int N=remoteCallbackListenerList.beginBroadcast();
        for (int i = 0; i < N; i++) {
            IOnNewBookArriveListener listener=remoteCallbackListenerList.getBroadcastItem(i);
            if(listener!=null){
                listener.onNewBookArriver(book);
            }
        }
        remoteCallbackListenerList.finishBroadcast();

    }

    private class ServiceWork implements Runnable {
        @Override
        public void run() {
            while (!isServiceDestorted.get()) {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int bookid = mBookList.size() + 1;
                Book book = new Book(bookid, "新书#" + bookid);
                try {
                    OnNewNotify(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
