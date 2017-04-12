// IBinderPool.aidl
package com.liran.binderconnpool;

// Declare any non-default types here with import statements

interface IBinderPool {

   IBinder queryBinder(int binderCode);

}
