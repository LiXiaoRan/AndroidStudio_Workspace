package com.inititute.binderconnpool;

import android.os.RemoteException;

/**
 * ISecurityCenter的Binder类
 * Created by LiRan on 2015-11-23.
 */
public class SecurityCenterImpl extends ISecurityCenter.Stub {


    @Override
    public String encyrpt(String content) throws RemoteException {
        char[] chars = content.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] += 1;
        }

        return new String(chars);
    }

    @Override
    public String dencyrpt(String password) throws RemoteException {
        char[] chars = password.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] -= 1;
        }


        return new String(chars);

    }
}
