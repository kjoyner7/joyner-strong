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
 * Home object for domain model class VtigerNotificationscheduler.
 * @see com.tmd.dao.data.VtigerNotificationscheduler
 * @author Hibernate Tools
 */
public class VtigerNotificationschedulerHome {

	private static final Log log = LogFactory.getLog(VtigerNotificationschedulerHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(VtigerNotificationscheduler transientInstance) {
		log.debug("persisting VtigerNotificationscheduler instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VtigerNotificationscheduler instance) {
		log.debug("attaching dirty VtigerNotificationscheduler instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VtigerNotificationscheduler instance) {
		log.debug("attaching clean VtigerNotificationscheduler instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VtigerNotificationscheduler persistentInstance) {
		log.debug("deleting VtigerNotificationscheduler instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VtigerNotificationscheduler merge(VtigerNotificationscheduler detachedInstance) {
		log.debug("merging VtigerNotificationscheduler instance");
		try {
			VtigerNotificationscheduler result = (VtigerNotificationscheduler) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VtigerNotificationscheduler findById(java.lang.Integer id) {
		log.debug("getting VtigerNotificationscheduler instance with id: " + id);
		try {
			VtigerNotificationscheduler instance = (VtigerNotificationscheduler) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.VtigerNotificationscheduler", id);
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

	public List findByExample(VtigerNotificationscheduler instance) {
		log.debug("finding VtigerNotificationscheduler instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.tmd.dao.data.VtigerNotificationscheduler").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
