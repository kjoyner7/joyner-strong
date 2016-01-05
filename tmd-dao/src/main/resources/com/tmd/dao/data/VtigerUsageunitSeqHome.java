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
 * Home object for domain model class VtigerUsageunitSeq.
 * @see com.tmd.dao.data.VtigerUsageunitSeq
 * @author Hibernate Tools
 */
public class VtigerUsageunitSeqHome {

	private static final Log log = LogFactory.getLog(VtigerUsageunitSeqHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(VtigerUsageunitSeq transientInstance) {
		log.debug("persisting VtigerUsageunitSeq instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VtigerUsageunitSeq instance) {
		log.debug("attaching dirty VtigerUsageunitSeq instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VtigerUsageunitSeq instance) {
		log.debug("attaching clean VtigerUsageunitSeq instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VtigerUsageunitSeq persistentInstance) {
		log.debug("deleting VtigerUsageunitSeq instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VtigerUsageunitSeq merge(VtigerUsageunitSeq detachedInstance) {
		log.debug("merging VtigerUsageunitSeq instance");
		try {
			VtigerUsageunitSeq result = (VtigerUsageunitSeq) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VtigerUsageunitSeq findById(int id) {
		log.debug("getting VtigerUsageunitSeq instance with id: " + id);
		try {
			VtigerUsageunitSeq instance = (VtigerUsageunitSeq) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.VtigerUsageunitSeq", id);
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

	public List findByExample(VtigerUsageunitSeq instance) {
		log.debug("finding VtigerUsageunitSeq instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("com.tmd.dao.data.VtigerUsageunitSeq")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
