package com.tmd.dao.data;
// Generated Jul 9, 2015 2:35:38 PM by Hibernate Tools 4.3.1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class VtigerDefaultactivitytype.
 * @see com.tmd.dao.data.VtigerDefaultactivitytype
 * @author Hibernate Tools
 */
public class VtigerDefaultactivitytypeHome {

	private static final Log log = LogFactory.getLog(VtigerDefaultactivitytypeHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(VtigerDefaultactivitytype transientInstance) {
		log.debug("persisting VtigerDefaultactivitytype instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VtigerDefaultactivitytype instance) {
		log.debug("attaching dirty VtigerDefaultactivitytype instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VtigerDefaultactivitytype instance) {
		log.debug("attaching clean VtigerDefaultactivitytype instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VtigerDefaultactivitytype persistentInstance) {
		log.debug("deleting VtigerDefaultactivitytype instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VtigerDefaultactivitytype merge(VtigerDefaultactivitytype detachedInstance) {
		log.debug("merging VtigerDefaultactivitytype instance");
		try {
			VtigerDefaultactivitytype result = (VtigerDefaultactivitytype) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VtigerDefaultactivitytype findById(java.lang.Integer id) {
		log.debug("getting VtigerDefaultactivitytype instance with id: " + id);
		try {
			VtigerDefaultactivitytype instance = (VtigerDefaultactivitytype) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.VtigerDefaultactivitytype", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(VtigerDefaultactivitytype instance) {
		log.debug("finding VtigerDefaultactivitytype instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.tmd.dao.data.VtigerDefaultactivitytype").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
