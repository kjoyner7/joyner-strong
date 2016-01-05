package com.tmd.dao.db;

import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ArifRouf
 */
public class DAOHelper {

    /** Class logger. */
    private static final Logger log = LoggerFactory.getLogger(DAOHelper.class);
    
    public static Session openSession(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
	    log.trace("session opened [{}]", session.toString());
        return session;
    }

    public static Session openSession(SessionFactory sessionFactory)
    {
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    log.trace("session opened [{}]", session.toString());
	    return session;
    }
    
    public static void closeSession( Session session ){
        session.getTransaction().commit();
        session.close();
	    log.trace("session closed [{}]", session.toString());
    }

}
