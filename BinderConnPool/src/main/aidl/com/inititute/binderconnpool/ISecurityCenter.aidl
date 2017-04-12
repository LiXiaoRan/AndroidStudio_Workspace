// ISecurityCenter.aidl
package com.liran.binderconnpool;

// Declare any non-default types here with import statements

interface ISecurityCenter {

   String encyrpt(String content);
   String dencyrpt(String password);
}
