package com.java.java_zaliczenie.utils;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.*;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    private static Session session;

    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = configureSessionFactory();
        }
        return sessionFactory;
    }

    public static Session getSession() {
        if (session == null) {
            session = getSessionFactory().openSession();
        }
        return session;
    }

    public static void refreshSession() {
        getSession().close();
        session = getSessionFactory().openSession();
    }
}
