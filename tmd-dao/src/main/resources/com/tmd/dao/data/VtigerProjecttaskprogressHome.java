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
 * Home object for domain model class VtigerProjecttaskprogress.
 * @see com.tmd.dao.data.VtigerProjecttaskprogress
 * @author Hibernate Tools
 */
public class VtigerProjecttaskprogressHome {

	private static final Log log = LogFactory.getLog(VtigerProjecttaskprogressHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(VtigerProjecttaskprogress transientInstance) {
		log.debug("persisting VtigerProjecttaskprogress instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VtigerProjecttaskprogress instance) {
		log.debug("attaching dirty VtigerProjecttaskprogress instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VtigerProjecttaskprogress instance) {
		log.debug("attaching clean VtigerProjecttaskprogress instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VtigerProjecttaskprogress persistentInstance) {
		log.debug("deleting VtigerProjecttaskprogress instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VtigerProjecttaskprogress merge(VtigerProjecttaskprogress detachedInstance) {
		log.debug("merging VtigerProjecttaskprogress instance");
		try {
			VtigerProjecttaskprogress result = (VtigerProjecttaskprogress) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VtigerProjecttaskprogress findById(java.lang.Integer id) {
		log.debug("getting VtigerProjecttaskprogress instance with id: " + id);
		try {
			VtigerProjecttaskprogress instance = (VtigerProjecttaskprogress) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.VtigerProjecttaskprogress", id);
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

	public List findByExample(VtigerProjecttaskprogress instance) {
		log.debug("finding VtigerProjecttaskprogress instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.tmd.dao.data.VtigerProjecttaskprogress").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
