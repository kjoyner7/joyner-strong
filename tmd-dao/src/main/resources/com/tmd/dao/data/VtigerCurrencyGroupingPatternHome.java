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
 * Home object for domain model class VtigerCurrencyGroupingPattern.
 * @see com.tmd.dao.data.VtigerCurrencyGroupingPattern
 * @author Hibernate Tools
 */
public class VtigerCurrencyGroupingPatternHome {

	private static final Log log = LogFactory.getLog(VtigerCurrencyGroupingPatternHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(VtigerCurrencyGroupingPattern transientInstance) {
		log.debug("persisting VtigerCurrencyGroupingPattern instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VtigerCurrencyGroupingPattern instance) {
		log.debug("attaching dirty VtigerCurrencyGroupingPattern instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VtigerCurrencyGroupingPattern instance) {
		log.debug("attaching clean VtigerCurrencyGroupingPattern instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VtigerCurrencyGroupingPattern persistentInstance) {
		log.debug("deleting VtigerCurrencyGroupingPattern instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VtigerCurrencyGroupingPattern merge(VtigerCurrencyGroupingPattern detachedInstance) {
		log.debug("merging VtigerCurrencyGroupingPattern instance");
		try {
			VtigerCurrencyGroupingPattern result = (VtigerCurrencyGroupingPattern) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VtigerCurrencyGroupingPattern findById(java.lang.Integer id) {
		log.debug("getting VtigerCurrencyGroupingPattern instance with id: " + id);
		try {
			VtigerCurrencyGroupingPattern instance = (VtigerCurrencyGroupingPattern) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.VtigerCurrencyGroupingPattern", id);
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

	public List findByExample(VtigerCurrencyGroupingPattern instance) {
		log.debug("finding VtigerCurrencyGroupingPattern instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.tmd.dao.data.VtigerCurrencyGroupingPattern").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
