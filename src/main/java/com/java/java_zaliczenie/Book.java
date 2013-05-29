package com.java.java_zaliczenie;

import java.util.HashSet;
import java.util.Set;


public class Book implements java.io.Serializable
{

    private String bookIsbn;
    private Shelf shelf;
    private String bookAuthor;
    private String bookTitle;
    private String bookDescription;
    private long bookPrice;

    public Book()
    {
    }

    public Book(String bookIsbn, Shelf shelf, String bookAuthor, String bookTitle, long bookPrice)
    {
        this.bookIsbn = bookIsbn;
        this.shelf = shelf;
        this.bookAuthor = bookAuthor;
        this.bookTitle = bookTitle;
        this.bookPrice = bookPrice;
    }

    public String getBookIsbn()
    {
        return this.bookIsbn;
    }

    public void setBookIsbn(String bookIsbn)
    {
        this.bookIsbn = bookIsbn;
    }

    public Shelf getShelf()
    {
        return this.shelf;
    }

    public void setShelf(Shelf shelf)
    {
        this.shelf = shelf;
    }

    public String getBookAuthor()
    {
        return this.bookAuthor;
    }

    public void setBookAuthor(String bookAuthor)
    {
        this.bookAuthor = bookAuthor;
    }

    public String getBookTitle()
    {
        return this.bookTitle;
    }

    public void setBookTitle(String bookTitle)
    {
        this.bookTitle = bookTitle;
    }

    public String getBookDescription()
    {
        return this.bookDescription;
    }

    public void setBookDescription(String bookDescription)
    {
        this.bookDescription = bookDescription;
    }

    public long getBookPrice()
    {
        return this.bookPrice;
    }

    public void setBookPrice(long bookPrice)
    {
        this.bookPrice = bookPrice;
    }
    
    @Override
    public String toString()
    {
        return bookAuthor+" "+bookTitle+" "+bookIsbn;
    }
}
