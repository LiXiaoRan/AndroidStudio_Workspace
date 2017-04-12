package com.inititute.binderconnpool;

import android.os.RemoteException;

/**
 * ICompute的binder类
 * Created by LiRan on 2015-11-23.
 */
public class ComputeImpl extends ICompute.Stub {


    @Override
    public int add(int a, int b) throws RemoteException {
        return a+b;
    }
}
