package com.java.java_zaliczenie;

public class Sfbook extends Book implements java.io.Serializable
{

    private String sfMovie;

    public Sfbook()
    {
    }

    public Sfbook(String bookIsbn, Book book)
    {
        super(bookIsbn, book.getShelf(), book.getBookAuthor(), book.getBookTitle(), book.getBookPrice());
    }

    public Sfbook(String bookIsbn, Book book, String sfMovie)
    {
        super(bookIsbn, book.getShelf(), book.getBookAuthor(), book.getBookTitle(), book.getBookPrice());
        this.sfMovie = sfMovie;
    }

    public String getSfMovie()
    {
        return this.sfMovie;
    }

    public void setSfMovie(String sfMovie)
    {
        this.sfMovie = sfMovie;
    }
}
