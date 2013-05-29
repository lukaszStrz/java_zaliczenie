package com.java.java_zaliczenie;

import com.java.java_zaliczenie.daos.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Book book = new Book();
        book.setBookAuthor("jakiś autor");
        book.setBookIsbn("1234567890123");
        book.setBookPrice((long) 10.10);
        book.setBookTitle("wuwuwuwuwuwuwuw");
        
        DaoBook daobook=new DaoBook();
        daobook.addBook(book);
    }
}
