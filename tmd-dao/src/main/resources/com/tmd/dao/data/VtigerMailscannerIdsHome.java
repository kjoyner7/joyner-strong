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
 * Home object for domain model class VtigerMailscannerIds.
 * @see com.tmd.dao.data.VtigerMailscannerIds
 * @author Hibernate Tools
 */
public class VtigerMailscannerIdsHome {

	private static final Log log = LogFactory.getLog(VtigerMailscannerIdsHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(VtigerMailscannerIds transientInstance) {
		log.debug("persisting VtigerMailscannerIds instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VtigerMailscannerIds instance) {
		log.debug("attaching dirty VtigerMailscannerIds instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VtigerMailscannerIds instance) {
		log.debug("attaching clean VtigerMailscannerIds instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VtigerMailscannerIds persistentInstance) {
		log.debug("deleting VtigerMailscannerIds instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VtigerMailscannerIds merge(VtigerMailscannerIds detachedInstance) {
		log.debug("merging VtigerMailscannerIds instance");
		try {
			VtigerMailscannerIds result = (VtigerMailscannerIds) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VtigerMailscannerIds findById(com.tmd.dao.data.VtigerMailscannerIdsId id) {
		log.debug("getting VtigerMailscannerIds instance with id: " + id);
		try {
			VtigerMailscannerIds instance = (VtigerMailscannerIds) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.VtigerMailscannerIds", id);
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

	public List findByExample(VtigerMailscannerIds instance) {
		log.debug("finding VtigerMailscannerIds instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("com.tmd.dao.data.VtigerMailscannerIds")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
