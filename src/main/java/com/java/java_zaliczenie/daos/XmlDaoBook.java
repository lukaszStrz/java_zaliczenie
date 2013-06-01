/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.java_zaliczenie.daos;

import com.java.java_zaliczenie.*;
import com.java.java_zaliczenie.daos.interfaces.DaoBook;
import com.java.java_zaliczenie.utils.DeepCopy;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Wojciech
 */
public class XmlDaoBook implements DaoBook {

    Logger logger = Logger.getLogger(XmlDaoBook.class.getName());
    List<Stand> memory;

    public XmlDaoBook(List<Stand> memory) {
        this.memory = memory;
    }

    public void addBook(Book book) {
        Stand stand = null;
        logger.trace("Szukam regału");
        for (Stand s : memory) {
            logger.trace(s.getIdStand() + " == " + book.getShelf().getStand().getIdStand());
            if (s.getIdStand().equals(book.getShelf().getStand().getIdStand())) {
                logger.trace("Znalazłem regał");
                stand = s;
                break;
            }
        }
        logger.trace("Szukam półki");
        if (stand != null) {
            for (Object shelf : stand.getShelfs()) {
                logger.trace(((Shelf) shelf).getIdShelf() + " == " + book.getShelf().getIdShelf());
                if (((Shelf) shelf).getIdShelf().equals(book.getShelf().getIdShelf())) {
                    logger.trace("Znalazłem półkę");
                    ((Shelf) shelf).getBooks().add(book);
                    logger.trace("Dodaje ksiazke");
                }
            }
        } else {
            logger.error("Nie znalezioo regału.... Lipa");
            return;
        }
        XmlDaoFactory.getInstance().serialize();
    }

    public void deleteBook(String isbn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<Book>();
        for (Stand stand : memory) {
            for (Object shelf : stand.getShelfs()) {
                for (Object book : ((Shelf) shelf).getBooks()) {
                    books.add((Book) book);
                }
            }
        }
        return books;
    }

    public Book getBookById(String isbn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateBook(Book book) {
        for (Stand stand : memory) {
            for (Object shelf : stand.getShelfs()) {
                for (Object b : ((Shelf) shelf).getBooks()) {
                    if (((Book) b).getBookIsbn().equals(book.getBookIsbn())) {
                        ((Book) b).setBookAuthor(book.getBookAuthor());
                        ((Book) b).setBookDescription(book.getBookDescription());
                        ((Book) b).setBookPrice(book.getBookPrice());
                        ((Book) b).setBookTitle(book.getBookTitle());
                        if (!book.getShelf().getIdShelf().equals(((Book) b).getShelf().getIdShelf())) {
                            logger.trace("Przenoszę książkę na inną półkę");
                            ((Book) b).getShelf().getBooks().remove(b);
                            ((Book) b).setShelf(book.getShelf());
                            book.getShelf().getBooks().add(b);
                        }
                    }
                }
            }
        }
        XmlDaoFactory.getInstance().serialize();
    }
}
