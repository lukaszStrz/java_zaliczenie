/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.java_zaliczenie.daos;

import com.java.java_zaliczenie.Stand;
import com.java.java_zaliczenie.daos.interfaces.DaoStand;
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
public class DbDaoStand implements DaoStand {

    Logger logger = Logger.getLogger(DbDaoStand.class.getName());

    @Override
    public void addStand(Stand stand) {
        logger.trace("Adding new Stand " + stand.getIdStand());
        Session session = HibernateUtil.getSession();
        try {
            session.getTransaction().begin();
            session.save(stand);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.error(e);
        } finally {
//            session.flush();
//            session.close();
        }
        logger.trace("New Stand " + stand.getIdStand() + " added");
    }

    @Override
    public void deleteStand(int id) {
        logger.trace("Deleting Stand " + id);
        Session session = HibernateUtil.getSession();
        try {
            session.getTransaction().begin();
            Stand stand = (Stand) session.load(Stand.class, new Integer(id));
            session.delete(stand);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.error(e);
        } finally {
//            session.flush();
//            session.close();
        }
        logger.trace("Stand " + id + " deleted");
    }

    @Override
    public void updateStand(Stand stand) {
        logger.trace("Updating stand " + stand.getIdStand());
        Session session = HibernateUtil.getSession();
        try {
            session.getTransaction().begin();
            session.update(stand);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
           session.getTransaction().rollback();
            logger.error(e);
        } finally {
//            session.flush();
//            session.close();
        }
        logger.trace("Stand " + stand.getIdStand() + " updated");
    }

    @Override
    public List<Stand> getAllStands() {
        logger.trace("Listing Stands");
        Session session = HibernateUtil.getSession();
        List<Stand> people = new ArrayList<Stand>();
        try {
            session.getTransaction().begin();
            people = session.createQuery("from Stand").list();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.error(e);
        } finally {
//            session.flush();
//            session.close();
        }
        logger.trace("Stands listed");
        return people;
    }

    @Override
    public Stand getStandById(int id) {
        logger.trace("Getting Stand " + id);
        Session session = HibernateUtil.getSession();
        Stand user = null;
        try {
            session.getTransaction().begin();
            String queryString = "from Stand where idStand = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            user = (Stand) query.uniqueResult();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            logger.error(e);
        } finally {
//            session.flush();
//            session.close();
        }
        logger.trace("Got stand " + id);
        return user;
    }
}