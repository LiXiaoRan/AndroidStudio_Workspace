// IBookManager.aidl
package com.liran.bindertest;

// Declare any non-default types here with import statements
import com.liran.bindertest.Book;
import com.liran/bindertest.IOnNewBookArriveListener;
interface IBookManager {

    List<Book> getBookList();

    void addBook(in Book book);

    void registerListener(IOnNewBookArriveListener listener);

    void unregisterListener(IOnNewBookArriveListener listener);
}
