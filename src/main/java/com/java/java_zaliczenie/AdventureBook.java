package com.java.java_zaliczenie;

public class AdventureBook extends Book implements java.io.Serializable
{

    private String adventureInformations;

    public AdventureBook()
    {
    }

    public AdventureBook(String bookIsbn, Book book)
    {
        super(bookIsbn, book.getShelf(), book.getBookAuthor(), book.getBookTitle(), book.getBookPrice());
    }

    public AdventureBook(String bookIsbn, Book book, String adventureInformations)
    {
        super(bookIsbn, book.getShelf(), book.getBookAuthor(), book.getBookTitle(), book.getBookPrice());
        this.adventureInformations = adventureInformations;
    }

    public String getAdventureInformations()
    {
        return this.adventureInformations;
    }

    public void setAdventureInformations(String adventureInformations)
    {
        this.adventureInformations = adventureInformations;
    }

    @Override
    public String toString()
    {
        return super.toString() + " (przygodowa)";
    }
}
