package com.tmd.dao.db;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    /** Class logger. */
    private static final Logger log = LoggerFactory.getLogger(HibernateUtil.class);

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new Configuration().configure("tmd-dao.cfg.xml").buildSessionFactory();            
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            log.error("Exception occured creating SessionFactory", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }
}
