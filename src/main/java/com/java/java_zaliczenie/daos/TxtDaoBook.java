/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.java_zaliczenie.daos;

import com.java.java_zaliczenie.Book;
import com.java.java_zaliczenie.Shelf;
import com.java.java_zaliczenie.Stand;
import com.java.java_zaliczenie.daos.interfaces.DaoBook;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Wojciech
 */
public class TxtDaoBook implements DaoBook {
    
    private List<Stand> stands = new ArrayList<Stand>();
    Logger logger = Logger.getLogger(TxtDaoBook.class.getName());
    
    public void addBook(Book book) {
        String tmp = TxtDaoFactory.jsonText;
        stands = new JSONDeserializer<List<Stand>>().deserialize(tmp);
        Stand stand = null;
        for (Stand s : stands) {
            if (s.getIdStand() == book.getShelf().getStand().getIdStand()) {
                stand = s;
                break;
            }
        }
        if (stand != null) {
            for (Object shelf : stand.getShelfs()) {
                if (((Shelf) shelf).getIdShelf() == book.getShelf().getIdShelf()) {
                    ((Shelf) shelf).getBooks().add((book));
                    logger.error("Dodaje ksiazke");
                }
            }
        } else {
            logger.warn("Nie znalezioo regału.... Lipa");
            return;
        }
        TxtDaoFactory.jsonText = new JSONSerializer().deepSerialize(stands);
    }
    
    public void deleteBook(String isbn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Book> getAllBooks() {
        String tmp = TxtDaoFactory.jsonText;
        stands = new JSONDeserializer<List<Stand>>().deserialize(tmp);
        List<Book> books = new ArrayList<Book>();
        for (Stand stand : stands) {
            for (Object shelf : stand.getShelfs()) {
                Shelf tmpShelf = (Shelf) shelf;
                tmpShelf.setStand(stand);
                for (Object book : tmpShelf.getBooks()) {
                    Book tmpBook = (Book) book;
                    tmpBook.setShelf((Shelf) shelf);
                    books.add(tmpBook);
                }
            }
        }
        return books;
    }
    
    public Book getBookById(String isbn) {
        for (Book b : getAllBooks()) {
            if (b.getBookIsbn().equals(isbn)) {
                return b;
            }
        }
        logger.warn("Nie znaleziono książki: " + isbn);
        return null;
    }
    
    public void updateBook(Book book) {
        String tmp = TxtDaoFactory.jsonText;
        stands = new JSONDeserializer<List<Stand>>().deserialize(tmp);
        for (Stand stand : stands) {
            for (Object shelf : stand.getShelfs()) {
                Shelf tmpShelf = (Shelf) shelf;
                for (Object b : tmpShelf.getBooks()) {
                    Book tmpBook = (Book) b;
                    if (tmpBook.getBookIsbn().equals(book.getBookIsbn())) {
                        tmpShelf.getBooks().remove(tmpBook);
                        tmpBook.setBookAuthor(book.getBookAuthor());
                        tmpBook.setBookDescription(book.getBookDescription());
                        tmpBook.setBookPrice(book.getBookPrice());
                        tmpBook.setBookTitle(book.getBookTitle());
                        tmpBook.setShelf(book.getShelf());
                        book.getShelf().getBooks().add(tmpBook);
                    }
                }
            }
        }
        TxtDaoFactory.jsonText = new JSONSerializer().deepSerialize(stands);
    }
}
