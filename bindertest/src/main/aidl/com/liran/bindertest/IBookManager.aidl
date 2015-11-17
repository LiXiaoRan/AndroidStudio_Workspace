// IBookManager.aidl
package com.liran.bindertest;

// Declare any non-default types here with import statements
import com.liran.bindertest.Book;
interface IBookManager {

    List<Book> getBookList();

    void addBook(in Book book);
}
