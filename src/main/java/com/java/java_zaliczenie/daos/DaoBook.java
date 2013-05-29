package com.java.java_zaliczenie.daos;

import com.java.java_zaliczenie.*;
import java.util.*;
import org.apache.log4j.Logger;
import org.hibernate.*;
import com.java.java_zaliczenie.utils.HibernateUtil;

public class DaoBook
{

    Logger logger = Logger.getLogger(DaoBook.class.getName());

    public void addBook(Book book)
    {
        logger.trace("Adding new Book");
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
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
            session.close();
        }
        logger.trace("New Book added");
    }

    public void deleteBook(int bookId)
    {
        logger.trace("Deleting Book");
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try
        {
            trns = session.beginTransaction();
            Book book = (Book) session.load(Book.class, new Integer(bookId));
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
            session.close();
        }
        logger.trace("Book deleted");
    }

    public void updateBook(Book book)
    {
        logger.trace("Updating book");
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
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
            session.close();
        }
        logger.trace("Book updated");
    }

    public List<Book> getAllBooks()
    {
        logger.trace("Listing Books");
        List<Book> people = new ArrayList<Book>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
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
            session.close();
        }
        logger.trace("Books listed");
        return people;
    }

    public Book getBookById(int bookId)
    {
        logger.trace("Getting Book");
        Book user = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try
        {
            trns = session.beginTransaction();
            String queryString = "from Book where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", bookId);
            user = (Book) query.uniqueResult();
        } catch (RuntimeException e)
        {
            logger.error(e);
        } finally
        {
            session.flush();
            session.close();
        }
        logger.trace("Got book");
        return user;
    }
}