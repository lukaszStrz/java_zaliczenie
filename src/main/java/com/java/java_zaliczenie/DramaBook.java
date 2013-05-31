package com.java.java_zaliczenie;

public class DramaBook extends Book implements java.io.Serializable
{

    public DramaBook()
    {
    }

    public DramaBook(String bookIsbn, Book book)
    {
        super(bookIsbn, book.getShelf(), book.getBookAuthor(), book.getBookTitle(), book.getBookPrice());
    }

    @Override
    public String toString()
    {
        return super.toString()
                + " (dramat)";
    }
}
