/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.java_zaliczenie.daos;

import com.java.java_zaliczenie.Shelf;
import com.java.java_zaliczenie.daos.interfaces.DaoShelf;
import com.java.java_zaliczenie.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Wojciech
 */
public class DbDaoShelf implements DaoShelf {

    Logger logger = Logger.getLogger(DbDaoShelf.class.getName());

    @Override
    public void addShelf(Shelf shelf) {
        logger.trace("Adding new Shelf " + shelf.getIdShelf());
        Session session = HibernateUtil.getSession();
        try {
            session.getTransaction().begin();
            session.save(shelf);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.error(e);
        } finally {
//            session.flush();
//            session.close();
        }
        logger.trace("New Shelf " + shelf.getIdShelf() + " added");
    }

    @Override
    public void deleteShelf(int id) {
        logger.trace("Deleting Shelf " + id);
        Session session = HibernateUtil.getSession();
        try {
            session.getTransaction().begin();
            Shelf shelf = (Shelf) session.load(Shelf.class, new Integer(id));
            session.delete(shelf);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.error(e);
        } finally {
//            session.flush();
//            session.close();
        }
        logger.trace("Shelf " + id + " deleted");
    }

    @Override
    public void updateShelf(Shelf shelf) {
        logger.trace("Updating shelf " + shelf.getIdShelf());
        Session session = HibernateUtil.getSession();
        try {
            session.getTransaction().begin();
            session.update(shelf);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.error(e);
        } finally {
//            session.flush();
//            session.close();
        }
        logger.trace("Shelf " + shelf.getIdShelf() + " updated");
    }

    @Override
    public List<Shelf> getAllShelfs() {
        logger.trace("Listing Shelfs");
        Session session = HibernateUtil.getSession();
        List<Shelf> people = new ArrayList<Shelf>();
        try {
            session.getTransaction().begin();
            people = session.createQuery("from Shelf").list();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.error(e);
        } finally {
//            session.flush();
//            session.close();
        }
        logger.trace("Shelfs listed");
        return people;
    }

    @Override
    public Shelf getShelfById(int id) {
        logger.trace("Getting Shelf " + id);
        Session session = HibernateUtil.getSession();
        Shelf user = null;
        try {
            session.getTransaction().begin();
            String queryString = "from Shelf where idShelf = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            user = (Shelf) query.uniqueResult();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.error(e);
        } finally {
//            session.flush();
//            session.close();
        }
        logger.trace("Got shelf " + id);
        return user;
    }
}