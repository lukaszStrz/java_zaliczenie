package com.java.java_zaliczenie.daos;

import com.java.java_zaliczenie.*;
import java.util.*;
import org.apache.log4j.Logger;
import org.hibernate.*;
import com.java.java_zaliczenie.utils.HibernateUtil;

public class DaoBook
{

    Session session = HibernateUtil.getSessionFactory().openSession();
    Logger logger = Logger.getLogger(DaoBook.class.getName());

    public void addBook(Book book)
    {
        logger.trace("Adding new Book " + book.getBookIsbn());
        Transaction trns = null;
        try
        {
            trns = session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();
        } catch (RuntimeException e)
        {
            if (trns != null)
            {
                trns.rollback();
            }
            logger.error(e);
        } finally
        {
            session.flush();
//            session.close();
        }
        logger.trace("New Book " + book.getBookIsbn() + " added");
    }

    public void deleteBook(String isbn)
    {
        logger.trace("Deleting Book " + isbn);
        Transaction trns = null;
        try
        {
            trns = session.beginTransaction();
            Book book = (Book) session.load(Book.class, isbn);
            session.delete(book);
            session.getTransaction().commit();
        } catch (RuntimeException e)
        {
            if (trns != null)
            {
                trns.rollback();
            }
            logger.error(e);
        } finally
        {
            session.flush();
//            session.close();
        }
        logger.trace("Book " + isbn + " deleted");
    }

    public void updateBook(Book book)
    {
        logger.trace("Updating book " + book.getBookIsbn());
        Transaction trns = null;
        try
        {
            trns = session.beginTransaction();
            session.update(book);
            session.getTransaction().commit();
        } catch (RuntimeException e)
        {
            if (trns != null)
            {
                trns.rollback();
            }
            logger.error(e);
        } finally
        {
            session.flush();
//            session.close();
        }
        logger.trace("Book " + book.getBookIsbn() + " updated");
    }

    public List<Book> getAllBooks()
    {
        logger.trace("Listing Books");
        List<Book> people = new ArrayList<Book>();
        Transaction trns = null;
        try
        {
            trns = session.beginTransaction();
            people = session.createQuery("from Book").list();
        } catch (RuntimeException e)
        {
            logger.error(e);
        } finally
        {
            session.flush();
//            session.close();
        }
        logger.trace("Books listed");
        return people;
    }

    public Book getBookById(String isbn)
    {
        logger.trace("Getting Book " + isbn);
        Book user = null;
        Transaction trns = null;
        try
        {
            trns = session.beginTransaction();
            String queryString = "from Book where Book_isbn = :isbn";
            Query query = session.createQuery(queryString);
            query.setString("isbn", isbn);
            user = (Book) query.uniqueResult();
        } catch (RuntimeException e)
        {
            logger.error(e);
        } finally
        {
            session.flush();
//            session.close();
        }
        logger.trace("Got book " + isbn);
        return user;
    }
    
    public void closeSession()
    {
        session.close();
    }
}