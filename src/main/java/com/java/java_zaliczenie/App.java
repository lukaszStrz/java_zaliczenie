package com.java.java_zaliczenie;

import com.java.java_zaliczenie.daos.*;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main(String[] args)
    {
        DaoBook daobook = new DaoBook();
        DaoShelf daoshelf = new DaoShelf();
//        DaoStand daostand = new DaoStand();
       
        Shelf shelf=daoshelf.getShelfById(2);
        //System.out.println(shelf.getShelfName());
        
        AdventureBook book = new AdventureBook();
        book.setAdventureInformations("informacje jakieś do przygodówki");
        book.setBookAuthor("jakiśtam autor");
        book.setBookDescription("fjdksafhdjkvba,vbuiahfuirealhfn");
        book.setBookIsbn("1234567890123");
        book.setBookPrice((long) 9.99);
        book.setBookTitle("pupa pupa pupa");
        book.setShelf(shelf);
        
        daobook.addBook(book);
    }
}
