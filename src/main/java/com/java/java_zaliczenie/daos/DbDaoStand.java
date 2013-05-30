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
import org.hibernate.Transaction;

/**
 *
 * @author Wojciech
 */
public class DbDaoStand implements DaoStand
{

    Session session = HibernateUtil.getSessionFactory().openSession();
    Logger logger = Logger.getLogger(DaoStand.class.getName());

    @Override
    public void addStand(Stand stand)
    {
        logger.trace("Adding new Stand " + stand.getIdStand());
        Transaction trns = null;
        try
        {
            trns = session.beginTransaction();
            session.save(stand);
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
        logger.trace("New Stand " + stand.getIdStand() + " added");
    }

    @Override
    public void deleteStand(int id)
    {
        logger.trace("Deleting Stand " + id);
        Transaction trns = null;
        try
        {
            trns = session.beginTransaction();
            Stand stand = (Stand) session.load(Stand.class, new Integer(id));
            session.delete(stand);
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
        logger.trace("Stand " + id + " deleted");
    }

    @Override
    public void updateStand(Stand stand)
    {
        logger.trace("Updating stand " + stand.getIdStand());
        Transaction trns = null;
        try
        {
            trns = session.beginTransaction();
            session.update(stand);
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
        logger.trace("Stand " + stand.getIdStand() + " updated");
    }

    @Override
    public List<Stand> getAllStands()
    {
        logger.trace("Listing Stands");
        List<Stand> people = new ArrayList<Stand>();
        Transaction trns = null;
        try
        {
            trns = session.beginTransaction();
            people = session.createQuery("from Stand").list();
        } catch (RuntimeException e)
        {
            logger.error(e);
        } finally
        {
            session.flush();
//            session.close();
        }
        logger.trace("Stands listed");
        return people;
    }

    @Override
    public Stand getStandById(int id)
    {
        logger.trace("Getting Stand " + id);
        Stand user = null;
        Transaction trns = null;
        try
        {
            trns = session.beginTransaction();
            String queryString = "from Stand where idStand = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            user = (Stand) query.uniqueResult();
        } catch (RuntimeException e)
        {
            logger.error(e);
        } finally
        {
            session.flush();
//            session.close();
        }
        logger.trace("Got stand " + id);
        return user;
    }
    
    @Override
    public void closeSession()
    {
        session.close();
    }
}