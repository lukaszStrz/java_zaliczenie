package com.java.java_zaliczenie.daos.interfaces;

import com.java.java_zaliczenie.*;
import java.util.*;

public interface DaoBook
{
    void addBook(Book book);

    void closeSession();

    void deleteBook(String isbn);

    List<Book> getAllBooks();

    Book getBookById(String isbn);

    void updateBook(Book book);
}