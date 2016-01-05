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
 * Home object for domain model class VtigerMailmanagerMailrecord.
 * @see com.tmd.dao.data.VtigerMailmanagerMailrecord
 * @author Hibernate Tools
 */
public class VtigerMailmanagerMailrecordHome {

	private static final Log log = LogFactory.getLog(VtigerMailmanagerMailrecordHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(VtigerMailmanagerMailrecord transientInstance) {
		log.debug("persisting VtigerMailmanagerMailrecord instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(VtigerMailmanagerMailrecord instance) {
		log.debug("attaching dirty VtigerMailmanagerMailrecord instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VtigerMailmanagerMailrecord instance) {
		log.debug("attaching clean VtigerMailmanagerMailrecord instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(VtigerMailmanagerMailrecord persistentInstance) {
		log.debug("deleting VtigerMailmanagerMailrecord instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VtigerMailmanagerMailrecord merge(VtigerMailmanagerMailrecord detachedInstance) {
		log.debug("merging VtigerMailmanagerMailrecord instance");
		try {
			VtigerMailmanagerMailrecord result = (VtigerMailmanagerMailrecord) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public VtigerMailmanagerMailrecord findById(com.tmd.dao.data.VtigerMailmanagerMailrecordId id) {
		log.debug("getting VtigerMailmanagerMailrecord instance with id: " + id);
		try {
			VtigerMailmanagerMailrecord instance = (VtigerMailmanagerMailrecord) sessionFactory.getCurrentSession()
					.get("com.tmd.dao.data.VtigerMailmanagerMailrecord", id);
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

	public List findByExample(VtigerMailmanagerMailrecord instance) {
		log.debug("finding VtigerMailmanagerMailrecord instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.tmd.dao.data.VtigerMailmanagerMailrecord").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
