/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.java_zaliczenie.daos;

import com.java.java_zaliczenie.Book;
import com.java.java_zaliczenie.daos.interfaces.DaoBook;
import com.java.java_zaliczenie.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Wojciech
 */
public class DbDaoBook implements DaoBook {

    Session session = HibernateUtil.getSessionFactory().openSession();
    Logger logger = Logger.getLogger(DbDaoBook.class.getName());

    @Override
    public void addBook(Book book) {
        logger.trace("Adding new Book " + book.getBookIsbn());
        Transaction trns = null;
        try {
            trns = session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            logger.error(e);
        } finally {
            session.flush();
//            session.close();
        }
        logger.trace("New Book " + book.getBookIsbn() + " added");
    }

    @Override
    public void deleteBook(String isbn) {
        logger.trace("Deleting Book " + isbn);
        Transaction trns = null;
        try {
            trns = session.beginTransaction();
            Book book = (Book) session.load(Book.class, isbn);
            session.delete(book);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            logger.error(e);
        } finally {
            session.flush();
//            session.close();
        }
        logger.trace("Book " + isbn + " deleted");
    }

    @Override
    public void updateBook(Book book) {
        logger.trace("Updating book " + book.getBookIsbn());
        Transaction trns = null;
        try {
            trns = session.beginTransaction();
            session.update(book);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            logger.error(e);
        } finally {
            session.flush();
//            session.close();
        }
        logger.trace("Book " + book.getBookIsbn() + " updated");
    }

    @Override
    public List<Book> getAllBooks() {
        logger.trace("Listing Books");
        List<Book> people = new ArrayList<Book>();
        Transaction trns = null;
        try {
            trns = session.beginTransaction();
            people = session.createQuery("from Book").list();
        } catch (RuntimeException e) {
            logger.error(e);
        } finally {
            session.flush();
//            session.close();
        }
        logger.trace("Books listed");
        return people;
    }

    @Override
    public Book getBookById(String isbn) {
        logger.trace("Getting Book " + isbn);
        Book user = null;
        Transaction trns = null;
        try {
            trns = session.beginTransaction();
            String queryString = "from Book where Book_isbn = :isbn";
            Query query = session.createQuery(queryString);
            query.setString("isbn", isbn);
            user = (Book) query.uniqueResult();
        } catch (RuntimeException e) {
            logger.error(e);
        } finally {
            session.flush();
//            session.close();
        }
        logger.trace("Got book " + isbn);
        return user;
    }
}
