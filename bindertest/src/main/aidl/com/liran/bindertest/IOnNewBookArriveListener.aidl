// IOnNewBookArriveListener.aidl
package com.liran.bindertest;

// Declare any non-default types here with import statements
import com.liran.bindertest.Book;
interface IOnNewBookArriveListener {

   void onNewBookArriver(in Book newBook);
}
