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
 * Home object for domain model class VtigerSmsnotifiercf.
 * @see com.tmd.dao.data.VtigerSmsnotifiercf
 * @author Hibernate Tools
 */
public class VtigerSmsnotifiercfHome {

	private static final Log log = LogFactory.getLog(VtigerSmsnotifiercfHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(VtigerSmsnotifiercf transientInstance) {
		log.debug("persisting VtigerSmsnotifiercf instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VtigerSmsnotifiercf instance) {
		log.debug("attaching dirty VtigerSmsnotifiercf instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VtigerSmsnotifiercf instance) {
		log.debug("attaching clean VtigerSmsnotifiercf instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VtigerSmsnotifiercf persistentInstance) {
		log.debug("deleting VtigerSmsnotifiercf instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VtigerSmsnotifiercf merge(VtigerSmsnotifiercf detachedInstance) {
		log.debug("merging VtigerSmsnotifiercf instance");
		try {
			VtigerSmsnotifiercf result = (VtigerSmsnotifiercf) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VtigerSmsnotifiercf findById(int id) {
		log.debug("getting VtigerSmsnotifiercf instance with id: " + id);
		try {
			VtigerSmsnotifiercf instance = (VtigerSmsnotifiercf) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.VtigerSmsnotifiercf", id);
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

	public List findByExample(VtigerSmsnotifiercf instance) {
		log.debug("finding VtigerSmsnotifiercf instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("com.tmd.dao.data.VtigerSmsnotifiercf")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
