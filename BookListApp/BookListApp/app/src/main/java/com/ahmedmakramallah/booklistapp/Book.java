package com.ahmedmakramallah.booklistapp;

/**
 * Created by ahmed on 8/10/2017.
 */

public class Book {
    // the book title
    private String mTitle;
    // the book author
    private String mAuthor;

    // init constructor with inital values for the title and author of the book;
    public Book(String title, String author){
        this.mTitle = title;
        this.mAuthor = author;
    }

    // get the book title
    public String getmTitle() {
        return mTitle;
    }

    // get the book author
    public String getmAuthor() {
        return mAuthor;
    }
}
