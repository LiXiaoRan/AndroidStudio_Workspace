package com.inititute.contentprovideripc;

/**
 * Created by LiRan on 2015-11-20.
 */
public class Book {
    private int bookId;
    private String bookName;

    public Book() {
    }

    public Book(int bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "id: " + bookId + " 书名：《" + bookName + "》";
    }
}
