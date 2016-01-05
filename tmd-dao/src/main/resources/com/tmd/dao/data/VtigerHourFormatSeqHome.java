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
 * Home object for domain model class VtigerHourFormatSeq.
 * @see com.tmd.dao.data.VtigerHourFormatSeq
 * @author Hibernate Tools
 */
public class VtigerHourFormatSeqHome {

	private static final Log log = LogFactory.getLog(VtigerHourFormatSeqHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(VtigerHourFormatSeq transientInstance) {
		log.debug("persisting VtigerHourFormatSeq instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VtigerHourFormatSeq instance) {
		log.debug("attaching dirty VtigerHourFormatSeq instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VtigerHourFormatSeq instance) {
		log.debug("attaching clean VtigerHourFormatSeq instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VtigerHourFormatSeq persistentInstance) {
		log.debug("deleting VtigerHourFormatSeq instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VtigerHourFormatSeq merge(VtigerHourFormatSeq detachedInstance) {
		log.debug("merging VtigerHourFormatSeq instance");
		try {
			VtigerHourFormatSeq result = (VtigerHourFormatSeq) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VtigerHourFormatSeq findById(int id) {
		log.debug("getting VtigerHourFormatSeq instance with id: " + id);
		try {
			VtigerHourFormatSeq instance = (VtigerHourFormatSeq) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.VtigerHourFormatSeq", id);
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

	public List findByExample(VtigerHourFormatSeq instance) {
		log.debug("finding VtigerHourFormatSeq instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("com.tmd.dao.data.VtigerHourFormatSeq")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
